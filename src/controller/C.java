package controller;
public class C extends Planeta {

	C(Grade grade)
	{
		this.grade = grade;
		this.nome = "C";
		this.velocidade = 10;
		this.numeroDeOrbita = 7;
		this.numeroDeTrajetoria = 8*7;
		this.posicaoQuadrangular = 7;
		this.numeroDeVoltas = 0;
		this.horasPorInstante = 0.1f;
		this.quadrangularParaCoordenada();
		this.grade.reinvidicar(this);
	}
	
	public void info()
	{
		System.out.println("\033[38;5;213mSobre: C eh uma linguagem estruturada, procedural e fortemente tipada.\n"
				+ "C eh muito utilizada por sua performance e \"proximidade\" ao hardware.\n"
				+ "Apesar da idade (criada em 1972), C eh uma das linguagens mais populares ate hoje.\n"
				+ "C tambem influenciou o surgimento das linguagens Java e C++.\033[0m");
	}
}
