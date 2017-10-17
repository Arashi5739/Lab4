package lab1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

public class OutputGraph implements ActionListener
{
	JCheckBox check_save;
	OutputGraph(JCheckBox save)
	{
		check_save=save;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Main.outputjpg(check_save.isSelected());
	}

}
