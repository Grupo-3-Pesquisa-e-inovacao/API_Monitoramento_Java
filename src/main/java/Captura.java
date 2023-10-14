import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.janelas.Janela;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.rede.RedeInterface;

import java.util.ArrayList;
import java.util.List;

    public class Captura {

        Looca looca = new Looca();

        private List<Volume> listaVolumes = looca.getGrupoDeDiscos().getVolumes();
        private Integer numeroDeVolumes;
        private Long discoVolumeTotal;
        private Long discoVolumeDisponivel;
        private List<Long> listaVolumeTotal = new ArrayList();
        private List<Long> listaVolumeDisponivel =  new ArrayList();
        private Long memoriaTotal = looca.getMemoria().getTotal();
        private Long memoriaDisponivel = looca.getMemoria().getDisponivel();
        private Double cpuEmUso = looca.getProcessador().getUso();
        private List<Processo> listaProcessos = looca.getGrupoDeProcessos().getProcessos();
        private List<Integer> listaPidProcessos = new ArrayList();
        private List<Double> listaUsoCpuProcesso = new ArrayList();
        private List<Double> listaUsoMemoriaProcesso = new ArrayList();
        private List<Long> listaUsoBytesProcesso = new ArrayList();
        private List<Janela> listaJanelasAbertas = looca.getGrupoDeJanelas().getJanelas();
        private List<String> listaCaminhoJanelas = new ArrayList();
        private List<String> listaTituloJanelas = new ArrayList();
        private List<Long> listaIdJanelas = new ArrayList();
        private List<RedeInterface> redes = looca.getRede().getGrupoDeInterfaces().getInterfaces();
        private RedeInterface redeAtual = null;
        private Long pacotesRecebidos;
        private Long pacotesEnviados;

        public void definirNumeroVolumes() {
            Integer volumesValidos = 0;
            for (int i = 0; i < listaVolumes.size(); i++) {
                if (listaVolumes.get(i).getTotal() != 0) {
                    volumesValidos++;
                }
            }
            numeroDeVolumes = volumesValidos;
        }
        public void definirVolumeTotal() {
            discoVolumeTotal = listaVolumes.get(0).getTotal();
        }
        public void definirVolumeDisponivel() {
            discoVolumeDisponivel = listaVolumes.get(0).getDisponivel();
        }
        public void popularListaVolumeTotal() {
            for (int i = 0; i < listaVolumes.size(); i++) {
                if (listaVolumes.get(i).getTotal() != 0) {
                    listaVolumeTotal.add(listaVolumes.get(i).getTotal());
                }

            }
        }
        public void popularListaVolumeDisponivel() {
            for (int i = 0; i < listaVolumes.size(); i++) {
                if (listaVolumes.get(i).getDisponivel() != 0) {
                    listaVolumeDisponivel.add(listaVolumes.get(i).getDisponivel());
                }

            }
        }
        public void popularListaPidProcessos() {
            for (int i = 0; i < listaProcessos.size(); i++) {
                listaPidProcessos.add(listaProcessos.get(i).getPid());
            }
        }
        public void popularListaUsoCpuProcesso() {
            for (int i = 0; i < listaProcessos.size(); i++) {
                listaUsoCpuProcesso.add(listaProcessos.get(i).getUsoCpu());
            }
        }
        public void popularListaUsoMemoriaProcesso() {
            for (int i = 0; i < listaProcessos.size(); i++) {
                listaUsoMemoriaProcesso.add(listaProcessos.get(i).getUsoMemoria());
            }
        }
        public void popularListaUsoBytesProcesso() {
            for (int i = 0; i < listaProcessos.size(); i++) {
                listaUsoBytesProcesso.add(listaProcessos.get(i).getBytesUtilizados());
            }
        }
        public void popularListaCaminhoJanelas() {
            for (int i = 0; i < listaJanelasAbertas.size(); i++) {
                listaCaminhoJanelas.add(listaJanelasAbertas.get(i).getComando());
            }
        }
        public void popularListaTituloJanelas() {
            for (int i = 0; i < listaJanelasAbertas.size(); i++) {
                if (listaJanelasAbertas.get(i).getTitulo() != "") {
                    listaTituloJanelas.add(listaJanelasAbertas.get(i).getTitulo());
                }

            }
        }
        public void popularListaIdJanelas() {
            for (int i = 0; i < listaJanelasAbertas.size(); i++) {
                listaIdJanelas.add(listaJanelasAbertas.get(i).getJanelaId());
            }
        }
        public void definirRedeAtual() {
            for (int i = 0; i < redes.size(); i++) {
                if (redes.get(i).getBytesRecebidos() != 0) {
                    redeAtual = redes.get(i);
                }
            }
        }

        public void definirPacotesRecebidos() {
            pacotesRecebidos = redeAtual.getPacotesRecebidos();
        }

        public void definirPacotesEnviados() {
            pacotesEnviados = redeAtual.getPacotesEnviados();
        }

        public Captura() {
            definirNumeroVolumes();
            if(numeroDeVolumes >= 2) {
                popularListaVolumeTotal();
                popularListaVolumeDisponivel();
            } else {
                definirVolumeTotal();
                definirVolumeDisponivel();
            }
            popularListaUsoCpuProcesso();
            popularListaPidProcessos();
            popularListaUsoMemoriaProcesso();
            popularListaUsoBytesProcesso();
            popularListaCaminhoJanelas();
            popularListaTituloJanelas();
            popularListaIdJanelas();
            definirRedeAtual();
            if (redeAtual != null) {
                definirPacotesRecebidos();
                definirPacotesEnviados();
            } else {
                System.out.println("Rede não encontrada, verifice sua conexão com a internet.");
            }

        }

        public List<Long> getListaVolumeTotal() {

            return listaVolumeTotal;
        }

        public Long getDiscoVolumeTotal() {

            return discoVolumeTotal;
        }

        public Long getDiscoVolumeDisponivel() {

            return discoVolumeDisponivel;
        }

        public List<Long> getListaVolumeDisponivel() {
            return listaVolumeDisponivel;
        }

        public Long getMemoriaTotal() {
            return memoriaTotal;
        }

        public Long getMemoriaDisponivel() {
            return memoriaDisponivel;
        }

        public Double getCpuEmUso() {
            return cpuEmUso;
        }

        public List<Integer> getListaPidProcessos() {
            return listaPidProcessos;
        }

        public List<Double> getListaUsoCpuProcesso() {
            return listaUsoCpuProcesso;
        }

        public List<Double> getListaUsoMemoriaProcesso() {
            return listaUsoMemoriaProcesso;
        }

        public List<Long> getListaUsoBytesProcesso() {
            return listaUsoBytesProcesso;
        }

        public List<String> getListaCaminhoJanelas() {
            return listaCaminhoJanelas;
        }

        public List<String> getListaTituloJanelas() {
            return listaTituloJanelas;
        }

        public List<Long> getListaIdJanelas() {
            return listaIdJanelas;
        }

        public RedeInterface getRedeAtual() {
            return redeAtual;
        }

        public Long getPacotesRecebidos() {
            return pacotesRecebidos;
        }

        public Long getPacotesEnviados() {
            return pacotesEnviados;
        }


    }

