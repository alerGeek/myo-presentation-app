package pl.pollub.model.factory.collectors.mousemover;

import com.thalmic.myo.Quaternion;
import com.thalmic.myo.Vector3;
import com.thalmic.myo.enums.XDirection;

import static pl.pollub.model.factory.collectors.mousemover.GlobalMembers.*;


public class MouseMover {
    private static final float DEFAULT_ACCELERATION = 0.3F;
    private static final float DEFAULT_SENSITIVITY = 0.5F;
    private float _dx = 0.0F;
    private float _dy = 0.0F;
    private float _dxFractional = 0.0F;
    private float _dyFractional = 0.0F;
    private Quaternion _quat = new Quaternion(0.0D, 0.0D, 0.0D, 0.0D);
    private XDirection _XTowardsWrist = XDirection.X_DIRECTION_UNKNOWN;
    private LinearParameter _acceleration = new LinearParameter(LAMBDA_MIN, LAMBDA_MAX, DEFAULT_ACCELERATION);
    private LinearParameter _sensitivity = new LinearParameter(INFLECTION_RATIO_MAX, INFLECTION_RATIO_MIN, DEFAULT_SENSITIVITY);


    public void onArm(XDirection xDirection) {
        this._XTowardsWrist = xDirection;
    }


    public void onOrientationData(Quaternion rotation) {
        this._quat = rotation;
    }

    public void onGyroscope(Vector3 gyro) {
        Vector3 gyroRad = new Vector3(GlobalMembers.deg2rad((float) gyro.getX()), GlobalMembers.deg2rad((float) gyro.getY()), GlobalMembers.deg2rad((float) gyro.getZ()));

        Vector3 gyroRadWorld = Vector3.rotate(this._quat, gyroRad);

        Vector3 forwardSource = (this._XTowardsWrist == XDirection.X_DIRECTION_TOWARDS_WRIST) ? new Vector3(1.0, 0.0, 0.0) : new Vector3(-1.0, 0.0, 0.0);


        Vector3 forward = Vector3.rotate(this._quat, forwardSource);

        Vector3 right = forward.cross(new Vector3(0.0, 0.0, -1.0));

        Vector3 up = new Vector3(0.0, 1.0, 0.0);
        Quaternion yCompensationQuat = Quaternion.rotate(right, up).normalized();

        Vector3 gyroVectorCompensated = Vector3.rotate(yCompensationQuat, gyroRadWorld);

        float dx = (float) -gyroRadWorld.getZ();
        float dy = (float) gyroVectorCompensated.getY();

        updateMouseDeltas(dx, dy);
    }

    private void updateMouseDeltas(float dx, float dy) {
        float frameDuration = 1.0f / FRAME_RATE;

        float norm = (float) Math.sqrt((dx * dx + dy * dy));

        float gain = GlobalMembers.getGain(norm, this._sensitivity.output(), this._acceleration.output());

        this._dx = dx * gain * frameDuration;
        this._dy = dy * gain * frameDuration;

        this._dxFractional += GlobalMembers.extractFractional(this._dx);
        this._dyFractional += GlobalMembers.extractFractional(this._dy);

        this._dx = (float) Math.floor(this._dx);
        this._dy = (float) Math.floor(this._dy);

        if (Math.abs(this._dxFractional) > 1.0F) {
            this._dx = (float) (this._dx + Math.floor(this._dxFractional));
            this._dxFractional = GlobalMembers.extractFractional(this._dxFractional);
        }
        if (Math.abs(this._dyFractional) > 1.0F) {
            this._dy = (float) (this._dy + Math.floor(this._dyFractional));
            this._dyFractional = GlobalMembers.extractFractional(this._dyFractional);
        }
    }

    public void moveMouse(int dx, int dy){

    }

    public final float getDx() {
        return this._dx;
    }


    public final float getDy() {
        return this._dy;
    }
}