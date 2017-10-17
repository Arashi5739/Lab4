package lab1;

import java.util.Map;

public class FlushGraph extends Thread
{
	public void run()
	{
		for(Map.Entry<Integer,StartPoint> i:Main.graph.startpoint.entrySet())
		{
			for(EndPoint j:i.getValue().endpoint)
			{
				j.selected=false;
			}
		}
	}
}
