package lab1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RandomWalk implements ActionListener {
    private static boolean running = false;
    public static boolean getrunning() {
        return running;
    }
    public static void setrunning(boolean running) {
        RandomWalk.running = running;
    }
    @Override
    public void actionPerformed(final ActionEvent e) {
        RandomWalk.setrunning(true);
        //new RandomWalkThread().run();
        RandomWalkThread r = new RandomWalkThread();
        Thread t=new Thread(r);
        t.start();
    }

}