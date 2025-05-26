/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankrupt;

import java.util.Random;

/**
 *
 * @author kali
 */
public class ComportamentoAleatorio implements IComportamento {
    
    private static Random random = new Random();
        
    public boolean decideComprar(int coins, int aluguel, int compra)
    {
      return random.nextBoolean(); 
    };
    
    
}
