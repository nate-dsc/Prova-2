package controller;
import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {

	//Registros:
	private int tempoTotal = 0;
	private int tempoInserido = 0;
	private int numeroRodadas = 0;
	private int numeroBugsInseridos = 0;
	private int numeroBugsTotais = 0;
	private int numeroDevsInseridos = 0;
	private int numeroDevsTotais = 0;
	private String alinhamento = new String();
	
	//ArrayList dos planetas:
	ArrayList <Planeta> planetaList = new ArrayList<Planeta>();
	
	//Construtor:
	public Sistema()
	{
		//Criando os planetas:
		Planeta C = new C(grade);
		planetaList.add(C);
		Planeta CMaisMais = new CMaisMais(grade);
		planetaList.add(CMaisMais);
		Planeta CSharp = new CSharp(grade);
		planetaList.add(CSharp);
		Planeta PHP = new PHP(grade);
		planetaList.add(PHP);
		Planeta RubyonRails = new RubyonRails(grade);
		planetaList.add(RubyonRails);
		Planeta JavaScript = new JavaScript(grade);
		planetaList.add(JavaScript);
		Planeta Python = new Python(grade);
		planetaList.add(Python);
	}

	//Criando a grade:
	Grade grade = new Grade();
	
	public Grade getGrade()
	{
		return grade;
	}
	
	//Criando o scanner:
	Scanner scan = new Scanner(System.in);
	
	//Criando bugs:
	ArrayList <Bug> bugList = new ArrayList<Bug>();
	
	public void criarBugs()
	{
		do
		{
			System.out.println("Numero de bugs: ");
			numeroBugsInseridos = scan.nextInt();
			if(numeroBugsInseridos > grade.espacosLivres())
			{
				System.out.println("Voce inseriu uma quantidade de bugs maior que a capacidade disponivel.");
				System.out.println("Tente novamente.");
				System.out.println("Capacidade disponivel: " + grade.espacosLivres());
			}
		}while(numeroBugsInseridos > grade.espacosLivres());
		
		System.out.println("Os bugs foram criados nas seguintes posicoes: ");
		int quebraLinha = 1;
		for(int i = 0; i < numeroBugsInseridos; i++)
		{
			Bug bug = new Bug(grade);
			adicionarBugsTotais();
			bug.coord.mostrarCoord();
			if(quebraLinha == 10)
			{
				System.out.println("");
				quebraLinha = 0;
			}
			bugList.add(bug);
			quebraLinha++;
		}
		System.out.println("");
	}

	//Criando devs:
	ArrayList <Dev> devList = new ArrayList<Dev>();
		
	public void criarDevs()
	{
		do
		{
			System.out.println("Numero de devs: ");
			numeroDevsInseridos = scan.nextInt();
			if(numeroDevsInseridos > grade.espacosLivres())
			{
				System.out.println("Voce inseriu uma quantidade de devs maior que a capacidade disponivel.");
				System.out.println("Tente novamente.");
				System.out.println("Capacidade disponivel: " + grade.espacosLivres());
			}
		}while(numeroDevsInseridos > grade.espacosLivres());
		
		System.out.println("Os devs foram criados nas seguintes posicoes: ");
		int quebraLinha = 1;
		for(int i = 0; i < numeroDevsInseridos; i++)
		{
			Dev dev = new Dev(grade);
			adicionarDevsTotais();
			dev.coord.mostrarCoord();
			if(quebraLinha == 10)
			{
				System.out.println("");
				quebraLinha = 0;
			}
			devList.add(dev);
			quebraLinha++;
		}
		System.out.println("");
	}

	//Receber e registrar o tempo inserido:
	private void registrarTempoTotal()
	{
		this.tempoTotal += this.tempoInserido;
	}
	
	public void pedirTempo()
	{
		do
		{
			tempoInserido = 0;
			System.out.println("Tempo: ");
			tempoInserido = scan.nextInt();
			System.out.println("");
			if(tempoInserido < 0)
			{
				System.out.println("Ainda nao inventamos uma maquina de viajar no tempo.");
				System.out.println("Insira um valor positivo.");
			}
		}while(tempoInserido < 0);
		registrarTempoTotal();
	}

	//Executar rodada:
	public void executarRodada()
	{
		System.out.println("\033[33;1m=== EVENTOS DA RODADA No " + (numeroRodadas+1) + " ===\033[0m");
		//Rotação e translação de cada planeta:
		for(Planeta p : planetaList)
		{
			p.rotacaoEtranslacao(tempoInserido);
			p.atualizarVelocidadeMedia(tempoTotal);
			if(p.existe())
			{
				System.out.print("\033[46;1m > \033[0m O planeta " + p.nome + " parou na posicao ");
				p.coord.mostrarCoord();
				System.out.println("");
			}
		}
		
		System.out.println("");
		
		//Checagem de colisões:
		for(Bug b : bugList)
		{
			for(Planeta p : planetaList)
			{
				b.checarColisao(p);
			}
		}
		
		for(Dev d : devList)
		{
			for(Planeta p : planetaList)
			{
				d.checarColisao(p);
			}
		}
		
		System.out.println("");
		
		//Removendo bugs e devs que colidiram:
		for(int i = 0; i < bugList.size(); i++ )
		{
			if(bugList.get(i).existe() == false)
				bugList.remove(i);
		}
		
		for(int i = 0; i < devList.size(); i++ )
		{
			if(devList.get(i).existe() == false)
				devList.remove(i);
		}
	}

	//Mostrar grade:
	public void mostrarGrade()
	{
		System.out.println("\033[33;1m=== GRADE AO FIM DA RODADA No " + (numeroRodadas+1) + " ===\033[0m");
		System.out.println("P = Planeta; - = Bug; + = Dev; J = Java.");
		grade.mostrarOcupacao();
	}
	
	//Mostrar posição Norte/Equador/Sul e se houve algum alinhamento:
	public void mostrarAlinhamentos()
	{
		checarAlinhamento();
		System.out.println("\033[33;1m=== POSICAO RELATIVA E ALINHAMENTO AO FIM DA RODADA No "
							+ (numeroRodadas+1) + " ===\033[0m");
		System.out.println("Numero de planetas ao norte de Java: " + grade.qtdNorte());
		System.out.println("Numero de planetas no equador de Java: " + grade.qtdEquador());
		System.out.println("Numero de planetas ao sul de Java: " + grade.qtdSul());
		System.out.println("");
		System.out.println("Alinhamento: " + alinhamento);
		System.out.println("");
	}
	
	//Mostrar distância planeta a planeta:
	public void mostrarDistancias()
	{
		System.out.println("\033[33;1m=== DISTANCIAS ENTRE PLANETAS ===\033[0m");
		
		for(int indice = 0; indice < planetaList.size(); indice++)
		{
			for(int j = indice + 1; j < planetaList.size(); j++)
			{
				if(planetaList.get(indice).existe() && planetaList.get(j).existe())
				{
					System.out.println("Distancia Euclidiana^2 entre " + planetaList.get(indice).getNome() + " e "
										+ planetaList.get(j).getNome() + ": "
										+ planetaList.get(indice).distanciaEuclidiana2(planetaList.get(j))
										+ " unidades^2");
					System.out.println("Distancia Fischer entre " + planetaList.get(indice).getNome() + " e "
										+ planetaList.get(j).getNome() + ": "
										+ planetaList.get(indice).distanciaFischer(planetaList.get(j))
										+ " unidades^2");
					System.out.println("");
				}
			}
		}
	}
	
	//Mostrar dados de todos os planetas:
	public void mostrarDadosPlanetas()
	{
		System.out.println("\033[33;1m=== DADOS DE CADA PLANETA AO FIM DA RODADA No "
							+ (numeroRodadas+1) +" ===\033[0m");
		System.out.println("");
		for(Planeta p : planetaList)
		{
			p.mostrarDados();
		}
	}
	
	//Mostrar dados do jogo até o momento:
	public void mostrarDadosAteAgora()
	{
		System.out.println("\033[33;1m=== DADOS DA PARTIDA ATE O MOMENTO ===\033[0m");
		System.out.println("Bugs inseridos nessa rodada: " + numeroBugsInseridos);
		System.out.println("Bugs inseridos ate agora: " + numeroBugsTotais);
		System.out.println("Devs inseridos nessa rodada: " + numeroDevsInseridos);
		System.out.println("Devs inseridos ate agora: " + numeroDevsTotais);
		System.out.println("Planetas destruidos ate agora: " + getPlanetasDestruidos());
	}
		
	//Registrar a rodada
	public void registrarRodada()
	{
		this.numeroRodadas++;
	}
	
	//Mostrar relatorio completo da partida e dos planetas:
	public void mostrarDadosFinais()
	{
		System.out.println("");
		System.out.println("\033[33;1m=== DADOS FINAIS ===\033[0m");
		System.out.println("Tempo total passado em JavaLar: " + tempoTotal + " instantes");
		System.out.println("");
		for(Planeta p : planetaList)
		{
			p.relatorioCompletoPlaneta();
		}
	}
	
	//Metodos para adicionar ao total de bugs e devs inseridos:
	public void adicionarBugsTotais()
	{
		this.numeroBugsTotais++;
	}
	
	public void adicionarDevsTotais()
	{
		this.numeroDevsTotais++;
	}
	
	//Metodos para checar alinhamento:
	public void checarAlinhamento()
	{
		//Checar alinhamento na diagonal primaria:
		boolean diagPrimaria = true;
		for(Planeta p : planetaList)
		{
			if((p.coord.getX() + p.coord.getY() != 16) && p.existe())
					diagPrimaria = false;
		}
		//Checar alinhamento vertical:
		boolean vertical = true;
		for(Planeta p : planetaList)
		{
			if((p.coord.getX() != 8) && p.existe())
				vertical = false;
		}
		//Checar alinhamento na diagonal secundaria:
		boolean diagSecundaria = true;
		for(Planeta p : planetaList)
		{
			if((p.coord.getX() != p.coord.getY()) && p.existe())
				diagSecundaria = false;
		}
		//Mudar o valor da string alinhamento:
		if(diagPrimaria == true)
		{
			alinhamento = "Diagonal Primaria";
		}else if(vertical == true)
		{
			alinhamento = "Vertical";
		}else if(diagSecundaria == true)
		{
			alinhamento = "Diagonal Secundaria";
		}
		else
		{
			alinhamento = "Nao ha alinhamento";
		}
	}
	
	//Metodos adicionais:
	public int getPlanetasDestruidos()
	{
		int contador = 0;
		for(Planeta p : planetaList)
		{
			if(p.existencia == false)
			{
				contador++;
			}
		}
		return contador;
	}
	
	public int getNumeroDeRodadas()
	{
		return this.numeroRodadas;
	}

	//QUESTÃO BONUS!
	public void areaPlanetas()
	{
		//Achando um centro pro poligono:
		double xc = 0;
		double yc = 0;
		for(Planeta p : planetaList)
		{
			xc += p.coord.getX();
			yc += p.coord.getY();
		}
		xc = xc/7;
		yc = yc/7;
		
		//Calculando os angulos entre os vertices ligados ao centro e a horizontal:
		double ang;
		double[] angulos = new double[7];
		int[] indices = new int[7];
		for(Planeta p : planetaList)
		{
			if(p.coord.getY() > yc)
			{
				ang = Math.acos((p.coord.getX()-xc)/p.distanciaEuclidiana(xc, yc));
			}
			else
			{
				ang = Math.acos(-(p.coord.getX()-xc)/p.distanciaEuclidiana(xc, yc)) + Math.PI;
			}
			angulos[planetaList.indexOf(p)] = ang;
			indices[planetaList.indexOf(p)] = planetaList.indexOf(p);
		}
		
		//Ordenando os angulos e os indices dos planetas:
		double passadorAng;
		int passadorInd;
		for(int j = 0; j < angulos.length; j++)
		{
			for(int i = 0; i < angulos.length - 1; i++)
			{
				if(angulos[i] > angulos[i+1])
				{
					passadorAng = angulos[i];
					passadorInd = indices[i];
					
					angulos[i] = angulos[i+1];
					indices[i] = indices[i+1];
					
					angulos[i+1] = passadorAng;
					indices[i+1] = passadorInd;
				}
			}
		}
		
		//Calculando a area
		double area = 0;
		double diagPrimaria = 0;
		double diagSecundaria = 0;
		
		
		for(int i = 0; i < indices.length - 1; i++)
		{
			diagPrimaria += planetaList.get(indices[i]).coord.getX()*planetaList.get(indices[i+1]).coord.getY();
		}
		diagPrimaria += planetaList.get(indices[indices.length - 1]).coord.getX()*planetaList.get(indices[0]).coord.getY();
		
		for(int i = 0; i < indices.length - 1; i++)
		{
			diagSecundaria += planetaList.get(indices[i]).coord.getY()*planetaList.get(indices[i+1]).coord.getX();
		}
		diagSecundaria += planetaList.get(indices[indices.length - 1]).coord.getY()*planetaList.get(indices[0]).coord.getX();
		
		area = diagPrimaria - diagSecundaria;
		area = area/2;
		area = Math.abs(area);
		
		System.out.println("\033[46;1m === QUESTAO BONUS! === \033[0m");
		System.out.printf("Area do poligono formado pelos planetas: %.2f unidades de area%n", area);
		System.out.println("");
	}
	
}
