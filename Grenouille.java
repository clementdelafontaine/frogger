public class Grenouille
{
	//attributs
	private boolean etatVie;
	private int taille, x, y;
	private String image;

	public Grenouille ()
	{	x = 375;
		y = 450;
		etatVie = true;
		taille = 50;
		image = "Ressources/jarjar.gif";
	}

	//accesseurs
	public boolean getEtatVie()
	{	return etatVie;
	}
	
	public int getTaille()
	{	return taille;
	}
	
	public int getX()
	{	return x;
	}
	
	public int getY()
	{	return y;
	}
	
	public String getImage()
	{	return image;
	}

	//modifieurs
	public void setEtatVie(boolean a)
	{	etatVie = a;
	}
	
	public void setTaille(int a)
	{	taille = a;
	}
	
	public void setX(int a)
	{	x = a;
	}
	
	public void setY(int a)
	{	y = a;
	}
	
	public void setImage(String a)
	{	image = a;
	}
	
	//Méthodes	
	public void tuer()
	{	etatVie = false;
	}
}