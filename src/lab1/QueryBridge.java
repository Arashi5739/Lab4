package lab1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JTextField;

public class QueryBridge implements ActionListener
{
	JTextField text_word1,text_word2,text_word3;
	QueryBridge(JTextField word1,JTextField word2,JTextField word3)
	{
		text_word1=word1;
		text_word2=word2;
		text_word3=word3;
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String word1=text_word1.getText(),word2=text_word2.getText();
		Set<Integer> bridges=Main.getbridge(word1, word2);
		if(bridges==null)
		{
			System.out.println("No word1 or word2 in the graph!");
			text_word3.setText("No word1 or word2 in the graph!");
		}
		else
		{
			if(bridges.isEmpty())
			{
				System.out.println("No bridge words from word1 to word2!");
				text_word3.setText("No bridge words from word1 to word2!");
			}
			else
			{
				StringBuilder output=new StringBuilder("The bridge words from word1 to word2 are:");
				boolean first=true;
				for(Integer i:bridges)
				{
					if(first) output.append(Main.graph.wordof.get(i));
					else output.append(",").append(Main.graph.wordof.get(i));
					first=false;
				}
				
				System.out.println(output.toString());
				text_word3.setText(output.toString());
			}
		}
	}
}
