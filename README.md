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



