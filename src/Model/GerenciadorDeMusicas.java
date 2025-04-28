package Model;

import javazoom.jl.player.Player;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class GerenciadorDeMusicas {

    private static Player player;

    public static void tocarMusica(String caminho) {
        try {
            if (player != null) {
                player.close(); // Fecha o que tava tocando antes
            }

            FileInputStream fis = new FileInputStream(caminho);
            player = new Player(fis);

            new Thread(() -> {
                try {
                    player.play();
                } catch (Exception e) {
                    System.out.println("Erro ao tocar música: " + e.getMessage());
                }
            }).start();
        } catch (Exception e) {
            System.out.println("Erro ao abrir arquivo: " + e.getMessage());
        }
    }

    public static void pararMusica() {
        if (player != null) {
            player.close();
        }
    }

    public static ArrayList<Musica> listarMusicas(String caminhoPasta) {
        ArrayList<Musica> lista = new ArrayList<>();
        File pasta = new File(caminhoPasta);

        File[] arquivos = pasta.listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3"));
        if (arquivos != null) {
            for (File arquivo : arquivos) {
                String nome = arquivo.getName();
                lista.add(new Musica(nome));
            }
        }
        return lista;
    }
}
