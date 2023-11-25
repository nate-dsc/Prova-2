package controller;
public class PHP extends Planeta {

	PHP(Grade grade)
	{
		this.grade = grade;
		this.nome = "PHP";
		this.velocidade = 2;
		this.numeroDeOrbita = 4;
		this.numeroDeTrajetoria = 8*4;
		this.posicaoQuadrangular = 4;
		this.numeroDeVoltas = 0;
		this.horasPorInstante = 60;
		this.quadrangularParaCoordenada();
		this.grade.reinvidicar(this);
	}
	
	public void info()
	{
		System.out.println("\033[38;5;213mSobre: PHP eh uma linguagem de scripting lancada em 1995.\n"
				+ "Como JavaScript, por ser uma linguagem de scripting, PHP eh focado no desenvolvimento web.\n"
				+ "O mascote do PHP eh um elePHPant (elefante).\n"
				+ "Mas o PHP nao para apenas na web.\n"
				+ "A linguagem ja foi usada para criar GUIs avulsas e ate mesmo controlar drones.\033[0m");
	}
}
