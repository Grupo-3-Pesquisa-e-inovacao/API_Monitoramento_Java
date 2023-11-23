import com.github.britooo.looca.api.core.Looca;
import com.sun.jna.platform.unix.X11;
import componentes.*;
import entidades.Maquina;

import java.util.ArrayList;
import java.util.List;

public class Teste {

    public static void main(String[] args) {

        Monitoramento monitoramento = new Monitoramento();



        System.out.println(monitoramento.getJanelasAbertas());

        }
    }

