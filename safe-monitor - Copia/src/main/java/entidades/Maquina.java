package entidades;

import com.github.britooo.looca.api.core.Looca;
import componentes.Janela;
import componentes.Sistema;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Maquina {


        private Integer idMaquina;
        private String nome;
        private String modelo;
        private String numeroSerie;
        private String marca;
        private String sistemaOperacional;
        private String fabricante;
        private Integer arquitetura;
        private String endereco_ipv4;
        private String enderecoMac;
        private String hostName;

        public Maquina() {}

    public void definirInfoMaquinaHardware(String sistemaOperacional, String fabricante, Integer arquitetura, String endereco_ipv4, String enderecoMac, String hostName) {
        this.sistemaOperacional = sistemaOperacional;
        this.fabricante = fabricante;
        this.arquitetura = arquitetura;
        this.endereco_ipv4 = endereco_ipv4;
        this.enderecoMac = enderecoMac;
        this.hostName = hostName;
    }


    public void cadastrarInfoMaquina(String nome, String modelo, String numeroSerie, String marca) {
        this.nome = nome;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.marca = marca;
    }

    public void definirInfoMaquina(){
        Scanner leitor = new Scanner(System.in);
        String nome = null;
        String modelo = null;
        String numeroSerie = null;
        String marca = null;
        String respostaBin = "";


        System.out.println("Deseja cadastrar um nome para essa máquina agora? S ou N");
        respostaBin = leitor.nextLine();

        if (respostaBin.equalsIgnoreCase("S")){
            System.out.println("Digite um nome para esta maquina: ");
            nome = leitor.nextLine();
        }

        System.out.println("Deseja cadastrar o modelo dessa máquina agora? S ou N");
        respostaBin = leitor.nextLine();

        if (respostaBin.equalsIgnoreCase("S")){
            System.out.println("Digite o modelo desta maquina: ");
            modelo = leitor.nextLine();
        }

        System.out.println("Deseja cadastrar a marca dessa máquina agora? S ou N");
        respostaBin = leitor.nextLine();

        if (respostaBin.equalsIgnoreCase("S")){
            System.out.println("Digite a marca desta maquina: ");
            marca = leitor.nextLine();
        }

        System.out.println("Deseja cadastrar o número de série dessa máquina agora? S ou N");
        respostaBin = leitor.nextLine();

        if (respostaBin.equalsIgnoreCase("S")){
            System.out.println("Digite o número de série desta maquina: ");
            numeroSerie = leitor.nextLine();
        }

        cadastrarInfoMaquina(nome, modelo, numeroSerie, marca);

    }

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Integer getArquitetura() {
        return arquitetura;
    }

    public void setArquitetura(Integer arquitetura) {
        this.arquitetura = arquitetura;
    }

    public String getEndereco_ipv4() {
        return endereco_ipv4;
    }

    public void setEndereco_ipv4(String endereco_ipv4) {
        this.endereco_ipv4 = endereco_ipv4;
    }

    public String getEnderecoMac() {
        return enderecoMac;
    }

    public void setEnderecoMac(String enderecoMac) {
        this.enderecoMac = enderecoMac;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    @Override
        public String toString() {
            return String.format("""
                *----- Máquina %s -----*
                Id: %d
                Modelo: %s
                Número de Série: %s
                Marca: %s
                Sistema Operacional: %s
                Fabricante: %s
                Arquitetura: %d
                Endereço IPV4: %s
                Endereço MAC: %S
                """,nome,idMaquina, modelo, numeroSerie, marca, sistemaOperacional, fabricante, arquitetura, endereco_ipv4, enderecoMac);
        }
}
