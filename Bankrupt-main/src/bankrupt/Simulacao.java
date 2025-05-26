package bankrupt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulacao {

    private static List<Resultado> resultados = new ArrayList<Resultado>();
    
    public static void main(String[] args) {

       
        int maximoRodadas = 1000;
        int jogosSimulados = 300;

        // Cria diretório 'log' se não existir
        File pastaLog = new File("log");
        if (!pastaLog.exists()) {
            boolean criada = pastaLog.mkdir();
            if (!criada) {
                System.out.println("Erro ao criar pasta de log.");
                return;
            }
        }

        int proximoNumero = obterProximoNumeroSequencial(pastaLog);

        for (int i = 0; i < jogosSimulados; i++) {

            Tabuleiro tabuleiro = new Tabuleiro();

            Player p1 = new Player(1, new ComportamentoAleatorio(), "Aleatório");
            Player p2 = new Player(2, new ComportamentoCauteloso(), "Cauteloso");
            Player p3 = new Player(3, new ComportamentoExigente(), "Exigente");
            Player p4 = new Player(4, new ComportamentoImpulsivo(), "Impulsivo");
            List<Player> jogadores = List.of(p1, p2, p3, p4);

            Jogo j = new Jogo(jogadores, tabuleiro, maximoRodadas, i);

            resultados.add(j.getResultado());
               

        }
        
        
        
        AnalisarResultados();
        
    }
    
    
    public static void AnalisarResultados() {
    int aleatorios = 0;
    int exigente = 0;
    int impulsivo = 0;
    int cauteloso = 0;
    int bancoVenceu = 0;
    int timeouts = 0;

    List<Integer> rodadas = new ArrayList<>();

    for (Resultado r : resultados) {
        String vencedor = r.getNomeVencedor();
        int numRodadas = r.getRodadas();

        rodadas.add(numRodadas);

        if (numRodadas >= 1000) {
            timeouts++;
        }

        switch (vencedor) {
            case "Aleatório": aleatorios++; break;
            case "Cauteloso": cauteloso++; break;
            case "Exigente":  exigente++;  break;
            case "Impulsivo": impulsivo++; break;
            case "Banco":     bancoVenceu++; break;
        }
    }

    int totalPartidas = resultados.size();
    double mediaRodadas = rodadas.stream().mapToInt(Integer::intValue).average().orElse(0);

    // Porcentagens
    double pctAleatorio = 100.0 * aleatorios / totalPartidas;
    double pctCauteloso = 100.0 * cauteloso / totalPartidas;
    double pctExigente  = 100.0 * exigente  / totalPartidas;
    double pctImpulsivo = 100.0 * impulsivo / totalPartidas;
    double pctBanco     = 100.0 * bancoVenceu / totalPartidas;

    // Comportamento com mais vitórias
    Map<String, Integer> vitorias = new HashMap<>();
    vitorias.put("Aleatório", aleatorios);
    vitorias.put("Cauteloso", cauteloso);
    vitorias.put("Exigente", exigente);
    vitorias.put("Impulsivo", impulsivo);

    String comportamentoMaisVitorioso = Collections.max(vitorias.entrySet(), Map.Entry.comparingByValue()).getKey();

    // Saída
    
    System.out.println("################################################");

    System.out.println("######### Estatísticas das partidas ###########");

    System.out.println("\n1 - ****** Partidas que terminaram por time out ********");

    System.out.println("Partidas que terminaram por time out (>= 1000 rodadas): " + timeouts);
    
    System.out.println("\n2 - ****** Turnos em média por partida ********");

    System.out.printf("Turnos em média por partida: %.2f\n", mediaRodadas);

    System.out.println("\n3 - ****** Porcentagem de vitórias por comportamento ********");
    System.out.printf("Aleatório: %.2f%%\n", pctAleatorio);
    System.out.printf("Cauteloso: %.2f%%\n", pctCauteloso);
    System.out.printf("Exigente:  %.2f%%\n", pctExigente);
    System.out.printf("Impulsivo: %.2f%%\n", pctImpulsivo);
    System.out.printf("Banco (sem vencedor): %.2f%%\n", pctBanco);
    System.out.println("\n4 - ****** Comportamento com mais vitórias ********");

    System.out.println("\nComportamento com mais vitórias: " + comportamentoMaisVitorioso);
}

    

    
    
    

    private static int obterProximoNumeroSequencial(File pastaLog) {
        int maiorNumero = 0;
        File[] arquivos = pastaLog.listFiles((dir, nome) ->
                nome.startsWith("SIMULACAO_") && nome.endsWith(".txt"));

        if (arquivos != null) {
            for (File arquivo : arquivos) {
                String nome = arquivo.getName(); // SIMULACAO_005.txt
                try {
                    String numeroStr = nome.substring("SIMULACAO_".length(), nome.length() - 4);
                    int numero = Integer.parseInt(numeroStr);
                    if (numero > maiorNumero) {
                        maiorNumero = numero;
                    }
                } catch (Exception e) {
                    // Ignora nomes mal formatados
                }
            }
        }

        return maiorNumero + 1;
    }
}
