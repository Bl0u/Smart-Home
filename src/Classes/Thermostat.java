package Classes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import ENUMS.Areas;
import Classes.* ;
import Interfaces.ThermostatInterface;

public class Thermostat extends Device implements ThermostatInterface {
    private int temperature = 72;
    private int minTemperature = 60;
    private int maxTemperature = 90;
    private String mode = "auto";
    private static final int DEFAULT_ENERGY_SAVING_TEMP = 78;


    //--------------------------------------------------------------------------
    public Thermostat(Areas area, int temperature, Hub hub) {
        super(area, hub);
        setTemperature(temperature);
        setArea(area) ;
    }
    //--------------------------------------------------------------------------
    public int getTemprature(){return this.temperature ;}
    public int getTemperatureStatus() {
        if (!isOn()) {
            System.out.println("Thermostat is off, not regulating temperature");
        } else {
            System.out.println("Current temperature: " + temperature + "°F, Mode: " + mode);
        }
        return this.isOn() ? this.temperature : 0;

    }
    public String getMode() {
        return mode;
    }

    //--------------------------------------------------------------------------
    public void setTemperature(int temperature) {
        if (!isOn()) {
            logOperation("Cannot set temperature - device is off");
            return;
        }

        if (temperature < minTemperature) {
            this.temperature = minTemperature;
            logOperation("Temperature set to minimum: " + minTemperature + "°F");
        } else if (temperature > maxTemperature) {
            this.temperature = maxTemperature;
            logOperation("Temperature set to maximum: " + maxTemperature + "°F");
        } else {
            this.temperature = temperature;
            logOperation("Temperature set to: " + temperature + "°F");
        }
    }

    public void setMode(String mode) {
        if (!isOn()) {
            logOperation("Cannot set mode - device is off");
            return;
        }

        if (mode.equalsIgnoreCase("heat") ||
                mode.equalsIgnoreCase("cool") ||
                mode.equalsIgnoreCase("auto")) {
            this.mode = mode.toLowerCase();
            logOperation("Mode set to: " + this.mode);
        } else {
            logOperation("Invalid mode. Use 'heat', 'cool', or 'auto'");
        }
    }
    //--------------------------------------------------------------------------
    @Override
    public void turnOn() {
        super.turnOn();
        if (temperature > 0){
        logOperation("Thermostat powered on, regulating temperature at " + temperature + "°F");
        } else {
        logOperation("Thermostat powered on");
        }
    }

    @Override
    public void turnOff() {
        super.turnOff();
        logOperation("Thermostat powered down, not regulating temperature");
    }
    @Override
    public void energySavingMode() {
        if (isOn()) {
            int oldTemp = temperature;
            if ("cool".equals(mode)) {
                setTemperature(DEFAULT_ENERGY_SAVING_TEMP);
                logOperation("Energy saving mode activated - temperature increased from "
                        + oldTemp + "°F to " + temperature + "°F");
            } else if ("heat".equals(mode)) {
                setTemperature(68); // Lower heating setpoint to save energy
                logOperation("Energy saving mode activated - temperature decreased from "
                        + oldTemp + "°F to " + temperature + "°F");
            }
        }
    }

    @Override
    public String toString() {
        return "Thermostat [ID=" + getId()
                + ", Area=" + this.getArea()
                + ", Status="
                + (isOn() ? "ON" : "OFF") +
                (isOn() ? ", Temperature=" + temperature
                        + "°F, Mode=" + mode : "") + "]";
    }

    public void increaseTemperature(int amount) {
        if (isOn() && amount > 0) {
            setTemperature(temperature + amount);
        }
    }

    public void decreaseTemperature(int amount) {
        if (isOn() && amount > 0) {
            setTemperature(temperature - amount);
        }
    }

}