const express = require('express');
const bodyParser = require('body-parser');
const fs = require('fs');
const {
    Connection,
    Keypair,
    LAMPORTS_PER_SOL,
    PublicKey,
    Transaction,
    SystemProgram,
    sendAndConfirmTransaction
} = require('@solana/web3.js');

const app = express();
const port = 3000;
const connection = new Connection("http://localhost:8899");

app.use(bodyParser.json());

// === Funções Auxiliares ===

function loadKeypairFromFile(filePath) {
    const secretKey = Uint8Array.from(JSON.parse(fs.readFileSync(filePath)));
    return Keypair.fromSecretKey(secretKey);
}

async function transferSol(fromKeypair, toPubKey, amountSol) {
    const transaction = new Transaction().add(
        SystemProgram.transfer({
            fromPubkey: fromKeypair.publicKey,
            toPubkey: new PublicKey(toPubKey),
            lamports: amountSol * LAMPORTS_PER_SOL,
        })
    );

    const signature = await sendAndConfirmTransaction(connection, transaction, [fromKeypair]);
    return signature;
}

// === Rotas ===

// Banco paga para Player
app.post('/banco/pagar', async (req, res) => {
    const { receptorPubKey, valor } = req.body;
    const banco = loadKeypairFromFile('./wallets/banco.json');

    try {
        const signature = await transferSol(banco, receptorPubKey, valor);
        res.json({ success: true, signature });
    } catch (err) {
        res.status(500).json({ success: false, error: err.message });
    }
});

// Player paga para Banco
app.post('/banco/receber', async (req, res) => {
    const { pagadorId, valor } = req.body;
    const bancoPubKey = loadKeypairFromFile('./wallets/banco.json').publicKey;
    const pagador = loadKeypairFromFile(`./wallets/player_${pagadorId}.json`);

    try {
        const signature = await transferSol(pagador, bancoPubKey.toString(), valor);
        res.json({ success: true, signature });
    } catch (err) {
        res.status(500).json({ success: false, error: err.message });
    }
});

function loadKeypairFromFile(filePath) {
    const secretKey = Uint8Array.from(JSON.parse(fs.readFileSync(filePath)));
    return Keypair.fromSecretKey(secretKey);
}

function loadWallet(filePath) {
    return loadKeypairFromFile(filePath);
}




// Exemplo usando express
app.get("/banco/saldo", async (req, res) => {
  const wallet = loadWallet("wallets/banco.json");
  const balance = await connection.getBalance(wallet.publicKey);
//  res.send(balance.toString()); // Retorna em lamports (1 SOL = 1_000_000_000 lamports)
res.send((balance / 1_000_000_000).toString());
});

app.get("/saldo", async (req, res) => {
  const pubkey = new PublicKey(req.query.pubkey);
  const balance = await connection.getBalance(pubkey);
  res.send(balance.toString());
});

app.get('/publicKey', (req, res) => {
    const { playerId } = req.query;
    const walletPath = `./wallets/player_${playerId}.json`;

    try {
        const keypair = loadKeypairFromFile(walletPath);
        res.send(keypair.publicKey.toString());
    } catch (err) {
        res.status(404).json({ error: `Carteira do jogador ${playerId} não encontrada.` });
    }
});


// Player paga aluguel a outro Player
app.post('/aluguel', async (req, res) => {
    const { pagadorId, recebedorPubKey, valor } = req.body;
    const pagador = loadKeypairFromFile(`./wallets/player_${pagadorId}.json`);

    try {
        const signature = await transferSol(pagador, recebedorPubKey, valor);
        res.json({ success: true, signature });
    } catch (err) {
        res.status(500).json({ success: false, error: err.message });
    }
});

app.listen(port, () => {
    console.log(`Servidor Solana rodando em http://localhost:${port}`);
});
