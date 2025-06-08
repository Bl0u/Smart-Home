package Interfaces;
import Classes.Device ;
public interface CentralHub {
    void centralHubEnergySavingMode () ;
    void displayAllDevices(Device device) ;
    void displayAllDevices(Device device, String status) ;
    void addDevice() ;
}
