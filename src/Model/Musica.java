package Model;

public class Musica {

    private String nome;
    private String artista;
    private String album;
    private String duracao;

    public Musica(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
