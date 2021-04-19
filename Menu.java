import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel	
{	public Menu()
	{
	}

	public void paintComponent(Graphics g)
	{	g.drawImage(Toolkit.getDefaultToolkit().getImage("Ressources/nass.jpg"), 0, 0, 792, 600, this);
	}
}