package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import model.Cuenta;



public class Main {

	public static void main(String[] args) {
		String rutaVehiculos = "ficheros/vehiculos.csv";
		
		/*******************EJERCICIO 1******************************/
		
		
		
		/*******************EJERCICIO 2******************************/
		
		
		
		/*******************EJERCICIO 3******************************/
		
		
		
		
		System.out.println("fin del programa");
		System.exit(0);

	}
	/*******************EJERCICIO 1******************************/
// 1.1- crear tabla vehiculos
	public void insertaVehiculos(String rutaVehiculos) {
		try {
			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaVehiculos));
			BaseDatos bd = new BaseDatos("localhost:3306", "vehiculos", "root", "");
			Connection conexion = bd.getConexion();
			Statement stmt = conexion.createStatement();		
			String registro;
			while ((registro = fichero.readLine()) != null) {
				String[] campos = registro.split("&&");			
				int codigo = Integer.parseInt(campos[0]);
				String nifPropietario = campos[1];
				String matricula = campos[2];
				char estado = campos[3].charAt(0);
				float precio = Float.parseFloat(campos[4]);
				String fechaMatricula = campos[5];
				
				String sql = "insert into equipos(codigo,matricula,fecha,estado,precio,nif) values";
				sql+= "(" + codigo + "\"" + matricula + "\",\"" + fechaMatricula + "\",\"" + estado + "\","+ precio + "\"" + nifPropietario + "\")";			
				System.out.println(sql);	
				stmt.executeUpdate(sql);				
			}	
			stmt.close();
			conexion.close();
			fichero.close();
			System.out.println("Fin de la lectura del fichero");
		} catch (FileNotFoundException excepcion) {
			System.out.println("fichero no encontrado");
		} catch (IOException e) {
			System.out.println("IO Excepcion");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// 1.2- Obtener listado vehiculos
	
	
	// 2.1 Desarrollar un método que devuelva un objeto de la clase
	
	public HashMap <Integer,Cuenta> cuentas(){
		HashMap <Integer, Cuenta> cuentas = new HashMap <Integer, Cuenta>();
		
		
		return cuentas;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
