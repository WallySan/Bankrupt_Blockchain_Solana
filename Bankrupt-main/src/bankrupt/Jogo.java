package bankrupt;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Jogo {

    private Tabuleiro tabuleiro;
    private List<Player> players = new ArrayList<>();
    private Dado dado = new Dado();
    private Player playerAtual;
    private Banco banco = new Banco();
    private int indicePlayer = 0;
    private int rodadaAtual = 0;
    private int numeroJogo = 0;
    private Resultado resultado;
    private Player vencedor;

    public Jogo(List<Player> p, Tabuleiro t, int rodadas, int numeroJogo) {

        this.players = p;
        this.tabuleiro = t;
        tabuleiro.desenharTabuleiro();
        System.out.println("Iniciando o jogo com " + players.size() + " jogadores.");
        imprimirEstadoDosJogadoresEBanco(banco);
        
        
        for (int i = 0; i < rodadas; i++) {
            rodadaAtual = i;
            realizarRodada();

           
            Player possivelVencedor = null;

            
             // Verifica quantos jogadores ainda estão ativos
            int ativos = 0;
            
            for (Player pp : players) {
                if (pp.isAtivo()) {
                    ativos++;
                    possivelVencedor = pp;
                }
            }
   

            if (ativos == 0) {
                
                System.out.println("Todos os jogadores faliram. Fim do jogo na rodada " + (i + 1));
                
                resultado = new Resultado(numeroJogo,"Ninguem",rodadaAtual,players);
                
                break;
            } else if (ativos == 1) {
                System.out.println("Vitória por eliminação! Jogador " + possivelVencedor.getID() + " (" + possivelVencedor.getNome() + ") venceu o jogo na rodada " + (i + 1));
                
                resultado = new Resultado(numeroJogo,possivelVencedor.getNome(),rodadaAtual,players);
                
                break;
            }
            
            
        }
        
        
    /*
     public Resultado(int nj, Player venc, int rod, List<Player> jogs)
    {
        this.numeroJogo = nj;
        this.vencedor = venc;
        this.rodadas = rod;
        this.jogadores = jogs;
    }
    */
    
    
    
    
    
    
    
    
        
       // Caso o jogo termine sem vencedor por falência ou eliminação
if (resultado == null) {
    System.out.println("Rodadas máximas atingidas. Decidindo vencedor com base nas moedas e posição de turno...");

    Player melhorJogador = null;
    int maiorSaldo = -1;

    for (Player pp : players) {
        if (pp.isAtivo()) {
            if (pp.getCoins() > maiorSaldo) {
                maiorSaldo = pp.getCoins();
                melhorJogador = pp;
            } else if (pp.getCoins() == maiorSaldo) {
                // Critério de desempate: vence quem tem a MAIOR posição
                if (pp.getPosicao() > melhorJogador.getPosicao()) {
                    melhorJogador = pp;
                }
            }
        }
    }

    if (melhorJogador != null) {
        System.out.println("Vitória por saldo! Jogador " + melhorJogador.getID() + " (" + melhorJogador.getNome() + ") venceu com " + maiorSaldo + " moedas.");
        resultado = new Resultado(numeroJogo, melhorJogador.getNome(), rodadaAtual, players);
    } else {
        System.out.println("Nenhum jogador ativo para desempate.");
        resultado = new Resultado(numeroJogo, "Empate", rodadaAtual, players);
    }
}

       








        System.out.println("\n===== ESTADO FINAL DOS JOGADORES =====");
        imprimirEstadoDosJogadoresEBanco(banco);
        
       

    }
    
    public Resultado getResultado()
    {
        return resultado;
    }
    

    public void realizarRodada() {
        playerAtual = players.get(indicePlayer);
        boolean debug = true; 

        
        if (!playerAtual.isAtivo()) {
            
            if(debug)
            System.out.println("Jogador " + playerAtual.getID() + " está inativo (quebrado). Turno ignorado.");
            
            avancarJogador();
            return;
        }

        
        System.out.println("\n#" + String.valueOf(rodadaAtual) + "--- Rodada do Jogador " + playerAtual.getID() + " ---");

        int n = dado.rolar();
        
        if(debug)
        System.out.println("Jogador " + playerAtual.getID() + " rolou o dado e tirou: " + n);

        playerAtual.mover(n);

        int pPos = playerAtual.getPosicao();
        
        if(debug)
        System.out.println("Jogador " + playerAtual.getID() + " moveu-se para a posição: " + pPos);

        if (!tabuleiro.posicaoExiste(pPos)) {
            
            if(debug)
            System.out.println("Tabuleiro ultrapassado. Jogador retorna à posição 0.");
            
            playerAtual.setPosicao(0);
            banco.pagar(playerAtual, 100); // Pagar os 100  na volta completa
            pPos = 0;
        }

        Bloco bloco = tabuleiro.getBlocoNaPosicao(pPos);

        if (bloco.temPropriedade()) {
            Propriedade prop = bloco.getPropriedade();
            
            if(debug)
            System.out.println("A propriedade nesta posição é: " + prop.getNome());

            if (prop.getDono() == null) {
                
                if(debug)
                System.out.println("A propriedade está sem dono.");
                
                boolean comprar = playerAtual.decideComprar(prop);

                if (comprar) {
                    
                    if(debug)
                    System.out.println("Jogador " + playerAtual.getID() + " decidiu comprar a propriedade.");

                    boolean sucesso = banco.receber(playerAtual, prop.getCustoVenda());
                    if (sucesso) {
                        prop.setDono(playerAtual);
                        
                        if(debug)
                        System.out.println("Compra realizada com sucesso.");
                    } else {
                        
                        if(debug)
                        System.out.println("Jogador " + playerAtual.getID() + " não tem dinheiro suficiente para comprar.");
                    }
                } else {
                    
                    if(debug)
                    System.out.println("Jogador " + playerAtual.getID() + " decidiu não comprar.");
                }
            } else {
                if (prop.getDono().getID() != playerAtual.getID()) {
                    
                    if(debug)
                    System.out.println("A propriedade pertence ao Jogador " + prop.getDono().getID() + ".");
                    
                    
                    banco.transferirAluguel(playerAtual, prop.getDono(), prop.getValorAluguel());
                } else {
                    
                    if(debug)
                    System.out.println("O jogador caiu na própria propriedade.");
                }
            }
        } else {
            
            if(debug)
            System.out.println("Esta posição não possui uma propriedade.");
        }

        //System.out.println("\n===== ESTADO FINAL DOS JOGADORES =====");
        //imprimirEstadoDosJogadoresEBanco(banco);
        avancarJogador();
    }

    /*
    private void imprimirEstadoDosJogadoresEBanco(Banco banco) {
        System.out.println("=== Estado Atual do Banco ===");
        System.out.println("Saldo do Banco: " + banco.getCoins() + " moedas\n");

        System.out.println("=== Estado Atual dos Jogadores ===");
        for (Player player : players) {
            System.out.println("Jogador " + player.getID() + " " + player.getNome()
                    + " \t| Moedas: " + player.getCoins()
                    + " \t| Posição: " + player.getPosicao()
                    + " \t| Ativo: " + (player.isAtivo() ? "Sim" : "Não"));
        }
    }*/
    
    private void imprimirEstadoDosJogadoresEBanco(Banco banco) {
    System.out.println("=== Estado Atual do Banco ===");
    long saldoBanco = banco.getCoins(); // Consulta saldo do banco via backend
    System.out.println("Saldo do Banco (on-chain): " + saldoBanco + " lamports\n");

    System.out.println("=== Estado Atual dos Jogadores ===");
    for (Player player : players) {
        String pubKey = player.getPublicKey();
        long saldoJogador = banco.consultarSaldo(pubKey); // Consulta saldo via backend

        System.out.println("Jogador " + player.getID() + " " + player.getNome()
                + " \t| Chave Pública: " + pubKey
                + " \t| Saldo (on-chain): " + saldoJogador + " lamports"
                + " \t| Posição: " + player.getPosicao()
                + " \t| Ativo: " + (player.isAtivo() ? "Sim" : "Não"));
    }
}


    private void avancarJogador() {
        indicePlayer++;
        if (indicePlayer >= players.size()) {
            indicePlayer = 0;
        }
    }
}
