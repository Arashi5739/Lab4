package lab1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JTextField;

public class ConvertNewText implements ActionListener
{
	JTextField text_newtext,text_newtext_converted;
	ConvertNewText(JTextField newtext,JTextField newtext_converted)
	{
		text_newtext=newtext;
		text_newtext_converted=newtext_converted;
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String[] words=text_newtext.getText().split("[^a-zA-Z]+");
		StringBuilder ans=new StringBuilder();
		for(int i=0;i<words.length-1;i++)
		{
			ans.append(words[i]).append(" ");
			Set<Integer> bridge=Main.getbridge(words[i],words[i+1]);
			if(bridge==null||bridge.isEmpty()) continue;
			ans.append(Main.graph.wordof.get(bridge.toArray()[0])).append(" ");
		}
		ans.append(words[words.length-1]);
		System.out.println(ans.toString());
		text_newtext_converted.setText(ans.toString());
	}
}
