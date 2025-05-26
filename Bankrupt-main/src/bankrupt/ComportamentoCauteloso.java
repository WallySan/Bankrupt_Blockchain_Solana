/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankrupt;

/**
 *
 * @author kali
 */
public class ComportamentoCauteloso implements IComportamento {
    
    public boolean decideComprar(int coins, int aluguel, int compra)
    {
      return (coins - compra) >  80;
    };
    
    
}
