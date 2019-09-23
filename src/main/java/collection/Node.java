package collection;

import java.util.ArrayList;
import java.util.List;

public class Node {
    //左子节点
    public Node leftNode;
    //右子节点
    public Node rightNode;

    public Object value;

    public void add(Object v) {
        if (null == value) {
            value = v;
        } else if ((int) v - (int) value <= 0) {
            if (null == leftNode)
                leftNode = new Node();
            leftNode.add(v);
        } else if ((int) v - (int) value > 0) {
            if (null == rightNode)
                rightNode = new Node();
            rightNode.add(v);
        }
    }

    public List<Object> values() {
        List<Object> values = new ArrayList<>();
        if (null != leftNode) {
            values.addAll(leftNode.values());
        }
        values.add(value);
        if (null != rightNode) {
            values.addAll(rightNode.values());
        }
        return values;
    }

    public static void main(String[] args) {
        int randoms[] = new int[] { 67, 7, 30, 73, 10, 0, 78, 81, 10, 74 };
        Node node =new Node();
        for(int num: randoms){
            node.add(num);
        }
        System.out.println(node.values());
    }
}
