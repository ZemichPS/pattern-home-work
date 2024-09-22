package service;

import model.api.AbstractDevice;

import java.util.ArrayList;
import java.util.List;

public class DeviceService {
    private List<AbstractDevice> devices = new ArrayList<>();

    public void register(AbstractDevice device) {
        devices.add(device);
    }

}
