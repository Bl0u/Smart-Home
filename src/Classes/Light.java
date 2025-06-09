package Classes;
import Classes.* ;
import ENUMS.Areas;
import Interfaces.LightInterface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Light extends Device implements LightInterface {
    private int brightness = 0;
    private static final int MAX_BRIGHTNESS = 100;
    private static final int MIN_BRIGHTNESS = 0;

    // ---------------------------------------------------------
    public Light(Areas area, int brightnessValue, Hub hub) {
        super(area, hub);
        setBrightness(brightnessValue);
    }
    // ---------------------------------------------------------
    public void setBrightness(int brightnessValue) {
        if (!isOn()) {
            System.out.println("Cannot set brightness - device is off");
            return;
        }

        if (brightnessValue >= MIN_BRIGHTNESS && brightnessValue <= MAX_BRIGHTNESS) {
            this.brightness = brightnessValue;
            logOperation("Brightness set to " + brightnessValue + "%");
        } else {
            System.out.println("Type a value between " + MIN_BRIGHTNESS + " and " + MAX_BRIGHTNESS);
        }
    }

    // ---------------------------------------------------------
    public int getBrightness() {
        if (!this.isOn()) {
            System.out.print("Light is not working, Brightness is ");
        } else {
            System.out.print("Light is working, Brightness is ");
        }
        return this.isOn() ? this.brightness : 0;
    }
    // ---------------------------------------------------------

    @Override
    public void turnOn() {
        super.turnOn();
        logOperation("Light turned on with brightness " + brightness + "%");
    }

    @Override
    public void turnOff() {
        super.turnOff();
        logOperation("Light turned off");
    }

    @Override
    public String toString() {
        return "Light [Area=" + getArea() +
                ", ID=" + getId() +
                ", Status=" + (isOn() ? "ON" : "OFF") +
                (isOn() ? ", Brightness=" + brightness + "%" : "") +
                "]";
    }

    @Override
    public void energySavingMode() {
        if (!this.isOn()){
        logOperation("Device must be On before activating Energy Saving Mode");
        }
        int newBrightness = this.brightness / 2;
        setBrightness(newBrightness);
    }


}
