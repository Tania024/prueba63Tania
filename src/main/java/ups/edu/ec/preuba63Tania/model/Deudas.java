package ups.edu.ec.preuba63Tania.model;

public class Deudas {
	private int codigo;
	private String mes;
	private double precio;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "Deudas [codigo=" + codigo + ", mes=" + mes + ", precio=" + precio + "]";
	}
	

}
