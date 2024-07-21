package model;

public class Usuario extends Persona {
	private String login;
	private String password;
	private int nivAcceso;

	public Usuario(int edad, char sexo, String nombre, String apellido,
			int cedula, String login, String password, int nivAcceso) {

		super(edad, sexo, nombre, apellido, cedula);
		this.login = login;
		this.password = password;
		this.nivAcceso = nivAcceso;
	}

	public Usuario(String nombre, String login, String password) {
		super.setNombre(nombre);
		this.login = login;
		this.password = password;
	}

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setNivAcceso(int nivAcceso) {
		this.nivAcceso = nivAcceso;
	}

	public int getNivAcceso() {
		return nivAcceso;
	}

	public boolean isLoginCorrect(String login, String password) {
		if (login.equals(this.login) && password.equals(this.password)) {
			return true;
		}

		return false;
	}

	public String toString() {

		String resp = ("Nombre: " + getNombre()
				+ System.getProperty("line.separator") + "Login: " + getLogin()
				+ System.getProperty("line.separator") + "Password: "
				+ getPassword() + System.getProperty("line.separator"));

		return resp;

	}

}
