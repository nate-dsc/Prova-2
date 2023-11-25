package view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PainelMenor extends JPanel {

	ImageIcon vazio = new ImageIcon("vazio.png");
	ImageIcon bug = new ImageIcon("bug-pa.png");
	ImageIcon dev = new ImageIcon("dev-pa.png");
	ImageIcon planeta = new ImageIcon("planeta-pa.png");
	
	ImageIcon imagem;
	JLabel label = new JLabel(imagem);
	
	PainelMenor(int id)
	{
		setBackground(Color.BLACK);
		setSize(48, 48);
		atualizarPainelMenor(id);
		add(label);
	}
	
	public void atualizarPainelMenor(int id)
	{
		if(id == 0)
		{
			imagem = vazio;
		} else if(id == 1)
		{
			imagem = bug;
		} else if(id == 2)
		{
			imagem = dev;
		} else if(id == 3)
		{
			imagem = planeta;
		}
	}
}
