package lab1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JTextField;

public class QueryBridge implements ActionListener {
    JTextField textword1, textword2, textword3;
    QueryBridge(final JTextField word1, final JTextField word2, final JTextField word3) {
        textword1 = word1;
        textword2 = word2;
        textword3 = word3;
    }
    @Override
    public void actionPerformed(final ActionEvent e) {
        String word1 = textword1.getText(), 
               word2 = textword2.getText();
        Set<Integer> bridges = Main.getbridge(word1, word2);
        if (bridges == null) {
            System.out.println("No word1 or word2 in the graph!");
            textword3.setText("No word1 or word2 in the graph!");
        } else {
            if (bridges.isEmpty()) {
                System.out.println("No bridge words from word1 to word2!");
                textword3.setText("No bridge words from word1 to word2!");
            } else {
                StringBuilder output = new StringBuilder("The bridge words from word1 to word2 are:");
                boolean first = true;
                for (Integer i:bridges) {
                    if (first) {
                        output.append(Main.graph.wordof.get(i));
                    } else {
                        output.append(",").append(Main.graph.wordof.get(i));
                    }
                    first = false;
                }
                System.out.println(output.toString());
                textword3.setText(output.toString());
            }
        }
    }
}
