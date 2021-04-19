import javax.swing.*;
import java.awt.*;

public class Panneau extends JPanel
{	//Attributs
	private int[][] terrain;
	private int nbVies, timer, temps, score, nbReussites, niveau, vitesse, crocoal, turtleal;
	private String image;
	private Grenouille player;
	private Elements[] objet;
	private long debut, current, gap;
	private boolean victoire = false, defaite = false, test5Sec, test3Sec;
	
	public Panneau()
	{	//Remplissage du tableau
		terrain = new int[20][32];
		for (int x=0;x<32;x++)
		{	for (int y=0;y<3;y++)
			{	//Ajout des cases "maison" sous condition
				if ((x%6 == 4) || (x%6 == 3))
				{	terrain[y][x] = 3;
				}
				else
				{	terrain[y][x] = 2;
				}
			}
			for (int y=3;y<9;y++)
			{	terrain[y][x] = 2;
			}
			for (int y=9;y<20;y++)
			{	terrain[y][x] = 1;
			}
		}
		
		//Creation grenouille + le joueur re?it 5 vies
		player = new Grenouille();
		nbVies = 5;
		nbReussites = 0;
		score = 0;
		niveau = 1;
		timer = 30;
		
		//Initialisation tableau d'objets
		objet = new Elements[32];
		
		//Creation de ligne 1 => vitesse=2 type 2 et 3/4 (rondins et tortues) 3 RONDINS + 1 TORTUE
		int j = 0;
		for (int i=0; i<3; i++)
		{	objet[i] = new Elements(j,75,2,2,0);
			objet[i].setImage("Ressources/vaisseauD.gif");
			j = j + 225;
		}
		objet[3] = new Elements(j,75,2,3,0);
		objet[3].setImage("Ressources/tortueD.gif");
		
		//Creation de ligne 2 <= vitesse=1 2 RONDINS + 2 TORTUES
		objet[4] = new Elements(600,125,1,2,0);
		objet[5] = new Elements(425,125,1,3,0);
		objet[6] = new Elements(200,125,1,2,0);
		objet[7] = new Elements(25,125,1,3,0);
		
		//Creation de ligne 3 <= vitesse=2 4 RONDINS
		j = 725;
		for (int i=8; i<12; i++)
		{	objet[i] = new Elements(j,175,2,2,0);
			j = j - 225;
		}
		
		//Creation de ligne 4 (voitures) => 4 VOITURES vitesse=1
		j = 0;
		for (int i=12; i<16; i++)
		{	objet[i] = new Elements(j,275,1,1,0);
			objet[i].setImage("Ressources/monstreD.gif");
			j = j + 225;
		}
		
		//Creation de ligne 5 (voitures) <= 3 VOITURES vitesse=2
		j = 650;
		for (int i=16; i<19; i++)
		{	objet[i] = new Elements(j,375,2,1,0);
			objet[i].setImage("Ressources/monstreG.gif");
			j = j - 270;
		}
		
		//Creation crocodiles
		j = 75;
		for (int i=19; i<24; i++)
		{	objet[i] = new Elements(j,25,0,5,0);
			j = j + 150;
		}
		
		//Creation gungans victoire
		j = 75;
		for (int i=24; i<29; i++)
		{	objet[i] = new Elements(j,25,0,6,0);
			j = j + 150;
		}
		
		//Creation 3 bonus au hasard
		for (int i=29; i<32; i++)
		{	if ((int)(Math.random()*2) == 1)
			{	int k = (int)(Math.random()*11);
				int randomY = objet[k].getY() + (int)(Math.random()*2)*25;
				randomY = randomY - (randomY % 25);
				int distanceBonusObjet = 25 + (int)(Math.random()*3)*25;
				int randomX = objet[k].getX() + distanceBonusObjet;
				randomX = randomX - (randomX % 25);
				
				objet[i] = new Elements(randomX,randomY,0,7,distanceBonusObjet);
			}
			else
			{	int randomY = 225 + (int)(Math.random()*(326-225));
				randomY = randomY - (randomY % 25);
				int randomX = (int)(Math.random()*776);
				randomX = randomX - (randomX % 25);
				
				objet[i] = new Elements(randomX,randomY,0,7,0);
			}
		}
	}
	
	//Accesseurs	
	public int getNbVies()
	{	return nbVies;
	}
	
	public int getTimer()
	{	return timer;
	}
	
	public int getTemps()
	{ return temps;
	}
	
	public int getScore()
	{	return score;
	}
	
	public int getNbReussites()
	{	return nbReussites;
	}
	
	public int getNiveau()
	{	return niveau;
	}
	
	public int getVitesse()
	{	return vitesse;
	}
	
	public int getGap()
	{	return (int)gap;
	}
	
	public String getImage()
	{	return image;
	}
	
	public int getTerrain(int x, int y)
	{	return terrain[y][x];
	}
	
	public boolean getVictoire()
	{	return victoire;
	}
	
	public boolean getDefaite()
	{	return defaite;
	}
	//Modifieurs
	public void setNbVies(int a)
	{	nbVies = a;
	}
	
	public void setTimer(int a)
	{	timer = a;
	}
	
	public void setTemps(int a)
	{	temps = a;
	}
	
	public void setScore(int a)
	{ score = a;
	}
	
	public void setNbReussites(int a)
	{	nbReussites = a;
	}
	
	public void setNiveau(int a)
	{	niveau = a;
	}
	
	public void setVitesse(int a)
	{	vitesse = a;
	}
	
	public void setImage(String a)
	{	image = a;
	}
	
	public void setVictoire(boolean a)
	{	victoire = a;
	}
	
	public void setDefaite(boolean a)
	{	defaite = a;
	}
	//Methodes
	//Deplacement personnage
	public void monterJ()
	{	player.setY(player.getY() - 25);
		player.setImage("Ressources/jarjar.gif");
	}
	
	public void descendreJ()
	{	player.setY(player.getY() + 25);
		player.setImage("Ressources/jarjarB.gif");		
	}
	
	public void deplacementGaucheJ()
	{	player.setX(player.getX() - 25);
		player.setImage("Ressources/jarjarG.gif");
	}
	
	public void deplacementDroiteJ()
	{	player.setX(player.getX() + 25);
		player.setImage("Ressources/jarjarD.gif");
	}
	
	
	//Respawn
	public void respawn()
	{	debut = System.nanoTime() / 1000000000;
		this.creerBonus();
		player.setX(375);
		player.setY(450);
		player.setImage("Ressources/jarjar.gif");
		timer = 30;
	}
	
	
	//Verifs de jeu
	public void jeu()
	{	//Verifications victoire et defaite
		if (nbReussites >= 5)
		{	victoire = true;
		}
		else if (nbVies <= 0)
		{	defaite = true;
		}

	//Deplacement elements
	//Gauche : 4, 5, 6, 7, 8, 9, 10, 11, 16, 17, 18
		for (int i = 4; i<12; i++)
		{	objet[i].setX(objet[i].getX() - (2 * objet[i].getVitesse()));
		}
		for (int i = 16; i<19; i++)
		{	objet[i].setX(objet[i].getX() - (2 * objet[i].getVitesse()));
		}
	//Droite : 0, 1, 2, 3, 12, 13, 14, 15
		for (int i = 0; i<4; i++)
		{	objet[i].setX(objet[i].getX() + (2 * objet[i].getVitesse()));
		}
		for (int i = 12; i<16; i++)
		{	objet[i].setX(objet[i].getX() + (2 * objet[i].getVitesse()));
		}
	
	//Repositionne les elements qui sortent de l'ecran a la position opposee
		for (int i = 4; i<12; i++)
		{	if (objet[i].getX() <= (- objet[i].getLargeur()))
			{	objet[i].setX(917 - objet[i].getLargeur()); //917 -> pour que les elements se mettent a une case d'ecart
			}
		}
		for (int i = 16; i<19; i++)
		{	if (objet[i].getX() <= (- objet[i].getLargeur()))
			{	objet[i].setX(792);
			}
		}
		for (int i = 0; i<4; i++)
		{	if (objet[i].getX() >= 792)
			{	objet[i].setX(-125);
			}
		}
		for (int i = 12; i<16; i++)
		{	if (objet[i].getX() >= 792)
			{	objet[i].setX(- objet[i].getLargeur());
			}
		}
		//bonus
		for (int i = 29; i<32; i++)
		{	if ((objet[i].getX() >= 792) && (objet[i].getY() < 125))
			{	objet[i].setX(objet[i].getDistanceObjet() - 125);
			}
			else if ((objet[i].getX() <= (- objet[i].getLargeur())) && (objet[i].getY() >= 125))
			{	objet[i].setX(792 + objet[i].getDistanceObjet());
			}
		}
		
		//Crocodiles et tortues
		if ((gap%5 == 0) && (test5Sec)) //choisit un croco et une tortue toutes les 5 secs
		{	//crocodiles
			if (crocoal != 0)
			{	objet[crocoal].setVisible(false);
			}
				
			crocoal = 19 + (int)(Math.random()*(24-19)); //variable aleatoire placement croco dans une maison
			if (objet[crocoal+5].getVisible() == false)  // verifie qu'il n'y a pas deja un Gungan dans la maison
			{	objet[crocoal].setVisible(true);
			}
			test5Sec = false;

			// tortues
			turtleal = 1 + (int)(Math.random()*(2)); //variable aleatoire choix tortue
			turtleal = turtleal*2 + 1; // affectationnb aleatoire e ne tortue
			if (turtleal == 3)
			{	objet[turtleal].setImage("Ressources/tortue2D.gif"); // lance l'image d'immersion
			}
			else
			{	objet[turtleal].setImage("Ressources/tortue2.gif"); // lance l'image d'immersion
			}
			objet[turtleal].setEmmerge(false);
		}
		else if (gap%5 != 0)
		{	test5Sec = true;
		}
		if (((gap-3)%5 == 0) && test3Sec)
		{	test3Sec = false;
			if (turtleal != 0)
			{	objet[turtleal].setEmmerge(true);
				if (turtleal == 3)
				{	objet[turtleal].setImage("Ressources/tortueD.gif"); // remet l'image de base
				}
				else
				{	objet[turtleal].setImage("Ressources/tortue.gif"); // remet l'image de base
				}
			}
		}
		else if ((gap-3)%5 != 0)
		{	test3Sec = true;
		}
		
		//verification croco tue Jarjar
		if ( (player.getY() < (objet[crocoal].getY() + objet[crocoal].getHauteur())) 
			&& (((player.getX() >= objet[crocoal].getX()) && ((player.getX() <= (objet[crocoal].getX() + objet[crocoal].getLargeur())))) 
			|| (((player.getX() + player.getTaille()) >= objet[crocoal].getX()) && ((player.getX() + player.getTaille()) <= (objet[crocoal].getX() + objet[crocoal].getLargeur())))))
		{	nbVies--;
			this.respawn();
		}
		
		//Tester si Jarjar ou un bonus est sur une plateforme et le cas echeant lui donner la vitesse de la plateforme (0 a 11)
		for (int i=0;i<12;i++)
		{	if ((player.getX() >= objet[i].getX()) && (player.getX() <= (objet[i].getX() + objet[i].getLargeur())) && (player.getY() == objet[i].getY()))
			{	if (((i>=4) && (i<=11)) || (i == 10) || (i == 11))
				{	player.setX(player.getX() - objet[i].getVitesse()*2);
				}
				else if (player.getX() <= (objet[i].getX() + objet[i].getLargeur() - 50))
				{	player.setX(player.getX() + objet[i].getVitesse()*2);
				}
			}
			//Bonus
			for (int j=29; j<32; j++)
			{	if ((objet[j].getX() >= objet[i].getX()) && (objet[j].getX() <= (objet[i].getX() + objet[i].getLargeur())) && ((objet[j].getY() == objet[i].getY()) || (objet[j].getY() == objet[i].getY() + 25)))
				{	if (((i>=4) && (i<=11)) || (i == 10) || (i == 11))
					{	objet[j].setX(objet[j].getX() - objet[i].getVitesse()*2);
					}
					else if (player.getX() <= (objet[i].getX() + objet[i].getLargeur() - 50))
					{	objet[j].setX(objet[j].getX() + objet[i].getVitesse()*2);
					}
					else 
					{	objet[j].setX(objet[j].getX() + objet[i].getVitesse()*2);
					}
				}
			}
		}
	
	//Regarde si Jarjar est en dehors de la zone 2 et le recadre sur un X multiple de 25px s'il a ete deplace par les plateformes
	if (((player.getY() < 75) || (player.getY() >= 225)) && (player.getX() % 25 != 0))
		{	if ((player.getX() % 25) < 13)
			{	player.setX(player.getX() - (player.getX() % 25));
			}
			else
			{	player.setX(player.getX() + (player.getX() % 25));
			}
		}
	
	
	//Verifie si Jarjar entre en collision avec un bonus et lui accorde le bonus
		for (int i=29;i<32;i++)
		{	if (player.getY() < 225)
			{	if (objet[i].getVisible() 
				&& (((objet[i].getY() >= player.getY()) && (objet[i].getY() <= (player.getY() + player.getTaille()))) || ((objet[i].getY() <= player.getY()) && (objet[i].getY() >= (player.getY() + player.getTaille()))))
				&& (((objet[i].getX() >= player.getX()) && (((objet[i].getX() + objet[i].getLargeur()) <= (player.getX() + player.getTaille())) || ((objet[i].getX() <= player.getX()) && ((objet[i].getX() + objet[i].getLargeur()) >= player.getX())) || ((objet[i].getX() <= (player.getX() + player.getTaille())) && ((objet[i].getX() + objet[i].getLargeur()) >= (player.getX() + player.getTaille())) ) ))))
				{	objet[i].setVisible(false);
					score = score + 500;
				}
			}
			else
			{	if (objet[i].getVisible() && (((objet[i].getX() == player.getX()) && (objet[i].getY() == player.getY())) || (((objet[i].getX() - objet[i].getLargeur()) == player.getX()) && (objet[i].getY() == player.getY())) || (((objet[i].getX() - objet[i].getLargeur()) == player.getX()) && ((objet[i].getY() - objet[i].getHauteur()) == player.getY())) || ((objet[i].getX() == player.getX()) && ((objet[i].getY() - objet[i].getHauteur()) == player.getY())))) 
				{	objet[i].setVisible(false);
					score = score + 500;
				}
			}
		}
	
	//METHODES MORT + VICTOIRE
	//Verifie si le gungan est en dehors de l'ecran de jeu et le tue le cas echeant
		if ((player.getX() < 0) || (player.getX() > 742) || (player.getY() > 450))
		{	nbVies--;
			this.respawn();
		}
	
	//V?ifie si le gungan se fait manger par un monstre (voiture) : 4 CAS
		for (int i=12;i<19;i++)
		{	if ((((player.getX() + player.getTaille()) > objet[i].getX()) && ((player.getX() + player.getTaille()) < (objet[i].getX() + objet[i].getLargeur())) && ((((player.getY() + player.getTaille()) > objet[i].getY()) && ((player.getY() + player.getTaille()) < (objet[i].getY() + objet[i].getHauteur()))) || ((player.getY() > objet[i].getY()) && (player.getY() < (objet[i].getY() + objet[i].getHauteur()))))))
			{	nbVies--;
				this.respawn();
			}
			else if ((player.getX() > objet[i].getX()) && (player.getX() < (objet[i].getX() + objet[i].getLargeur())) && ((((player.getY() + player.getTaille()) > objet[i].getY()) && ((player.getY() + player.getTaille()) < (objet[i].getY() + objet[i].getHauteur()))) || ((player.getY() > objet[i].getY()) && (player.getY() < (objet[i].getY() + objet[i].getHauteur())))))
			{	nbVies--;
				this.respawn();
			}
			else if (((player.getX() + player.getTaille()) > objet[i].getX()) && ((player.getX() + player.getTaille()) < (objet[i].getX() + objet[i].getLargeur())) && (player.getY() == objet[i].getY()))
			{	nbVies--;
				this.respawn();
			}
			else if ((player.getX() > objet[i].getX()) && (player.getX() < (objet[i].getX() + objet[i].getLargeur())) && (player.getY() == objet[i].getY()))
			{	nbVies--;
				this.respawn();
			}
		}
	
	//V?ifie si le gungan est hors plateforme ou hors maisons et dans l'eau (zone 2) plateformes = 0 ?11 
	//Usage d'un boolean car il ne suffit que d'UNE plateforme / +- 20px : tol?ance
	//Verifie si le gungan est arriv??destination et change le num?o des cases de la maison ?4 s'il est arriv?
		boolean on = false;
		for (int i=0;i<12;i++)
		{	//Tester si sur platforme
			if (objet[i].getEmmerge() && ((player.getX() >= (objet[i].getX() - 20)) && (player.getX() <= (objet[i].getX() + player.getTaille() + 20)) && (player.getY() >= objet[i].getY()) && (player.getY() <= (objet[i].getY() + objet[i].getHauteur()))))
			{	on = true;
			}
		}
		//Tester si sur maison et qu'il n'y a pas de crocodile
		if (((terrain[player.getY()/25][player.getX()/25] == 3)
				|| (terrain[player.getY()/25 + 1][player.getX()/25] == 3)
				|| (terrain[player.getY()/25][player.getX()/25 + 1] == 3)
				|| (terrain[player.getY()/25 + 1][player.getX()/25 + 1] == 3))
			&& !( (player.getY() < (objet[crocoal].getY() + objet[crocoal].getHauteur())) 
			&& (((player.getX() >= objet[crocoal].getX()) && ((player.getX() <= (objet[crocoal].getX() + objet[crocoal].getLargeur())))) 
			|| (((player.getX() + player.getTaille()) >= objet[crocoal].getX()) && ((player.getX() + player.getTaille()) <= (objet[crocoal].getX() + objet[crocoal].getLargeur()))))))
		{	on = true;
			//Attribution des points
			score = score + 2000 + (int)gap*100;
			//Changer le code de la case objets 24 a 28
			if ((player.getX()/25) % 6 == 3)
			{	terrain[player.getY()/25][player.getX()/25] = 4;
				terrain[player.getY()/25][player.getX()/25 + 1] = 4;
				//Afficher un GUNGAN
				for (int j=24; j<29; j++)
				{	if ((player.getX() >= (objet[j].getX() - 25)) && (player.getX() <= (objet[j].getX() + 75)))
					{	objet[j].setVisible(true);
					}
				}
			}
			else if ((player.getX()/25) % 6 == 4)
			{	terrain[player.getY()/25][player.getX()/25] = 4;
				terrain[player.getY()/25][player.getX()/25 - 1] = 4;
				//Afficher un GUNGAN
				for (int j=24; j<29; j++)
				{	if ((player.getX() >= (objet[j].getX() - 25)) && (player.getX() <= (objet[j].getX() + 75)))
					{	objet[j].setVisible(true);
					}
				}
			}
			else
			{	terrain[player.getY()/25][player.getX()/25 + 2] = 4;
				terrain[player.getY()/25][player.getX()/25 + 1] = 4;
				//Afficher un GUNGAN
				for (int j=24; j<29; j++)
				{	if ((player.getX() >= (objet[j].getX() - 25)) && (player.getX() <= (objet[j].getX() + 75)))
					{	objet[j].setVisible(true);
					}
				}
			}
			this.respawn();
			nbReussites++;
		}
			
		//Interdire l'acces si 4 (maison d?eja prise)
		if ((terrain[player.getY()/25][player.getX()/25] == 4)
			|| (terrain[player.getY()/25 + 1][player.getX()/25] == 4)
			|| (terrain[player.getY()/25][player.getX()/25 + 1] == 4)
			|| (terrain[player.getY()/25 + 1][player.getX()/25 + 1] == 4))
		{	player.setY(player.getY() + 25);
		}
		//Tuer si dans l'eau
		else if ((on == false) && ((terrain[player.getY()/25][player.getX()/25] == 2)
			|| (terrain[player.getY()/25 + 1][player.getX()/25] == 2)
			|| (terrain[player.getY()/25][player.getX()/25 + 1] == 2)
			|| (terrain[player.getY()/25 + 1][player.getX()/25 + 1] == 2)))
		{	nbVies--;
			this.respawn();
		}
		
		on = false;
	}	
	
	public void startTimer()
	{	debut = System.nanoTime() / 1000000000;
		gap = 30;
	}
	
	public void timer()
	{	//Chrono
		if ((gap <= 0) && (nbVies > 0))
		{	nbVies--;
			this.respawn();
		}
		current = System.nanoTime() / 1000000000;
		gap = timer - (current - debut);
	}
	
	public void pauseTimer()
	{	timer = (int)gap;
	}
	
	public void resumeTimer()
	{	debut = System.nanoTime() / 1000000000;
	}
	
	public void creerBonus()
	{	for (int i=29; i<32; i++)
		{	if ((int)(Math.random()*2) == 1)
			{	int k = (int)(Math.random()*11);
				int randomY = objet[k].getY() + (int)(Math.random()*2)*25;
				randomY = randomY - (randomY % 25);
				int distanceBonusObjet = 25 + (int)(Math.random()*3)*25;
				int randomX = objet[k].getX() + distanceBonusObjet;
				randomX = randomX - (randomX % 25);
				
				objet[i] = new Elements(randomX,randomY,0,7,distanceBonusObjet);
			}
			else
			{	int randomY = 225 + (int)(Math.random()*(326-225));
				randomY = randomY - (randomY % 25);
				int randomX = (int)(Math.random()*776);
				randomX = randomX - (randomX % 25);
				
				objet[i] = new Elements(randomX,randomY,0,7,0);
			}
		}
	}
	
	public void paintComponent(Graphics g)
	{	//Animation victoire
		if (victoire)
		{	g.setColor(Color.BLACK);
			g.fillRect(0, 0, 792, 600);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("Ressources/victoire.gif"), 0, 0, 792, 550, this);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("Ressources/echap.gif"), 230, 500, 332, 18, this);
		}
		//Animation defaite
		else if (defaite)
		{	g.setColor(Color.BLACK);
			g.fillRect(0, 0, 792, 600);
			g.drawRect(0, 0, 792, 800);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("Ressources/defaite.gif"), 0, 0, 792, 550, this);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("Ressources/echap.gif"), 230, 500, 332, 18, this);
		}	
		else	
		{	//Background
			g.drawImage(Toolkit.getDefaultToolkit().getImage("Ressources/background.gif"), 0, 0, 792, 500, this);
			//Elements
			for (int cpt = 0; cpt < 12; cpt++)
			{	if (objet[cpt].getVisible())
				{	g.drawImage(Toolkit.getDefaultToolkit().getImage(objet[cpt].getImage()), objet[cpt].getX(), objet[cpt].getY(), objet[cpt].getLargeur(), objet[cpt].getHauteur(), this);
				}
			}
			//Bonus
			for (int cpt = 29; cpt < 32; cpt++)
			{	if (objet[cpt].getVisible())
				{	g.drawImage(Toolkit.getDefaultToolkit().getImage(objet[cpt].getImage()), objet[cpt].getX(), objet[cpt].getY(), objet[cpt].getLargeur(), objet[cpt].getHauteur(), this);
				}
			}
			//Voitures
			for (int cpt = 12; cpt < 29; cpt++)
			{	if (objet[cpt].getVisible())
				{	g.drawImage(Toolkit.getDefaultToolkit().getImage(objet[cpt].getImage()), objet[cpt].getX(), objet[cpt].getY(), objet[cpt].getLargeur(), objet[cpt].getHauteur(), this);
				}
			}
			
			//Joueur
			g.drawImage(Toolkit.getDefaultToolkit().getImage(player.getImage()), player.getX(), player.getY(), player.getTaille(), player.getTaille(), this);
			
			//Filtre
			g.drawImage(Toolkit.getDefaultToolkit().getImage("Ressources/filtreeau.png"), 0, 0, 792, 500, this);
			
			//Banniere bas
			Color grisclair = new Color(192,192,192);
			g.setColor(grisclair);
			g.fillRect(0,500,792,65);
			//Timer
			g.setColor(Color.GRAY);
			g.fillRect(50,515,100,35);
			//Compteur points
			g.fillRect(425,507,150,50);
			//NbReussites
			for (int cpt = 1; cpt <= nbReussites; cpt++)
			{	if (nbReussites > 5)
				{	nbReussites = 5;
				}
				g.drawImage(Toolkit.getDefaultToolkit().getImage("Ressources/gungan.png"), 120+cpt*50, 518, 30, 30, this);
			}
			//Barre de vie
			g.drawImage(Toolkit.getDefaultToolkit().getImage("Ressources/lightsaber"+nbVies+".png"), 609, 510, 150, 50, this);
			g.setColor(Color.BLACK);
		}
	}
}