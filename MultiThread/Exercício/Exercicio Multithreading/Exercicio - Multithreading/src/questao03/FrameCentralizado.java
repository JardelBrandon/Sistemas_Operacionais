package questao03;


import javax.swing.JFrame;

public abstract class FrameCentralizado extends JFrame
{
	private static final long serialVersionUID = 1L;

	public FrameCentralizado(String titulo)
    {   super(titulo);
    }
    
    public void centralizar() 
    {   java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();  
        int componentWidth = this.getWidth();  
        int componentHeight = this.getHeight();  
        this.setBounds((screenSize.width-componentWidth)/2, (screenSize.height-componentHeight)/2, componentWidth, componentHeight);  
    }  
    
}
