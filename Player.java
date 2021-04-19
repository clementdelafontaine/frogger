import java.io.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Player extends Thread
{	private Thread sound;
	private boolean stop;
	private SourceDataLine line = null;
	private int nBytesRead;
	private String titre = "none";
	
	public Player()
	{	sound = new Thread(this);
		sound.start();
		stop = true;
	}
	
	public void start()
	{	stop = false;
	}
	
	public void music()
	{	File soundFile = new File(titre);
		AudioInputStream audioInputStream = null;
		try 
		{	audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e) {}
		AudioFormat audioFormat = audioInputStream.getFormat();
		DataLine.Info info = new DataLine.Info(SourceDataLine.class,audioFormat);
		try
		{	line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(audioFormat);
		} catch (LineUnavailableException e) {}
		catch (Exception e) {}
		line.start();
		nBytesRead = 0;
		byte[] abData = new byte[128000];
		while (nBytesRead != -1)
		{	try
			{	nBytesRead = audioInputStream.read(abData, 0, abData.length);
			} catch (IOException e) {}
			if (nBytesRead >= 0)
			{	int nBytesWritten = line.write(abData, 0, nBytesRead);
			}
		}
		line.drain();
		line.stop();
	}
	
	public void setMusic(String nomSon)
	{	titre = nomSon;
	}
	
	public String getMusic()
	{	return titre;
	}
	
	public void fin()
	{	nBytesRead = -1;
		stop = true;
	}
	
	public void run()
	{	while(sound.isAlive())
		{	try
			{	if(!stop)
				{	music();
				}
				sleep(1);
			}
			catch (Exception e) {}
		}
	}
}