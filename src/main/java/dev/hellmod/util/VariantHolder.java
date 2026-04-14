package dev.hellmod.util;

public interface VariantHolder {
    void hellmod$setVariant(String variant);
    String hellmod$getVariant();

    void hellmod$setExplosionPower(float power);
    float hellmod$getExplosionPower();

    void hellmod$setFuseTime(int fuse);
    int hellmod$getFuseTime();
}