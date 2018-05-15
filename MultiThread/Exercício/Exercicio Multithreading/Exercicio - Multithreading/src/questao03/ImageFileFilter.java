package questao03;

import java.io.File;
import java.io.FileFilter;

import javax.imageio.ImageIO;

public class ImageFileFilter implements FileFilter 
{
	private static final String[] SUFIXOS = ImageIO.getReaderFileSuffixes();
	
	public boolean accept(File arquivo) 
	{	boolean aceita = false;
		for (String sufixo: SUFIXOS) 
		{	aceita = aceita || (arquivo.isFile() && arquivo.getName().toLowerCase().endsWith(sufixo));
		}
		return aceita;
	}
}
