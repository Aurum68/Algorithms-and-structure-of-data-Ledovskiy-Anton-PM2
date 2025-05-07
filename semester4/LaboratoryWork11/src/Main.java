import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Graph.Node> nodes = Arrays.asList(
                new Graph.Node(1),
                new Graph.Node(2),
                new Graph.Node(3),
                new Graph.Node(4),
                new Graph.Node(5),
                new Graph.Node(6)
                );

        Graph graph = new Graph(
                Arrays.asList(
                        new Graph.Edge(nodes.get(0), nodes.get(2)),
                        new Graph.Edge(nodes.get(1), nodes.get(2)),
                        new Graph.Edge(nodes.get(1), nodes.get(5)),
                        new Graph.Edge(nodes.get(5), nodes.get(3)),
                        new Graph.Edge(nodes.get(3), nodes.get(4)),
                        new Graph.Edge(nodes.get(3), nodes.get(0)),
                        new Graph.Edge(nodes.get(4), nodes.get(2))
                )
        );

        for (Graph.Node node : graph.getNodes()) {
            if (node.getColor() == 0) {
                int maxColor = 0;
                for (Graph.Node adjNode : graph.getAdjacentNodes(node)) {
                    if (adjNode.getColor() != 0) {maxColor = Math.max(maxColor, adjNode.getColor());}
                }
                node.setColor(maxColor+1);
            }
        }

        for (Graph.Edge edge : graph.getEdges()) {
            System.out.println(edge);
        }

    }

}