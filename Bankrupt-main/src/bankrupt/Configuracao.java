package bankrupt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Configuracao {

    public static final List<String> NOMES_CASAS = Arrays.asList(
        "Rua do Loop Infinito",
        "Viela do NullPointer",
        "Beco do Bug Misterioso",
        "Avenida do Deploy de Sexta",
        "Travessa do Stack Overflow",
        "Alameda dos Println",
        "Rua 404 — Não Encontrada",
        "Praça do Update Sem Backup",
        "Condomínio dos Ifs Aninhados",
        "Marginal dos Merge Conflict",
        "Vila do Wi-Fi Lento",
        "Rua do Comando Errado em Produção",
        "Alameda do For sem Break",
        "Residencial do Código Espaguete",
        "Rua do Dev que Funciona na Minha Máquina",
        "Caminho do While Verdadeiro",
        "Rodovia do Timeout",
        "Ladeira do Commit Malicioso",
        "Travessa do Comentário Críptico",
        "Avenida dos Logs Infinitos"
    );

    public static List<Propriedade> carregarPropriedades(String caminhoArquivo) {
        List<Propriedade> propriedades = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            int contador = 0;

            while ((linha = br.readLine()) != null && contador < 20) {
                //String[] partes = linha.trim().split(" ");
                String[] partes = linha.trim().split("\\s+");


                if (partes.length >= 2) {
                    int custo = Integer.parseInt(partes[0]);
                    int aluguel = Integer.parseInt(partes[1]);
                    
                                   
                    
                    Propriedade prop = new Propriedade();
                     
                    prop.setNome(NOMES_CASAS.get(contador));
                    prop.setCustoVenda(custo);
                    prop.setValorAluguel(aluguel);

                    propriedades.add(prop);
                    contador++;
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter número: " + e.getMessage());
        }

        return propriedades;
    }
}
