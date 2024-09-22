package model.command;

import model.api.Command;
import model.api.Lamp;

public class ChancheMaxBrightCommand implements Command {
    private Lamp lamp;
    private int brightness;

    public ChancheMaxBrightCommand(Lamp lamp, int brightness) {
        this.lamp = lamp;
        this.brightness = brightness;
    }

    @Override
    public void execute() {
        lamp.setBrightness(brightness);
    }
}
