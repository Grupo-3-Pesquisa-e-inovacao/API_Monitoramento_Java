package componentes;

import entidades.Componente;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.SystemInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Gpu extends Componente{
    private HardwareAbstractionLayer hardware;
    private List<GraphicsCard> listasGpus;

    public Gpu(HardwareAbstractionLayer hardware) {
        this.hardware = hardware;
        this.listasGpus = new ArrayList<>();
    }
    public List<GraphicsCard> getListaGPU() {
        listasGpus = hardware.getGraphicsCards();
        return listasGpus;
    }
    public void capturarDadosGPU() {
        DecimalFormat df = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.US));

        for (GraphicsCard gpu : listasGpus) {
            if (gpu.getVendor().contains("NVIDIA")) {
                int porcentagem = 0;
                double mem_uso = 0.0;
                double mem_disp = 0.0;

                try {
                    Process process = Runtime.getRuntime().exec("nvidia-smi --query-gpu=utilization.gpu,memory.used,memory.free --format=csv,noheader,nounits");

                    String line;
                    String[] memoryInfo;
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    while ((line = reader.readLine()) != null) {
                        memoryInfo = line.trim().split(",");
                        porcentagem = Integer.parseInt(memoryInfo[0].trim());
                        mem_uso = (double) Long.parseLong(memoryInfo[1].trim()) / 1024.0;
                        mem_disp = (double) Long.parseLong(memoryInfo[2].trim()) / 1024.0;
                    }

                    process.waitFor();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        Gpu gpu01 = new Gpu(hardware);

        List<GraphicsCard> listaGpus = gpu01.getListaGPU();
        System.out.println("Lista de GPUs:");
        for (GraphicsCard gpu : listaGpus) {
            System.out.println("Nome: " + gpu.getName());
            System.out.println("ID do Dispositivo: " + gpu.getDeviceId());
            System.out.println("Vendedor: " + gpu.getVendor());
            System.out.println("VRAM: " + gpu.getVRam());
            System.out.println("Versão do Driver: " + gpu.getVersionInfo());
            System.out.println("------------------------");
        }
        gpu01.capturarDadosGPU();
    }
    @Override
    public Double converterParaGigas(Double valor) {
        return valor / 1000000000;
    }
    @Override
    public void definirNome() {

    }
    @Override
    public void definirModelo() {
        System.out.println("Modelo da GPU: ".formatted(getListaGPU().get(0).getName()));
    }
    @Override
    public void definirUso() {

    }
    @Override
    public void definirTotal() {
    }
}
