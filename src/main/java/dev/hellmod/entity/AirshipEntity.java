package dev.hellmod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AirshipEntity extends Entity {

    private boolean ascending;
    private boolean braking;

    private double clientX;
    private double clientY;
    private double clientZ;

    private double clientYaw;

    private float yawVelocity;
    private float clientYawVelocity;

    private int lerpTicks;

    private float pitchVelocity;
    private float targetPitchVelocity;

    private float clientVisualYaw;

    private static final TrackedData<Float> VISUAL_YAW =
            DataTracker.registerData(
                    AirshipEntity.class,
                    TrackedDataHandlerRegistry.FLOAT
            );

    private static final TrackedData<Float> PITCH =
            DataTracker.registerData(
                    AirshipEntity.class,
                    TrackedDataHandlerRegistry.FLOAT
            );

    private static final TrackedData<Float> YAW_VELOCITY =
            DataTracker.registerData(
                    AirshipEntity.class,
                    TrackedDataHandlerRegistry.FLOAT
            );


    public AirshipEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }
    public void setBraking(boolean braking) { this.braking = braking; }

    public PlayerEntity getPilot() {

        if (getFirstPassenger() instanceof PlayerEntity player) {
            return player;
        }

        return null;
    }

    @Override
    public void tick() {
        super.tick();

        if (getWorld().isClient) {

            clientVisualYaw += MathHelper.wrapDegrees(
                    getTrackedVisualYaw() - clientVisualYaw
            ) * 0.25f;

            if (this.lerpTicks > 0) {

                setPosition(
                        getX() + (clientX - getX()) / lerpTicks,
                        getY() + (clientY - getY()) / lerpTicks,
                        getZ() + (clientZ - getZ()) / lerpTicks
                );

                float oldYaw = getYaw();

                float deltaYaw = MathHelper.wrapDegrees(
                        (float)(clientYaw - getYaw())
                );

                setYaw(
                        getYaw() + deltaYaw / lerpTicks
                );

                clientYawVelocity = MathHelper.wrapDegrees(
                        getYaw() - oldYaw
                );

                if (Math.abs(clientYawVelocity) < 0.02f) {
                    clientYawVelocity = 0;
                }


                this.lerpTicks--;
            }

            return;
        }

        PlayerEntity pilot = getPilot();

        if (pilot != null) {
            controlShip(pilot);
        }

        // Rozamiento
        if (isOnGround()) {

            setVelocity(
                    getVelocity().multiply(0.85)
            );

        } else if (braking) {

            setVelocity(
                    getVelocity().multiply(0.97)
            );
        } else {
            setVelocity(
                    getVelocity().multiply(0.995)
            );
        }

        // Velocidad máxima
        double maxSpeed = 2;

        if (getVelocity().horizontalLength() > maxSpeed) {

            Vec3d horizontalVelocity = new Vec3d(
                    getVelocity().x,
                    0,
                    getVelocity().z
            );

            horizontalVelocity = horizontalVelocity
                    .normalize()
                    .multiply(maxSpeed);

            setVelocity(
                    horizontalVelocity.x,
                    getVelocity().y,
                    horizontalVelocity.z
            );
        }

        for (Entity passenger : getPassengerList()) {
            passenger.fallDistance = 0;
        }

        Vec3d forward = Vec3d.fromPolar(
                getPitch(),
                getYaw()
        );

        if (ascending) {

            float thrust = 0.06f - (float)(forward.y * 0.02f);

            thrust = MathHelper.clamp(
                    thrust,
                    0.01f,
                    0.06f
            );

            addVelocity(
                    forward.x * thrust,
                    forward.y * 0.12,
                    forward.z * thrust
            );
        }

        addVelocity(
                0,
                -0.01,
                0
        );

        float visualYaw = getVisualYaw(getYaw());

        dataTracker.set(
                VISUAL_YAW,
                visualYaw
        );

        dataTracker.set(
                PITCH,
                getPitch()
        );

        dataTracker.set(
                YAW_VELOCITY,
                yawVelocity
        );

        move(MovementType.SELF, getVelocity());
    }

    private void controlShip(PlayerEntity pilot) {

        float turnAcceleration = 0.4f;
        float maxTurnSpeed = 1.5f;

        boolean turning = false;

            if (pilot.sidewaysSpeed > 0.1f) {

                yawVelocity -= turnAcceleration;
                turning = true;

            }
            else if (pilot.sidewaysSpeed < -0.1f) {

                yawVelocity += turnAcceleration;
                turning = true;
            }

        if (!turning) {

            float damping =
                    ascending
                            ? 0.90f
                            : 0.85f;

            yawVelocity *= damping;

            if (Math.abs(yawVelocity) < 0.07f) {
                yawVelocity = 0;
            }
        }

        yawVelocity = MathHelper.clamp(
                yawVelocity,
                -maxTurnSpeed,
                maxTurnSpeed
        );
        setYaw(
                getYaw() + yawVelocity
        );

        if (pilot.forwardSpeed > 0.1f) {

            targetPitchVelocity = 1.5f;

        } else if (pilot.forwardSpeed < -0.1f) {

            targetPitchVelocity = -1.5f;

        } else {

            if (Math.abs(getPitch()) < 0.01f) {
                setPitch(0);
            }

            targetPitchVelocity = 0;

            setPitch(
                    MathHelper.lerp(
                            0.1f,
                            getPitch(),
                            0
                    )
            );
        }

        pitchVelocity +=
                (targetPitchVelocity - pitchVelocity)
                        * 0.25f;

        setPitch(
                MathHelper.clamp(
                        getPitch() + pitchVelocity,
                        -20f,
                        20f
                )
        );
    }

    @Override
    public void updateTrackedPositionAndAngles(
            double x,
            double y,
            double z,
            float yaw,
            float pitch,
            int interpolationSteps
    ) {
        this.clientX = x;
        this.clientY = y;
        this.clientZ = z;

        this.clientYaw = yaw;

        this.lerpTicks = interpolationSteps;
    }


    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        builder.add(VISUAL_YAW, 0f);
        builder.add(PITCH, 0f);
        builder.add(YAW_VELOCITY, 0f);
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
    }

    @Override
    public boolean canHit() {
        return true;
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public boolean hasNoGravity() {
        return false;
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {

        if (!getWorld().isClient) {

            player.startRiding(this);
        }

        return ActionResult.SUCCESS;
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return getPassengerList().size() < 2;
    }

    @Override
    protected void updatePassengerPosition(
            Entity passenger,
            PositionUpdater positionUpdater
    ) {

        int index = getPassengerList().indexOf(passenger);


        Vec3d seatOffset = switch (index) {

            // DR
            case 0 -> new Vec3d(
                    0.0,
                    0.25,
                    1
            );

            // P L
            case 1 -> new Vec3d(
                    -0.6,
                    0.25,
                    -0.3
            );

            // P R
            case 2 -> new Vec3d(
                    0.6,
                    0.25,
                    -0.3
            );

            default -> Vec3d.ZERO;
        };

        float visualYaw = getTrackedVisualYaw();

        float pitchOffset = getTrackedPitch() * 0.02f;

        Vec3d offset = seatOffset
                .rotateY((float)Math.toRadians(-visualYaw));

        positionUpdater.accept(
                passenger,
                getX() + offset.x,
                getY() + offset.y - pitchOffset,
                getZ() + offset.z
        );

        if (passenger instanceof PlayerEntity player) {

            float targetYaw = getVisualYaw(getYaw());

            player.setYaw(targetYaw);
            player.setHeadYaw(targetYaw);
            player.setBodyYaw(targetYaw);
        }



    }

    @Override
    public float getStepHeight() {
        return 1.0f;
    }

    public float getVisualYaw(float baseYaw) {

        Vec3d velocity = getVelocity();

        if (
                velocity.horizontalLengthSquared() > 0.001
                        && !horizontalCollision
        ) {

            float velocityYaw = (float) Math.toDegrees(
                    Math.atan2(
                            -velocity.x,
                            velocity.z
                    )
            );

            float driftFactor = 0.7f;

            float delta = MathHelper.wrapDegrees(
                    velocityYaw - baseYaw
            );

            return baseYaw + delta * driftFactor;
        }

        return baseYaw;
    }

    public float getTrackedVisualYaw() {
        return dataTracker.get(VISUAL_YAW);
    }

    public float getTrackedPitch() {
        return dataTracker.get(PITCH);
    }

    public float getTrackedYawVelocity() {
        return dataTracker.get(YAW_VELOCITY);
    }


}