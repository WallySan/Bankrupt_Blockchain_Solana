package bankrupt;

public class Player {

    private int ID;
    private int coins;
    private int posicao;
    private boolean ativo = true;
    private String nome;
    private IComportamento comportamento;

    //Conta Solana
    private String publicKey; // para backend web3
    private String keyFilePath; // local físico do arquivo JSON da chave 

    // Construtor
    public Player(int ID, IComportamento comp, String nom) {
        this.ID = ID;
        this.coins = 300;   // Valor inicial
        this.posicao = 0;   // Posição inicial
        this.comportamento = comp;
        this.nome = nom;

    }

    public String getNome() {
        return nome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // Getter e Setter para ID
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    // Getter e Setter para coins
    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    // Getter e Setter para posicao
    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    // Método utilitário: mover jogador
    public void mover(int casas) {
        this.posicao += casas;
    }

    // Método utilitário: alterar saldo
    public void adicionarMoedas(int valor) {
        this.coins += valor;
    }

    public void removerMoedas(int valor) {
        this.coins -= valor;
    }

    public boolean decideComprar(Propriedade prop) {
        int valorCompra = prop.getCustoVenda();
        int valorAluguel = prop.getValorAluguel();
        return comportamento.decideComprar(this.coins, valorAluguel, valorCompra);
    }

    
    //Consulta de chaves
    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getKeyFilePath() {
        return keyFilePath;
    }

    public void setKeyFilePath(String keyFilePath) {
        this.keyFilePath = keyFilePath;
    }

}
