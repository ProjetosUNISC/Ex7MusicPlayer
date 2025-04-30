package View;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

import Model.GerenciadorDeMusicas;
import Model.Musica;



public class FrmMusicPlayer extends JFrame {


        //paineis
    private JPanel painelPrincipal;
    private JPanel painelListaMusic;
    private JPanel painelMusicButton;
    private JPanel painelMusicProgressBar;

        //parte da lista
    private JTable tabelaMusicas;
    private JScrollPane scrollTabela;

        //botoes
    private JButton botaoPlay = new JButton("");
    private JButton botaoStop = new JButton("");
    private JButton botaoPause = new JButton("");
        //barra progresso só para enfeite
    private JSlider barraProgresso = new JSlider();
        //icones
    private ImageIcon iconPlay = new ImageIcon("src/View/icons/play.png");
    private ImageIcon iconPause = new ImageIcon("src/View/icons/pause.png");
    private ImageIcon iconStop = new ImageIcon("src/View/icons/stop.png");

        //menus
    private JMenuBar menuBar;

    private JMenu menuArquivo;

    private JMenuItem Adicionar;
    private JMenuItem Excluir;



        //contrutor
    public FrmMusicPlayer() {

        super("Music Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);

        //inicia funções onde estão cada parte do componente
        inicializarComponentes();
        configurarLayout();
        carregarMusicas();
        configurarEventos();

        setVisible(true);
    }


    //função dos painéis
    private void inicializarComponentes() {


        painelPrincipal = new JPanel(new BorderLayout());
        painelListaMusic = new JPanel(new BorderLayout());
        painelMusicButton = new JPanel();
        painelMusicProgressBar = new JPanel();

        tabelaMusicas = new JTable();
        scrollTabela = new JScrollPane(tabelaMusicas);

        painelMusicButton.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        painelMusicProgressBar.setLayout(new BorderLayout());

        botaoPlay.setIcon(iconPlay);
        botaoPause.setIcon(iconPause);
        botaoStop.setIcon(iconStop);

            //mensagens de ajuda
        botaoPlay.setToolTipText("Tocar música selecionada");
        botaoPause.setToolTipText("Pausar (não disponível)");
        botaoStop.setToolTipText("Parar a música");
        tabelaMusicas.setToolTipText("Clique em uma música para tocar");

            //slider travado
        barraProgresso.setEnabled(false);
        barraProgresso.setToolTipText("Progresso da música (não disponível para ajuste)");
        barraProgresso.setValue(0);

            //menu
        menuBar = new JMenuBar();

        menuArquivo = new JMenu("Arquivo");

        Adicionar = new JMenuItem("Adicionar");
        Excluir = new JMenuItem("Excluir");

    }

        //configura painéis
    private void configurarLayout() {

            //configura
        add(menuBar,BorderLayout.NORTH);
        menuBar.add(menuArquivo);
        menuArquivo.add(Adicionar);
        menuArquivo.add(Excluir);


        add(painelPrincipal, BorderLayout.CENTER);

        painelPrincipal.add(painelListaMusic, BorderLayout.NORTH);
        painelPrincipal.add(painelMusicButton, BorderLayout.CENTER);
        painelPrincipal.add(painelMusicProgressBar, BorderLayout.SOUTH);

        painelListaMusic.add(scrollTabela, BorderLayout.CENTER);
        scrollTabela.setBackground(Color.LIGHT_GRAY);

        painelMusicButton.add(botaoPause);
        painelMusicButton.add(botaoPlay);
        painelMusicButton.add(botaoStop);
        painelMusicButton.setBackground(Color.GRAY);

        painelMusicProgressBar.add(barraProgresso, BorderLayout.CENTER);
    }

        //funcao musicas
    private void carregarMusicas() {
        //criar a lista num array usando a função do gerenciador
        ArrayList<Musica> musicas = GerenciadorDeMusicas.listarMusicas("src/Model/Musicas");


        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[]{"Nome"});

        for (Musica m : musicas) {
            modelo.addRow(new Object[]{m.getNome()});
        }

        tabelaMusicas.setModel(modelo);

        // Melhorar visual da tabela
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);
        tabelaMusicas.getColumnModel().getColumn(0).setCellRenderer(centro);
        tabelaMusicas.setFont(new Font("Arial", Font.PLAIN, 16));
        tabelaMusicas.setRowHeight(30);
    }

        //configurar eventos
    private void configurarEventos() {
        botaoPlay.addActionListener(e -> {

            int linha = tabelaMusicas.getSelectedRow();
            if (linha != -1) {
                String nomeArquivo = (String) tabelaMusicas.getValueAt(linha, 0);
                String caminho = "src/Model/Musicas/" + nomeArquivo;

                GerenciadorDeMusicas.tocarMusica(caminho);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma música para tocar.");
            }
        });

        botaoPause.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Não disponível com o modo atual.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        });

        botaoStop.addActionListener(e -> {
            GerenciadorDeMusicas.pararMusica();
            barraProgresso.setValue(0);
        });
    }
}
