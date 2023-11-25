package controller;
public abstract class Astro {

	//Caracteristicas comuns a planetas, bugs e devs:
	protected Coord coord = new Coord();
	protected boolean existencia = true;
	protected Grade grade;
	
	//Metodo para destruir um astro:
	protected void destruir()
	{
		this.existencia = false;
		this.grade.limpar(this.coord);
	}
	
	//Metodos para passar a existencia dos astros:
	protected boolean existe()
	{
		return this.existencia;
	}
	
	//Metodo abstrato para bugs, devs e planetas passarem seus identificadores a grade:
	protected abstract char tipo();
	
	//Metodos para o calculo de distancias:
	protected int distanciaEuclidiana2(Astro outro)
	{
		int a, b;
		a = (this.coord.getX() - outro.coord.getX())*(this.coord.getX() - outro.coord.getX());
		b = (this.coord.getY() - outro.coord.getY())*(this.coord.getY() - outro.coord.getY());
		return (a + b);
	}
	
	protected int distanciaFischer(Astro outro)
	{
		int a, b;
		a = Math.abs(this.coord.getX() - outro.coord.getX()) + 1;
		b = Math.abs(this.coord.getY() - outro.coord.getY()) + 1;
		return (a*b);
	}
	
	//Metodo para calculo de distancia usado na questão bonus:
	public double distanciaEuclidiana(double x, double y)
	{
		double a, b;
		a = (this.coord.getX() - x)*(this.coord.getX() - x);
		b = (this.coord.getY() - y)*(this.coord.getY() - y);
		return Math.sqrt(a + b);
	}
	
}
