package Model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.ArrayList;

public class GerenciadorDeMusicas {

    private static MediaPlayer mediaPlayer;

    public static ArrayList<Musica> listarMusicas(String caminho) {
        ArrayList<Musica> lista = new ArrayList<>();
        File pasta = new File(caminho);

        File[] arquivos = pasta.listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3"));
        if (arquivos != null) {
            for (File arquivo : arquivos) {
                String nome = arquivo.getName();
                lista.add(new Musica(nome, "Desconhecido", "Desconhecido", "00:00"));
            }
        }
        return lista;
    }

    public static MediaPlayer tocarMusica(String caminho) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        Media media = new Media(new File(caminho).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

        return mediaPlayer;
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
