package Algorithms;

import Shapes.Point;

import java.util.*;

public class GrahamScan {
    // Вспомогательная функция для определения ориентации
    private static double cross(Point o, Point a, Point b) {
        return (a.getX() - o.getX()) * (b.getY() - o.getY()) - (a.getY() - o.getY()) * (b.getX() - o.getX());
    }

    public static List<Point> grahamScan(List<Point> points) {
        if (points.size() < 3) throw new IllegalArgumentException("Graham scan requires at least three points");

        // Сортируем точки по x, затем по y
        Collections.sort(points);

        List<Point> lower = new ArrayList<>();
        for (Point p : points) {
            while (lower.size() >= 2 && cross(lower.get(lower.size() - 2), lower.getLast(), p) <= 0) {
                lower.removeLast();
            }
            lower.add(p);
        }

        List<Point> upper = new ArrayList<>();
        for (int i = points.size() - 1; i >= 0; i--) {
            Point p = points.get(i);
            while (upper.size() >= 2 && cross(upper.get(upper.size() - 2), upper.getLast(), p) <= 0) {
                upper.removeLast();
            }
            upper.add(p);
        }

        // Удаляем последний элемент каждого списка, чтобы избежать повторений
        lower.removeLast();
        upper.removeLast();

        // Объединяем
        lower.addAll(upper);
        return lower;
    }

}