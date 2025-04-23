package Model;

import java.io.File;
import java.util.ArrayList;

public class GerenciadorDeMusicas {


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
}
