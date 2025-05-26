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
