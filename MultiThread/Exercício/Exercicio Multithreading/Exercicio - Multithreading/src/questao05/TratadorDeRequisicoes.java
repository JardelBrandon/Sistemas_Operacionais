package questao05;

import java.io.IOException;

import java.io.OutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TratadorDeRequisicoes 
{
	private static final DateFormat FORMATADOR_DATA = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
	private Socket soquete;

	public TratadorDeRequisicoes(Socket soquete) 
	{	this.soquete = soquete;
	}

	public void escreverSaida() throws IOException 
	{	OutputStream saida = soquete.getOutputStream();
		saida.write("HTTP/1.1 200 OK".getBytes());
		saida.write("\r\n".getBytes());
		saida.write(("Date: " + FORMATADOR_DATA.format(new Date())).getBytes());
		saida.write("\r\n".getBytes());
		saida.write("Connection: Close".getBytes());
		saida.write("\r\n".getBytes());
		saida.write("Content-Type: text/html; charset=UTF-8".getBytes());
		saida.write("\r\n".getBytes());
		saida.write("\r\n".getBytes());
		saida.write("<html><head><title>Aplicacao WWW</title></head><body>".getBytes());
		saida.write("<body><html>".getBytes());
		saida.write("<h1>Hora atual</h1>".getBytes());
		saida.write(("<h2>" + FORMATADOR_DATA.format(new Date()) + "</h2>").getBytes());
		saida.write("</ul></body></html>".getBytes());
		soquete.close();
	}
}
