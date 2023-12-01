package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PainelBotoes extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Janela janela;
	
	PainelBotoes(Janela janela)
	{
		this.janela = janela;
		setPreferredSize(new Dimension(300, 750));;
		setBackground(Color.BLACK);
		add(new BotaoProximoInstante());
		add(new BotaoNovoArquivoEntrada());
		add(new BotaoGravarRelatorio());
		add(new BotaoDadosParticipantes());
		add(new BotaoGravarArquivoSaida());
	}
	
	private class BotaoProximoInstante extends JButton implements ActionListener
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		BotaoProximoInstante()
		{
			setText("Processar próximo instante");
			setPreferredSize(new Dimension(280,40));
			addActionListener(this);
			setFocusable(false);
		}

		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == this)
			{
				janela.janelaProximoInstante();
			}
		}
	}
	
	private class BotaoNovoArquivoEntrada extends JButton implements ActionListener
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		BotaoNovoArquivoEntrada()
		{
			setText("Ler novo arquivo de entrada");
			setPreferredSize(new Dimension(280,40));
			addActionListener(this);
			setFocusable(false);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == this)
			{
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filtro = new FileNameExtensionFilter(".txt, .csv", "txt", "csv");
				chooser.setFileFilter(filtro);
				int retorno = chooser.showOpenDialog(this);
				if(retorno == JFileChooser.APPROVE_OPTION)
				{
					janela.janelaPassarArquivo(chooser.getSelectedFile());
				}
			}
		}
	}
	
	private class BotaoGravarRelatorio extends JButton implements ActionListener
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		BotaoGravarRelatorio()
		{
			setText("Gravar relatório");
			setPreferredSize(new Dimension(280,40));
			addActionListener(this);
			setFocusable(false);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == this)
			{
				janela.janelaGravarRelatorio();
			}
		}
	}
	
	private class BotaoDadosParticipantes extends JButton implements ActionListener
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		BotaoDadosParticipantes()
		{
			setText("Ler dados de outros participantes");
			setPreferredSize(new Dimension(280,40));
			addActionListener(this);
			setFocusable(false);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == this)
			{
				janela.janelaProcessarDados();
			}
		}
	}
	
	private class BotaoGravarArquivoSaida extends JButton implements ActionListener
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		BotaoGravarArquivoSaida()
		{
			setText("Gravar arquivo de saída");
			setPreferredSize(new Dimension(280,40));
			addActionListener(this);
			setFocusable(false);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == this)
			{
				janela.janelaGravarArquivoSaida();
			}
		}
	}
}
