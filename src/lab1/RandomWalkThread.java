package lab1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.logging.Logger;


public class RandomWalkThread extends Thread {
    static final Logger log = Logger.getLogger(RandomWalkThread.class.getName());
    public void run() {
        PrintWriter writer;
            try {
                writer = new PrintWriter("RandomWalk", "UTF-8");
            } catch (FileNotFoundException e) {
                log.fine("File Not Found.");
                return;
            } catch (UnsupportedEncodingException e) {
                log.fine("Coding not support.");
                return;
            }

        int numbers = Main.graph.indexof.size();
        int[][] maplist = new int[numbers][numbers];
        int[][] mapfinding = new int[numbers][numbers];
        int[] mapcost = new int[numbers];
        for (int g = 0; g < numbers; g++) {
            for (int q = 0; q < numbers; q++) {
                if (!RandomWalk.running) {
                    writer.close();
                    return;
                }
                maplist[g][q] = 0;
            }
        }
        for (int g = 0; g < numbers; g++) {
            for (int q = 0; q < numbers; q++) {
                if (!RandomWalk.running) {
                    writer.close();
                    return;
                }
                mapfinding[g][q] = 0;
            }
        }
        for (Map.Entry<Integer, StartPoint> i:Main.graph.startpoint.entrySet()) {
            for (EndPoint j:i.getValue().endpoint) {
                if (!RandomWalk.running) {
                    writer.close();
                    return;
                }
                mapfinding[i.getKey()][j.index] = 1;
            }
        }
        for (int g = 0; g < numbers; g++) {
            int cost = 0;
            for (int q = 0; q < numbers; q++) {
                   if (mapfinding[g][q] == 1) {
                       if (!RandomWalk.running) {
                            writer.close();
                           return;
                        }
                        cost++;
                    }
            }
            mapcost[g] = cost;
        }
        int key = (int) (numbers * Math.random());
        while (RandomWalk.running) {
            int endnum = (int) (100 * Math.random());
            if (mapcost[key] == 0) {
                RandomWalk.running = false;
            } else if (mapcost[key] == 1) {
                endnum = 1;
            } else {
                endnum = endnum % mapcost[key];
            }
            for (int t = 0; t < numbers; t++) {
                int insert = 0;
                if (mapfinding[key][t] == 1) {
                    insert++;
                }
                if (insert == endnum) {
                    if (maplist[key][t] == 1) {
                        RandomWalk.running = false;
                        break;
                    } else {
                        maplist[key][t] = 1;
                        String tt = Main.graph.wordof.get(key) + "-" + Main.graph.wordof.get(t);
                        log.fine(tt);
                        writer.println(tt);
                        key = t;
                    }
                }
            }
        }
        writer.close();
        return;
    }
}
