package questao02;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RelogioDigital implements Relogio
{      
	private JLabel labelHora = new JLabel();
	private JLabel labelMinuto = new JLabel();

	public RelogioDigital()
	{	JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		Font fonte = new Font("Itálico", Font.ITALIC, 35);
		labelHora.setFont(fonte);
		labelHora.setForeground(Color.GREEN);
		panel.add(labelHora);
		JLabel labelSeparador = new JLabel(":");
		labelSeparador.setFont(fonte);
		labelSeparador.setForeground(Color.GREEN);
		panel.add(labelSeparador);
		labelMinuto.setFont(fonte);
		labelMinuto.setForeground(Color.GREEN);
		panel.add(labelMinuto);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));   
		frame.getContentPane().add(panel);   
		frame.setResizable(false);   
		frame.setBounds(250, 200, 150, 80);   
		frame.setLocationRelativeTo(null);  
		frame.setVisible(true); 
		setHora(0);
		setMinuto(0);
	}   

	public void setHora(int hora)
	{	labelHora.setText(toDuasCasaDecimais(hora));   
	}
	
	public void setMinuto(int minuto)
	{	labelMinuto.setText(toDuasCasaDecimais(minuto));   
	}

	private String toDuasCasaDecimais(int valor)
	{	return ((valor < 10) ? "0" : "") + valor;
	}

	public void horaMudou(int novaHora) 
	{	setHora(novaHora);
	}

	public void minutoMudou(int novoMinuto) 
	{	setMinuto(novoMinuto);	
	}
} 
