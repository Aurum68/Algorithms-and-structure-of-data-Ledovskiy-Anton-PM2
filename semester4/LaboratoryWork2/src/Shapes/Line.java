package Shapes;

public class Line {
    private final double A;
    private final double B;
    private final double C;

    public Line(double a, double b, double c) {
        A = a;
        B = b;
        C = c;
    }

    public double getA() {
        return A;
    }

    public double getB() {
        return B;
    }

    public double getC() {
        return C;
    }

    public double lineValue(double x, double y) {
        return A * x + B * y + C;
    }
}
