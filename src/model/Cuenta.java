package model;

public class Cuenta implements Comparable<Cuenta>{
	private int id;
	private String descripcion;
	private float saldo;
	
	public Cuenta() {
		super();
	}
	
	public Cuenta(int id, String descripcion, float saldo) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.saldo = saldo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	@Override
	public String toString() {
		return "Cuenta [id=" + id + ", descripcion=" + descripcion + ", saldo=" + saldo + "]";
	}

	@Override
	public int compareTo(Cuenta o) {
		if(this.id < o.id) {
			return -1;
		}
		
		if(this.id > o.id) {
			return 1;
		}
		return 0;
	}
	
}
