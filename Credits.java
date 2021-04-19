import javax.swing.*;
import java.awt.*;

public class Credits extends JPanel	
{	public Credits()
	{	
	}

	public void paintComponent(Graphics g)
	{	g.setColor(Color.GRAY);
		g.drawImage(Toolkit.getDefaultToolkit().getImage("Ressources/pause.jpg"), 0, 0, 792, 600, this);
		g.fillRect(50,75,692,400);
		g.drawImage(Toolkit.getDefaultToolkit().getImage("Ressources/chico.gif"), 250, 100, 300, 125, this);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Distant Galaxy", Font.PLAIN, 20));
		g.drawString("Code et images :", 310, 265);
		g.drawString("Musiques :", 345, 320);
		g.setFont(new Font("Distant Galaxy", Font.PLAIN, 15));
		g.drawString("Guillaume, Theophile et Clement - 021", 235, 290);
		g.drawString("Cantina theme - The Force theme", 250, 340);
		g.drawString("Distant Galaxy font - shyfonts.com", 415, 470);
	}
}