package model.command;

import model.api.Command;
import model.api.Device;

public class TurnOffDeviceCommand implements Command {
    private Device device;

    public TurnOffDeviceCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOff();
    }
}
