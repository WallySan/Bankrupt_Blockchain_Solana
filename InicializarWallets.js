const fs = require('fs');
const { Keypair } = require('@solana/web3.js');

function createWallet(filename) {
    const kp = Keypair.generate();
    fs.writeFileSync(`./wallets/${filename}.json`, JSON.stringify(Array.from(kp.secretKey)));
    console.log(`Wallet ${filename} criada: ${kp.publicKey.toString()}`);
}

createWallet("banco");
createWallet("player_1");
createWallet("player_2");
createWallet("player_3");
createWallet("player_4");
