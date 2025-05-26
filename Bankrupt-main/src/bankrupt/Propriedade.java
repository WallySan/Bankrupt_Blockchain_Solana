/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankrupt;

/**
 *
 * @author kali
 */
public class Propriedade {
 
    private int custoVenda;
    private int valorAluguel;
    private Player dono;
    private String nome;
    
    
    // Getter e Setter para custoVenda
    public int getCustoVenda() {
        return custoVenda;
    }

    public void setCustoVenda(int custoVenda) {
        this.custoVenda = custoVenda;
    }

    // Getter e Setter para valorAluguel
    public int getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(int valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    // Getter e Setter para dono
    public Player getDono() {
        return dono;
    }

    public void setDono(Player dono) {
        this.dono = dono;
    }

    // Getter e Setter para nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
