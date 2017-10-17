package lab1;

public class EndPoint
{
	public int index;
	public int weight;
	public boolean selected;
	EndPoint(int point_index,int edge_weight)
	{
		index=point_index;
		weight=edge_weight;
		selected=false;
	}
}
