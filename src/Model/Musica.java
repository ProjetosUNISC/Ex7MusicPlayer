package Model;

public class Musica {

    private String nome;
    private String artista;
    private String album;
    private String duracao;

    public Musica(String nome, String artista, String album, String duracao) {
        this.nome = nome;
        this.artista = artista;
        this.album = album;
        this.duracao = duracao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }
}
