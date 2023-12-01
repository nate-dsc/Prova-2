package view;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFrame;
import controller.Sistema;

public class Janela extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Sistema sistema;
	private PainelGrade painelGrade;
	private PainelBotoes painelBotoes;
	
	public Janela(Sistema sistema)
	{
		this.sistema = sistema;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("JavaLar 2");
		setSize(1050, 750);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		painelGrade = new PainelGrade(sistema.getGrade());
		painelBotoes = new PainelBotoes(this);
		add(painelGrade, BorderLayout.CENTER);
		add(painelBotoes, BorderLayout.EAST);
		setVisible(true);
		pack();
	}

	public PainelGrade getPainelGrade()
	{
		return this.painelGrade;
	}
	
	public void janelaProximoInstante()
	{
		sistema.sistemaProximoInstante();
		this.remove(painelGrade);
		painelGrade.atualizarPainel();
		this.repaint();
		this.revalidate();
		this.add(painelGrade, BorderLayout.CENTER);
		this.pack();
	}
	
	public void janelaPassarArquivo(File arquivo)
	{
		sistema.sistemaPassarArquivo(arquivo);
	}
	
	public void janelaGravarRelatorio()
	{
		sistema.sistemaGravarRelatorio();
	}
	
	public void janelaProcessarDados()
	{
		sistema.sistemaProcessarDados();
	}
	
	public void janelaGravarArquivoSaida()
	{
		sistema.sistemaGravarArquivoSaida();
	}
	
}
