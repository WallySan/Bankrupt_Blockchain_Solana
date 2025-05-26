# Projeto: Banco Imobiliário na Blockchain Solana

# 🎯 Este projeto é uma simulação de um jogo de Banco Imobiliário onde as transações financeiras entre jogadores são realizadas utilizando a blockchain da Solana.

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



# SIMULADOR BASE (Classe Banco fora da Blockchain):

# Diagrama de Classes

![image](https://github.com/user-attachments/assets/09a378f3-b520-403a-add6-0e3b217763c5)


# SIMULADOR

Rodadas e Jogos personalizaveis:

![image](https://github.com/user-attachments/assets/bae01adc-8e32-4299-a80a-30c132723661)


Import do gameConfig.txt

![image](https://github.com/user-attachments/assets/d9d814de-1c43-434a-8ddc-876b61d40094)


Tabuleiro da Simulação:

![image](https://github.com/user-attachments/assets/100aa1d8-9034-4bf7-86ae-a229fcf8966b)


Logs das Jogadas:

![image](https://github.com/user-attachments/assets/33594a59-8a6f-4fcc-b24a-5f6db49e72b8)


Log da finalização do Jogo

![image](https://github.com/user-attachments/assets/be466c00-a05c-4190-bac4-67ca02dbb815)

Estatísticas (40 casas alternando uma vazia e outra com propriedade)

![image](https://github.com/user-attachments/assets/ef6a8b15-fcd9-4f34-8b1a-82a350aba4e5)

![image](https://github.com/user-attachments/assets/7f24e03c-7b34-4c91-b151-2a8afe2c8808)

Estatísticas (20 casas todas propriedades)

Para alterar o tabuleiro para apenas 20 casas e todas sendo propriedades, foi incluso o metodo de importação reduzida:

![image](https://github.com/user-attachments/assets/3711cc64-4ee3-4282-bab3-11143c416ca7)

O jogo ficou mais curto:

![image](https://github.com/user-attachments/assets/28ba0a0e-f889-4357-9e75-cb4e2c13dde0)

![image](https://github.com/user-attachments/assets/c62210a9-2572-42cd-9ee9-111800ce8541)

Alterando para 60 casas, alternando entre 2 vazias e 1 propriedade

![image](https://github.com/user-attachments/assets/a5b6777e-c4b0-4a6d-bee5-d6c0ce1471d5)

![image](https://github.com/user-attachments/assets/7ebc19bc-2e97-4d45-995d-887208285557)

![image](https://github.com/user-attachments/assets/78833900-5d61-496e-95cf-f23073da4326)



#CONCLUSÃO

Com 40 blocos as chances são iguais para os perfis, mas quando o jogo tem 20 ou 60 blocos o Exigente é o que menos vence e o Cauteloso quem mais vence

