package Algorithms;

import Shapes.Circle;
import Shapes.Line;
import Shapes.LineSegment;
import Shapes.Point;

import java.util.ArrayList;
import java.util.List;

public class Intersection {
    public static Point lineIntersection(Line line1, Line line2) {
        double A1 = line1.getA();
        double A2 = line2.getA();
        double B1 = line1.getB();
        double B2 = line2.getB();
        double C1 = line1.getC();
        double C2 = line2.getC();

        double det = A1 * B2 - A2 * B1;
        if (det == 0) {
            if (A1 * C2 - A2 * C1 == 0 && B1 * C2 - B2 * C1 == 0){
                System.out.println("Lines are equal. The intersection is infinite.");
            }
            return null;
        }

        double x = (B1 * C2 - B2 * C1) / det;
        double y = (A2 * C1 - A1 * C2) / det;
        return new Point(x, y);
    }

    public static Point lineSegmentIntersection(Line line, LineSegment lineSegment) {
        double A = line.getA();
        double B = line.getB();

        double xa = lineSegment.getStart().getX();
        double ya = lineSegment.getStart().getY();
        double xb = lineSegment.getEnd().getX();
        double yb = lineSegment.getEnd().getY();

        double deltaX = xb - xa;
        double deltaY = yb - ya;

        double denominator = A * deltaX + B * deltaY;
        if (denominator == 0) return null;

        double t = - line.lineValue(xa, ya) / denominator;
        if (t <0 || t > 1) return null;

        return new Point(xa + t * deltaX, ya + t * deltaY);
    }

    public static Point segmentSegmentIntersection(LineSegment lineSegment1, LineSegment lineSegment2) {
        double startX1 = lineSegment1.getStart().getX();
        double startY1 = lineSegment1.getStart().getY();
        double endX1 = lineSegment1.getEnd().getX();
        double endY1 = lineSegment1.getEnd().getY();
        double startX2 = lineSegment2.getStart().getX();
        double startY2 = lineSegment2.getStart().getY();
        double endX2 = lineSegment2.getEnd().getX();
        double endY2 = lineSegment2.getEnd().getY();

        double denominator = (startX1 - endX1) * (startY2 - endY2) - (startY1 - endY1) * (startX2 - endX2);
        if (denominator == 0) return null;

        double px = ((startX1 * endY1 - startY1 * endX1) * (startX2 - endX2) -
                (startX1 - endX1) * (startX2 * endY2 - startY2 * endX2)) / denominator;

        double py = ((startX1 * endY1 - startY1 * endX1) * (startY2 - endY2) -
                (startY1 - endY1) * (startX2 * endY2 - startY2 * endX2)) / denominator;

        if (isBetween(px, startX1, endX1) && isBetween(py, startY1, endY1) &&
            isBetween(px, startX2, endX2) && isBetween(py, startY2, endY2)) return new Point(px, py);
        return null;
    }

    private static boolean isBetween(double value, double bound1, double bound2) {
        return value >= Math.min(bound1, bound2) - 1e-12 && value <= Math.max(bound1, bound2) + 1e-12;
    }

    public static List<Point> lineCircleIntersection(Line line, Circle circle) {
        double A = line.getA();
        double B = line.getB();

        double centerX = circle.getCenter().getX();
        double centerY = circle.getCenter().getY();
        double radius = circle.getRadius();

        List<Point> points = new ArrayList<>();

        double distance = Math.abs(line.lineValue(centerX, centerY)) / Math.sqrt(A * A + B * B);
        if (distance > radius) return null;

        double denominator = A * A + B * B;
        if (denominator == 0) throw new ArithmeticException();

        double x0 = centerX - A * line.lineValue(centerX, centerY) / denominator;
        double y0 = centerY - B * line.lineValue(centerX, centerY) / denominator;

        if (distance == radius) {
            points.add(new Point(x0, y0));
            return points;
        }

        double offset = Math.sqrt(radius * radius - distance * distance);
        double mult = offset / Math.sqrt(denominator);

        Point p1 = new Point(x0 + B * mult, y0 - A * mult);
        Point p2 = new Point(x0 - B * mult, y0 + A * mult);
        points.add(p1);
        points.add(p2);
        return points;
    }

    public static List<Point> segmentCircleIntersection(LineSegment lineSegment, Circle circle) {
        double startX = lineSegment.getStart().getX();
        double startY = lineSegment.getStart().getY();
        double endX = lineSegment.getEnd().getX();
        double endY = lineSegment.getEnd().getY();

        double radius = circle.getRadius();
        double centerX = circle.getCenter().getX();
        double centerY = circle.getCenter().getY();

        double deltaX = endX - startX;
        double deltaY = endY - startY;

        double a = deltaX * deltaX + deltaY * deltaY;
        double b = 2 * (deltaX * (startX - centerX) + deltaY * (startY - centerY));
        double c = Math.pow((startX - centerX), 2) + Math.pow((startY - centerY), 2) - radius * radius;

        double discriminant = b * b - 4 * a * c - 1e-13;

        if (discriminant < 0) return null;

        List<Point> points = new ArrayList<>();

        double sqrtDiscriminant = Math.sqrt(discriminant);
        double t1 = (-b + sqrtDiscriminant) / (2 * a);
        double t2 = (-b - sqrtDiscriminant) / (2 * a);

        if (t1 == t2){
            if (t1 < 0 || t1 > 1) return null;
            points.add(new Point(startX + deltaX * t1, startY + deltaY * t1));
            return points;
        }

        if (t1 >= 0 && t1 <= 1) points.add(new Point(startX + deltaX * t1, startY + deltaY * t1));
        if (t2 >= 0 && t2 <= 1) points.add(new Point(startX + deltaX * t2, startY + deltaY * t2));

        return points;
    }

    public static List<Point> circleCircleIntersection(Circle circle1, Circle circle2) {
        double radius1 = circle1.getRadius();
        double radius2 = circle2.getRadius();

        double centerX1 = circle1.getCenter().getX();
        double centerY1 = circle1.getCenter().getY();
        double centerX2 = circle2.getCenter().getX();
        double centerY2 = circle2.getCenter().getY();

        double deltaX = centerX2 - centerX1;
        double deltaY = centerY2 - centerY1;

        double dist = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        if (dist > radius1 + radius2) {
            System.err.println("Distance between centers is greater than sum of radius. Algorithms.Intersection impossible");
            return null;
        }
        if (dist < Math.abs(radius1 - radius2)) {
            System.err.println("One circle inside another circle. Algorithms.Intersection impossible");
            return null;
        }
        if (dist - 1e-12 == 0){
            System.err.println("Circles are equal. Algorithms.Intersection infinite.");
            return null;
        }

        double a = (Math.pow(radius1, 2) - Math.pow(radius2, 2) + Math.pow(dist, 2)) / (2*dist);
        double x0 = centerX1 + a * deltaX / dist;
        double y0 = centerY1 + a * deltaY / dist;

        double h = Math.sqrt(Math.pow(radius1, 2) - Math.pow(a, 2));
        double rx = -deltaY * h / dist;
        double ry = deltaX * h / dist;

        List<Point> points = new ArrayList<>();

        if (dist == radius1 + radius2 || dist == Math.abs(radius1 - radius2)) {
            points.add(new Point(x0 + rx, y0 + ry));
            return points;
        }

        points.add(new Point(x0 + rx, y0 + ry));
        points.add(new Point(x0 - rx, y0 - ry));
        return points;
    }





}
