package tiago.exercicios.ex0918;

import java.util.Date;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;

public class ContaPoupança extends Conta{
	@Override
	public void imprimeExtrato() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
		Date date = new Date();
		JOptionPane.showMessageDialog(null, "Saldo: " + LocaleUtil.formataValor(this.getSaldo(), true) +
				"\nData: " + sdf.format(date), "##### Extrato da Conta #####", JOptionPane.INFORMATION_MESSAGE);
		System.out.println("##### Extrato da Conta #####\nSaldo: " + LocaleUtil.formataValor(this.getSaldo(), true) +
				"\nData: " + sdf.format(date));
		System.out.println("R$" + String.format("%.2f", this.getSaldo()));
//		System.out.println("Saldo: " + LocaleUtil.formataValor(this.getSaldo(), true));
//		System.out.println("Data: " + sdf.format(date));
		}
}