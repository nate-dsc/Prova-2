package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class JavalarDAO {
	
	ArrayList<Relatorio> dadosBanco;
		
	public void gravarRelatorio(Relatorio relatorio)
	{
		try
		{
			Connection conexao = new Conexao().getConexao();
			PreparedStatement inserir = conexao.prepareStatement("INSERT INTO javalar (nome, matricula, nome_arquivo,"
					+ "bug_python, bug_javascript, bug_ruby, bug_php, bug_csharp, bug_cmais, bug_c,"
					+ "dev_python, dev_javascript, dev_ruby, dev_php, dev_csharp, dev_cmais, dev_c, "
					+ "v_python, v_javascript, v_ruby, v_php, v_csharp, v_cmais, v_c,"
					+ "d_python, d_javascript, d_ruby, d_php, d_csharp, d_cmais, d_c,"
					+ "a_python, a_javascript, a_ruby, a_php, a_csharp, a_cmais, a_c,"
					+ "bug_q1, bug_q2, bug_q3, bug_q4, dev_q1, dev_q2, dev_q3, dev_q4)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
					+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			inserir.setString(1, relatorio.getNome());
			inserir.setString(2, relatorio.getMatricula());
			inserir.setString(3, relatorio.getNomeArquivo());
			for(int i = 0; i < 7; i++)
			{
				inserir.setInt(i+4, relatorio.getBugs()[i]);
			}
			for(int i = 0; i < 7; i++)
			{
				inserir.setInt(i+11, relatorio.getDevs()[i]);
			}
			for(int i = 0; i < 7; i++)
			{
				inserir.setInt(i+18, relatorio.getVelocidades()[i]);
			}
			for(int i = 0; i < 7; i++)
			{
				inserir.setInt(i+25, relatorio.getDias()[i]);
			}
			for(int i = 0; i < 7; i++)
			{
				inserir.setInt(i+32, relatorio.getAnos()[i]);
			}
			for(int i = 0; i < 4; i++)
			{
				inserir.setInt(i+39, relatorio.getBugsQ()[i]);
			}
			for(int i = 0; i < 4; i++)
			{
				inserir.setInt(i+43, relatorio.getDevsQ()[i]);
			}
			inserir.execute();
			JOptionPane.showMessageDialog(null, "Relatorio enviado");
			conexao.close();
			
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<Relatorio> getDadosBanco()
	{
		dadosBanco = new ArrayList<Relatorio>();
		
		try
		{
			Connection conexao = new Conexao().getConexao();
			PreparedStatement resultado = conexao.prepareStatement("SELECT * FROM javalar ORDER BY id ASC");
			ResultSet dados = resultado.executeQuery();
			
			String[] planetas = {"python", "javascript", "ruby", "php", "csharp", "cmais", "c"};
			
			while(dados.next())
			{
				String nome = dados.getString("nome");
				String matricula = dados.getString("matricula");
				String nomeArquivo = dados.getString("nome_arquivo");
				Relatorio r = new Relatorio(nome, matricula, nomeArquivo);
				
				int[] bugs = new int[7];
				for(int j = 0; j < 7; j++)
				{
					bugs[j] = dados.getInt("bug_" + planetas[j]);
				}
				r.setBugs(bugs);
				
				int[] devs = new int[7];
				for(int j = 0; j < 7; j++)
				{
					devs[j] = dados.getInt("dev_" + planetas[j]);
				}
				r.setDevs(devs);
				
				int[] velocidades = new int[7];
				for(int j = 0; j < 7; j++)
				{
					velocidades[j] = dados.getInt("v_" + planetas[j]);
				}
				r.setVelocidades(velocidades);
				
				int[] dias = new int[7];
				for(int j = 0; j < 7; j++)
				{
					dias[j] = dados.getInt("d_" + planetas[j]);
				}
				r.setDias(dias);
				
				int[] anos = new int[7];
				for(int j = 0; j < 7; j++)
				{
					anos[j] = dados.getInt("a_" + planetas[j]);
				}
				r.setAnos(anos);
			
				int[] bugs_q = new int[4];
				bugs_q[0] = dados.getInt("bug_q1");
				bugs_q[1] = dados.getInt("bug_q2");
				bugs_q[2] = dados.getInt("bug_q3");
				bugs_q[3] = dados.getInt("bug_q4");
				r.setBugsQ(bugs_q);
				
				int[] devs_q = new int[4];
				devs_q[0] = dados.getInt("dev_q1");
				devs_q[1] = dados.getInt("dev_q2");
				devs_q[2] = dados.getInt("dev_q3");
				devs_q[3] = dados.getInt("dev_q4");
				r.setDevsQ(devs_q);
				
				dadosBanco.add(r);
			}
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return dadosBanco;
	}
	
	
}
