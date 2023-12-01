package controller;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import model.IO;
import model.Relatorio;

public class Sistema {
	
	//ArrayList dos planetas:
	private ArrayList <Planeta> planetaList = new ArrayList<Planeta>();
	
	public ArrayList<Planeta> getPlanetaList()
	{
		return planetaList;
	}
	
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
	private Grade grade = new Grade();
	
	public Grade getGrade()
	{
		return grade;
	}
	
	//Criando bugs:
	ArrayList <Bug> bugList = new ArrayList<Bug>();
	
	public void criarBugs(int num)
	{
		if(num > grade.espacosLivres())
		{
			num = grade.espacosLivres();
		}
		
		for(int i = 0; i < num; i++)
		{
			Bug bug = new Bug(grade);
			bugList.add(bug);
		}
	}

	//Criando devs:
	ArrayList <Dev> devList = new ArrayList<Dev>();
		
	public void criarDevs(int num)
	{
		if(num > grade.espacosLivres())
		{
			num = grade.espacosLivres();
		}
		
		for(int i = 0; i < num; i++)
		{
			Dev dev = new Dev(grade);
			devList.add(dev);
		}
	}


	//Executar rodada a partir dos dados:
	public void executarRodada(int[] dados)
	{
		criarBugs(dados[8]);
		criarDevs(dados[9]);
		for(Planeta p : planetaList)
		{
			p.rotacaoEtranslacao(dados[7 - planetaList.indexOf(p)]);
		}
		
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
	
	//Metodos para rodar o sistema a partir do arquivo
	ArrayList<String> lista;
	int contadorLinhas;
	
	public void setArrayListArquivo(ArrayList<String> listaArquivo)
	{
		lista = listaArquivo;
		contadorLinhas = 0;
	}
	
	public void sistemaProximoInstante()
	{
		if(io.checarArquivo() == true)
		{
			if(contadorLinhas < lista.size())
			{
				executarRodada(parseLinha(lista.get(contadorLinhas)));
				contadorLinhas++;
			} else
			{
				JOptionPane.showMessageDialog(null, "Todas as linhas do arquivo foram processadas", "Aviso", JOptionPane.WARNING_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Nenhum arquivo foi selecionado", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	
	private int[] parseLinha(String linha)
	{
		String[] dadosStr = linha.split(",");
		int[] dados = new int[10];
		for(int i = 0; i < 10; i++)
		{
			dados[i] = Integer.parseInt(dadosStr[i]);
		}
		return dados;
	}
	
	//IO e metodos para passar ordens da janela ao IO
	IO io = new IO(this);
	
	public void sistemaPassarArquivo(File arquivo)
	{
		io.receberArquivoEntrada(arquivo);
	}
	
	
	public void sistemaGravarRelatorio()
	{
		io.ioGravarRelatorio(prepararRelatorio());
	}
	
	
	public void sistemaProcessarDados()
	{
		io.ioProcessarDados();
	}
	
	
	public void sistemaGravarArquivoSaida()
	{
		io.ioGravarArquivoSaida();
	}
	
	
	//Funções para calcular o que o relatório pede:
	
	private int[] getBugs()
	{
		int[] bugs = new int[7];
		for(int i = 0; i < 7; i++)
		{
			bugs[i] = planetaList.get(6-i).getBugsRecebidos();
		}
		return bugs;
	}
	
	
	private int[] getDevs()
	{
		int[] devs = new int[7];
		for(int i = 0; i < 7; i++)
		{
			devs[i] = planetaList.get(6-i).getDevsRecebidos();
		}
		return devs;
	}
	
	
	private int[] getVelocidades()
	{
		int[] velocidades = new int[7];
		for(int i = 0; i < 7; i++)
		{
			velocidades[i] = planetaList.get(6-i).getVelocidadeMedia();
		}
		return velocidades;
	}
	
	
	private int[] getDias()
	{
		int[] dias = new int[7];
		for(int i = 0; i < 7; i++)
		{
			dias[i] = planetaList.get(6-i).getDias();
		}
		return dias;
	}
	
	
	private int[] getAnos()
	{
		int[] anos = new int[7];
		for(int i = 0; i < 7; i++)
		{
			anos[i] = planetaList.get(6-i).getAnos();
		}
		return anos;
	}
	
	
	public Relatorio prepararRelatorio()
	{
		Relatorio r = new Relatorio(io.getNomeArquivo());
		
		r.setBugs(getBugs());
		r.setDevs(getDevs());
		r.setVelocidades(getVelocidades());
		r.setDias(getDias());
		r.setAnos(getAnos());
		r.setBugsQ(grade.getBugsQ());
		r.setDevsQ(grade.getDevsQ());
		
		return r;
	}
}
