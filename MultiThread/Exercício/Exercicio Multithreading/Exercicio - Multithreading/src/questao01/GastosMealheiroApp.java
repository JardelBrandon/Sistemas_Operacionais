package questao01;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GastosMealheiroApp 
{
	public static void main(String[] args) 
	{	Homem homem = new Homem();
		Thread threadHomem = new Thread(homem);
		Mulher mulher = new Mulher();
		Thread threadMulher = new Thread(mulher);
                threadHomem.start();
                threadMulher.start();
            try {
                threadHomem.join();
                threadMulher.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(GastosMealheiroApp.class.getName()).log(Level.SEVERE, null, ex);
            }
                
		System.out.println("O ganhador é quem falou por último!");
	}
}
