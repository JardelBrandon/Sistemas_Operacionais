package questao02;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JFrame;


public class RelogioAnalogico extends JComponent implements Relogio 
{	
    private static final long serialVersionUID = 1L;
    private static final double TWO_PI   = 2.0 * Math.PI;
    private int diameter;                 // Height and width of clock face
    private int centerX;                  // x coord of middle of clock
    private int centerY;                  // y coord of middle of clock
    private BufferedImage _clockImage;     // Saved image of the clock face
    private int hora;
    private int minuto;
    
    public RelogioAnalogico() {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(this);
		window.pack();                          // Layout components
		window.setLocationRelativeTo(null);     // Center window.
		window.setVisible(true);
    	
		window.setBounds(250, 200, 150, 180); 
    }
    
 
    public void setHora(int hora)
	{	this.hora = hora;  
		repaint();
	}
	
	public void setMinuto(int minuto)
	{	this.minuto = minuto;
		repaint();
	}
    
    public void paintComponent(Graphics g)
    {	Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        
        //... The panel may have been resized, get current dimensions
        int w = getWidth();
        int h = getHeight();
        diameter = ((w < h) ? w : h);
        centerX = diameter / 2;
        centerY = diameter / 2;
        
        //... Create the clock face background image if this is the first time,
        //    or if the size of the panel has changed
        if (_clockImage == null
                || _clockImage.getWidth() != w
                || _clockImage.getHeight() != h) {
            _clockImage = (BufferedImage)(this.createImage(w, h));
            
            //... Get a graphics context from this image
            Graphics2D g2a = _clockImage.createGraphics();
            g2a.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                 RenderingHints.VALUE_ANTIALIAS_ON);
            drawClockFace(g2a);
        }
        
        //... Draw the clock face from the precomputed image
        g2.drawImage(_clockImage, null, 0, 0);
        
        //... Draw the clock hands dynamically each time.
        drawClockHands(g2);
    }
    
    
    private void drawClockHands(Graphics2D g2) 
    {	//... second hand
        int handMax = diameter / 2;    // Second hand extends to outer rim.
        //double fseconds = (seconds + (double)millis/1000) / 60.0;
        //drawRadius(g2, fseconds, 0, handMax);
        
        //... minute hand
        handMax = diameter / 3; 
        double fminutes = minuto / 60.0;
        drawRadius(g2, fminutes, 0, handMax);
        
        //... hour hand
        handMax = diameter / 4;
        drawRadius(g2, hora / 12.0, 0, handMax);
    }
    
    private void drawClockFace(Graphics2D g2) {
        // ... Draw the clock face.  Probably into a buffer.        
        g2.setColor(Color.PINK);
        g2.fillOval(0, 0, diameter, diameter);
        g2.setColor(Color.BLACK);
        g2.drawOval(0, 0, diameter, diameter);
        
        int radius = diameter / 2;
        
        //... Draw the tick marks around the circumference.
        for (int sec = 0; sec < 60; sec++) {
            int tickStart;
            if (sec%5 == 0) {
                tickStart = radius - 10;  // Draw long tick mark every 5.
            } else {
                tickStart = radius - 5;   // Short tick mark.
            }
            drawRadius(g2, sec / 60.0, tickStart , radius);
        }
    }
    
    private void drawRadius(Graphics2D g2, double percent, int minRadius, int maxRadius) 
    {
        double radians = (0.5 - percent) * TWO_PI;
        double sine   = Math.sin(radians);
        double cosine = Math.cos(radians);
        
        int dxmin = centerX + (int)(minRadius * sine);
        int dymin = centerY + (int)(minRadius * cosine);
        
        int dxmax = centerX + (int)(maxRadius * sine);
        int dymax = centerY + (int)(maxRadius * cosine);
        g2.drawLine(dxmin, dymin, dxmax, dymax);
    }


    public void horaMudou(int novaHora) 
	{	setHora(novaHora);
	}

	public void minutoMudou(int novoMinuto) 
	{	setMinuto(novoMinuto);	
	}
}