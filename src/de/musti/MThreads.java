package de.musti;

public class MThreads extends Thread{
	
	boolean th;

	@Override
	public void run() // Thread method, but run with start()
	{
		th = true;
		
		while(th)
		{
			if(SimpleMusicPlayer.getMicroPosi() >= SimpleMusicPlayer.getMicroLength())
			{
				Main.goNext(); // Next Song when current ends.
			}				
		}
	}
	
}
