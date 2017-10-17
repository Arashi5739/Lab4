package lab1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;

import javax.swing.JTextField;

public class ShortPath implements ActionListener
{
	JTextField text_pathstart,text_pathend,text_pathans;
	ShortPath(JTextField pathstart,JTextField pathend,JTextField pathans)
	{
		text_pathstart=pathstart;
		text_pathend=pathend;
		text_pathans=pathans;
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(!Main.floydready) text_pathans.setText("未准备就绪");
		String s1=text_pathstart.getText(),s2=text_pathend.getText();
		Integer n1=Main.graph.indexof.get(s1),n2=Main.graph.indexof.get(s2);
		if(s2.compareTo("")==0)
		{
			n2=(int)(Math.random()*Main.graph.indexof.size());
			text_pathend.setText(Main.graph.wordof.get(n2));
		}
		if(n1==null||n2==null)
		{
			text_pathans.setText("单词不存在");
			return;
		}
		Queue<Integer> ans=Main.floyd(n1,n2);
		if(ans==null) text_pathans.setText("单词不存在");
		else if(ans.size()==0) text_pathans.setText("无可选路径");
		else
		{
			int lastnum=ans.peek();
			while(!ans.isEmpty())
			{
				int num=ans.poll();
				for(EndPoint i:Main.graph.startpoint.get(lastnum).endpoint) if(i.index==num) i.selected=true;
				lastnum=num;
			}
			text_pathans.setText(""+Main.floyd_dis[n1][n2]);
			Main.outputjpg();
			new FlushGraph().run();
		}
	}
}
