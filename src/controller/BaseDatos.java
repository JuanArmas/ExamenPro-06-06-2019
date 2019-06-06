package controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;			// 
import java.sql.PreparedStatement;  // preparada para dar mayor seguridad y proteger acceso sobretodo en web
import java.sql.ResultSet; 			// primer objeto al que podremos acceder a los datos
import java.sql.ResultSetMetaData;  // obtener informacion de esa consulta, nombre de los campos, etc.


// JDBC -> Java DataBase Connection
public class BaseDatos {
	// build path - library - indicar la ruta del archivo de la libreria
	// desde dentro del proyecto se usara jdbc -> java.sql
	// propiedades seran visibles en toda la bbdd---> host - dbname(schema) - dbuser - dbpasword
	// host donde esta la bbdd, el servidor, donde esta y que puerto escucha
	// clases jdbc 
	// clase connection
	
	private String host;
	private String dbname;
	private String dbuser;
	private String dbpassword;
	private Connection conexion;
	private Statement st = null;

	public BaseDatos(String host, String dbName, String dbUser, String dbpassword) {
		super();
		this.host = host;
		this.dbname = dbName;
		this.dbuser = dbUser;
		this.dbpassword = dbpassword;
		try {			
			Class.forName("com.mysql.cj.jdbc.Driver");		
			this.conexion = DriverManager.getConnection("jdbc:mysql://" + host + "/" +
					dbName + "?&user=" + dbUser +"&password="+ dbpassword); // +"&serverTimezone=UTC" //despues del dbpassword si hiciera falta por error de localizacion
		
			System.out.println("Conectado ...");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			
		}
	}
	
	//acceso a bbdd, en realidad no iria aqui, seria crear un paquete para ello

	public BaseDatos() {
		super();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getDbuser() {
		return dbuser;
	}

	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}

	public String getDbpassword() {
		return dbpassword;
	}

	public void setDbpassword(String dbpassword) {
		this.dbpassword = dbpassword;
	}
	
	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public Statement getSt() {
		return st;
	}

	public void setSt(Statement st) {
		this.st = st;
	}

}
