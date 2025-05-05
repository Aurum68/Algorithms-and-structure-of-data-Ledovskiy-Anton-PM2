package Shapes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Triangle implements Comparable<Triangle>{

    private Point a;
    private  Point b;
    private Point c;

    private LineSegment ab;
    private LineSegment bc;
    private LineSegment ac;

    public Triangle(Point a, Point b, Point c) {
        if (!checkPointsOnLine(a, b, c)) {return;}
        this.a = a;
        this.b = b;
        this.c = c;
        ab = new LineSegment(a, b);
        bc = new LineSegment(b, c);
        ac = new LineSegment(a, c);
    }

    private boolean checkPointsOnLine(Point p1, Point p2, Point p3) {
        LineSegment l1 = new LineSegment(p1, p2);
        LineSegment l2 = new LineSegment(p2, p3);
        LineSegment l3 = new LineSegment(p3, p1);

        List<LineSegment> lineSegments = Arrays.asList(l1, l2, l3);
        lineSegments.sort(LineSegment::compareTo);
        return lineSegments.get(0).length() + lineSegments.get(1).length() != lineSegments.get(2).length();
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public Point getC() {
        return c;
    }

    public LineSegment getAb() {
        return ab;
    }

    public LineSegment getBc() {
        return bc;
    }

    public LineSegment getAc() {
        return ac;
    }

    public List<Point> getVertices() {
        return Arrays.asList(a, b, c);
    }

    public List<LineSegment> getEdges() {
        return Arrays.asList(ab, bc, ac);
    }

    @Override
    public String toString() {
        return String.format("{a=%s, b=%s, c=%s}", a, b, c);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Triangle other)) {return false;}
        List<Point> thisVertices = this.getVertices();
        Collections.sort(thisVertices);
        List<Point> otherVertices = other.getVertices();
        Collections.sort(otherVertices);
        return thisVertices.equals(otherVertices);
    }



    @Override
    public int compareTo(Triangle o) {
        return this.radiusMinCircle().compareTo(o.radiusMinCircle());
    }

    public Double radiusMinCircle(){
        return ab.length() * bc.length() * ac.length() / (4 * area());
    }

    private double area() {
        double p = perimeter() / 2;
        return Math.sqrt(p * (p - ab.length()) * (p - bc.length()) * (p - ac.length()));
    }

    private double perimeter() {
        return ab.length() + bc.length() + ac.length();
    }

}
