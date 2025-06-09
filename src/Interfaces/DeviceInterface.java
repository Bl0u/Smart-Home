package Interfaces;

import Classes.Device;
import ENUMS.* ;
public interface DeviceInterface {

    void energySavingMode() ;
    int getId();
    void turnOn();
    void turnOff();
    boolean isOn();
    String getStatus();
    String getArea() ;
    void setArea(Areas area) ;














//    boolean toggleStats(Device deviceType) ;
}
