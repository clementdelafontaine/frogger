import javax.swing.*;
import java.awt.*;

public class Pause extends JPanel	
{	public Pause()
	{	
	}

	public void paintComponent(Graphics g)
	{	g.setColor(Color.GRAY);
		g.drawImage(Toolkit.getDefaultToolkit().getImage("Ressources/pause.jpg"), 0, 0, 792, 600, this);
		g.fillRect(271,200,250,200);
	}
}