/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankrupt;

/**
 *
 * @author kali
 */
public class Banco {

    private int coins = 10_000_000;

    public Banco() {}

    public int getCoins() {
        return coins;
    }

    // Banco recebe valor de um player, se ele tiver saldo suficiente
    public boolean receber(Player pagador, int valor) {
        if (pagador.getCoins() >= valor) {
            pagador.setCoins(pagador.getCoins() - valor);
            this.coins += valor;
            return true;
        } else {
            return false;
        }
    }

    // Banco paga valor para um player, se tiver saldo suficiente
        public boolean pagar(Player receptor, int valor) {
        if (this.coins >= valor) {
            this.coins -= valor;
            receptor.setCoins(receptor.getCoins() + valor);
            return true;
         } else {
            return false;
        }
    }

    public void transferirAluguel(Player pagador, Player recebedor, int valor) {
        int saldo = pagador.getCoins();

        if (saldo >= valor) {
            pagador.setCoins(saldo - valor);
            recebedor.setCoins(recebedor.getCoins() + valor);
        } else {
            // Pagador quebra: paga o que tem e fica inativo
            pagador.setCoins(0);
            recebedor.setCoins(recebedor.getCoins() + saldo);
            pagador.setAtivo(false);
            System.out.println("Jogador " + pagador.getID() + " faliu!");
        }
    }
    
    
}