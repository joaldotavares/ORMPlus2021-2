package br.com.ucsal;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class IniciarInterface extends JFrame {

    private final int B_HEIGHT = 580;
    private final int B_WIDTH = 250;
    private String texto;
    Main main = new Main();

    private JButton ok;
    private TextField arquivo = new TextField();

    public IniciarInterface() {
        configurarTela();
        add(campos());

    }

    private void configurarTela() {
        setSize(B_HEIGHT, B_WIDTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Compiler");

        ok = new JButton("OK");
        ok.setBounds(385, 70, 70, 25);
        add(ok);

        ok.addActionListener( e -> {
            texto = arquivo.getText();
            try {
                main.IniciarPrograma(texto);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private JComponent campos() {
        JPanel painelMontaTela = new JPanel();
        painelMontaTela.setLayout(null);

        JLabel label01 = new JLabel();
        label01.setText("Digite o nome do arquivo que deseja analisar: ");
        painelMontaTela.add(label01);
        label01.setBounds(35, 30, 280, 25);
        painelMontaTela.add(arquivo);
        arquivo.setBounds(35, 70, 300, 25);
        add(arquivo);

        getContentPane().add(label01);

        return painelMontaTela;
    }
}
