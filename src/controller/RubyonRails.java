package controller;
public class RubyonRails extends Planeta {

	RubyonRails(Grade grade)
	{
		this.grade = grade;
		this.nome = "Ruby on Rails";
		this.velocidade = 2;
		this.numeroDeOrbita = 3;
		this.numeroDeTrajetoria = 8*3;
		this.posicaoQuadrangular = 3;
		this.numeroDeVoltas = 0;
		this.horasPorInstante = 48;
		this.quadrangularParaCoordenada();
		this.grade.reinvidicar(this);
	}
	
	public void info()
	{
		System.out.println("\033[38;5;213mSobre: Ruby on Rails nao eh uma linguagem, mas um framework na linguagem Ruby.\n"
				+ "Ruby on Rails eh focado no lado dos servidores no desenvolvimento web.\n"
				+ "O framework facilita e incentiva o uso de padroes da industria, como JSON para transferencia de dados.\n"
				+ "Apesar de relativamente novo (19 anos), Ruby on Rails ja demonstra influencia sobre varios outros frameworks.\033[0m");
	}
	
	public char tipo()
	{
		return '3';
	}
}
