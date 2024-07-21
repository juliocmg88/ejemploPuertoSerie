package model;

public class Persona {
	private int edad;
	private char sexo;
	private String nombre;
	private String apellido;
	private int cedula;

	public Persona() {
		edad = 22;
		sexo = 'M';
	}

	public Persona(int edad, char sexo, String nombre, String apellido,
			int cedula) {

		this.edad = edad;
		this.sexo = sexo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public String obtenerNombreCompleto() {
		return nombre + " " + apellido;
	}

}
