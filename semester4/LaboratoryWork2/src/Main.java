import Algorithms.GrahamScan;
import Shapes.Point;
import Shapes.Triangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Triangle> triangles = getTriangles();

        triangles.sort(Triangle::compareTo);

        for (int i = triangles.size() - 1; i >= 1; i--) {
            List<Point> triangle1 = triangles.get(i).getVertices();

            for (int j = i - 1; j >= 0; j--) {
                List<Point> triangle2 = triangles.get(j).getVertices();

                if (!Collections.disjoint(triangle1, triangle2)) {
                    continue;
                }

                List<Point> trianglesVertices = new ArrayList<>(Stream.concat(triangle1.stream(), triangle2.stream()).toList());
                List<Point> hull = GrahamScan.grahamScan(trianglesVertices);
                if (hull.size() == 3) {
                    Triangle hullTriangle = new Triangle(hull.get(0), hull.get(1), hull.get(2));
                    if (hullTriangle.equals(triangles.get(i))) {
                        System.out.printf("%s contains %s%n", triangles.get(i), triangles.get(j));
                    }
                }
            }
        }
    }

    private static List<Triangle> getTriangles() {
        List<Point> points = Arrays.asList(
                new Point(0, -3),
                new Point(0, 3),
                new Point(1, -1),
                new Point(1, 1),
                new Point(2, 0),
                new Point(3, 0),
                new Point(0, -9),
                new Point(-3, 4),
                new Point(7, 3));

        List<Triangle> triangles = new ArrayList<>();
        for (int i = 0; i < points.size() - 2; i++) {
            for (int j = i + 1; j < points.size() - 1; j++) {
                for (int k = j + 1; k < points.size(); k++) {
                    Triangle triangle = new Triangle(points.get(i), points.get(j), points.get(k));
                    if (triangle.getA() != null && triangle.getB() != null && triangle.getC() != null) triangles.add(triangle);
                }
            }
        }
        return triangles;
    }
}