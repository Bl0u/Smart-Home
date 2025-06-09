package Interfaces;

import ENUMS.Resolution;

public interface CameraInterface {
    Resolution getResolution();
    void setResolution(Resolution resolution);
    void setResolution(String resolutionStr);
    boolean isRecording();
    void setRecording(boolean recording);
    void startRecording();
    void stopRecording();
    boolean hasMotionDetection();
    void setMotionDetection(boolean motionDetection);
    boolean hasNightVision();
    void setNightVision(boolean nightVision);
    void detectMotion();
    void takePicture();
    void pan(String direction);
}