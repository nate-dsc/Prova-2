package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.Sistema;

public class IO {

	private File arquivo;
	private String nomeArquivo;
	private File arquivoSaida;
	
	private FileReader fileReader;
	private BufferedReader leitor;
	private FileWriter fileWriter;
	
	private Sistema sistema;
	private JavalarDAO javalarDAO;
	
	private String ultimosDadosProcessados;
	
	public IO(Sistema sistema)
	{
		this.sistema = sistema;
		javalarDAO = new JavalarDAO();
	}
	
	//Métodos pra tratar do arquivo de entrada
	public void receberArquivoEntrada(File arquivoEntrada)
	{
		arquivo = arquivoEntrada;
		try {
			fileReader = new FileReader(arquivo);
			leitor = new BufferedReader(fileReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		nomeArquivo = arquivo.getName();
		sistema.setArrayListArquivo(arrayListArquivo());
	}
	
	public boolean checarArquivo()
	{
		if(arquivo != null)
		{
			return true;
		} else
		{
			return false;
		}
	}
	
	public ArrayList<String> arrayListArquivo()
	{
		ArrayList<String> lista = new ArrayList<String>();
		String linha;
		try 
		{
			linha = leitor.readLine();
			while(leitor.ready())
			{
				linha = leitor.readLine();
				lista.add(linha);
			}
			leitor.close();
			fileReader.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return lista;
	}
	
	public String getNomeArquivo()
	{
		return nomeArquivo;
	}
	
	//Metodo pra tratar da gravação do relatorio
	public void ioGravarRelatorio(Relatorio relatorio)
	{
		javalarDAO.gravarRelatorio(relatorio);
	}
	
	//Metodo pro registro do arquivo de saída
	public void ioGravarArquivoSaida()
	{
		try
		{
			arquivoSaida = new File("ArquivoSaida.txt");
			if(arquivoSaida.exists() ==  false)
			{
				arquivoSaida.createNewFile();
			}
			fileWriter = new FileWriter(arquivoSaida);
			fileWriter.write(ultimosDadosProcessados);
			fileWriter.close();
			JOptionPane.showMessageDialog(null, "Arquivo de saída salvo com sucesso");
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	//Metodo pro processamento/leitura dos dados
	public void ioProcessarDados()
	{
		ArrayList<Relatorio> lista = javalarDAO.getDadosBanco();
		ArrayList<String> nomes = getNomesNosDados(lista);
		
		String[] nomeMaisLido_instantesTotal = nomeMaisLido_instantesTotal(nomes, lista);
		String matriculaMaisLida = matriculaMaisLida(nomeMaisLido_instantesTotal[0], lista);
		String planetaMaisMorto = planetaMaisMorto(lista);
		String planetaMaisVivo = planetaMaisVivo(lista);
		int quadranteBug = quadranteBug(lista);
		int quadranteDev = quadranteDev(lista);
		String mediaVelocidades = mediaVelocidades(lista);
		int[] qtdBugsDevs = qtdBugsDevs(lista);
		int qtdHoras = qtdHoras(lista);
		int qtdAnos = qtdAnos(lista);
		
		JOptionPane.showMessageDialog(null, lista.size() + " linhas do banco de dados foram processadas");
		
		ultimosDadosProcessados = matriculaMaisLida + " - " + nomeMaisLido_instantesTotal[0] + ", "
				+ planetaMaisMorto + ", " + planetaMaisVivo + ", "
				+ quadranteBug + ", " + quadranteDev + ", " + nomeMaisLido_instantesTotal[1] + ", "
				+ mediaVelocidades + ", " + qtdBugsDevs[0] + ", " + qtdBugsDevs[1] + ", "
				+ qtdHoras + ", " + qtdAnos + "\n";
	}
	
	private ArrayList<String> getNomesNosDados(ArrayList<Relatorio> lista)
	{
		ArrayList<String> nomes = new ArrayList<String>();
		nomes.add(lista.get(0).getNome());
		for(Relatorio r : lista)
		{
			if(nomes.contains(r.getNome()) == false)
			{
				nomes.add(r.getNome());
			}
		}
		return nomes;
	}
	
	private String[] nomeMaisLido_instantesTotal(ArrayList<String> nomes, ArrayList<Relatorio> lista)
	{
		//Cria array com linhas lidas por cada nome
		String nomeArquivo;
		int[] contadores = new int[nomes.size()];
		for(int i = 0; i < contadores.length; i++)
		{
			for(Relatorio r : lista)
			{
				if(r.getNome().equals(nomes.get(i)))
				{
					nomeArquivo = r.getNomeArquivo();
					if(nomeArquivo.contains("."))
					{
						nomeArquivo = nomeArquivo.substring(0, nomeArquivo.lastIndexOf('.'));
					}
					if(nomeArquivo.equals("AE_10"))
						contadores[i] += 10;
					if(nomeArquivo.equals("AE_50"))
						contadores[i] += 50;
					if(nomeArquivo.equals("AE_100"))
						contadores[i] += 100;
					if(nomeArquivo.equals("AE_500"))
						contadores[i] += 500;
					if(nomeArquivo.equals("AE_1000"))
						contadores[i] += 1000;
					if(nomeArquivo.equals("AE_1500"))
						contadores[i] += 1500;
					if(nomeArquivo.equals("AE_2000"))
						contadores[i] += 2000;
				}
			}
		}
		
		//Acha o maior elemento da arary de repetições
		int indice = 0;
		if(nomes.size() > 1)
		{
			for(int j = 0; j < contadores.length; j++)
			{
				if(contadores[j] > contadores[indice])
				{
					indice = j;
				}
			}
		}
		
		//Contando o total de instantes
		int totalInstantes = 0;
		for(int k = 0; k < contadores.length; k++)
		{
			totalInstantes += contadores[k];
		}
		
		String[] resultado = new String[2];
		resultado[0] = nomes.get(indice);
		resultado[1] = Integer.toString(totalInstantes);
		
		return resultado;
	}
	
	private String matriculaMaisLida(String nome, ArrayList<Relatorio> lista)
	{
		for(Relatorio r : lista)
		{
			if(r.getNome().equals(nome))
			{
				return r.getMatricula();
			}
		}
		return null;
	}
	
	private String planetaMaisMorto(ArrayList<Relatorio> lista)
	{
		String[] planetas = {"Python", "Javascript", "Ruby on Rails", "PHP", "C#", "C++", "C"};
		int[] contadores = new int[7];
		for(Relatorio r : lista)
		{
			for(int i = 0; i < contadores.length; i++)
			{
				if(r.getVelocidades()[i] == 0)
					contadores[i]++;
			}
		}
		
		int indice = 0;
		for(int j = 0; j < contadores.length; j++)
		{
			if(contadores[j] > contadores[indice])
			{
				indice = j;
			}
		}
		
		return planetas[indice];
	}
	
	private String planetaMaisVivo(ArrayList<Relatorio> lista)
	{
		String[] planetas = {"Python", "Javascript", "Ruby on Rails", "PHP", "C#", "C++", "C"};
		int[] contadores = new int[7];
		for(Relatorio r : lista)
		{
			for(int i = 0; i < contadores.length; i++)
			{
				if(r.getVelocidades()[i] > 0)
					contadores[i]++;
			}
		}
		
		int indice = 0;
		for(int j = 0; j < contadores.length; j++)
		{
			if(contadores[j] > contadores[indice])
			{
				indice = j;
			}
		}
		
		return planetas[indice];
	}
	
	private int quadranteBug(ArrayList<Relatorio> lista)
	{
		int[] contadores = new int[4];
		for(Relatorio r : lista)
		{
			for(int i = 0; i < contadores.length; i++)
			{
				contadores[i] += r.getBugsQ()[i];
			}
		}
		
		int indice = 0;
		for(int j = 0; j < contadores.length; j++)
		{
			if(contadores[j] > contadores[indice])
			{
				indice = j;
			}
		}
		
		return indice+1;
	}
	
	private int quadranteDev(ArrayList<Relatorio> lista)
	{
		int[] contadores = new int[4];
		for(Relatorio r : lista)
		{
			for(int i = 0; i < contadores.length; i++)
			{
				contadores[i] += r.getDevsQ()[i];
			}
		}
		
		int indice = 0;
		for(int j = 0; j < contadores.length; j++)
		{
			if(contadores[j] > contadores[indice])
			{
				indice = j;
			}
		}
		
		return indice+1;
	}
	
	private String mediaVelocidades(ArrayList<Relatorio> lista)
	{
		String[] planetas = {"Python", "Javascript", "Ruby on Rails", "PHP", "C#", "C++", "C"};
		double[] velocidades = new double[7];
		for(Relatorio r : lista)
		{
			for(int i = 0; i < velocidades.length; i++)
			{
				velocidades[i] += r.getVelocidades()[i];
			}
		}
		for(int i = 0; i < velocidades.length; i++)
		{
			velocidades[i] = velocidades[i]/lista.size();
		}
		for(int i = 0; i < planetas.length; i++)
		{
			planetas[i] = planetas[i] + ": " + String.format("%.2f", velocidades[i]);
		}
		
		return (planetas[0] + " - " + planetas[1] + " - " + planetas[2] + " - " 
				+ planetas[3] + " - " + planetas[4] + " - " + planetas[5] + " - "
				+ planetas[6]);
	}
	
	private int[] qtdBugsDevs(ArrayList<Relatorio> lista)
	{
		int[] resultado = new int[2];
		for(Relatorio r : lista)
		{
			for(int i = 0; i < r.getBugsQ().length; i++)
			{
				resultado[0] += r.getBugsQ()[i];
			}
		}
		for(Relatorio r : lista)
		{
			for(int i = 0; i < r.getDevsQ().length; i++)
			{
				resultado[1] += r.getDevsQ()[i];
			}
		}
		return resultado;
	}
	
	private int qtdHoras(ArrayList<Relatorio> lista)
	{
		int qtdHoras = 0;
		for(Relatorio r : lista)
		{
			for(int i = 0; i < r.getDias().length; i++)
			{
				qtdHoras += r.getDias()[i];
			}
		}
		return qtdHoras*24;
	}
	
	private int qtdAnos(ArrayList<Relatorio> lista)
	{
		int qtdAnos = 0;
		for(Relatorio r : lista)
		{
			for(int i = 0; i < r.getAnos().length; i++)
			{
				qtdAnos += r.getAnos()[i];
			}
		}
		return qtdAnos;
	}
	
}
