package controller;
public class CMaisMais extends Planeta {

	CMaisMais(Grade grade)
	{
		this.grade = grade;
		this.nome = "C++";
		this.velocidade = 2;
		this.numeroDeOrbita = 6;
		this.numeroDeTrajetoria = 8*6;
		this.posicaoQuadrangular = 6;
		this.numeroDeVoltas = 0;
		this.horasPorInstante = 0.5f;
		this.quadrangularParaCoordenada();
		this.grade.reinvidicar(this);
	}
	
	public void info()
	{
		System.out.println("\033[38;5;213mSobre: C++ eh uma linguagem multiparadigma.\n"
				+ "C++ comecou como uma extensao do C ate virar sua propria linguagem.\n"
				+ "C++ eh muito utilizada por conta do seu acesso a memoria, ao estilo do C.\n"
				+ "Diferente do C, C++ permite a orientacao a objetos.\n"
				+ "Assim, C++ tambem eh usada para aplicacoes como jogos e sistemas operacionais\033[0m");
	}
	
	public char tipo()
	{
		return '6';
	}
}
