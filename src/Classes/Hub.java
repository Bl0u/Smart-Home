package Classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import Interfaces.HubInterfaces.HubInterface;

public class Hub implements HubInterface {
    protected ArrayList<Light> lightList = new ArrayList<>();
    protected ArrayList<Thermostat> thermostatList = new ArrayList<>();
    protected ArrayList<Camera> cameraList = new ArrayList<>();

    @Override
    public void addDevice(Device device) {
        if (device instanceof Light) {
            System.out.println("Light device has been added successfully");
            lightList.add((Light) device);
        } else if (device instanceof Thermostat) {
            System.out.println("Thermostat device has been added successfully");
            thermostatList.add((Thermostat) device);
        } else if (device instanceof Camera) {
            System.out.println("Camera device has been added successfully");
            cameraList.add((Camera) device);
        }
    }

    @Override
    public void displayAllDevices(String type) {
        if ("Light".equalsIgnoreCase(type)) {
            lightList.forEach(element -> {
                System.out.println(element.toString());
            });
        } else if ("Thermostat".equalsIgnoreCase(type)) {
            thermostatList.forEach(element -> {
                System.out.println(element.toString());
            });
        } else if ("Camera".equalsIgnoreCase(type)) {
            cameraList.forEach(element -> {
                System.out.println(element.toString());
            });
        } else {
            System.out.println("Empty");

        }
    }

    @Override
    public void displayAllDevices(Device device, String status) {
        boolean desiredStatus = "on".equalsIgnoreCase(status);

        if (device instanceof Light) {
            lightList.forEach(element -> {
                if (element.isOn() == desiredStatus) {
                    System.out.println(element.toString());
                }
            });
        } else if (device instanceof Thermostat) {
            thermostatList.forEach(element -> {
                if (element.isOn() == desiredStatus) {
                    System.out.println(element.toString());
                }
            });
        } else if (device instanceof Camera) {
            cameraList.forEach(element -> {
                if (element.isOn() == desiredStatus) {
                    System.out.println(element.toString());
                }
            });
        }
    }

    @Override
    public void removeDevice(int id) {
        // Remove from lights
        lightList.removeIf(device -> device.getId() == id);

        // Remove from thermostats
        thermostatList.removeIf(device -> device.getId() == id);

        // Remove from cameras
        cameraList.removeIf(device -> device.getId() == id);

        System.out.println("Device with ID " + id + " has been removed");
    }

    @Override
    public void turnOffAll(Device device) {
        if (device instanceof Light) {
            lightList.forEach(element -> {
                if (element.getIsWorking())
                    element.turnOff();
            });
        } else if (device instanceof Thermostat) {
            thermostatList.forEach(element -> {
                if (element.getIsWorking())
                    element.turnOff();
            });
        } else if (device instanceof Camera) {
            cameraList.forEach(element -> {
                if (element.getIsWorking())
                    element.turnOff();
            });
        }
    }



    @Override
    public void turnOnAll(Device device) {
        if (device instanceof Light) {
            lightList.forEach(element -> {
                if (!element.getIsWorking())
                    element.turnOn();
            });
        } else if (device instanceof Thermostat) {
            thermostatList.forEach(element -> {
                if (!element.getIsWorking())
                    element.turnOn();
            });
        } else if (device instanceof Camera) {
            cameraList.forEach(element -> {
                if (!element.getIsWorking())
                    element.turnOn();
            });
        }
    }




    @Override
    public void centralHubEnergySavingMode() {
        // Turn off all lights
        lightList.forEach(light -> {
            if (light.isOn()) {
                light.energySavingMode();
            }
        });

        thermostatList.forEach(thermostat -> {
            if (thermostat.isOn()) {
                thermostat.energySavingMode();
            }
        });
        cameraList.forEach(camera -> {
                camera.energySavingMode();
        });

        System.out.println("Energy saving mode activated");
    }


    @Override
    public void ChangeDeviceStatusByArea(String deviceType, String area, String status) {
        boolean validStatus = status.equalsIgnoreCase("on") || status.equalsIgnoreCase("off");
        if (!validStatus) {
            logOperation("Invalid status: " + status + ". Must be 'on' or 'off'.");
            return;
        }

        boolean turnOn = status.equalsIgnoreCase("on");
        int devicesChanged = 0;

        // Process lights
        if (deviceType.equalsIgnoreCase("light") || deviceType.equalsIgnoreCase("all")) {
            for (Light light : lightList) {
                if (light.getArea().equalsIgnoreCase(area)) {
                    if (turnOn && !light.isOn()) {
                        light.turnOn();
                        devicesChanged++;
                    } else if (!turnOn && light.isOn()) {
                        light.turnOff();
                        devicesChanged++;
                    }
                }
            }
            logOperation(devicesChanged + " lights in " + area + " area have been turned " + status);
        }

        // Process cameras
        if (deviceType.equalsIgnoreCase("camera") || deviceType.equalsIgnoreCase("all")) {
            devicesChanged = 0;
            for (Camera camera : cameraList) {
                if (camera.getArea().equalsIgnoreCase(area)) {
                    if (turnOn && !camera.isOn()) {
                        camera.turnOn();
                        devicesChanged++;
                    } else if (!turnOn && camera.isOn()) {
                        camera.turnOff();
                        devicesChanged++;
                    }
                }
            }
            logOperation(devicesChanged + " cameras in " + area + " area have been turned " + status);
        }

        // Process thermostats
        if (deviceType.equalsIgnoreCase("thermostat") || deviceType.equalsIgnoreCase("all")) {
            devicesChanged = 0;
            for (Thermostat thermostat : thermostatList) {
                if (thermostat.getArea().equalsIgnoreCase(area)) {
                    if (turnOn && !thermostat.isOn()) {
                        thermostat.turnOn();
                        devicesChanged++;
                    } else if (!turnOn && thermostat.isOn()) {
                        thermostat.turnOff();
                        devicesChanged++;
                    }
                }
            }
            logOperation(devicesChanged + " thermostats in " + area + " area have been turned " + status);
        }

        // Log summary
        if (deviceType.equalsIgnoreCase("all")) {
            logOperation("All devices in " + area + " have been processed");
        }
    }

    public void logOperation(String operation) {
        String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );
        System.out.println("[" + timestamp + "] User: Bl0u - " + operation);
    }
}