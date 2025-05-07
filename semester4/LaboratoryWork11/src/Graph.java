import java.util.*;

public class Graph {
    public static class Node implements Comparable<Node> {


        private final int value;
        private int color;

        public Node(int value) {
            this.value = value;
        }

        public int getColor() {return color;}

        public void setColor(int color) {this.color = color;}

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return value == node.value;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(value);
        }

        @Override
        public String toString() {
            return String.format("Node{value=%d, color=%d}", value, color);
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(value, o.value);
        }
    }

    public record Edge(Node from, Node to) implements Iterable<Node> {


        private List<Node> getEdge() {
            return Arrays.asList(from, to);
        }

            @Override
            public boolean equals(Object o) {
                if (o == null || getClass() != o.getClass()) return false;
                Edge nodes = (Edge) o;
                return (Objects.equals(from, nodes.from) && Objects.equals(to, nodes.to))
                        || (Objects.equals(from, nodes.to) && Objects.equals(to, nodes.from));
            }

        @Override
            public String toString() {
                return String.format("(%s, %s)", from, to);
            }

            @Override
            public Iterator<Node> iterator() {
                return new Iterator<>() {
                    private int i = 0;

                    public boolean hasNext() {
                        return i < getEdge().size();
                    }

                    public Node next() {
                        return getEdge().get(i++);
                    }
                };
            }
        }

    private List<Node> nodes;
    private List<Edge> edges;

    public Graph() {}

    public Graph(List<Edge> edges) {
        this.nodes = new ArrayList<>();
        setEdges(edges);
//        for (Edge e : edges) {
//            for (Node n : e) {
//                if (!nodes.contains(n)) {nodes.add(n);}
//            }
//        }
//
//        Collections.sort(nodes);
    }

    private void setEdges(List<Edge> edges) {
        this.edges = new ArrayList<>();
        for (Edge e : edges) {
            if (!this.edges.contains(e)) {this.addEdge(e);}
        }
    }

    public void addEdge(Edge edge) {
        if (this.edges.contains(edge)) return;
        this.edges.add(edge);
        for (Node n : edge) {
            if (!nodes.contains(n)) {nodes.add(n);}
        }

        Collections.sort(nodes);
    }

    public List<Node> getNodes() {return nodes;}
    public List<Edge> getEdges() {return edges;}


    public List<Node> getAdjacentNodes(Node node) {
        List<Node> adjacentNodes = new ArrayList<>();

        for (Edge e : edges) {
            if (e.from().equals(node)) if (!adjacentNodes.contains(e.to())) adjacentNodes.add(e.to());
            if (e.to().equals(node)) if (!adjacentNodes.contains(e.from())) adjacentNodes.add(e.from());
        }
        return adjacentNodes;
    }

    @Override
    public String toString() {
        return edges.toString();
    }
}
