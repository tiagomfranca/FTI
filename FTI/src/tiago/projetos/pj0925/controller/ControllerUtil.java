package tiago.projetos.pj0925.controller;

import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ControllerUtil {
	
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
	
	public Date transformaData(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		sdf.setLenient(false);
		Date data = new Date();
		try {
			data = sdf.parse(date);
		} catch(ParseException e) {
			e.printStackTrace();
		}
		return data;
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
		if (cpf.length() == 11) {
			if (validaApenasNumeros(cpf)){
				if (cpf.equals("11111111111") || cpf.equals("22222222222") || cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555") ||
						cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") || cpf.equals("99999999999") || cpf.equals("00000000000")) {
					return false;
				}
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
		}
		return verifica;
	}
	
	public boolean validaTexto(String texto){
		if(texto == null || texto.equals("")){
			return false;
		}
		return true;
	}
	
	public boolean validaEmail(String email) {
		for (int i = 0; i < email.length(); i++){
			if(email.charAt(i) == '@') {
				return true;
			}
		}
		return false;
	}
	
	public void apenasNumeros(KeyEvent e) {
		char c = e.getKeyChar();
		if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE && e.getKeyCode() != KeyEvent.VK_LEFT && e.getKeyCode() != KeyEvent.VK_RIGHT) {
			e.consume();
		}
	}
}
