package lab1;


import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Main
{
	//TODO
	static final int img_w=660,img_h=800;
	static final int window_w=img_w+340,window_h=img_h+40;
	
	static DirectedGraph graph=new DirectedGraph();
	static boolean floydready=false;
	static Set<Integer> getbridge(String word1,String word2)
	{
		word1=word1.toLowerCase();
		word2=word2.toLowerCase();
		Integer n1=graph.indexof.get(word1),n2=graph.indexof.get(word2);
		if(n1==null||n2==null) return null;
		Set<Integer> ans=new HashSet<Integer>();
		for(EndPoint i:graph.startpoint.get(n1).endpoint)
		{
			if(i==null||graph.startpoint.get(i.index).endpoint==null) continue;
			for(EndPoint j:graph.startpoint.get(i.index).endpoint)
			{
				if(j==null) continue;
				if(graph.wordof.get(j.index).compareTo(word2)==0)
				{
					ans.add(i.index);
				}
			}
		}
		return ans;
	}
	static int floyd_dis[][]=null,floyd_path[][]=null;
	static Queue<Integer> floyd(int n1,int n2)
	{
		if(!graph.wordof.containsKey(n1)||!graph.wordof.containsKey(n2)) return null;
		Queue<Integer> ans=new LinkedList<Integer>();
		if(Main.floyd_dis[n1][n2]==Integer.MAX_VALUE) return ans;
		ans.offer(n1);
		System.out.println(graph.wordof.get(n1));
		Stack<Integer> s=new Stack<Integer>();
		s.push(n2);
		s.push(n1);
		while(!s.isEmpty())
		{
			int i=s.pop(),j=s.pop();
			if(i==j) continue;
			if(floyd_path[i][j]==0)
		    {
		    	System.out.println(graph.wordof.get(j));
		    	ans.offer(j);
		    }
			else
		    {
				s.push(j);s.push(floyd_path[i][j]);
				s.push(floyd_path[i][j]);s.push(i);
		    }
		}
		return ans;
	}
	static void outputjpg()
	{
		outputjpg(false);
	}
	static void outputjpg(boolean save_img)
	{
		try
		{
			String gvname="lab1_graph"+(int)(Math.random()*1000);
			PrintWriter writer = new PrintWriter("/dev/shm/"+gvname+".gv", "UTF-8");
			writer.println("digraph "+gvname);
			writer.println("{");
			for(Map.Entry<Integer,StartPoint> i:Main.graph.startpoint.entrySet())
			{
				System.out.println("{"+Main.graph.wordof.get(i.getKey())+"}");
				for(EndPoint j:i.getValue().endpoint)
				{
					System.out.println(Main.graph.wordof.get(j.index)+"["+j.weight+"]");
					writer.print("    ");
					writer.print(Main.graph.wordof.get(i.getKey()));
					writer.print(" -> ");
					writer.print(Main.graph.wordof.get(j.index));
					writer.print(" [label=\"");
					writer.print(j.weight);
					writer.print("\",color=");
					if(j.selected) writer.print("red");
					else writer.print("black");
					writer.println("]");
				}
				System.out.println();
			}
			writer.println("}");
			writer.println();
			writer.close();
			try
			{
				//String filename="/dev/shm/"+gvname+".jpg";
				Runtime.getRuntime().exec("dot /dev/shm/"+gvname+".gv -Tjpg -o /dev/shm/"+gvname+".jpg").waitFor();
				Runtime.getRuntime().exec("rm /dev/shm/"+gvname+".gv").waitFor();
				//TODO
				ImageIcon img=new ImageIcon("/dev/shm/"+gvname+".jpg");
				img.setImage(img.getImage().getScaledInstance(img_w,img_h,Image.SCALE_DEFAULT));
				graph_label.setIcon(img);
				if(save_img) Runtime.getRuntime().exec("cp /dev/shm/"+gvname+".jpg "+gvname+".jpg").waitFor();
				Runtime.getRuntime().exec("rm /dev/shm/"+gvname+".jpg").waitFor();
			}
			catch (InterruptedException e)
			{
				System.out.println("interrupted");
			}
		}
		catch (IOException e1)
		{
			System.out.println("output failed.");
		}
	}
	static JLabel graph_label=new JLabel();
	public static void main(String args[])
	{
		if(System.getProperty("os.name").toLowerCase().compareTo("linux")!=0)
		{
			System.out.println("This program can ONLY run in linux.");
			return;
		}
		try
		{
			Runtime.getRuntime().exec("dot -V");
		}
		catch (IOException e)
		{
			System.out.println("Package graphviz is required.");
			return;
		}
		JFrame fr_main = new JFrame();
		fr_main.setLayout(null);
		fr_main.setSize(window_w,window_h);
		fr_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr_main.setLocationRelativeTo(null);
		
		JLabel label_input=new JLabel("文件名：");
		label_input.setBounds(0,40,60,25);
		fr_main.add(label_input);
		JTextField text_input=new JTextField();
		text_input.setBounds(60,40,120,25);
		fr_main.add(text_input);
		JButton button_input=new JButton("从文件读取");
		button_input.setBounds(200,40,100,25);
		fr_main.add(button_input);
		JLabel label_input_message=new JLabel("");
		label_input_message.setBounds(130,70,170,25);
		fr_main.add(label_input_message);
		JButton button_output=new JButton("输出有向图");
		button_output.setBounds(0,70,100,25);
		fr_main.add(button_output);
		JCheckBox check_save=new JCheckBox("并保存为文件");
		check_save.setBounds(150,70,150,25);
		fr_main.add(check_save);
		
		JLabel label_word1=new JLabel("word1:");
		label_word1.setBounds(0,150,60,25);
		fr_main.add(label_word1);
		JTextField text_word1=new JTextField();
		text_word1.setBounds(60,150,120,25);
		fr_main.add(text_word1);
		JLabel label_word2=new JLabel("word2:");
		label_word2.setBounds(0,180,60,25);
		fr_main.add(label_word2);
		JTextField text_word2=new JTextField();
		text_word2.setBounds(60,180,120,25);
		fr_main.add(text_word2);
		JLabel label_word3=new JLabel("word3:");
		label_word3.setBounds(0,210,60,25);
		fr_main.add(label_word3);
		JTextField text_word3=new JTextField();
		text_word3.setBounds(60,210,240,25);
		fr_main.add(text_word3);
		JButton button_bridge=new JButton("计算桥接词");
		button_bridge.setBounds(200,150,100,25);
		fr_main.add(button_bridge);
		
		JLabel label_newtext=new JLabel("newtext:");
		label_newtext.setBounds(0,300,80,25);
		fr_main.add(label_newtext);
		JTextField text_newtext=new JTextField();
		text_newtext.setBounds(80,300,220,25);
		fr_main.add(text_newtext);
		JButton button_newtext=new JButton("转换新文本");
		button_newtext.setBounds(200,330,100,25);
		fr_main.add(button_newtext);
		JTextField text_newtext_converted=new JTextField();
		text_newtext_converted.setBounds(0,330,200,25);
		fr_main.add(text_newtext_converted);
		
		JLabel label_pathstart=new JLabel("最短路起点：");
		label_pathstart.setBounds(0,400,80,25);
		fr_main.add(label_pathstart);
		JTextField text_pathstart=new JTextField();
		text_pathstart.setBounds(80,400,220,25);
		fr_main.add(text_pathstart);
		JLabel label_pathend=new JLabel("最短路终点：");
		label_pathend.setBounds(0,430,80,25);
		fr_main.add(label_pathend);
		JTextField text_pathend=new JTextField();
		text_pathend.setBounds(80,430,220,25);
		fr_main.add(text_pathend);
		JLabel label_pathans=new JLabel("最短路长度：");
		label_pathans.setBounds(0,460,80,25);
		fr_main.add(label_pathans);
		JTextField text_pathans=new JTextField();
		text_pathans.setBounds(80,460,130,25);
		fr_main.add(text_pathans);
		JButton button_pathcal=new JButton("计算");
		button_pathcal.setBounds(230,460,70,25);
		fr_main.add(button_pathcal);
		
		JButton button_walk=new JButton("随机游走");
		button_walk.setBounds(0,540,100,25);
		fr_main.add(button_walk);
		JButton button_walk_stop=new JButton("停止");
		button_walk_stop.setBounds(100,540,100,25);
		fr_main.add(button_walk_stop);
		
		//TODO
		graph_label.setBounds(320,20,img_w,img_h);
		fr_main.add(graph_label);
		
		ActionListener act_input=new InputGraph(text_input,label_input_message);
		button_input.addActionListener(act_input);
		
		ActionListener act_output=new OutputGraph(check_save);
		button_output.addActionListener(act_output);
		
		ActionListener act_bridge=new QueryBridge(text_word1, text_word2, text_word3);
		button_bridge.addActionListener(act_bridge);
		
		ActionListener act_newtext=new ConvertNewText(text_newtext,text_newtext_converted);
		button_newtext.addActionListener(act_newtext);
		
		ActionListener act_pathcal=new ShortPath(text_pathstart,text_pathend,text_pathans);
		button_pathcal.addActionListener(act_pathcal);
		
		ActionListener act_walk=new RandomWalk();
		button_walk.addActionListener(act_walk);
		
		ActionListener act_walk_stop=new RandomWalk();
		button_walk_stop.addActionListener(act_walk_stop);
		
		fr_main.setVisible(true);
		
	}
}
