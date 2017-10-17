package lab1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class InputGraph implements ActionListener
{
	JTextField text_input;
	JLabel label_input_message;
	InputGraph(JTextField text,JLabel input_message)
	{
		text_input=text;
		label_input_message=input_message;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Main.floydready=false;
		Main.graph.clear();
		String filename=text_input.getText();
		try
		{
			Main.graph.readin(filename);
			System.out.println("read finish");
			label_input_message.setText("");
		}
		catch(IOException e1)
		{
			System.out.println("Error when reading file.");
			label_input_message.setText("文件读取出错");
		}
		new FloydInit().start();
	}
}
