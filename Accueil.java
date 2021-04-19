import javax.swing.*;
import java.awt.*;

public class Accueil extends JPanel	
{	public Accueil()
	{
	}

	public void paintComponent(Graphics g)
	{	g.drawImage(Toolkit.getDefaultToolkit().getImage("Ressources/nass.jpg"), 0, 0, 792, 600, this);
	}
}