public class Elements
{	//attributs
	private int  x, y, type, vitesse, largeur, hauteur, dObjet;
	private boolean fixe, emmerge, visible;
	private String image;

	public Elements(int X, int Y, int vit, int typ, int a)
	{	x = X;
		y = Y;
		vitesse = vit;
		type = typ;
		emmerge = true;
		visible = true;
		fixe = true;
		hauteur = 50;
		
		if (typ == 1)
		{	largeur = 100;
			image = "voiture.gif";
		}
		else if (typ == 2)
		{	largeur = 125;
			image = "Ressources/vaisseau.gif";
		}
		else if (typ == 3)
		{	image = "Ressources/tortue.gif";
			largeur = 100;
		}
		else if (typ == 5)
		{	visible = false;
			image = "Ressources/croco.gif";
			largeur = 50;
		}
		//Type 6 = affichage gungans dans les maisons
		else if (typ == 6)
		{	visible = false;
			image = "Ressources/gungan.png";
			largeur = 50;
		}
		//Type 7 = bonus de vie
		else if (typ == 7)
		{	image = "Ressources/boumasse.png";
			largeur = 25;
			hauteur = 25;
			dObjet = a;
		}
	}

	//accesseurs
	public int getX()
	{	return x;
	}
	
	public int getY()
	{	return y;
	}
	
	public int getHauteur()
	{	return hauteur;
	}
	
	public int getLargeur()
	{	return largeur;
	}
	
	public int getType()
	{	return type;
	}
	
	public int getVitesse()
	{	return vitesse;
	}
	
	public boolean getFixe()
	{	return fixe;
	}
	
	public boolean getEmmerge()
	{	return emmerge;
	}
	
	public boolean getVisible()
	{	return visible;
	}
	
	public String getImage()
	{	return image;
	}
	
	public int getDistanceObjet()
	{	return dObjet;
	}

	//modifieurs
	public void setX(int a)
	{	x = a;
	}
	
	public void setY(int a)
	{	y = a;
	}
	
	public void setHauteur(int a)
	{	hauteur = a;
	}
	
	public void setLargeur(int a)
	{	largeur = a;
	}
	
	public void setType(int a)
	{	type = a;
	}
	
	public void setVitesse(int a)
	{	vitesse = a;
	}
	
	public void setFixe(boolean a)
	{	fixe = a;
	}
	
	public void setEmmerge(boolean a)
	{	emmerge = a;
	}
	
	public void setVisible(boolean a)
	{	visible = a;
	}
	
	public void setImage(String a)
	{	image = a;
	}
}