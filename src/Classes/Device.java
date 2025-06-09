package Classes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import ENUMS.Areas;
import Interfaces.DeviceInterface;
import Classes.* ;
public abstract class Device implements DeviceInterface  {
    protected boolean isOn = false; // Better field name
    public Hub centralHub ;
    protected static int nextId = 1; // shared
    protected int id; // instance's id
    protected String area = "" ;



    // ---------------------------------------------------------
    public Device(Areas area, Hub hub) {
        this.turnOn() ;
        this.id = nextId++;
        setArea(area);
        setHub(hub);
        this.centralHub.addDevice(this);
    }

    // -------------------GET--------------------------------------
    protected boolean getIsWorking(){return this.isOn ;}

    public int getId() {
        return id;
    }

    public String getStatus() {
        return isOn
                ? " Working fine"
                : "Not working" ;
    }
    public String getArea(){
        return this.area ;
    }
    // -------------------SET--------------------------------------
    void setIsOn(boolean status){
        this.isOn = status ;
    }
    public void setArea(Areas area) {
        this.area = area.getDisplayName();
    }
    private void setHub(Hub hub){
        this.centralHub = hub ;
    }
    // ------------------Actions---------------------------------------
    public void turnOn() {
        setIsOn(true);
    }

    public void turnOff() {
        setIsOn(false);
    }

    public boolean isOn(){return this.isOn ;} ;

    public void energySavingMode(){}

    protected void logOperation(String operation) {
        String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );
        System.out.println("[" + timestamp + "] User: Bl0u - " + operation);
    }
}
