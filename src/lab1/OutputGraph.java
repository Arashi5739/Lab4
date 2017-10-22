package lab1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

public class OutputGraph implements ActionListener {
    JCheckBox checksave;
    OutputGraph(final JCheckBox save) {
        checksave = save;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        Main.outputjpg(checksave.isSelected());
    }
}
