package com.epam.rd.autotasks.figures;

import java.util.Arrays;
import java.util.Objects;

import static java.lang.Math.max;
import static java.lang.Math.min;

class Quadrilateral extends Figure {
    private Point a, b, c, d;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        if (isNull(a, b, c, d) || isDegenerative(a,b,c,d) || isNonConvex(a,c,b,d)){
            throw new IllegalArgumentException();
        }
        this.a = new Point(a.getX(), a.getY());
        this.b = new Point(b.getX(), b.getY());
        this.c = new Point(c.getX(), c.getY());
        this.d = new Point(d.getX(), d.getY());
    }

    @Override
    public Point centroid() {
        Triangle abc = new Triangle(a,b,c);
        Triangle cda = new Triangle(c,d,a);
        Triangle bcd = new Triangle(b,c,d);
        Triangle dab = new Triangle(d,a,b);
        Point abcCentr = abc.centroid();
        Point cdaCentr = cda.centroid();
        Point bcdCentr = bcd.centroid();
        Point dabCentr = dab.centroid();

        double k1 = (abcCentr.getY() - cdaCentr.getY()) / (abcCentr.getX() - cdaCentr.getX());
        double k2 = (bcdCentr.getY() - dabCentr.getY()) / (bcdCentr.getX() - dabCentr.getX());

        double b1 = cdaCentr.getY() - k1*cdaCentr.getX();
        double b2 = dabCentr.getY() - k2*dabCentr.getX();
        double x = (b2-b1) / (k1-k2);
        double y = k1*x+b1;
        return new Point(x,y);
    }

    @Override
    public boolean isTheSame(Figure figure) {
        return this.equals(figure);
    }
    public boolean isDegenerative(Point a, Point b, Point c, Point d) {
        Triangle abc = new Triangle(new Point(7, 7), new Point(4, 0), new Point(5, 5));
        boolean rect1 = abc.isDegenerative(a, b, c);
        boolean rect2 = abc.isDegenerative(c, d, a);
        boolean rect3 = abc.isDegenerative(b, c, d);
        boolean rect4 = abc.isDegenerative(d, a, b);
        return rect1 || rect2 || rect3 || rect4;
    }

    private boolean isNull(Point a, Point b, Point c, Point d) {
        return (a == null) || (b == null) || (c == null) || (d == null);
    }
    private boolean isNonConvex(Point a, Point b, Point c, Point d) {
        return isIntersected(a, b, c, d) == null;
    }
    private Point isIntersected(Point a, Point b, Point c, Point d){
        double t = ((a.getX()-c.getX())*(c.getY()-d.getY())-(a.getY()-c.getY())*(c.getX()-d.getX()))/
                ((a.getX()-b.getX())*(c.getY()-d.getY())-(a.getY()-b.getY())*(c.getX()-d.getX()));
        double u = ((a.getX()-c.getX())*(a.getY()-b.getY())-(a.getY()-c.getY())*(a.getX()-b.getX()))/
                ((a.getX()-b.getX())*(c.getY()-d.getY())-(a.getY()-b.getY())*(c.getX()-d.getX()));
        if (t>=0 && t<=1 && u>=0 && u<=1) {
            double x = a.getX() + t*(b.getX()-a.getX());
            double y = a.getY() + t*(b.getY()-a.getY());
            return new Point(x,y);
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quadrilateral that = (Quadrilateral) o;
        Point [] points = new Point[]{a,b,c,d};
        Point [] points2 = new Point[]{that.a,that.b,that.c,that.d};
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (points[i].equals(points2[j])){
                    count++;
                }
            }
        }
        return count == 4;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c, d);
    }
}