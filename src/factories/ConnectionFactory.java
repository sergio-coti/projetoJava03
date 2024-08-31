package factories;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 * Classe para fabricar conexões para o banco de dados
 * Design Pattern Gof: Factory Method
 */
public class ConnectionFactory {

	/*
	 * Método estático para retornar uma conexão
	 * com um banco de dados do PostGreSQL
	 */
	public static Connection getConnection() {
		
		Connection connection = null; //vazio
		
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5434/bd_aula02", "postgres", "coti");
		}
		catch(Exception e) {
			System.out.println("\nFalha ao abrir conexão com o banco de dados.");
			System.out.println(e.getMessage());
		}
		
		return connection;
		
	}
}
