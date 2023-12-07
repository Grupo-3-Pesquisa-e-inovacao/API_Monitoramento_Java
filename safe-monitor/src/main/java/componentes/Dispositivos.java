package componentes;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.dispositivos.DispositivoUsb;
import com.github.britooo.looca.api.group.dispositivos.DispositivosUsbGrupo;

import java.util.List;

public class Dispositivos {

    Looca looca = new Looca();

    public void cadastrarDispositivos() {
        List<DispositivoUsb> listaDispositivo = looca.getDispositivosUsbGrupo().getDispositivosUsb();

        for (DispositivoUsb dispositivoUsb : listaDispositivo) {
            String deviceId = dispositivoUsb.getIdDispositivoUsbExclusivo();

            if (isBluetoothDevice(deviceId)) {
                System.out.println("Dispositivo Bluetooth: " + deviceId);
            } else if (isUSBDevice(deviceId)) {
                System.out.println("Dispositivo USB: " + deviceId);
            } else {
                System.out.println("Dispositivo não reconhecido: " + deviceId);
            }
        }
    }

    private boolean isBluetoothDevice(String deviceId) {
        return deviceId.startsWith("BTH");
    }

    private boolean isUSBDevice(String deviceId) {
        return deviceId.startsWith("USB");
    }
}
