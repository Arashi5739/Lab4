package lab1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RandomWalk implements ActionListener
{
	static boolean running=false;
	@Override
	public void actionPerformed(ActionEvent e)
	{
		running=true;
		
		new RandomWalkThread().run();
		
		
		
	}
}
