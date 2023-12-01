package model;

public class Relatorio {

	private String nome;
	private String matricula;
	private String nomeArquivo;
	
	private int[] bugs;
	private int[] devs;
	private int[] velocidades;
	private int[] dias;
	private int[] anos;
	private int[] bugs_q;
	private int[] devs_q;
	
	Relatorio(String nome, String matricula, String nomeArquivo)
	{
		this.nome = nome;
		this.matricula = matricula;
		this.nomeArquivo = nomeArquivo;
	}
	
	public Relatorio(String nomeArquivo)
	{
		this.nome = "Nathan";
		this.matricula = "555180";
		this.nomeArquivo = nomeArquivo;
	}
	
	public void setBugs(int[] bugs)
	{
		this.bugs = bugs;
	}
	
	public void setDevs(int[] devs)
	{
		this.devs = devs;
	}
	
	public void setVelocidades(int[] velocidades)
	{
		this.velocidades = velocidades;
	}
	
	public void setDias(int[] dias)
	{
		this.dias = dias;
	}
	
	public void setAnos(int[] anos)
	{
		this.anos = anos;
	}
	
	public void setBugsQ(int[] bugs_q)
	{
		this.bugs_q = bugs_q;
	}
	
	public void setDevsQ(int[] devs_q)
	{
		this.devs_q = devs_q;
	}
	
	public String getNome()
	{
		return this.nome;
	}
	
	public String getMatricula()
	{
		return this.matricula;
	}
	
	public String getNomeArquivo()
	{
		return this.nomeArquivo;
	}
	
	public int[] getBugs()
	{
		return this.bugs;
	}
	
	public int[] getDevs()
	{
		return this.devs;
	}
	
	public int[] getVelocidades()
	{
		return this.velocidades;
	}
	
	public int[] getDias()
	{
		return this.dias;
	}
	
	public int[] getAnos()
	{
		return this.anos;
	}
	
	public int[] getBugsQ()
	{
		return this.bugs_q;
	}
	
	public int[] getDevsQ()
	{
		return this.devs_q;
	}
	
}
