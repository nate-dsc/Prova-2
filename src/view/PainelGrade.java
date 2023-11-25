package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import controller.Grade;

public class PainelGrade extends JPanel {

	PainelMenor[][] grade;
	int[][] ints;
	
	public PainelGrade(Grade gradeSis)
	{
		setBackground(Color.RED);
		grade = new PainelMenor[15][15];
		ints = gradeSis.passarInts();
		setSize(800, 800);
		setLayout(new GridLayout(15, 15, 5, 5));
		criarPainel();
		atualizarPainel();
	}
	
	private void criarPainel()
	{
		for(int linha = 0; linha < 15; linha++)
		{
			for(int coluna = 0; coluna < 15; coluna++)
			{
				grade[linha][coluna] = new PainelMenor(ints[linha][coluna]);
				add(grade[linha][coluna]);
			}
		}
	}
	
	private void atualizarPainel()
	{
		setVisible(false);
		for(int linha = 0; linha < 15; linha++)
		{
			for(int coluna = 0; coluna < 15; coluna++)
			{
				grade[linha][coluna].atualizarPainelMenor(ints[linha][coluna]);
			}
		}
		setVisible(true);
	}
}
