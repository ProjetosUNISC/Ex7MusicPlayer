package View;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

import Model.GerenciadorDeMusicas;
import Model.Musica;

public class FrmMusicPlayer extends JFrame {



        //paines
    private JPanel painelPrincipal;
    private JPanel painelListaMusic;
    private JPanel painelMusicButton;
    private JPanel painelMusicProgressBar;

        //tabela de lista
    private JTable tabelaMusicas;
    private JScrollPane scrollTabela;

        //botoes
    private JButton botaoPlay = new JButton("");
    private JButton botaoStop = new JButton("");
    private JButton botaoPause = new JButton("");
    // Carregar os ícones
    ImageIcon iconPlay = new ImageIcon("src/View/icons/play.png");
    ImageIcon iconPause = new ImageIcon("src/View/icons/pause.png");
    ImageIcon iconStop = new ImageIcon("src/View/icons/stop.png");


        //barra de progresso
    private JSlider barraProgresso = new JSlider();



    public FrmMusicPlayer() {

        super("Music Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);


        //organizacao paineis
        painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(Color.gray);

        painelListaMusic = new JPanel(new BorderLayout());
        painelListaMusic.setBackground(Color.gray);

        painelMusicButton = new JPanel();
        painelMusicButton.setLayout(new BoxLayout(painelMusicButton, BoxLayout.X_AXIS)); // define o layout
        painelMusicButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        painelMusicButton.setBackground(Color.gray);

        painelMusicProgressBar = new JPanel();
        painelMusicProgressBar.setLayout(new BoxLayout(painelMusicProgressBar, BoxLayout.X_AXIS));
        painelMusicProgressBar.setBackground(Color.gray);

        add(painelPrincipal, BorderLayout.CENTER);
        painelPrincipal.add(painelListaMusic, BorderLayout.NORTH);
        painelPrincipal.add(painelMusicButton, BorderLayout.CENTER);
        painelPrincipal.add(painelMusicProgressBar, BorderLayout.SOUTH);


        ///organizando a lista de musica
        // nome das colunas
        String [] colunas = {"Nome", "Album", "Artista", "Duração"};
        Object[][] dados = {};

        tabelaMusicas = new JTable(dados, colunas);
        scrollTabela = new JScrollPane(tabelaMusicas);
        tabelaMusicas.setToolTipText("Lista com as músicas disponíveis");

        painelListaMusic.add(scrollTabela, BorderLayout.CENTER);

        //carregar musicas
        ArrayList<Musica> musicas = GerenciadorDeMusicas.listarMusicas("src/Model/Musicas");

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[]{"Nome", "Album", "Artista", "Duração"});

        for (Musica m : musicas) {
            modelo.addRow(new Object[]{m.getNome(), m.getAlbum(), m.getArtista(), m.getDuracao()});
        }

        tabelaMusicas.setModel(modelo);

        //organizando os botoes no painel de botoes
        botaoPause.setFont(new Font("Arial", Font.BOLD, 20));
        botaoPlay.setFont(new Font("Arial", Font.BOLD, 20));
        botaoStop.setFont(new Font("Arial", Font.BOLD, 20));


        botaoPause.setIcon(iconPause);
        botaoPlay.setIcon(iconPlay);
        botaoStop.setIcon(iconStop);


        botaoPlay.setToolTipText("Toca a música selecionada");
        botaoPause.setToolTipText("Pausa a música");
        botaoStop.setToolTipText("Para a música completamente");


        painelMusicButton.add(Box.createHorizontalGlue());
        painelMusicButton.add(botaoPause);
        painelMusicButton.add(Box.createRigidArea(new Dimension(20, 0)));
        painelMusicButton.add(botaoPlay);
        painelMusicButton.add(Box.createRigidArea(new Dimension(20, 0)));
        painelMusicButton.add(botaoStop);
        painelMusicButton.add(Box.createHorizontalGlue());


        //organizando o painel da barra de progresso
        barraProgresso.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
        barraProgresso.setPreferredSize(new Dimension(700, 20));
        barraProgresso.setBackground(Color.gray);
        barraProgresso.setToolTipText("Mostra o andamento da música. Você pode arrastar para adiantar.");
        painelMusicProgressBar.add(Box.createHorizontalStrut(10));
        painelMusicProgressBar.add(barraProgresso);
        painelMusicProgressBar.add(Box.createHorizontalStrut(10));






        setVisible(true);
    }


}
