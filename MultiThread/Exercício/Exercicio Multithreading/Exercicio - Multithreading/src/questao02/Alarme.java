package questao02;

import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Alarme extends Thread
{
	private static final int INTERVALO = 100;
	private int hora;
	private int minuto;
	private boolean primeiraVez = true;
	private Relogio[] relogios;
        
	
        @Override
        public void run() {
            while (true) {
                if (this.primeiraVez) {
                    System.out.println("Ajustando relógios pela primeira vez!");
                    this.ajustaRelogios();
                    this.primeiraVez = false;
                }
                else if (this.hora != getHoraAtual() || this.minuto != getMinutoAtual()) {
                    System.out.println("Alteração no horário!");
                    ajustaRelogios();
                }
                esperarIntervalo();
            }
        }
        
        private void esperarIntervalo() {
            try {
                Thread.sleep(INTERVALO);
            } catch (InterruptedException ex) {
                Logger.getLogger(Alarme.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        private void ajustaRelogios() {
            this.hora = this.getHoraAtual();
            this.minuto = this.getMinutoAtual();
            this.disparaHoraMudou(hora);
            this.disparaMinutoMudou(minuto);
        }
        
	public Alarme(Relogio[] relogios)
	{	this.relogios = relogios;
	}
	
	private int getHoraAtual()
	{	//pega o momento atual
		GregorianCalendar momento = new GregorianCalendar();
		//pega a hora atual
		return momento.get(GregorianCalendar.HOUR_OF_DAY);
	}
	
	private int getMinutoAtual()
	{	//pega o momento atual
		GregorianCalendar momento = new GregorianCalendar();
		//pega o minuto atual
		return momento.get(GregorianCalendar.MINUTE);
	}

	private void disparaMinutoMudou(int novoMinuto) 
	{	for (Relogio relogio: relogios) 
		{	relogio.minutoMudou(novoMinuto);	
		}
	}

	private void disparaHoraMudou(int novaHora) 
	{	for (Relogio relogio: relogios) 
		{	relogio.horaMudou(novaHora);	
		}
	}
}
