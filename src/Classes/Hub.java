package Classes;

import java.util.ArrayList;
import Classes.* ;
import Interfaces.LightHub;

public class Hub implements LightHub {
    protected ArrayList<Light> lightList = new ArrayList<>();
    public void addDevice(Device device){
        if (device instanceof Light){
            System.out.println("Light device has been added successfuly");
            lightList.add((Light) device) ;
        }
    }

    public void allDevices(Device device){
        if (device instanceof Light){
            lightList.forEach(element -> {
                System.out.println(element.toString());
            });
        }
    }
    public void allDevices(Device device, String status) {
        if (device instanceof Light) {
            boolean desiredStatus = "on".equals(status);
            lightList.forEach(element -> {
                if (element.isWorking == desiredStatus) {
                    System.out.println(element.toString());
                }
            });
        }
    }

    public void closeAllLights(){
            lightList.forEach(element -> {
                if (element.getIsWorking())
                    element.toggleWorkStats(element) ;
            });
    }
    public void openAllLights(){
            lightList.forEach(element -> {
                if (!element.getIsWorking())
                    element.toggleWorkStats(element) ;
            });
    }




}
