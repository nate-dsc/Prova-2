package controller;
public class Grade {

	//Grade e construtor:
	private boolean[][] ocupacao = new boolean[15][15];
	private char[][] tipos = new char[15][15];
	private int[][] ints = new int[15][15];
	
	Grade()
	{
		for(int x = 0; x < 15; x++)
		{
			for(int y = 0; y < 15; y++)
			{
				ocupacao[x][y] = false;
				tipos[x][y] = 'V';
				ints[x][y] = 0;
			}
		}
		ocupacao[7][7] = true;
		tipos[7][7] = 'J';
		ints[7][7] = 0;
	}
	
	//Metodos para astros interagirem com a grade:
	public boolean checar(Coord coord)
	{
		return ocupacao[coord.getX() - 1][coord.getY() - 1];
	}
	
	public void limpar(Coord coord)
	{
		ocupacao[coord.getX() - 1][coord.getY() - 1] = false;
		tipos[coord.getX() - 1][coord.getY() - 1] = 'V';
		ints[coord.getX() - 1][coord.getY() - 1] = 0;
	}
	
	public void reinvidicar(Astro outro)
	{
		if(this.espacosLivres() > 0)
		{
			if(ocupacao[outro.coord.getX() - 1][outro.coord.getY() - 1] == false)
			{
				ocupacao[outro.coord.getX() - 1][outro.coord.getY() - 1] = true;
				tipos[outro.coord.getX() - 1][outro.coord.getY() - 1] = outro.tipo();
				if(outro.tipo() == 'P')
				{
					ints[outro.coord.getX() - 1][outro.coord.getY() - 1] = 3;
				} else if(outro.tipo() == 'D')
				{
					ints[outro.coord.getX() - 1][outro.coord.getY() - 1] = 2;
				} else if(outro.tipo() == 'B')
				{
					ints[outro.coord.getX() - 1][outro.coord.getY() - 1] = 1;
				}
			}
		}
	}

	//Metodo para passar informações da grade:
	public void mostrarOcupacao()
	{
		System.out.println("");
		for(int y = 14; y >= 0; y--)
		{
			System.out.printf(" %2d ", (y+1));
			for(int x = 0; x < 15; x++)
			{
				if(ocupacao[x][y] == true)
				{
					System.out.print("\033[47;1m\033[30;1m " + tipos[x][y] + " \033[0m");
				}
				else
				{
					System.out.print("\033[47;1m   \033[0m");
				}
			}
			System.out.println("");
		}
		System.out.println("^yx> 1  2  3  4  5  6  7  8  9  10 11 12 13 14 15");
		System.out.println("");
		System.out.println("Ocupacao: " + qtdOcupada() + "/215");
		System.out.println("");
	}

	public int espacosLivres()
	{
		return (215 - this.qtdOcupada());
	}
	
	public int qtdOcupada()
	{
		int contador = 0;
		for(int x = 0; x < 15; x++)
		{
			for(int y = 0; y < 15; y++)
			{
				if(this.ocupacao[x][y] == true)
					contador++;
			}
		}
		return contador;
	}
	
	public int[][] passarInts()
	{
		return ints;
	}
	
	//Metodos para passar posicoes relativas ao sistema:
	public int qtdNorte()
	{
		int contador = 0;
		for(int y = 8; y < 15; y++)
			for(int x = 0; x < 15; x++)
			{
				if(this.ocupacao[x][y] && this.tipos[x][y] == 'P')
					contador++;
			}
		return contador;
	}

	public int qtdEquador()
	{
		int contador = 0;
		for(int x = 0; x < 15; x++)
		{
			if(this.ocupacao[x][7] && this.tipos[x][7] == 'P')
				contador++;
		}
		return contador;
	}
	
	public int qtdSul()
	{
		int contador = 0;
		for(int y = 0; y < 7; y++)
			for(int x = 0; x < 15; x++)
			{
				if(this.ocupacao[x][y] && this.tipos[x][y] == 'P')
					contador++;
			}
		return contador;
	}
	
}
