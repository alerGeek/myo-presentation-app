package pl.pollub.model.factory.collectors.mousemover;

public class GlobalMembers {
    public static final float PIXEL_DENSITY = 0.83F;
    public static final float FRAME_RATE = 60.0F;
    public static final float V_MAX = (float) Math.PI;
    public static final float V_MIN = 0.17453292F;
    public static final float CD_MAX = 4580.0F / ((float) Math.PI / 6.0f);
    public static final float CD_MIN = 16.0f / 0.274532925F;
    public static final float INFLECTION_RATIO_MIN = 0.4F;
    public static final float INFLECTION_RATIO_MAX = 0.7F;
    public static final float LAMBDA_MIN = 4.0f / (V_MAX - V_MIN);
    public static final float LAMBDA_MAX = 5.0f / (V_MAX - V_MIN);

    public static float getGain(float deviceSpeed, float sensitivity, float acceleration) {
        float inflectionVelocity = sensitivity * (V_MAX - V_MIN) + V_MIN;

        float CDGain = CD_MIN + (CD_MAX + CD_MIN) / (float) (1.0D + Math.exp((-acceleration * (deviceSpeed - inflectionVelocity))));
        return CDGain * PIXEL_DENSITY;
    }

    public static float deg2rad(float deg) {
        return (float) (deg * Math.PI / 180.0F);
    }
    public static float clamp(float value, float min, float max) {
        return (value < min) ? min : ((value > max) ? max : value);
    }

    public static float extractFractional(float value) {
        return value - (float) Math.floor(value);
    }
}