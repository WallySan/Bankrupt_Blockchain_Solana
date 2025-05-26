/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankrupt;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fatec
 */
public class Resultado {
    
    private int numeroJogo;
    private String vencedor;
    private int rodadas;
    private List<Player> jogadores = new ArrayList<Player>();
    
    public Resultado(int nj, String venc, int rod, List<Player> jogs)
    {
        this.numeroJogo = nj;
        this.vencedor = venc;
        this.rodadas = rod;
        this.jogadores = jogs;
    }
    
    
    
    public int getNumeroJogo()
    {
        return this.numeroJogo;
    }
    
    
    public String getNomeVencedor()
    {
       
        return this.vencedor;
       
    }
    
    public int getRodadas()
    {
        return this.rodadas;
    }
    
    public List<Player> getJogadores()
    {
        return this.jogadores;
    }
    
        
    
}
