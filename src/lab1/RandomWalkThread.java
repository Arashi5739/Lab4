package lab1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;


public class RandomWalkThread extends Thread
{
	public void run()
	{
		PrintWriter writer;
			try {
				writer = new PrintWriter("RandomWalk", "UTF-8");
			} catch (FileNotFoundException e) {
				System.out.println("File Not Found.");
				return;
			} catch (UnsupportedEncodingException e) {
				
				System.out.println("Coding not support.");
				return;
			}

		int numbers=Main.graph.indexof.size();
		int map_list[][]=new int[numbers][numbers];
		int map_finding[][]=new int[numbers][numbers];
		int map_cost[]=new int[numbers];
		for(int g=0;g<numbers;g++)
		{
			for(int q=0;q<numbers;q++)
			{
				if(RandomWalk.running==false)
				{
					writer.close();
					return;
				}	
				map_list[g][q]=0;
			}
		}
		for(int g=0;g<numbers;g++)
		{
			for(int q=0;q<numbers;q++)
			{
				if(RandomWalk.running==false)
				{
					writer.close();
					return;
				}	
				map_finding[g][q]=0;
			}
		}
		//int key=Main.graph.indexof.get("to");
		//Set<EndPoint> cur_end_point=Main.graph.startpoint.get(key).endpoint;
		for(Map.Entry<Integer,StartPoint> i:Main.graph.startpoint.entrySet())
		{
			//System.out.println("{"+Main.graph.wordof.get(i.getKey())+"}");
			for(EndPoint j:i.getValue().endpoint)
			{
				if(RandomWalk.running==false)
				{
					writer.close();
					return;
				}
				map_finding[i.getKey()][j.index]=1;
			}

		}
		for(int g=0;g<numbers;g++)
		{
			int cost=0;
			for(int q=0;q<numbers;q++)
			{				  	
				   if(map_finding[g][q]==1)
					{
					   if(RandomWalk.running==false)
						{
							writer.close();
						   return;
						}
					   //System.out.println(Main.graph.wordof.get(g)+"-"+Main.graph.wordof.get(q));
						cost++;
					}
			}
			map_cost[g]=cost;
			//System.out.println(map_cost[g]);
		}
		int key=(int) (numbers*Math.random());
		//System.out.println(key);
		while(RandomWalk.running)
		{			
			
			int endnum=(int) (100*Math.random());
			if(map_cost[key]==0)
			{
				RandomWalk.running=false;

			}
			else if(map_cost[key]==1)
			{
				endnum=1;
			}
			else
			{
				endnum=endnum%map_cost[key];
			}			
			//System.out.println(map_cost[key]);
		   //System.out.println(endnum);
			for(int t=0;t<numbers;t++)
			{
				int insert=0;
				if(map_finding[key][t]==1)
				{
					insert++;
				}
			    if(insert==endnum)
			    {
			    	if(map_list[key][t]==1)
			    	{
			    		RandomWalk.running=false;
			    		break;
			    	}
			    	else
			    	{
				    	map_list[key][t]=1;
				    	String tt=Main.graph.wordof.get(key)+"-"+Main.graph.wordof.get(t);
				    	System.out.println(tt);
				    	writer.println(tt);
				    	key=t;
				    	
			    	}
			    	//System.out.println(Main.graph.wordof.get(key)+"-"+Main.graph.wordof.get(t));
			    }
			}
			//RandomWalk.running=false;
			/*if(endnum==-1)
			{
				RandomWalk.running=false;
			}
			else if(map_list[key][endnum]==1)
            {
            	//System.out.println("orz");
            	RandomWalk.running=false;
            	//System.out.println("orz");
            }
            else
            {
            	map_list[key][endnum]=1;
            	key=endnum;
            }*/
            
			//System.out.println("orz");
			//judgement=1;
		}
		
		/*for(int g=0;g<numbers;g++)
		{
			for(int q=0;q<numbers;q++)
			{
				if((map_list[g][q]==1)&&(g!=q))
				{
					System.out.println(Main.graph.wordof.get(g));
					//System.out.println('-');
					System.out.println(Main.graph.wordof.get(q));
				}
			}
		}*/
		//System.out.println("orz");
		writer.close();
		return;
		//System.out.println();
	}
}
