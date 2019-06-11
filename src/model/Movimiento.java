package model;


public class Movimiento {

	private int id;
	private int idcta;
	private String fecha;
	private String descripcion;
	private float importe;
	
	public Movimiento() {
		super();
	}

	public Movimiento(int id, int idcta, String fecha, String descripcion, float importe) {
		super();
		this.id = id;
		this.idcta = idcta;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.importe = importe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdcta() {
		return idcta;
	}

	public void setIdcta(int idcta) {
		this.idcta = idcta;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	@Override
	public String toString() {
		return "Movimiento [id=" + id + ", idcta=" + idcta + ", fecha=" + fecha + ", descripcion=" + descripcion
				+ ", importe=" + importe + "]";
	}

	
}
