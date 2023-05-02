package com.epam.rd.autotasks.figures;

import java.util.Objects;

class Triangle extends Figure{
    private Point a, b, c;

    public Triangle(Point a, Point b, Point c) {
        if (!isNull(a, b, c) || isDegenerative(a, b, c)){
            throw new IllegalArgumentException();
        }
        this.a = new Point(a.getX(), a.getY());
        this.b = new Point(b.getX(), b.getY());
        this.c = new Point(c.getX(), c.getY());
    }

    @Override
    public Point centroid() {
        double x0 = (a.getX()+ b.getX()+c.getX())/3;
        double y0 = (a.getY()+ b.getY()+c.getY())/3;
        return new Point(x0, y0);
    }

    @Override
    public boolean isTheSame(Figure figure) {
        return this.equals(figure);
    }
    boolean isDegenerative(Point a, Point b, Point c){
        if (Math.abs(((a.getX()-c.getX())*(b.getY()-c.getY())-(b.getX()-c.getX())*(a.getY()-c.getY()))) == 0){
            return true;
        }
        return false;
    }
    private boolean isNull(Point a, Point b, Point c) {
        if ((a == null) || (b == null) || (c == null)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        Point [] points = new Point[]{a,b,c};
        Point [] points2 = new Point[]{triangle.a,triangle.b,triangle.c};
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (points[i].equals(points2[j])){
                    count++;
                }
            }
        }
        return count == 3;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}
