package lab1;

public class EndPoint {
    public int index;
    public int weight;
    public boolean selected;
    EndPoint(final int point_index, final int edge_weight) {
        index = point_index;
        weight = edge_weight;
        selected = false;
    }
}
