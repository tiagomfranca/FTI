package tiago.exercicios.ex0919;

import javax.swing.JOptionPane;
import tiago.exercicios.ex0918.LocaleUtil;

public class TesteSalario {

	public static void main(String[] args) {
		CalculaSalario cs = new CalculaSalario();
		Object[] opções = { "Com comissão", "Sem comissão", "Cancelar" };
		int n = JOptionPane.showOptionDialog(null, "Calcular salário com ou sem comissão?", "Calcular salário",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opções, opções[2]);
		if (n == 0) {
			double salario = Double.parseDouble(JOptionPane.showInputDialog("Insira o sálario"));
			double comissao = Double.parseDouble(JOptionPane.showInputDialog("Insira a comissão"));
			JOptionPane.showMessageDialog(null, LocaleUtil.formataValor(cs.calculaSalario(salario, comissao), true), "Salário do funcionário com comissão", JOptionPane.DEFAULT_OPTION);
		} else if (n == 1){
			double salario = Double.parseDouble(JOptionPane.showInputDialog("Insira o Salario"));
			JOptionPane.showMessageDialog(null, LocaleUtil.formataValor(cs.calculaSalario(salario), true), "Salário do funcionário sem comissão", JOptionPane.DEFAULT_OPTION);
		}
	}
}