package pl.pollub.utils;

public class GlobalMembers {
    public static float clamp(float value, float min, float max) {
        return (value < min) ? min : ((value > max) ? max : value);
    }


    public static float extractFractional(float value) {
        return value - (float) Math.floor(value);
    }
}
