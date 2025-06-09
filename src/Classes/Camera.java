package Classes;

import ENUMS.*;
import Interfaces.CameraInterface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Camera extends Device implements CameraInterface {
    private boolean isRecording = false;
    private Resolution resolution = Resolution.SD;
    private boolean motionDetection = false;
    private boolean nightVision = false;

    // ---------------------------------------------------------
    public Camera(Areas area, Hub hub) {
        super(area, hub);
    }

    // ---------------------------------------------------------
    public Resolution getResolution() {
        return resolution;
    }

    // ---------------------------------------------------------
    public void setResolution(Resolution resolution) {
        if (!isOn()) {
            logOperation("Cannot change resolution - camera is off");
            return;
        }

        this.resolution = resolution;
        logOperation("Resolution set to " + resolution.name() + " (" + resolution.getDisplayName() + "p)");
    }

    public void setResolution(String resolutionStr) {
        if (!isOn()) {
            logOperation("Cannot change resolution - camera is off");
            return;
        }

        try {
            Resolution newResolution = Resolution.valueOf(resolutionStr.toUpperCase());
            setResolution(newResolution);
        } catch (IllegalArgumentException e) {
            logOperation("Invalid resolution. Valid options are: " + getValidResolutionOptions());
        }
    }

    private String getValidResolutionOptions() {
        StringBuilder options = new StringBuilder();
        for (Resolution res : Resolution.values()) {
            options.append(res.name()).append(", ");
        }
        if (options.length() > 2) {
            options.setLength(options.length() - 2);
        }
        return options.toString();
    }

    public void setRecording(boolean recording) {
        if (!isOn()) {
            logOperation("Cannot set recording - camera is off");
            return;
        }

        this.isRecording = recording;
        if (recording) {
            logOperation("Recording started at " + resolution.name() + " (" + resolution.getDisplayName() + "p) resolution");
        } else {
            logOperation("Recording stopped");
        }
    }

    public void setMotionDetection(boolean motionDetection) {
        if (!isOn()) {
            logOperation("Cannot change motion detection - camera is off");
            return;
        }

        this.motionDetection = motionDetection;
        logOperation("Motion detection " + (motionDetection ? "enabled" : "disabled"));
    }

    public void setNightVision(boolean nightVision) {
        if (!isOn()) {
            logOperation("Cannot change night vision - camera is off");
            return;
        }

        this.nightVision = nightVision;
        logOperation("Night vision " + (nightVision ? "enabled" : "disabled"));
    }

    // ---------------------------------------------------------
    public boolean isRecording() {
        return isOn() && isRecording;
    }

    public void startRecording() {
        setRecording(true);
    }

    public void stopRecording() {
        setRecording(false);
    }

    public boolean hasMotionDetection() {
        return motionDetection;
    }

    public boolean hasNightVision() {
        return nightVision;
    }

    @Override
    public void turnOn() {
        super.turnOn();
        logOperation("Camera powered on");
    }

    @Override
    public void turnOff() {
        if (isRecording) {
            stopRecording();
        }
        super.turnOff();
        logOperation("Camera powered off");
    }

    @Override
    public void energySavingMode() {
        if (!isOn()) {
            logOperation("Device must be on before activating Energy Saving Mode");
            return;
        }

        // If recording, don't change settings
        if (isRecording) {
            logOperation("Cannot activate Energy Saving Mode while recording");
            return;
        }

        if (resolution == Resolution.ULTRA) {
            setResolution(Resolution.UHD);
        } else if (resolution == Resolution.UHD) {
            setResolution(Resolution.HD);
        } else if (resolution == Resolution.HD) {
            setResolution(Resolution.SD);
        }

        if (nightVision) {
            setNightVision(false);
        }

        logOperation("Energy saving mode activated");
    }

    @Override
    public String toString() {
        return "Camera [ID=" + getId() +
                ", Area=" + getArea() +
                ", Status=" + (isOn() ? "ON" : "OFF") +
                (isOn() ? ", Recording=" + (isRecording ? "YES" : "NO") +
                        ", Resolution=" + resolution.name() + " (" + resolution.getDisplayName() + "p)" +
                        ", Motion Detection=" + (motionDetection ? "ON" : "OFF") +
                        ", Night Vision=" + (nightVision ? "ON" : "OFF") : "") +
                "]";
    }

    public void detectMotion() {
        if (isOn() && motionDetection) {
            logOperation("Motion detected in " + getArea() + "!");
            if (!isRecording) {
                logOperation("Automatically starting recording due to motion detection");
                startRecording();
            }
        }
    }

    public void takePicture() {
        if (isOn()) {
            logOperation("Picture taken at " + resolution.name() + " (" + resolution.getDisplayName() + "p) resolution");
        } else {
            logOperation("Cannot take picture - camera is off");
        }
    }

    public void pan(String direction) {
        if (isOn()) {
            logOperation("Camera panning " + direction);
        } else {
            logOperation("Cannot pan camera - device is off");
        }
    }
}