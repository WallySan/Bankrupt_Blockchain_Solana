/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankrupt;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kali
 */
import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {

    private List<Bloco> posicoes;
    private List<Propriedade> propriedades;

    public Tabuleiro() {
        this.posicoes = new ArrayList<>();
        this.propriedades = new ArrayList<>();

        importPropriedades();
        
        // Simula com 40 casas sendo 20 propriedades
        //inicializarPosicoes(); 
        
        //Simula com 20 casas todas propriedades
        //inicializarPosicoesReduzido(); 
        
        // Simula com 60 casas, 2 vazias 1 propriedade
        inicializarPosicoesExtendido(); 
    
    }

   
    private void importPropriedades() {

    this.propriedades = Configuracao.carregarPropriedades("src/bankrupt/gameConfig.txt");

    System.out.println("=== Propriedades Importadas ===");
    System.out.printf("%-40s | %-10s | %-10s%n", "Nome", "Custo", "Aluguel");
    System.out.println("=".repeat(70));

    for (Propriedade prop : propriedades) {
        System.out.printf("%-40s | %-10d | %-10d%n",
                prop.getNome(),
                prop.getCustoVenda(),
                prop.getValorAluguel());
    }

    System.out.println("=".repeat(70) + "\n");
}


    private void inicializarPosicoes() {
    int propIndex = 0;
    for (int i = 0; i < 40; i++) {
        Bloco bloco = new Bloco();

        // Coloca uma propriedade a cada 2 posições
        if (i % 2 == 1 && propIndex < propriedades.size()) {
            bloco.setPropriedade(propriedades.get(propIndex));
            propIndex++;
        }

        posicoes.add(bloco);
    }
}
    
    private void inicializarPosicoesReduzido() {
    posicoes.clear(); // Limpa caso esteja reaproveitando

    for (int i = 0; i < propriedades.size(); i++) {
        Bloco bloco = new Bloco();
        bloco.setPropriedade(propriedades.get(i));
        posicoes.add(bloco);
    }
}

    private void inicializarPosicoesExtendido() {
    int propIndex = 0;

    for (int i = 0; i < 60; i++) {
        Bloco bloco = new Bloco();

        // A cada 3 blocos, o primeiro recebe propriedade
        if (i % 3 == 0 && propIndex < propriedades.size()) {
            bloco.setPropriedade(propriedades.get(propIndex));
            propIndex++;
        }

        posicoes.add(bloco);
    }
}



    public void desenharTabuleiro() {
    System.out.println("===== TABULEIRO =====");
    for (int i = 0; i < posicoes.size(); i++) {
        Bloco bloco = posicoes.get(i);
        if (bloco.temPropriedade()) {
            System.out.println("[" + i + "] " + bloco.getPropriedade().getNome());
        } else {
            System.out.println("[" + i + "] Bloco Comum");
        }
    }
    System.out.println("======================\n");
}
    
    // Getters
    public List<Bloco> getPosicoes() {
        return posicoes;
    }

    public List<Propriedade> getPropriedades() {
        return propriedades;
    }
    
    public Bloco getBlocoNaPosicao(int posicao) {
        if (posicao >= 0 && posicao < posicoes.size()) {
            return posicoes.get(posicao);
        }
        return null; // ou lançar uma exceção, se preferir
    }

    // ✅ Verifica se a posição existe no tabuleiro
    public boolean posicaoExiste(int posicao) {
        return posicao >= 0 && posicao < posicoes.size();
    }
    
    
}

