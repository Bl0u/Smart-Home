package Interfaces.HubInterfaces;
import Classes.Device ;
public interface HubInterface {
    void centralHubEnergySavingMode () ;
    void displayAllDevices(String type) ;
    void displayAllDevices(Device device, String status) ;
    void addDevice(Device device);
    void removeDevice(int id) ;
    void turnOnAll(Device device) ;
    void turnOffAll(Device device) ;
    void ChangeDeviceStatusByArea(String Device, String area, String Status) ;
    void logOperation(String operation) ;

}
