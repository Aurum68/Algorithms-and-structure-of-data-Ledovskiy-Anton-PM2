package Shapes;

public class LineSegment implements Comparable<LineSegment>{

    private final Point start;
    private final Point end;

    public LineSegment(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public Double length() {
        return Math.sqrt(Math.pow(end.getX() - start.getX(), 2) + Math.pow(end.getY() - start.getY(), 2));
    }

    @Override
    public int compareTo(LineSegment o) {
        return this.length().compareTo(o.length());
    }
}
