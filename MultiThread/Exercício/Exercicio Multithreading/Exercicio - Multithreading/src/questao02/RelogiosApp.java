package questao02;

import java.text.ParseException;

public class RelogiosApp 
{
	public static void main(String[] args) throws ParseException 
	{	Relogio[] relogios = new Relogio[2];
		relogios[0] = new RelogioDigital();
		relogios[1] = new RelogioAnalogico();
		Alarme alarme = new Alarme(relogios);
                Thread threadAlarme = new Thread(alarme);
                alarme.start();
	}
}
