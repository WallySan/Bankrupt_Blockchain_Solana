# Projeto: Banco Imobiliário na Blockchain Solana

🎯 Este projeto é uma simulação de um jogo de Banco Imobiliário onde as transações financeiras entre jogadores são realizadas utilizando a blockchain da Solana.


![image](https://github.com/user-attachments/assets/e7ff5cb1-7de3-4b82-a88b-12549392fe0d)


# 🔗 Tecnologia:

    Utiliza web3.js com um servidor local (Express) para orquestrar as transações.

    Executa com um validador local Solana para fins de teste, sem necessidade de conexão com a mainnet ou testnet.

# 🧪 Ambiente de Teste:

    A blockchain roda 100% localmente.

    As transações podem ser inspecionadas via comandos como solana confirm -v <assinatura>.

# 🧰 Simplicidade proposital:

    O projeto foi feito com foco em simplicidade didática.

    A gestão de carteiras (wallets) é feita com arquivos locais (.json), sem criptografia nem proteção extra.

    Ideal para fins de estudo, prototipação e aprendizado sobre blockchain na prática.

# 🔐 Segurança em produção:

    ⚠️ Atenção: Este modelo não deve ser utilizado em produção como está.
    Para uso seguro real com valores financeiros:

    🔒 Utilize HSM (Hardware Security Module) ou KMS (Key Management Service).

    ⚙️ Proteja as chaves privadas com camadas de autenticação, criptografia e controle de acesso adequado.



> ⚠️ **Atenção: Configure o ambiente Solana para local**
>
> Certifique-se de que a variável de ambiente `SOLANA_CLUSTER` ou as configurações da sua aplicação estejam apontando para a rede de teste local:
>
> ```bash
> solana config set --url http://127.0.0.1:8899
> ```
>
> Nunca execute este código conectado à **mainnet-beta**, pois isso pode causar **perda real de valores** em SOL.  
> Utilize sempre `solana-test-validator` para simulações de jogo e testes locais.


# Instalar 
```bash
npm install express @solana/web3.js body-parser
```


# Iniciar rede de teste Solana
```bash
solana-test-validator
```


# Criar par de chaves de cada Player
```bash
node InicializarWallets.js
```

Irá criar as chaves utilizadas pelo jogo

![image](https://github.com/user-attachments/assets/1b64bef2-5436-46e7-bf93-bdd4a77d6489)

# Abastecer os players via Airdrop:

![image](https://github.com/user-attachments/assets/0e6040de-1c69-42b2-b291-49f7601d32c4)

# Rodar servidor Web3
Este irá criar o servidor de comunicação entre o Banco do Jogo e o RPC Local
```bash
node server.js
```
![image](https://github.com/user-attachments/assets/7f665948-faef-4ea9-8655-cdc7d15978c2)

# Log de evidenciando Jogador 2 comprando propriedade 2 do Banco:

![image](https://github.com/user-attachments/assets/0c57b9c4-15a3-4600-b1a4-45e7319ab51b)

Transação realizada na Blockchain:

![image](https://github.com/user-attachments/assets/1e6bf0fd-356a-4a04-8422-5b0b568809d9)

# Log evidenciando o Jogador 2 pagando aluguel ao Jogador 1 dono da atual propriedade:

![image](https://github.com/user-attachments/assets/d17bccab-681e-4087-a92b-968e81281731)

Transação realizada na Blockchain:

![image](https://github.com/user-attachments/assets/70753afb-82f9-4f4c-b8c1-99f351303235)



# ============== SIMULADOR BASE (Sem Blockchain): ==============

# Diagrama de Classes

![image](https://github.com/user-attachments/assets/c19e3854-118f-4ae9-a5f8-7531c7032149)


# SIMULADOR

Rodadas e Jogos personalizaveis:

![image](https://github.com/user-attachments/assets/72e65e8c-9db4-44cc-9929-6c9273b3dac6)


Import do gameConfig.txt

![image](https://github.com/user-attachments/assets/b32736ab-12b8-4448-a302-d23d873c7549)


Tabuleiro da Simulação:

![image](https://github.com/user-attachments/assets/1ffa5b34-413d-47ef-99fb-b48af36214ec)


Logs das Jogadas:

![image](https://github.com/user-attachments/assets/95c493c8-72b2-4110-8212-466bc68692dc)


Log da finalização do Jogo

![image](https://github.com/user-attachments/assets/f1a2b70b-5512-4d88-a2ed-91be5bf59f01)

Estatísticas (40 casas alternando uma vazia e outra com propriedade)

![image](https://github.com/user-attachments/assets/bd89a000-bac4-4969-bd06-2764e8c8bc95)

![image](https://github.com/user-attachments/assets/e92d1b65-97ce-4da7-a460-2c9721f969ce)

Estatísticas (20 casas todas propriedades)

Para alterar o tabuleiro para apenas 20 casas e todas sendo propriedades, foi incluso o metodo de importação reduzida:

![image](https://github.com/user-attachments/assets/5e80df26-cbe5-42de-94f2-1d3064198c6d)

O jogo ficou mais curto:

![image](https://github.com/user-attachments/assets/0a4216c8-d06f-4eb4-a8ed-2c9792343124)

![image](https://github.com/user-attachments/assets/69f60a0d-b5c1-4029-8f28-08add052d4d9)

Alterando para 60 casas, alternando entre 2 vazias e 1 propriedade

![image](https://github.com/user-attachments/assets/159f1b08-1d7d-40e2-a7e9-b5bdee3f9cc3)

![image](https://github.com/user-attachments/assets/92a37216-4ba6-42e2-a1ab-e362ed23b08c)



#CONCLUSÃO

Com 40 blocos as chances são iguais para os perfis, mas quando o jogo tem 20 ou 60 blocos o Exigente é o que menos vence e o Cauteloso quem mais vence

