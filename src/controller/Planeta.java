package controller;
public abstract class Planeta extends Astro {

	//Variaveis de um planeta:
	protected String nome;
	protected int numeroDeOrbita;
	protected int velocidade;
	protected int numeroDeTrajetoria;
	protected int posicaoQuadrangular;
	protected int unidadesAndadas = 0;
	protected int unidadesAndadasTotal = 0;
	protected int numeroDeVoltas = 0;
	
	protected float horasPorInstante;
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
	
 	public void atualizarVelocidadeMedia(int tempoTotal)
	{
		if(tempoTotal != 0)
			this.velocidadeMedia = ((float)this.unidadesAndadasTotal)/((float)tempoTotal);
		else
			this.velocidadeMedia = 0;
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
	
	public void mostrarDados()
	{
		System.out.println("\033[36;1m=== #" + (8 - this.numeroDeOrbita) + " === >>> " + this.nome + " <<< ===\033[0m");
		System.out.println("Velocidade atual: " + this.velocidade + " unidades/instante");
		System.out.printf("Horas totais: %.1f horas (%.2f dias de 24h)%n", this.horasTotais, this.diasTotais);
		System.out.printf("Horas passadas na ultima rodada: %.1f horas (%.2f dias de 24h)%n", this.horasPassadas, this.diasPassados);
		System.out.printf("Anos completos passados: %d anos JavaLar%n", this.numeroDeVoltas);
		System.out.printf("Anos totais: %.2f anos JavaLar%n", (float)this.unidadesAndadasTotal/this.numeroDeTrajetoria);
		System.out.println("");
	}

	public void relatorioCompletoPlaneta()
	{
		System.out.println("\033[36;1m=== #" + (8 - this.numeroDeOrbita) + " === >>> " + this.nome + " <<< ===\033[0m");
		if(this.existe()) System.out.println("Status: \u001b[38;5;10mEm orbita\u001b[0m");
		else System.out.println("Status final: \u001b[38;5;1mDestruido\u001b[0m");
		System.out.println("Bugs colididos: " + this.bugsRecebidos);
		System.out.println("Devs recebidos: " + this.devsRecebidos);
		System.out.printf("Velocidade media: %.2f unidades/instante%n", this.velocidadeMedia);
		System.out.printf("Velocidade de rotacao: %.4f dias/instante%n", this.horasPorInstante/24);
		System.out.printf("Dias totais: %.2f dias%n", this.diasTotais);
		System.out.printf("Anos completos passados: %d anos JavaLar%n", this.numeroDeVoltas);
		System.out.printf("Anos totais: %.2f anos JavaLar%n", (float)this.unidadesAndadasTotal/this.numeroDeTrajetoria);
		System.out.println("");
		this.info();
		System.out.println("");
	}
	
	//Metodo abstrato pra cada planeta implementar um pequeno paragrafo:
	public abstract void info();
	
}
