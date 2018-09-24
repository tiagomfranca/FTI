package tiago.exercicios.ex0919;

import javax.swing.JOptionPane;
import tiago.exercicios.ex0917.MesException;

public class TesteMes {
	public static int mes;
	public static String mesString = "";
	public static boolean verifica = false;
	
	public static void main(String[] args) {
		recebeMes();
		
		try {
			transformaMes(mesString);
			MesesEnum enumMes = MesesEnum.getInstance(mes);
			obterMes(enumMes);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Erro ao converter String em int", "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return;
		} catch (MesException e) {
			JOptionPane.showMessageDialog(null, "Número de mês inválido", "Erro", JOptionPane.ERROR_MESSAGE);
			System.out.println("IDErro " + e.getIdErro() + "\nMensagem: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void recebeMes() {
		while (verifica == false) {
			mesString = JOptionPane.showInputDialog("Insira o número do mês", "1-12");
			if (mesString != null && !mesString.equals("")) {
				verifica = true;
			} else {
				JOptionPane.showMessageDialog(null, "Digite um número", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public static void transformaMes(String m) throws NumberFormatException {
		mes = Integer.parseInt(m);
	}

	public static void obterMes(MesesEnum enumMes) throws MesException {
		if (MesesEnum.isJaneiro(enumMes)){
			JOptionPane.showMessageDialog(null, "Janeiro");
		} else if (MesesEnum.isFevereiro(enumMes)){
			JOptionPane.showMessageDialog(null, "Fevereiro");
		} else if (MesesEnum.isMarco(enumMes)){
			JOptionPane.showMessageDialog(null, "Março");
		} else if (MesesEnum.isAbril(enumMes)){
			JOptionPane.showMessageDialog(null, "Abril");
		} else if (MesesEnum.isMaio(enumMes)){
			JOptionPane.showMessageDialog(null, "Maio");
		} else if (MesesEnum.isJunho(enumMes)){
			JOptionPane.showMessageDialog(null, "Junho");
		} else if (MesesEnum.isJulho(enumMes)){
			JOptionPane.showMessageDialog(null, "Julho");
		} else if (MesesEnum.isAgosto(enumMes)){
			JOptionPane.showMessageDialog(null, "Agosto");
		} else if (MesesEnum.isSetembro(enumMes)){
			JOptionPane.showMessageDialog(null, "Setembro");
		} else if (MesesEnum.isOutubro(enumMes)){
			JOptionPane.showMessageDialog(null, "Outubro");
		} else if (MesesEnum.isNovembro(enumMes)){
			JOptionPane.showMessageDialog(null, "Novembro");
		} else if (MesesEnum.isDezembro(enumMes)){
			JOptionPane.showMessageDialog(null, "Dezembro");
		}
	}
}