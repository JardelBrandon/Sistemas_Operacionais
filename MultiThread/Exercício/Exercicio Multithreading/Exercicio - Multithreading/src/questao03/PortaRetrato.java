package questao03;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PortaRetrato implements Runnable
{
	private File diretorio;
	private FrameImagem frameImagem;
	private long tempo;
        
        @Override
        public void run() {
            while (true) {
                File[] imagens = this.getArquivosImagens();
                
                for (File imagem: imagens) {
                    this.frameImagem.setImagem(imagem);
                    aguardarTempo();
                }
            }
        }
        
        private void aguardarTempo() {
            try {
                Thread.sleep(tempo);
            } catch (InterruptedException ex) {
                Logger.getLogger(PortaRetrato.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

	public PortaRetrato(String imagensDir, long tempo) 
	{	this.diretorio = new File(imagensDir);
		this.tempo = tempo;
		this.frameImagem = new FrameImagem("Digital Portrait");
	}
	
	private File[] getArquivosImagens() 
	{	return diretorio.listFiles(new ImageFileFilter());
	}
}
