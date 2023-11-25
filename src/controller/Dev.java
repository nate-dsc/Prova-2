package controller;
public class Dev extends Astro {

	//Construtor:
	Dev(Grade grade)
	{
		this.grade = grade;
		this.coord.aleatoria();
		while(grade.checar(this.coord) == true)
		{
			this.coord.aleatoria();
		}
		this.grade.reinvidicar(this);
	}
	
	//Metodo para interação com planetas:
	void checarColisao(Planeta p)
	{
		if(this.existe() && p.existe() && this.distanciaEuclidiana2(p) == 0)
		{
			p.aumentarVelocidade();
			p.registrarColisaoDev();
			this.destruir();
			System.out.println("\033[42;1m + \033[0m Um dev pousou no planeta " + p.nome + "!");
			p.grade.reinvidicar(p);
		}
	}
	
	//Metodo para passar o tipo a grade:
	public char tipo()
	{
		return '+';
	}
	
}
