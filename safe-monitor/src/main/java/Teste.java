import com.github.britooo.looca.api.core.Looca;
import componentes.Sistema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;

public class Teste {

    public static void main(String[] args) {

        Sistema sistema = new Sistema();
        Looca looca = new Looca();
        Query query = new Query();

        DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");


        System.out.println("Sistema operacional: " + sistema.getSistemaOperacional());
        System.out.println("Fabricante: " + sistema.getFabricante());
        System.out.println("Inicializado: " + sistema.getInicializado());
        System.out.println("Arquitetura: " + sistema.getArquitetura());
        System.out.println("Tempo atividade: " + sistema.getTempoAtividade().doubleValue());

        query.conectarMaquina(3);
        query.inserirDadosMaquina();



    }


}
