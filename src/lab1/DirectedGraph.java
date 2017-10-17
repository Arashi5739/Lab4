package lab1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DirectedGraph
{
	public Map<String,Integer> indexof=new HashMap<String,Integer>();
	public Map<Integer,String> wordof=new HashMap<Integer,String>();
	public Map<Integer,StartPoint> startpoint=new HashMap<Integer,StartPoint>();
	DirectedGraph(){}
	void clear()
	{
		indexof.clear();
		wordof.clear();
		startpoint.clear();
	}
	boolean readin(String filename) throws IOException
	{
		File fin=new File(filename);
		String w1=null,w2=null;
		int n=0;
		while((w2=fin.getsingalword())!=null)
		{
			if(!indexof.containsKey(w2))
			{
				indexof.put(w2,n);
				wordof.put(n++,w2);
			}
			if(w1!=null)
			{
				Integer n1=indexof.get(w1),n2=indexof.get(w2);
				if(n1!=null&&n2!=null)
				{
					//System.out.println(w1+"-"+w2);
					if(startpoint.containsKey(n1))
					{
						StartPoint t=startpoint.get(n1);
						for(EndPoint i:t.endpoint)
						{
							if(i.index==n2)
							{
								i.weight++;
								t=null;
								break;
							}
						}
						if(t!=null) t.endpoint.add(new EndPoint(n2,1));
					}
					else
					{
						StartPoint t=new StartPoint();
						t.endpoint.add(new EndPoint(n2,1));
						startpoint.put(n1,t);
						
					}
				}
			}
			
			w1=w2;
		}
		return true;
	}
}
