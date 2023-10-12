public class Main {
    public static void main(String[] args) {
        Captura var = new Captura();


        System.out.println("Lista de espaço total presente no volume: ");
        System.out.println(var.getListaVolumeTotal() + " \n");

        System.out.println("Lista de espaço disponivel presente no volume: ");
        System.out.println(var.getListaVolumeDisponivel()  + " \n");

        System.out.println("Valor de espaço total presente no volume: ");
        System.out.println(var.getDiscoVolumeTotal() + " \n");

        System.out.println("Valor de espaço disponivel presente no volume: ");
        System.out.println(var.getDiscoVolumeDisponivel()  + " \n");

        System.out.println("Memoria total:");
        System.out.println(var.getMemoriaTotal()  + " \n");

        System.out.println("Memoria disponivel:");
        System.out.println(var.getMemoriaDisponivel()  + " \n");

        System.out.println("CPU em uso:");
        System.out.println(var.getCpuEmUso()  + " \n");

        System.out.println("Lista de uso de memoria processos:");
        System.out.println(var.getListaUsoMemoriaProcesso()  + " \n");

        System.out.println("Lista de uso de cpu processos:");
        System.out.println(var.getListaUsoCpuProcesso() + " \n");

        System.out.println("Lista de uso de bytes processos:");
        System.out.println(var.getListaUsoBytesProcesso() + " \n");

        System.out.println("Lista de caminhos das janelas abertas:");
        System.out.println(var.getListaCaminhoJanelas() + " \n");

        System.out.println("Lista de titulos das janelas abertas:");
        System.out.println(var.getListaTituloJanelas() + " \n");

        System.out.println("Lista rede atual:");
        System.out.println(var.getRedeAtual() + " \n");

        System.out.println("Pacotes recebidos:");
        System.out.println(var.getPacotesRecebidos() + " \n");

        System.out.println("Pacotes enviados:");
        System.out.println(var.getPacotesEnviados() + " \n");

    }
}
