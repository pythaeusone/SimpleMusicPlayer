package de.musti;

import java.util.Scanner;

public class Main {
	
	static SimpleMusicPlayer mp;
	static MThreads newThread;
	static boolean loops; // Scanner Loop
	static Scanner scan;
	static String[] music_list = {"ButtonMasher.wav","8BitElectronicLoop1.wav","8BitMinimalTechnoLoop1.wav"};
	static String filepath = "audio/" + music_list[0]; // Add Path with Songname.
	static int countSong; // counter for Current Song.

	public static void main(String[] args)
	{		
		
		playM();	// Start the player.
		

	}
	
	static void playM()
	{		
		
		mp = new SimpleMusicPlayer(); // MusicPlayer
		newThread = new MThreads();	// Thread to check Song Position.
		
		mp.playMusic(filepath); // play Song with the current Path.
		String[] textSplit = filepath.split("audio/"); // Split for Current Song
		String[] curentSong = textSplit[1].split(".wav"); // Split for Current Song
		System.out.println("Current Song: " + curentSong[0]); // Print Current Song.
		newThread.start(); // Start the thread to check Song Posi.
		
		loops = true;
		while(loops) // Scanner Loop
		{			
			scan = new Scanner(System.in);

				System.out.print("Enter x to Stop the Player : ");
				String next = scan.next();
				System.out.println(next);
				
				switch(next)
				{
				case "x":
					mp.stopSong();
					loops = false;
					System.exit(0);
					break;
				case "y":
					System.out.println(mp.getMicroPosi());
					break;
				default:
					System.out.println("....");
					break;
				}			
		}
		System.out.println("-------------------------------------");
	}
	
	static void goNext() // Add Song end, thread calls this.
	{
		mp.stopSong();
		loops = false;
		newThread.th = false;
		countSong++;
		nextSong();
	}
	
	static void nextSong() // prepare next Song.
	{
		int i = countSong;
		System.out.println(i);
		if(countSong >= music_list.length) // Add Listend, go back to first Song.
		{
			countSong = 0;
			i = countSong;
		}			
		filepath = "audio/" + music_list[i];
		loops = true;
		newThread.th = true;
		playM();
	}

}
