package Classes;
import java.util.ArrayList;

import Interfaces.DeviceInterface;
import Classes.* ;
public abstract class Device  {
    protected boolean isWorking = false; // Better field name
    public static Hub centralHub = new Hub() ;
    protected static int nextId = 1; // shared
    protected int id; // instance's id

    public Device() {
        this.id = nextId++;
    }
    protected boolean getIsWorking(){return this.isWorking ;}

    protected int getId() {
        return id;
    }
    protected void energySavingMode(){}
    // Method to toggle and display device state
    protected boolean toggleWorkStats(Device deviceType) {
        deviceType.isWorking = !deviceType.isWorking;
        String deviceTypeName = deviceType.getClass().getSimpleName(); // return the class name in runtime
        System.out.println(deviceTypeName + " is currently " + (deviceType.isWorking ? "on" : "off"));
        return isWorking;
    }
}
