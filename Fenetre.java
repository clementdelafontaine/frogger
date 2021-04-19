import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Fenetre extends JFrame implements ActionListener, Runnable, KeyListener
{	private Panneau p;
	private Thread tache1;
	private JLabel score, scoreText, timer;
	private boolean lance = false, paused = false, playerOn = false;
	private Accueil accueil;
	private Pause pause;
	private JButton b1, b2, b3, b4, b5, b6, b7, b8;
	private Player lecteur = new Player();
	private Credits credits;
	
	public Fenetre()
	{	this.setTitle("Gungan");
		this.setSize(800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(this);
		//this.setResizable(false);
		
		accueil();
	}
	
	public void reset()
	{	p = new Panneau();
		p.setLayout(null);
		p.setVictoire(false);
		p.setDefaite(false);
		this.setContentPane(p);
		
		lecture("Ressources/forcetheme.wav");
		
		//Permet de laisser le focus sur p
		p.addKeyListener(this);
		p.setFocusable(true);
		p.requestFocusInWindow();

		score = new JLabel(""+p.getScore(), JLabel.CENTER);
		timer = new JLabel("0", JLabel.CENTER);
		scoreText = new JLabel("Score", JLabel.CENTER);
		
		score.setFont(new Font("Distant Galaxy", Font.PLAIN, 24));
		score.setBounds(425,527,150,35);
		p.add(score);
		timer.setFont(new Font("Distant Galaxy", Font.PLAIN, 24));
		timer.setBounds(50,515,100,35);
		p.add(timer);
		scoreText.setFont(new Font("Distant Galaxy", Font.PLAIN, 24));
		scoreText.setBounds(425,505,150,35);
		p.add(scoreText);
		
		if (!lance)
		{	tache1 = new Thread(this);
			tache1.start();
			lance = true;
		}
		p.startTimer();
	}
	
	public void accueil()
	{	accueil = new Accueil();
		accueil.setLayout(null);
		this.setContentPane(accueil);
		this.setSize(801,601);
		this.setSize(800,600);
		
		lecture("Ressources/forcetheme.wav");
		
		//Changement police
		//Font swFont = Font.createFont(Font.TRUETYPE_FONT, new File("1.ttf"));
		//Boutons
		b1 = new JButton("Jouer");
		//b1.setFont(swFont);
		b1.setFont(new Font("Distant Galaxy", Font.PLAIN, 28));
		b1.setBounds(55,50,300,20);
		b1.setOpaque(false);
		b1.setContentAreaFilled(false);
		b1.setBorderPainted(false);
		//Event b1
		b1.addActionListener(this);
		accueil.add(b1);
		b2 = new JButton("Credits");
		//b2.setFont(swFont);
		b2.setFont(new Font("Distant Galaxy", Font.PLAIN, 28));
		b2.setBounds(55,100,300,20);
		b2.setOpaque(false);
		b2.setContentAreaFilled(false);
		b2.setBorderPainted(false);
		//Event b2
		b2.addActionListener(this);
		accueil.add(b2);
		b3 = new JButton("Quitter");
		//b3.setFont(swFont);
		b3.setFont(new Font("Distant Galaxy", Font.PLAIN, 28));
		b3.setBounds(55,150,300,20);
		b3.setOpaque(false);
		b3.setContentAreaFilled(false);
		b3.setBorderPainted(false);
		//Event b3
		b3.addActionListener(this);
		accueil.add(b3);
	}
	
	public void credits()
	{	credits = new Credits();
		this.setContentPane(credits);
		this.setSize(801,601);
		this.setSize(800,600);
		credits.setLayout(null);
		
		b8 = new JButton("Retour");
		//b8.setFont(swFont);
		b8.setFont(new Font("Distant Galaxy", Font.PLAIN, 30));
		b8.setBounds(246,375,300,40);
		b8.setOpaque(false);
		b8.setContentAreaFilled(false);
		b8.setBorderPainted(false);
		//Event b8
		b8.addActionListener(this);
		credits.add(b8);
	}
	
	public void pause()
	{	pause = new Pause();
		this.setContentPane(pause);
		this.setSize(801,601);
		this.setSize(800,600);
		pause.setLayout(null);
		
		//lecture("cucumber.wav");
		
		//Changement police
		//Font swFont = Font.createFont(Font.TRUETYPE_FONT, new File("1.ttf"));
		//Boutons
		b4 = new JButton("Menu");
		//b4.setFont(swFont);
		b4.setFont(new Font("Distant Galaxy", Font.PLAIN, 28));
		b4.setBounds(246,205,300,40);
		b4.setOpaque(false);
		b4.setContentAreaFilled(false);
		b4.setBorderPainted(false);
		//Event b4
		b4.addActionListener(this);
		pause.add(b4);
		b5 = new JButton("Reprendre");
		//b5.setFont(swFont);
		b5.setFont(new Font("Distant Galaxy", Font.PLAIN, 28));
		b5.setBounds(246,255,300,40);
		b5.setOpaque(false);
		b5.setContentAreaFilled(false);
		b5.setBorderPainted(false);
		//Event b5
		b5.addActionListener(this);
		pause.add(b5);
		b6 = new JButton("Rejouer");
		//b6.setFont(swFont);
		b6.setFont(new Font("Distant Galaxy", Font.PLAIN, 28));
		b6.setBounds(246,305,300,40);
		b6.setOpaque(false);
		b6.setContentAreaFilled(false);
		b6.setBorderPainted(false);
		//Event b6
		b6.addActionListener(this);
		pause.add(b6);
		b7 = new JButton("Quitter");
		//b7.setFont(swFont);
		b7.setFont(new Font("Distant Galaxy", Font.PLAIN, 28));
		b7.setBounds(246,355,300,40);
		b7.setOpaque(false);
		b7.setContentAreaFilled(false);
		b7.setBorderPainted(false);
		//Event b7
		b7.addActionListener(this);
		pause.add(b7);
	}
	
	public void lecture(String titre)
	{	if (!lecteur.getMusic().equals(titre))
		{	if (playerOn)
			{	lecteur.fin();
			}
			lecteur.setMusic(titre);
			lecteur.start();
			playerOn = true;
		}
	}
	
	public void actionPerformed(ActionEvent ae)
	{	if (ae.getSource() == b1)
		{	reset();
		}
		else if (ae.getSource() == b2)
		{	credits();
		}
		else if (ae.getSource() == b3)
		{	System.exit(0);
		}
		else if (ae.getSource() == b4)
		{	accueil();
		}
		else if (ae.getSource() == b5)
		{	//Reprendre
			if (!(p.getVictoire()) || !(p.getDefaite()))
			{	this.setContentPane(p);
				p.requestFocusInWindow();
				paused = false;
				p.resumeTimer();
				lecture("Ressource/forcetheme.wav");
			}
		}
		else if (ae.getSource() == b6)
		{	reset();
		}
		else if (ae.getSource() == b7)
		{	System.exit(0);
		}
		else if (ae.getSource() == b8)
		{	accueil();
		}
	}
	
	public void keyPressed(KeyEvent ke) 
	{	if (tache1.isAlive() & !p.getVictoire() & !p.getDefaite())
		{	if (ke.getKeyCode() == KeyEvent.VK_UP)
			{	p.monterJ();
			}
			else if (ke.getKeyCode() == KeyEvent.VK_DOWN)
			{	p.descendreJ();
			}
			else if (ke.getKeyCode() == KeyEvent.VK_RIGHT)
			{	p.deplacementDroiteJ();
			}
			else if (ke.getKeyCode() == KeyEvent.VK_LEFT)
			{	p.deplacementGaucheJ();
			}
		}
		if(ke.getKeyCode() == KeyEvent.VK_ESCAPE)
		{	p.pauseTimer();
			pause();
		}
		else if (ke.getKeyCode() == KeyEvent.VK_SPACE)
		{	lecture("Ressources/cantina.wav");
		}
	}
	
	public void keyReleased(KeyEvent ke) {
		// aucune action mais la m?hode doit appara?re
	}
	
	public void keyTyped(KeyEvent ke) {
		// aucune action mais la m?hode doit appara?re
	}
	
	public void run()
	{	while(tache1.isAlive())
		{	try
			{	if (!paused)
				{	//Chrono
					p.timer();
					if (p.getDefaite())
					{	timer.setText("");
						lecture("Ressources/cantina.wav");
					}
					else if (p.getVictoire())
					{	timer.setText("");
						lecture("Ressources/victoire.wav");
					}
					else
					{	timer.setText(""+p.getGap());
					}
					
					//Score
					if (p.getVictoire() || p.getDefaite())
					{	score.setText("");
						scoreText.setText("");
					}
					else
					{	score.setText(""+p.getScore());
					}
					
					//Verifs
					p.jeu();
					p.repaint();
				}
				tache1.sleep(50);
			}
			catch (Exception e) {}
		}
	}
}