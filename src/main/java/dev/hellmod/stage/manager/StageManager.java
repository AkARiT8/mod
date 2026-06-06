package dev.hellmod.stage.manager;

public class StageManager {

    private static int currentStage = 0;

    public static int getCurrentStage() {
        return currentStage;
    }

    public static void setStage(int stage) {
        currentStage = stage;
    }
}