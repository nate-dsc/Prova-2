package controller;
public class Bug extends Astro {

	//Construtor:
	Bug(Grade grade)
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
	public void checarColisao(Planeta p)
	{
		if(this.existe() && p.existe() && this.distanciaEuclidiana2(p) == 0)
		{
				p.diminuirVelocidade();
				p.registrarColisaoBug();
				this.destruir();
				System.out.println("\033[41;1m - \033[0m Um bug atingiu o planeta " + p.nome + "!");
				p.grade.reinvidicar(p);
		}
		if(p.velocidade == 0 && p.existe())
		{
			p.destruir();
			System.out.println("\033[41;1m >  O planeta " + p.nome + " foi destruido!\033[0m");
		}
	}
	
	//Metodo para passar o tipo a grade:
	public char tipo()
	{
		return '-';
	}

}
