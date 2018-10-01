package tiago.projetos.pj0925.controller;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class ControllerUtil {
	private Border simValidou;
	private Border naoValidou;
	private Border defaultBorder;
	private Toolkit tk;
	private static int countNum;
	
	public ControllerUtil() {
		naoValidou = BorderFactory.createLineBorder(Color.RED);
		simValidou = BorderFactory.createLineBorder(Color.GREEN);
		defaultBorder = new JTextField().getBorder();
		tk = Toolkit.getDefaultToolkit();
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
		if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE && e.getKeyCode() != KeyEvent.VK_LEFT && 
				e.getKeyCode() != KeyEvent.VK_RIGHT && e.getKeyCode() != KeyEvent.VK_TAB) {
			e.consume();
			tk.beep();
		}
	}
	
	public void apenasNumerosLimite(KeyEvent e, String texto, int limite){
		char c = e.getKeyChar();
		if (texto.length() > limite-1){
			if(c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE && e.getKeyCode() != KeyEvent.VK_LEFT && e.getKeyCode() != KeyEvent.VK_RIGHT){
				e.consume();
				tk.beep();
			}
		} else if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE && e.getKeyCode() != KeyEvent.VK_LEFT && e.getKeyCode() != KeyEvent.VK_RIGHT
				|| texto.length() > limite-1) {
			e.consume();
			tk.beep();
		}
	}
	
	public void apenasDouble(KeyEvent e) {
		char c = e.getKeyChar();
//		System.out.println(e.getKeyCode());
//		System.out.println();
		//System.out.println(KeyEvent.VK_DECIMAL + "      " + KeyEvent.VK_SEPARATOR + "   " + KeyEvent.VK_SEPARATER);
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && countNum == 3){
			countNum = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_DECIMAL){
			if (countNum == 0){
				countNum = 1;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_COMMA) {
			if (countNum == 0){
				countNum = 1;
			} else {
				e.consume();
			}
		}
		if (countNum > 0){
			if (countNum < 5){
				if (Character.isDigit(c)){
					countNum++;
				}
			} else {
				if (Character.isDigit(c) || e.getKeyCode() == KeyEvent.VK_COMMA || e.getKeyCode() == KeyEvent.VK_DECIMAL || e.getKeyCode() == KeyEvent.VK_SEPARATOR){
					e.consume();
				}
			}
		}
		if (countNum > 0){
			switch (e.getKeyCode()){
				case KeyEvent.VK_LEFT: countNum-=2; if(countNum==1)countNum=0; break;
				case KeyEvent.VK_BACK_SPACE: countNum-=2; if(countNum==1)countNum=0; break;
				case KeyEvent.VK_COMMA: e.consume(); break;
				case KeyEvent.VK_SEPARATOR: e.consume(); break;
				case KeyEvent.VK_DECIMAL: e.consume(); break;
			}
		}
		if (countNum < 5 && countNum > 0){
			switch (e.getKeyCode()){
			case KeyEvent.VK_RIGHT: countNum+=2; System.out.println(countNum); break;
			}
		}
		if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE && c != KeyEvent.VK_COMMA && e.getKeyCode() 
				!= KeyEvent.VK_LEFT && e.getKeyCode() != KeyEvent.VK_RIGHT && e.getKeyCode() != KeyEvent.VK_TAB) {
			e.consume();
			tk.beep();
		}
	}
	
	public boolean validaDouble(String input){
		try {
			Double teste = (Double.parseDouble(input));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}
	
	public FocusListener focusListenNome(JTextField texto, String exemplo) {
		return new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(texto.getText().isEmpty() || texto.getText().equals(exemplo)){
					texto.setBorder(defaultBorder);
					texto.setForeground(Color.GRAY);
					texto.setText("ex: José");
				} else if(!validaTexto(texto.getText())){
					texto.setBorder(naoValidou);
				} else {
					texto.setBorder(simValidou);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				texto.setForeground(Color.black);
				if(texto.getText().equals(exemplo)){
					texto.setText(null);
				}
			}
		};	
	}
	
	public FocusListener focusListenEndereço(JTextArea texto, String exemplo) {
		return new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(texto.getText().isEmpty() || texto.getText().equals(exemplo)){
					texto.setBorder(defaultBorder);
					texto.setForeground(Color.GRAY);
					texto.setText(exemplo);
				} else if(!validaTexto(texto.getText())){
					texto.setBorder(naoValidou);
				} else {
					texto.setBorder(simValidou);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				texto.setForeground(Color.black);
				if(texto.getText().equals(exemplo)){
					texto.setText(null);
				}
			}
		};	
	}
	
	public FocusListener focusListenDouble(JTextField texto, String exemplo) {
		return new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(texto.getText().isEmpty() || texto.getText().equals(exemplo)){
					texto.setBorder(defaultBorder);
					texto.setForeground(Color.GRAY);
					texto.setText(exemplo);
				} else if(!validaDouble(texto.getText().replace(',','.'))){
					texto.setBorder(naoValidou);
				} else {
					texto.setBorder(simValidou);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				countNum = 0;
				texto.setForeground(Color.black);
				if(texto.getText().equals(exemplo)){
					texto.setText(null);
				} else {
					countNum = 0;
					String[] substitui = texto.getText().split(",");
					texto.setText(substitui[0]);
				}
				texto.addKeyListener(keyListenDouble());
			}
		};	
	}
	
	public KeyListener keyListenDouble() {
		return new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				apenasDouble(e);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				e.consume();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				apenasDouble(e);
			}
		};
	}
	
	public FocusListener focusListenInt(JTextField texto, String exemplo) {
		return new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(texto.getText().isEmpty() || texto.getText().equals(exemplo)){
					texto.setBorder(defaultBorder);
					texto.setForeground(Color.GRAY);
					texto.setText(exemplo);
				} else if(!validaApenasNumeros(texto.getText())){
					texto.setBorder(naoValidou);
				} else {
					texto.setBorder(simValidou);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				texto.setForeground(Color.black);
				if(texto.getText().equals(exemplo)){
					texto.setText(null);
				}
				texto.addKeyListener(keyListenInt());
			}
		};
	}
			
	public KeyListener keyListenInt() {
		return new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				apenasNumeros(e);	
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				apenasNumeros(e);		
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				apenasNumeros(e);
			}
		};
	}
	
	public FocusListener focusListenEmail(JTextField texto, String exemplo) {
		return new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(texto.getText().isEmpty() || texto.getText().equals(exemplo)){
					texto.setBorder(defaultBorder);
					texto.setForeground(Color.GRAY);
					texto.setText(exemplo);
				} else if(!validaEmail(texto.getText())){
					texto.setBorder(naoValidou);
				} else {
					texto.setBorder(simValidou);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				texto.setForeground(Color.black);
				if(texto.getText().equals(exemplo)){
					texto.setText(null);
				}
			}
		};
	}
	
	public void apenasData(KeyEvent e) {
		char c = e.getKeyChar();
		if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE && c != KeyEvent.VK_SLASH && e.getKeyCode() 
				!= KeyEvent.VK_LEFT && e.getKeyCode() != KeyEvent.VK_RIGHT && e.getKeyCode() != KeyEvent.VK_TAB) {
			e.consume();
			tk.beep();
		}
	}
	
	public FocusListener focusListenData(JTextField texto, String exemplo) {
		return new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(texto.getText().isEmpty() || texto.getText().equals(exemplo)){
					texto.setBorder(defaultBorder);
					texto.setForeground(Color.GRAY);
					texto.setText(exemplo);
				} else if(!validaData(texto.getText())){
					texto.setBorder(naoValidou);
				} else {
					texto.setBorder(simValidou);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				texto.setForeground(Color.black);
				if(texto.getText().equals(exemplo)){
					texto.setText(null);
				}
				texto.addKeyListener(keyListenData());
			}
		};
	}
	
	public KeyListener keyListenData() {
		return new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				apenasData(e);	
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				apenasData(e);		
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				apenasData(e);
			}
		};
	}
	
	public FocusListener focusListenLimiteNumeros(JTextField texto, String exemplo, int limite) {
		return	new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(texto.getText().isEmpty() || texto.getText().equals(exemplo)){
					texto.setForeground(Color.GRAY);
					texto.setText(exemplo);
				} else if(!validaApenasNumeros(texto.getText())){
					texto.setBorder(naoValidou);
				} else {
					texto.setBorder(simValidou);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				texto.setForeground(Color.black);
				if(texto.getText().equals(exemplo)){
					texto.setText(null);
				}
				texto.addKeyListener(keyListenLimiteNumeros(texto, limite));
			}
		};
	}
	
	public KeyListener keyListenLimiteNumeros(JTextField texto, int limite) {
		return new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				apenasNumerosLimite(e, texto.getText(), 9);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				apenasNumerosLimite(e, texto.getText(), limite);
				if (texto.getText().length() == limite){
					texto.setBorder(simValidou);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				apenasNumerosLimite(e, texto.getText(), limite);
			}
		};
	}
	
	public FocusListener focusListenCpf(JTextField texto){
		return new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(texto.getText().equals("") || texto.getText().equals("ex: 12345678901")){
					texto.setForeground(Color.GRAY);
					texto.setBorder(defaultBorder);
					texto.setText("ex: 12345678901");
				} else if (validaCpf(texto.getText())){
					texto.setBorder(naoValidou);
				} else {
					texto.setBorder(simValidou);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				texto.setForeground(Color.black);
				if(texto.getText().equals("ex: 12345678901")){
					texto.setText(null);
				}
				texto.addKeyListener(new KeyListener() {
					
					@Override
					public void keyTyped(KeyEvent e) {
						apenasNumerosLimite(e, texto.getText(), 11);
					}
					
					@Override
					public void keyReleased(KeyEvent e) {
						apenasNumerosLimite(e, texto.getText(), 11);
						if (texto.getText().length() == 11){
							if (validaCpf(texto.getText())){
								texto.setBorder(simValidou);
							} else {
								texto.setBorder(naoValidou);
							}
						} else {
							texto.setBorder(naoValidou);
						}
					}
					
					@Override
					public void keyPressed(KeyEvent e) {
						apenasNumerosLimite(e, texto.getText(), 11);
					}
				});
			}
		};
	}
}
