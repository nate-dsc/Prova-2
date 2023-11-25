package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Grade;
import controller.Sistema;

public class Janela extends JFrame {

	Sistema sistema;
	
	PainelGrade painelGrade;
	PainelBotoes painelBotoes;
	
	public Janela(Sistema sistema)
	{
		this.sistema = sistema;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 800);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setLayout(new BorderLayout(0, 0));
		painelGrade = new PainelGrade(sistema.getGrade());
		painelBotoes = new PainelBotoes();
		add(painelGrade, BorderLayout.WEST);
		add(painelBotoes, BorderLayout.EAST);
	}

	
	
}
