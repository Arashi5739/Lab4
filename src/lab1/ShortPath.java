package lab1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;

import javax.swing.JTextField;

public class ShortPath implements ActionListener {
    private transient final JTextField text_path_start, text_path_end, text_path_ans;
    ShortPath(final JTextField pathstart, final JTextField pathend, final JTextField pathans) {
        text_path_start = pathstart;
        text_path_end = pathend;
        text_path_ans = pathans;
    }
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (!Main.getfloydready()) {
            text_path_ans.setText("未准备就绪");
        }
        final String s1 = text_path_start.getText(), 
                     s2 = text_path_end.getText();
        Integer n1 = Main.graph.indexof.get(s1), 
                n2 = Main.graph.indexof.get(s2);
        if (s2.compareTo("") == 0) {
            n2 = (int) (Math.random() * Main.graph.indexof.size());
            text_path_end.setText(Main.graph.wordof.get(n2));
        }
        if (n1 == null || n2 == null) {
            text_path_ans.setText("单词不存在");
            return;
        }
        Queue<Integer> ans = Main.floyd(n1, n2);
        if (ans == null) {
            text_path_ans.setText("单词不存在");
        } else if (ans.size() == 0) {
            text_path_ans.setText("无可选路径");
        } else {
            int lastnum = ans.peek();
            while (!ans.isEmpty()) {
                int num = ans.poll();
                for (EndPoint i:Main.graph.startpoint.get(lastnum).endpoint) {
                    if (i.index == num) {
                        i.selected = true;
                    }
                }
                lastnum = num;
            }
            text_path_ans.setText("" + Main.floyd_dis[n1][n2]);
            Main.outputjpg();
            //new  FlushGraph().run();
            //*****************************************
            FlushGraph f=new  FlushGraph();
            //r.run();//这是方法调用，而不是开启一个线程
            Thread t=new Thread(f);//调用了Thread(Runnable target)方法。且父类对象变量指向子类对象。
            t.start();
            //可能不能用，你自己试试，我们尽力了 _(:з」∠)_
            //*****************************************
        }
    }
}
