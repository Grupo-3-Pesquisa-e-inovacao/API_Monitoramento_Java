import com.github.britooo.looca.api.core.Looca;
import com.sun.jna.platform.unix.X11;
import componentes.*;
import entidades.Maquina;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Teste {

    public static void main(String[] args) {

        Timer timer = new Timer();


        Monitoramento monitoramento = new Monitoramento();
        Query query = new Query();
        List<Janela> janelasAbertas = new ArrayList<>();

        monitoramento.popularListaJanelasFechadas("TESTETESTETESTETESTETESTETESTETESTETESTETESTETESTETESTETESTETESTETESTETESTETESTETESTETESTETESTETESTETESTETESTE");

        System.out.println(monitoramento.getJanelasFechadas());

        }
    }

