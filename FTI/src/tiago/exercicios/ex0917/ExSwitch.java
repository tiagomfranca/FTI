package tiago.exercicios.ex0917;

import javax.swing.JOptionPane;

public class ExSwitch {
	public static int mes;
	public static String mesString = "";
	public static boolean verifica = false;
	
	public static void main(String[] args){
		recebeMes();
		try {
			transformaMes(mesString);
			obterMes();
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
		while (verifica == false){
			mesString = JOptionPane.showInputDialog("Insira o número do mês", "1-12");
			if (mesString != null && !mesString.equals("")){
				verifica = true;
			} else {
				JOptionPane.showMessageDialog(null, "Digite um número", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public static void transformaMes(String m) throws NumberFormatException{
			mes = Integer.parseInt(m);
	}

	public static void obterMes() throws MesException{
		switch (mes) {
		case 1: JOptionPane.showMessageDialog(null, "Janeiro"); break;
		case 2: JOptionPane.showMessageDialog(null, "Fevereiro"); break;
		case 3: JOptionPane.showMessageDialog(null, "Março"); break;
		case 4: JOptionPane.showMessageDialog(null, "Abril"); break;
		case 5: JOptionPane.showMessageDialog(null, "Maio"); break;
		case 6: JOptionPane.showMessageDialog(null, "Junho"); break;
		case 7: JOptionPane.showMessageDialog(null, "Julho"); break;
		case 8: JOptionPane.showMessageDialog(null, "Agosto"); break;
		case 9: JOptionPane.showMessageDialog(null, "Setembro"); break;
		case 10: JOptionPane.showMessageDialog(null, "Outubro"); break;
		case 11: JOptionPane.showMessageDialog(null, "Novembro"); break;
		case 12: JOptionPane.showMessageDialog(null, "Dezembro"); break;
		
		default: throw new MesException(1, "Número de mês inválido");
		}
	}
}