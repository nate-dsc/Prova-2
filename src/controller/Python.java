package controller;
public class Python extends Planeta {

	Python(Grade grade)
	{
		this.grade = grade;
		this.nome = "Python";
		this.velocidade = 4;
		this.numeroDeOrbita = 1;
		this.numeroDeTrajetoria = 8*1;
		this.posicaoQuadrangular = 1;
		this.numeroDeVoltas = 0;
		this.horasPorInstante = 24;
		this.quadrangularParaCoordenada();
		this.grade.reinvidicar(this);
	}
	
	public void info()
	{
		System.out.println("\033[38;5;213mSobre: Python eh uma linguagem focada na legibilidade.\n"
				+ "Em 2022, ultrapassou C como a linguagem mais popular.\n"
				+ "Python eh bastante usada para ensinar programacao e tambem aplicar algoritmos de Machine Learning.\n"
				+ "No entanto, Python nem sempre eh bem perfomante.\n"
				+ "Apesar da popularidade recente, Python ja tem 32 anos!.\033[0m");
	}
	
	public char tipo()
	{
		return '1';
	}
}
