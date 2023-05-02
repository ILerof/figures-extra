package com.epam.rd.autotasks.figures;

import java.util.Objects;

class Circle extends Figure{
    private Point a;
    double radius;

    public Circle(Point a, double r) {
        if (!isCircle(r) || a == null){
            throw new IllegalArgumentException();
        }
        this.a = new Point(a.getX(), a.getY());
        this.radius = r;
    }
    @Override
    public Point centroid(){
        return new Point(a.getX(),a.getY());
    }

    @Override
    public boolean isTheSame(Figure figure) {
        return this.equals(figure);
    }


    private boolean isCircle(double radius){
        return radius > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return Math.abs(radius - circle.radius) < 0.0000001 && Objects.equals(a, circle.a);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, radius);
    }
}