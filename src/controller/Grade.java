package controller;
public class Grade {

	//Dados da grade:
	private boolean[][] ocupacao = new boolean[15][15];
	private char[][] tipos = new char[15][15];
	
	//Dados do relatório
	int[] bugs_q = new int[4];
	int[] devs_q = new int[4];
	
	Grade()
	{
		for(int x = 0; x < 15; x++)
		{
			for(int y = 0; y < 15; y++)
			{
				ocupacao[x][y] = false;
				tipos[x][y] = 'V';
			}
		}
		ocupacao[7][7] = true;
		tipos[7][7] = 'J';
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
	}
	
	public void reinvidicar(Astro outro)
	{
		if(this.espacosLivres() > 0)
		{
			if(ocupacao[outro.coord.getX() - 1][outro.coord.getY() - 1] == false)
			{
				ocupacao[outro.coord.getX() - 1][outro.coord.getY() - 1] = true;
				tipos[outro.coord.getX() - 1][outro.coord.getY() - 1] = outro.tipo();
				if(outro.tipo() == '-')
				{
					if(outro.coord.getX() < 7 && outro.coord.getY() >= 7)
					{
						bugs_q[0]++;
					} else if(outro.coord.getX() >= 7 && outro.coord.getY() > 7)
					{
						bugs_q[1]++;
					} else if(outro.coord.getX() > 7 && outro.coord.getY() <= 7)
					{
						bugs_q[3]++;
					} else if(outro.coord.getX() <= 7 && outro.coord.getY() < 7)
					{
						bugs_q[2]++;
					}
				} else if(outro.tipo() == '+')
				{
					if(outro.coord.getX() < 7 && outro.coord.getY() >= 7)
					{
						devs_q[0]++;
					} else if(outro.coord.getX() >= 7 && outro.coord.getY() > 7)
					{
						devs_q[1]++;
					} else if(outro.coord.getX() > 7 && outro.coord.getY() <= 7)
					{
						devs_q[3]++;
					} else if(outro.coord.getX() <= 7 && outro.coord.getY() < 7)
					{
						devs_q[2]++;
					}
				}
			}
		}
	}

	//Metodos usados pelo sistema
	public int espacosLivres()
	{
		return (215 - this.qtdOcupada());
	}
	
	private int qtdOcupada()
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
	
	//Metodo para passar info para a janela
	public char[][] getTipos()
	{
		return tipos;
	}
	
	//Metodo para passar info dos quadrantes
	public int[] getBugsQ()
	{
		return this.bugs_q;
	}
	
	public int[] getDevsQ()
	{
		return this.devs_q;
	}
	
}
