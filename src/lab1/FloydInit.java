package lab1;

import java.util.Map;

public class FloydInit extends Thread
{
	static int add(int a,int b)
	{
		if(a==Integer.MAX_VALUE||b==Integer.MAX_VALUE) return Integer.MAX_VALUE;
		else return a+b;
	}
	static boolean bigger(int a,int b)
	{
		if(b==Integer.MAX_VALUE) return false;
		else return a>b;
	}
	public void run()
	{
		final int size=Main.graph.indexof.size();
		Main.floyd_dis=new int[size][size];
		Main.floyd_path=new int[size][size];
		for(int i=0;i<size;i++) for(int j=0;j<size;j++)
		{
			if(i==j) Main.floyd_dis[i][j]=0;
			else Main.floyd_dis[i][j]=Integer.MAX_VALUE;
			Main.floyd_path[i][j]=0;
		}
		for(Map.Entry<Integer, StartPoint> i:Main.graph.startpoint.entrySet())
		{
			int u=i.getKey();
			for(EndPoint j:i.getValue().endpoint)
			{
				int v=j.index,w=j.weight;
				Main.floyd_dis[u][v]=w;
			}
		}
		for(int k=0;k<size;k++) for(int i=0;i<size;i++) for(int j=0;j<size;j++)
		{
			if(bigger(Main.floyd_dis[i][j],add(Main.floyd_dis[i][k],Main.floyd_dis[k][j])))
			{
				Main.floyd_dis[i][j]=add(Main.floyd_dis[i][k],Main.floyd_dis[k][j]);
				Main.floyd_path[i][j]=k;
			}
		}
		Main.floydready=true;
	}
}
