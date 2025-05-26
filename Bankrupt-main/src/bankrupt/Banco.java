/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankrupt;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader; 
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Banco {

    public Banco() {
    }

    public boolean receber(Player pagador, int valor) {
        if (pagador.getCoins() >= valor) {
            pagador.setCoins(pagador.getCoins() - valor);

            // Transação Solana: pagador envia para o banco
            postJSON("http://localhost:3000/banco/receber",
                    String.format("{\"pagadorId\": %d, \"valor\": %d}", pagador.getID(), valor));

            return true;
        } else {
            return false;
        }
    }

    public boolean pagar(Player receptor, int valor) {
        // Transação Solana: banco envia para o player receptor
        postJSON("http://localhost:3000/banco/pagar",
                String.format("{\"receptorPubKey\": \"%s\", \"valor\": %d}", receptor.getPublicKey(), valor));

        // Atualiza saldo local apenas como interface visual (opcional)
        receptor.setCoins(receptor.getCoins() + valor);
        return true;
    }

    /*
    public void transferirAluguel(Player pagador, Player recebedor, int valor) {
        int saldo = pagador.getCoins();

        if (saldo >= valor) {
            pagador.setCoins(saldo - valor);
            recebedor.setCoins(recebedor.getCoins() + valor);

            // Transação Solana: pagador envia SOL para recebedor
            postJSON("http://localhost:3000/aluguel",
                    String.format("{\"pagadorId\": %d, \"recebedorPubKey\": \"%s\", \"valor\": %d}",
                            pagador.getID(), recebedor.getPublicKey(), valor));
        } else {
            // Jogador quebra: transfere o que tem e fica inativo
            pagador.setCoins(0);
            recebedor.setCoins(recebedor.getCoins() + saldo);
            pagador.setAtivo(false);
            System.out.println("Jogador " + pagador.getID() + " faliu!");
        }
    }*/

    public void transferirAluguel(Player pagador, Player recebedor, int valor) {
        int saldo = pagador.getCoins();

        if (saldo >= valor) {
            pagador.setCoins(saldo - valor);
            recebedor.setCoins(recebedor.getCoins() + valor);

            // Transação Solana: pagador envia SOL para recebedor
            String jsonBody = String.format("{\"pagadorId\": %d, \"recebedorPubKey\": \"%s\", \"valor\": %d}",
                    pagador.getID(), recebedor.getPublicKey(), valor);

            try {
                String resposta = postJSON("http://localhost:3000/aluguel", jsonBody);
                // Exemplo de resposta esperada: {"success":true,"signature":"XYZ...123"}
                if (resposta.contains("signature")) {
                    String signature = extrairCampo(resposta, "signature");
                    System.out.println("Transação realizada com sucesso.");
                    System.out.println("Para verificar: solana confirm -v " + signature);
                } else {
                    System.out.println("Transação realizada, mas não foi possível obter a assinatura.");
                }
            } catch (Exception e) {
                System.err.println("Erro ao realizar transação na blockchain: " + e.getMessage());
            }
        } else {
            // Jogador quebra: transfere o que tem e fica inativo
            pagador.setCoins(0);
            recebedor.setCoins(recebedor.getCoins() + saldo);
            pagador.setAtivo(false);
            System.out.println("Jogador " + pagador.getID() + " faliu!");
        }
    }

    private String extrairCampo(String json, String campo) {
    int start = json.indexOf(campo + "\":\"");
    if (start == -1) return null;
    start += (campo.length() + 3);
    int end = json.indexOf("\"", start);
    return json.substring(start, end);
}

    
    
    public long consultarSaldo(String publicKey) {
        try {
            URL url = new URL("http://localhost:3000/saldo?pubkey=" + publicKey);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();
            if (status == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder content = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                in.close();
                con.disconnect();

                return Long.parseLong(content.toString()); // Saldo em lamports
            } else {
                System.err.println("Erro ao consultar saldo do jogador. HTTP: " + status);
            }

        } catch (Exception e) {
            System.err.println("Erro ao consultar saldo via backend:");
            e.printStackTrace();
        }

        return -1; // Erro
    }

    // Novo método: consulta saldo do banco via backend
    public long getCoins() {
        try {
            URL url = new URL("http://localhost:3000/banco/saldo");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();

            if (status == 200) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                in.close();
                con.disconnect();

                return Long.parseLong(content.toString()); // Ex: saldo em lamports
            } else {
                System.err.println("Erro ao consultar saldo do banco. Código HTTP: " + status);
            }

        } catch (Exception e) {
            System.err.println("Erro ao consultar saldo no backend:");
            e.printStackTrace();
        }

        return -1; // Indica erro
    }

    private String postJSON(String urlString, String json) {
        try {
            URL url = new URL(urlString);
            System.out.println("Enviando POST para: " + url);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");

            // Envia o corpo da requisição
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Lê a resposta do servidor
            int responseCode = con.getResponseCode();
            InputStream is = (responseCode < HttpURLConnection.HTTP_BAD_REQUEST)
                    ? con.getInputStream()
                    : con.getErrorStream();

            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line.trim());
                }
            }

            System.out.println("HTTP response code: " + responseCode);
            System.out.println("Resposta do backend: " + response);

            return response.toString();

        } catch (Exception e) {
            System.err.println("Erro ao enviar requisição para backend Solana:");
            e.printStackTrace();
            return null;
        }
    }

}

/**
 *
 * @author kali
 *
 *
 * public class Banco {
 *
 * private int coins = 10_000_000;
 *
 * public Banco() {}
 *
 * public int getCoins() { return coins; }
 *
 * // Banco recebe valor de um player, se ele tiver saldo suficiente public
 * boolean receber(Player pagador, int valor) { if (pagador.getCoins() >= valor)
 * { pagador.setCoins(pagador.getCoins() - valor); this.coins += valor; return
 * true; } else { return false; } }
 *
 * // Banco paga valor para um player, se tiver saldo suficiente public boolean
 * pagar(Player receptor, int valor) { if (this.coins >= valor) { this.coins -=
 * valor; receptor.setCoins(receptor.getCoins() + valor); return true; } else {
 * return false; } }
 *
 * public void transferirAluguel(Player pagador, Player recebedor, int valor) {
 * int saldo = pagador.getCoins();
 *
 * if (saldo >= valor) { pagador.setCoins(saldo - valor);
 * recebedor.setCoins(recebedor.getCoins() + valor); } else { // Pagador quebra:
 * paga o que tem e fica inativo pagador.setCoins(0);
 * recebedor.setCoins(recebedor.getCoins() + saldo); pagador.setAtivo(false);
 * System.out.println("Jogador " + pagador.getID() + " faliu!"); } }
 *
 *
 * }
 */
