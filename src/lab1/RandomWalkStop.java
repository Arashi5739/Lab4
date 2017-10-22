package lab1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RandomWalkStop implements ActionListener {
    @Override
    public void actionPerformed(final ActionEvent e) {
        RandomWalk.setrunning(false);
    }
}
