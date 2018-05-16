package questao05;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServidorHTTP
{
	private static final int PORTA_PADRAO = 8080;
	
	public static void main(String[] args) throws IOException
	{	ServerSocket soqueteServidor = null;
		try 
		{	soqueteServidor = new ServerSocket(PORTA_PADRAO);
			System.out.println("Servidor aguardando conexao...");
                        while (true) {
                                Socket soquete = soqueteServidor.accept();
                                System.out.println("Nova conexao estabelecida em " + new Date());
                                new Thread(new TratadorDeRequisicoes(soquete)).start();
                        }
		} catch (Exception e) 
		{	System.out.println(e.getMessage());
		} finally 
		{	soqueteServidor.close();	
		}
	}
}
