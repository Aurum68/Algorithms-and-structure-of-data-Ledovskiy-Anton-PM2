import java.util.*;
import java.util.regex.PatternSyntaxException;

public class GrahamScan {
    public static class Point implements Comparable<Point> {
        private final double x, y;
        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Point p) {
            if (this.x != p.x) return Double.compare(this.x, p.x);
            return Double.compare(this.y, p.y);
        }
        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }

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

    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        System.out.print("Enter coordinate separated by a space or Enter 'q' to quit: ");
        String input = in.nextLine();
        while (!input.equals("q")) {
            try {
                String[] tokens = input.split(" ");
                Point p = new Point(Double.parseDouble(tokens[0]), Double.parseDouble(tokens[1]));
                points.add(p);
            }catch (PatternSyntaxException | NumberFormatException | NullPointerException e){
                System.err.println(e.getMessage());
                System.exit(1);
            }
            System.out.print("Enter coordinate separated by a space or Enter 'q' to quit: ");
            input = in.nextLine();
        }

        List<Point> hull = grahamScan(new ArrayList<>(points));
        System.out.println("Оболочка (Грэхем): " + hull);
    }
}