package controller;
public abstract class Planeta extends Astro {

	//Variaveis de um planeta:
	protected String nome;
	protected int numeroDeOrbita;
	protected int velocidade;
	protected int numeroDeTrajetoria;
	protected int posicaoQuadrangular;
	protected float horasPorInstante;
	
	protected int tempoTotal = 0;
	protected int unidadesAndadas = 0;
	protected int unidadesAndadasTotal = 0;
	protected int numeroDeVoltas = 0;
	protected float horasTotais = 0;
	protected float horasPassadas = 0;
	protected float diasTotais = 0;
	protected float diasPassados = 0;
	protected float velocidadeMedia = 0;
	
	protected int bugsRecebidos = 0;
	protected int devsRecebidos = 0;
	
	//Metodos para chamar translacao e rotacao:
	public void rotacaoEtranslacao(int tempo)
	{
		this.translacao(tempo);
		this.rotacao(tempo);
	}
	
	protected void translacao(int tempo)
	{
		if(this.existe())
		{
			this.tempoTotal += tempo;
			this.grade.limpar(this.coord);
			this.unidadesAndadas = tempo*this.velocidade;
			this.unidadesAndadasTotal += this.unidadesAndadas;
			this.numeroDeVoltas = this.unidadesAndadasTotal/this.numeroDeTrajetoria;
			this.posicaoQuadrangular = (this.posicaoQuadrangular + this.unidadesAndadas)%this.numeroDeTrajetoria;
			this.quadrangularParaCoordenada();
			this.grade.reinvidicar(this);
		}
			
	}
	
	protected void rotacao(int tempo)
	{
		this.diasPassados = 0;
		this.horasPassadas = 0;
		if(this.existe())
		{
			this.horasPassadas = tempo*this.horasPorInstante;
			this.horasTotais += this.horasPassadas;
			this.diasPassados = this.horasPassadas/24;
			this.diasTotais += this.horasPassadas/24;
		}
	}
	
	//Metodos para o funcionamento interno:
 	protected void quadrangularParaCoordenada()
	{
		//aresta de cima
		if(2*this.numeroDeOrbita > this.posicaoQuadrangular && this.posicaoQuadrangular >= 0)
		{
			this.coord.setCoord(8 + this.numeroDeOrbita - this.posicaoQuadrangular, 8 + this.numeroDeOrbita);
		}
		//aresta da esquerda
		if(4*this.numeroDeOrbita > this.posicaoQuadrangular && this.posicaoQuadrangular >= 2*this.numeroDeOrbita)
		{
			this.coord.setCoord(8 - this.numeroDeOrbita, 8 + 3*this.numeroDeOrbita - this.posicaoQuadrangular);
		}
		//aresta de baixo
		if(6*this.numeroDeOrbita > this.posicaoQuadrangular && this.posicaoQuadrangular >= 4*this.numeroDeOrbita)
		{
			this.coord.setCoord(8 - 5*this.numeroDeOrbita + this.posicaoQuadrangular, 8 - this.numeroDeOrbita);
		}
		//aresta da direita
		if(8*this.numeroDeOrbita > this.posicaoQuadrangular && this.posicaoQuadrangular >= 6*this.numeroDeOrbita)
		{
			this.coord.setCoord(8 + this.numeroDeOrbita, 8 - 7*this.numeroDeOrbita + this.posicaoQuadrangular);
		}
	}
 	
 	//Metodos para interagir com devs e bugs:
	public void aumentarVelocidade()
	{
		this.velocidade += 1;
	}
	
	public void diminuirVelocidade()
	{
		this.velocidade -= 1;
	}
	
	public void registrarColisaoBug()
	{
		this.bugsRecebidos++;
	}
	
	public void registrarColisaoDev()
	{
		this.devsRecebidos++;
	}
	
	//Metodos para passar informações do planeta:
	public String getNome()
	{
		return this.nome;
	}
	
	public char tipo()
	{
		return 'P';
	}
	
	
	//Metodo abstrato pra cada planeta implementar um pequeno paragrafo:
	public abstract void info();
	
	//Getters para dados do relatório:
	public int getBugsRecebidos()
	{
		return this.bugsRecebidos;
	}
	
	public int getDevsRecebidos()
	{
		return this.bugsRecebidos;
	}
	
	public int getVelocidadeMedia()
	{
		this.velocidadeMedia = this.unidadesAndadasTotal/this.tempoTotal;
		return (int) this.velocidadeMedia;
	}
	
	public int getDias()
	{
		return (int) this.diasTotais;
	}
	
	public int getAnos()
	{
		return this.numeroDeVoltas;
	}
	
}
