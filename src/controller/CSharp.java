package controller;
public class CSharp extends Planeta {

	CSharp(Grade grade)
	{
		this.grade = grade;
		this.nome = "C#";
		this.velocidade = 1;
		this.numeroDeOrbita = 5;
		this.numeroDeTrajetoria = 8*5;
		this.posicaoQuadrangular = 5;
		this.numeroDeVoltas = 0;
		this.horasPorInstante = 4;
		this.quadrangularParaCoordenada();
		this.grade.reinvidicar(this);
	}
	
	public void info()
	{
		System.out.println("\033[38;5;213mSobre: C# foi criada pela Microsoft para ser uma linguagem OO de alto nivel.\n"
				+ "Inicialmente, C# foi muito comparada ao Java, como uma copia inferior.\n"
				+ "Apesar disso, C# persistiu e foi evoluindo ao longo dos anos.\n"
				+ "Mesmo sendo de alto nivel, C# permite acessar a memoria como seus irmaos C e C++.\n"
				+ "Ainda sim, C# nao eh tao popular quanto seus irmaos.\033[0m");
	}
}
