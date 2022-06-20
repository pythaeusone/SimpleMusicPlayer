package de.musti;

import java.util.Scanner;

// Credits: ButtonMasher by Amber Waldron , 8BitMinimalTechnoLoop1 & 8BitElectronicLoop1 by jabameister.

public class Main {
	
	static SimpleMusicPlayer mp;
	static MThreads newThread;
	static boolean loops; // Scanner Loop
	static Scanner scan;
	static String[] music_list = {"ButtonMasher","8BitElectronicLoop1","8BitMinimalTechnoLoop1"};
	static String fileType = ".wav"; // only WAV format -.-
	static String filepath = "audio/" + music_list[0] + fileType; // Add Path with Songname.
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
		newThread.start(); // Start the thread to check Song Posi.
		String[] textSplit = filepath.split("audio/"); // Split for Current Song
		String[] curentSong = textSplit[1].split(".wav"); // Split for Current Song
		System.out.println(String.format("%-14s %-1s","Current Song: ", curentSong[0])); // Print Current Song.
		String duration = String.format("%.02f", mp.getMicroLength());
		System.out.println(String.format("%-14s %-1s %-1s","Duration: ", duration, "sec.")); // Print duration.
		System.out.println();
		
		loops = true;
		while(loops) // Scanner Loop
		{			
			scan = new Scanner(System.in);

				System.out.print("x = stop Player & Exit: ");
				System.out.println("\n");
				String next = scan.next();
				
				switch(next)
				{
				case "x":
					mp.stopSong();
					loops = false;
					newThread.th = false;
					System.exit(0);
					break;
				default:
					System.out.println("....");
					break;
				}			
		}
	}
	
	static void goNext() // Add Song end, thread calls this.
	{
		loops = false;
		newThread.th = false;
		mp.stopSong();		
		countSong++;
		nextSong();
	}
	
	static void nextSong() // prepare next Song.
	{
		int i = countSong;
		if(countSong >= music_list.length) // Add Listend, go back to first Song.
		{
			countSong = 0;
			i = countSong;
		}			
		filepath = "audio/" + music_list[i] + fileType;
		loops = true;
		newThread.th = true;
		playM();
	}

}
