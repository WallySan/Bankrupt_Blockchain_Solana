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

