package lab1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class InputGraph implements ActionListener {
    static final Logger log = Logger.getLogger(InputGraph.class.getName());
    JTextField text_input;
    JLabel label_input_message;
    InputGraph(final JTextField text, final JLabel input_message) {
        text_input = text;
        label_input_message = input_message;
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        Main.setfloydready(false);
        Main.graph.clear();
        String filename = text_input.getText();
        try {
            Main.graph.readin(filename);
            log.fine("read finish");
            label_input_message.setText("");
        } catch (IOException e1) {
            log.fine("Error when reading file.");
            label_input_message.setText("文件读取出错");
        }
        new FloydInit().start();
    }
}
