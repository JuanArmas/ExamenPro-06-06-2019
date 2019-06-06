package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import model.Cuenta;
import model.Movimiento;
import model.Vehiculo;



public class Main {

	public static void main(String[] args) {
		Main ejercicios = new Main();
		String rutaVehiculos = "ficheros/vehiculos.csv";
		
		/*******************LLAMADAS EJERCICIO 1******************************/
		// 1.1 DESCOMENTAR, CODIGO FUNCIONAL
			// ejercicios.insertaVehiculos(rutaVehiculos);
		
		// 1.2 
			
		HashMap<String, Vehiculo> mapaVehiculos = ejercicios.crearMapaVehiculos(rutaVehiculos);
		System.out.println(mapaVehiculos);
		
		/*******************LLAMADAS EJERCICIO 2******************************/
		// 2.1- DESCOMENTAR, CODIGO FUNCIONAL
			// HashMap<Integer, Cuenta> mapaCuentas = ejercicios.cuentas();
			// System.out.println(mapaCuentas);
		
		//2.2-DESCOMENTAR, CODIGO FUNCIONAL
			// HashMap<Cuenta, ArrayList<Movimiento>> movimientos = ejercicios.listadoMovimientos();
			// System.out.println(movimientos);
		
		//2.3-CODIGO INCONCLUSO
			
		
		System.out.println("fin del programa");
		System.exit(0);

	}
	
	/*******************INICIO EJERCICIO 1******************************/
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
				int precio = Integer.parseInt(campos[4]);
				String fechaMatricula = campos[5];
				
				String sql = "insert into vehiculos(codigo,matricula,fecha,estado,precio,nif) values";
				sql+= "(" + codigo + ",\"" + matricula + "\",\"" + fechaMatricula + "\",\"" + estado + "\","+ precio + ",\"" + nifPropietario + "\")";			
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
	
	// 1.2- Obtener un listado en el que aparezcan los vehiculos pertenecientes a cada propietario
	
	public HashMap<String, Vehiculo> crearMapaVehiculos(String rutaFichero) {
		try {
			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro;
			Vehiculo vehiculo = null;
			HashMap<String, Vehiculo> vehiculos = new HashMap<String, Vehiculo>();
			while ((registro = fichero.readLine()) != null) {
				String[] campos = registro.split("&&");
				//codigo,matricula,fecha,estado,precio,nif
				int codigo = Integer.parseInt(campos[0]);
				String matricula = campos[1];
				String fechaMatricula = campos[2];
				char estado = campos[3].charAt(0);
				int precio = Integer.parseInt(campos[4]);
				String nifPropietario = campos[5];
			
				if(!vehiculos.containsKey(nifPropietario)) {
					vehiculo = new Vehiculo(codigo, nifPropietario, matricula, estado, precio, fechaMatricula);
					vehiculos.put(nifPropietario,vehiculo);	
				}else {
					vehiculos.put(nifPropietario,vehiculo);	
				}
				
				
			}
			fichero.close();
			System.out.println("Fin de la lectura del fichero");
			return vehiculos;
		} catch (FileNotFoundException excepcion) {
			System.out.println("fichero no encontrado");
		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}
		return null;
	}
	
/* Codigo NO FUNCIONAL INTENTO DE ACCESO DESD BBDD
	public ArrayList<Vehiculo> listadoVehiculos(){
		ArrayList<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
		try {
			BaseDatos bd = new BaseDatos("localhost:3306",  "vehiculos", "root", "");
			Connection conexion = bd.getConexion();
			Statement stmt = conexion.createStatement();
			String sql = "select * from vehiculos order by nif";
			ResultSet rS = stmt.executeQuery(sql);
			while(rS.next()) { 
				Vehiculo unVehiculo = new Vehiculo();
				unVehiculo.setCodigo(rS.getInt("codigo"));
				unVehiculo.setMatricula(rS.getString("matricula"));
				unVehiculo.setFechaMatricula(rS.getString("fecha"));
				unVehiculo.setEstado((char)rS.getInt("estado"));
				unVehiculo.setPrecio(rS.getInt("precio"));			
				unVehiculo.setNifPropietario(rS.getString("nif"));							
				listaVehiculos.add(unVehiculo);			
			}			
			rS.close();
			stmt.close();
			conexion.close();
			return listaVehiculos;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
*/
	
	
	/*******************INICIO EJERCICIO 2******************************/
	// 2.1 Desarrollar un método que devuelva un objeto de la clase
	// id, descripcion,saldo
	public HashMap <Integer,Cuenta> cuentas(){
		HashMap <Integer, Cuenta> cuentas = new HashMap <Integer, Cuenta>();
		try {
			BaseDatos bd = new BaseDatos("localhost:3306",  "banco", "root", "");
			Connection conexion = bd.getConexion();
			Statement stmt = conexion.createStatement();
			ResultSet rst = stmt.executeQuery("select * from cuentas where 1;");
			ResultSetMetaData rstMeta = rst.getMetaData();
			rstMeta.getColumnCount(); 
			// id, String descripcion, float saldo
			while(rst.next()) { 		
				int id = Integer.parseInt(rst.getString(1));
				String descripcion = rst.getString(2);
				float saldo = Float.parseFloat(rst.getString(3));
				Cuenta cuenta = new Cuenta(id, descripcion, saldo);
				cuentas.put(id, cuenta);			
			}
			rst.close();
			stmt.close();
			conexion.close();
			return cuentas;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	// 2.2. Desarrollar un método que devuelva un objeto de la clase
	
	public HashMap < Cuenta, ArrayList<Movimiento>> listadoMovimientos(){
		Main ejercicios = new Main();
		HashMap<Integer, Cuenta> cuentas = ejercicios.cuentas();
		HashMap < Cuenta, ArrayList<Movimiento>> listaMovs= new HashMap < Cuenta, ArrayList<Movimiento>>();
		try {
			BaseDatos bd = new BaseDatos("localhost:3306",  "banco", "root", "");
			Connection conexion = bd.getConexion();
			Statement stmt = conexion.createStatement();
			ResultSet rst = stmt.executeQuery("select * from movs where 1;");
			ResultSetMetaData rstMeta = rst.getMetaData();
			rstMeta.getColumnCount(); 
			// id, int idcta, String fecha, String descripcion, float importe
			while(rst.next()) { 		
				int id = Integer.parseInt(rst.getString(1));
				int idcta = Integer.parseInt(rst.getString(2));
				String fecha = rst.getString(3);
				String descripcion = rst.getString(4);
				float importe = Float.parseFloat(rst.getString(5));			
				Movimiento movimientos = new Movimiento(id, idcta, fecha, descripcion, importe);
				
				Set<Integer> clavesCuentas = cuentas.keySet();
				for(Integer clave:clavesCuentas) {
					Cuenta unaCuenta = cuentas.get(clave);
					ArrayList<Movimiento> movimientoCuenta = new ArrayList<Movimiento>();
					movimientoCuenta.add(movimientos);
					listaMovs.put(unaCuenta, movimientoCuenta);
				}						
			}
			rst.close();
			stmt.close();
			conexion.close();
			return listaMovs;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	// 2.3-
	public void mostrarMovimientosCuenta() {
		Main ejercicios = new Main();
		HashMap<Integer, Cuenta> mapaCuentas = ejercicios.cuentas();
		HashMap<Cuenta, ArrayList<Movimiento>> movimientos = ejercicios.listadoMovimientos();
		System.out.println("LISTADO DE MOVIMIENTOS A CUENTAS...");
		
		Set<Integer> clavesCuentas = mapaCuentas.keySet();
		Set<Cuenta> clavesMovimientos = movimientos.keySet();	
		for(Integer numCuenta : clavesCuentas) {
			Cuenta saldoCuenta = mapaCuentas.get(numCuenta);
			for(Cuenta numCuentaMovimiento: clavesMovimientos) {
				 ArrayList<Movimiento> listaMovimientos = movimientos.get(numCuentaMovimiento);
				 
				 if(numCuentaMovimiento.equals(numCuenta)) {
					System.out.println("Cuenta :" + "cuenta"+numCuenta);					
					System.out.println("Saldo Inicial :" + saldoCuenta );	
					
					for(int i = 0; i < listaMovimientos.size(); i++) {	

						
						// float saldoFinal = saldoCuenta + listaMovimientos.get(i);
						System.out.println("\t\t" + listaMovimientos.get(i));
						// System.out.println("Saldo Final : \t" + saldoFinal);						
					}
				}
			}
		}
		
	}
	
	
}
