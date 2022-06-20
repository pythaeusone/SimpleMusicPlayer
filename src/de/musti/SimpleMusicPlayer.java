package de.musti;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SimpleMusicPlayer {
	
	static Clip clip; // declare the Clip
	
	void playMusic(String songP)
	{

		try
		{			
			File songPath = new File(songP); // New Song filepath
			
			if(songPath.exists()) // file is there
			{
				
				AudioInputStream audio = AudioSystem.getAudioInputStream(songPath); // create stream with current path.
				
				clip = AudioSystem.getClip(); // Entrypoint to the Audioclip.
				clip.open(audio); // open Audiofile						
				clip.start();	// start Song.
				clip.loop(Clip.LOOP_CONTINUOUSLY);	// just for loop if needed.			
				
			}
			else
			{
				System.out.println("Unknown Songpath"); // File not there.
			}
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}
		
	}
	
	void stopSong()
	{
		clip.stop(); // Stop the Clip thread(daemon)
	}
	
	static double getMicroPosi() // Get the current position.
	{
		return (double)clip.getMicrosecondPosition()/1000000;
	}
	
	static double getMicroLength() // Get Song length.
	{
		return (double)clip.getMicrosecondLength()/1000000;
	}
	
}
