package tiago.exercicios.ex0917;

public class MesException extends Exception{
	private int idErro;
	
	public MesException(int idErro, String msgErro){
		super(msgErro);
		this.idErro = idErro;
	}

	public int getIdErro() {
		return idErro;
	}

	public void setIdErro(int idErro) {
		this.idErro = idErro;
	}
}