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
public class Dado {

    private Random random;
    private int ultimoResultado;

    public Dado() {
        random = new Random();
    }

    public int rolar() {
        ultimoResultado = random.nextInt(6) + 1; // Gera número de 1 a 6
        return ultimoResultado;
    }

    public int getUltimoResultado() {
        return ultimoResultado;
    }
}
