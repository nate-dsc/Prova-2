package model;

import java.sql.*;

public class Conexao {

	private String host;
	private String usuario;
	private String senha;
	private String banco;
		
	public Conexao()
	{
//		this.host = "127.0.0.1";
//		this.banco = "teste_javalar";
//		this.usuario = "root";
//		this.senha = "";
		
		this.host = "da_java.mysql.dbaas.com.br";
		this.banco = "da_java";
		this.usuario = "da_java";
		this.senha = "Tecnicas*2023@";
	}
		
	public Connection getConexao()
	{
		String url = "jdbc:mysql://" + this.host + "/" + this.banco + "?enabledTLSProtocols=TLSv1.2&verifyServerCertificate=false&useSSL=true";
		try
		{
			Connection conexao = DriverManager.getConnection(url, usuario, senha);
			System.out.println("Conexao realizada");
			return conexao;
		}
		catch (SQLException e)
		{
			System.out.println("Conexao nao realizada");
			e.printStackTrace();
		}
		return null;
	}
}
