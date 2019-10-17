package pl.pollub.model;

public class GlobalMembers {
  public static final float PIXEL_DENSITY = 0.83F;
  public static final float FRAME_RATE = 60.0F;
  public static final float V_MAX = 3.1415927F;
  public static final float V_MIN = 0.17453292F;
  public static final float CD_MAX = 8747.155F;
  public static final float CD_MIN = 58.280807F;
  public static final float INFLECTION_RATIO_MIN = 0.4F;
  public static final float INFLECTION_RATIO_MAX = 0.7F;
  public static final float LAMBDA_MIN = 1.348136F;
  public static final float LAMBDA_MAX = 1.6851699F;

  //TODO sprawdzić wartości
  public static float getGain(float deviceSpeed, float sensitivity, float acceleration) {
    float inflectionVelocity = sensitivity * 2.9670599F + V_MIN;

    float CDGain = CD_MIN + 8688.874F / (float)(1.0D + Math.exp((-acceleration * (deviceSpeed - inflectionVelocity))));
    return CDGain * PIXEL_DENSITY;
  }


  public static float deg2rad(float deg) { return (float)(deg * Math.PI / 180.0D); }
}