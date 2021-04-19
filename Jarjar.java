import java.io.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Jarjar
{	public static void main(String args[])
	{	Fenetre f = new Fenetre();
		f.setVisible(true);
		music();
	}
	
	public static void music()
	{	File soundFile = new File("Ressources/forcetheme.wav");
		AudioInputStream audioInputStream = null;
		try 
		{	audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e) {}
		AudioFormat audioFormat = audioInputStream.getFormat();
		SourceDataLine line = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class,audioFormat);
		try
		{	line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(audioFormat);
		} catch (LineUnavailableException e) {}
		catch (Exception e) {}
		line.start();
		int nBytesRead = 0;
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
}