package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import controller.Grade;

public class PainelGrade extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Grade grade;
	PainelMenor[][] malha = new PainelMenor[15][15];
	char[][] tipos;
	
	public PainelGrade(Grade grade)
	{
		this.grade = grade;
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(750, 750));
		setLayout(new GridLayout(15, 15, 0, 0));
		criarPainel();
		atualizarPainel();
	}
	
	private void criarPainel()
	{
		for(int linha = 0; linha < 15; linha++)
		{
			for(int coluna = 0; coluna < 15; coluna++)
			{
				malha[linha][coluna] = new PainelMenor();
				add(malha[linha][coluna]);
			}
		}
	}
	
	public void atualizarPainel()
	{
		tipos = grade.getTipos();
		for(int coluna = 14; coluna >= 0; coluna--)
		{
			for(int linha = 0; linha < 15; linha++)
			{
				malha[linha][14 - coluna].atualizarPainelMenor(tipos[14 - coluna][14 - linha]);
			}
		}
	}
}
