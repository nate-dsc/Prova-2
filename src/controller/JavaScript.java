package controller;
public class JavaScript extends Planeta {

	JavaScript(Grade grade)
	{
		this.grade = grade;
		this.nome = "JavaScript";
		this.velocidade = 3;
		this.numeroDeOrbita = 2;
		this.numeroDeTrajetoria = 8*2;
		this.posicaoQuadrangular = 2;
		this.numeroDeVoltas = 0;
		this.horasPorInstante = 10;
		this.quadrangularParaCoordenada();
		this.grade.reinvidicar(this);
	}
	
	public void info()
	{
		System.out.println("\033[38;5;213mSobre: JavaScript e uma linguagem de alto nivel fracamente tipada.\n"
				+ "JavaScript e multiparadigma, apesar de ser mais comum seu uso OO.\n"
				+ "Apesar disso, C# persistiu e foi evoluindo ao longo dos anos.\n"
				+ "JavaScript e um dos pilares da Internet contemporanea.\n"
				+ "Isso pois foi criada para executar scripts no cliente (computador do usuario) de navegadores.\n"
				+ "Assim, servidores nao precisam fazer o processamento de tudo que acontece no site.\033[0m");
	}
	
	public char tipo()
	{
		return '2';
	}
}
