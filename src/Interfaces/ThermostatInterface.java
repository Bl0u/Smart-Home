package Interfaces;

public interface ThermostatInterface {
    int getTemprature();
    int getTemperatureStatus();
    String getMode();
    void setTemperature(int temperature);
    void setMode(String mode);
    void increaseTemperature(int amount);
    void decreaseTemperature(int amount);
}