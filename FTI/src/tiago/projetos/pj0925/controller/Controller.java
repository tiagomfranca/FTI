package tiago.projetos.pj0925.controller;

import tiago.projetos.pj0925.model.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {
	
	
	public void cadastraProfessor(Professor professor){
		
	}
	
	public void cadastraAluno(Aluno aluno){
		
	}
	
	public boolean validaData(String date){
		if (date == null){
			return false;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		sdf.setLenient(false);
		
		try {
			Date data = sdf.parse(date);
		} catch(ParseException e) {
			e.printStackTrace();
			return false; 
		}
		return true;
	}
	
	public boolean validaApenasNumeros(String numeros){
		boolean verifica = false;
		for (int i = 0; i < numeros.length(); i++){
			try {
				Integer.parseInt(numeros.substring(i, i+1));
				verifica = true;
			} catch(Exception e) {
				e.printStackTrace();
				verifica = false;
			}
		}
		return verifica;
	}
	
	public boolean validaCpf(String cpf){
		boolean verifica = false;
		
		if (validaApenasNumeros(cpf)){
			int digitoVerificador1 = Integer.parseInt(cpf.substring(9,10));
			int digitoVerificador2 = Integer.parseInt(cpf.substring(10,11));
			int resultado = 0;
			
			for(int i = 10, j = 0; i >= 2; i--, j++){
				resultado = resultado + (Integer.parseInt(cpf.substring(j,j+1)) * i);
			}
			resultado = (resultado * 10)%11;
			if(resultado == digitoVerificador1){
				resultado = 0;
				for(int i = 11, j = 0; i >= 2; i--, j++){
					resultado = resultado + (Integer.parseInt(cpf.substring(j, j+1)) * i);
				}
				resultado = (resultado * 10)%11;
				if (resultado == digitoVerificador2){
					verifica = true;
				} else if(digitoVerificador2 == 0 && resultado == 10){
					verifica = true;
				} else {
					verifica = false;
				}
			} else if(digitoVerificador1 == 0 && resultado == 10){
				resultado = 0;
				for(int i = 11, j = 0; i >= 2; i--, j++){
					resultado = resultado + (Integer.parseInt(cpf.substring(j, j+1)) * i);
				}
				resultado = (resultado * 10)%11;
				if (resultado == digitoVerificador2){
					verifica = true;					
				} else if(digitoVerificador2 == 0 && resultado == 10){
						verifica = true;
				} else {
					verifica = false;
				}
			} else {
				verifica = false;
			}
		} else {
			verifica = false;
		}
		return verifica;
	}
	
	public boolean validaTexto(String texto){
		if(texto == null){
			return false;
		}
		return true;
	}
}
