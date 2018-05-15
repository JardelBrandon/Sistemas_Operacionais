package questao03;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FrameImagem extends FrameCentralizado
{
	private static final long serialVersionUID = 1L;
	private JLabel imagemLabel;

	public FrameImagem(String titulo)
    {   super(titulo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        imagemLabel = new JLabel();
        add(imagemLabel);
        setVisible(true);
    }

	public void setImagem(File arquivoImagem) 
	{	try 
		{	Image imagem = ImageIO.read(arquivoImagem);
			Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
			if (imagem.getWidth(null) > tela.getWidth() || imagem.getHeight(null) > tela.getHeight())
			{	double relacaoLargura = (double) tela.getWidth() / imagem.getWidth(null);
				double relacaoAltura = (double) tela.getHeight() / imagem.getHeight(null);
				double menorRelacao = Math.min(relacaoAltura, relacaoLargura);
				imagem = imagem.getScaledInstance((int) (imagem.getWidth(null) * menorRelacao), (int) (imagem.getHeight(null) * menorRelacao), Image.SCALE_SMOOTH);	
			}
			Icon icone = new ImageIcon(imagem);
			setSize(icone.getIconWidth(), icone.getIconHeight());
	        imagemLabel.setIcon(icone);
	        centralizar();
		} catch (IOException e) 
		{	e.printStackTrace();
		}
	}
}
