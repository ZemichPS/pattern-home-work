package model.command;

import model.api.Command;
import model.api.Device;

public class TurnOnDeviceCommand implements Command {
    private Device device;

    public TurnOnDeviceCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOn();
    }
}
