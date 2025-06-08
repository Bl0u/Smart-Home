package Classes;
import Classes.* ;
public class Light extends Device {
    private int brightness = 0;
    @Override
    public String toString() {
        return isWorking
                ? "This light device ID = " + this.getId() + " is ON and brightness is " + brightness
                : "This light device ID = " + this.getId() +" is OFF";
    }

    public Light(int brightnessValue){
        setBrightness(brightnessValue);
        this.centralHub.addDevice(this) ;
    }
    public void setBrightness(int brightnessValue) {
        if (brightnessValue >= 0 && brightnessValue <= 100) {
            this.brightness = brightnessValue;
            if (!this.isWorking)
                this.toggleWorkStats(this);
        } else {
            System.out.println("Type a value between 0 and 100");
        }
    }

    public void openLight() {
        this.setBrightness(this.brightness);

        if (!this.isWorking){
            this.toggleWorkStats(this);
            System.out.println("openLight");
        } else {
            System.out.println("Already open");
        }
    }

    public void closeLight() {
        if (this.isWorking) {
            this.toggleWorkStats(this);
            System.out.println("closeLight");
        } else {
            System.out.println("it's already off");
        }
    }

    public int getBrightness() {
        return this.brightness;
    }

    @Override
    protected void energySavingMode() {
        this.setBrightness(this.brightness/2);
    }

}
