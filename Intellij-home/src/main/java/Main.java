import model.*;
import model.api.Device;
import model.api.Lamp;
import model.api.PlayBox;
import model.infrastructure.BluetoothConnectivity;
import model.infrastructure.WifiConnectivity;

import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Lamp badroomSmartLamp = new SmartLamp(
                UUID.randomUUID(),
                "Samsung multiColor smart lamp",
                "172.16.74.7",
                new WifiConnectivity()
        );

        badroomSmartLamp.setBrightness(80);
        badroomSmartLamp.setColor(Color.YELLOW);

        PlayBox playBox = new MusicBox(
                UUID.randomUUID(),
                "Alice smart music box",
                "00:1A:7D:DA:71:13",
                new BluetoothConnectivity()
        );

        List.of(playBox, badroomSmartLamp).forEach(device-> {
            Device any = (Device) device;
            any.turnOn();
        });

    }
}
