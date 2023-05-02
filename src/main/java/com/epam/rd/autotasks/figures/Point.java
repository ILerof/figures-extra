package com.epam.rd.autotasks.figures;

import java.util.Objects;

class Point {
    private double x;
    private double y;

    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return (Math.abs(point.x - x) < 0.0000001) && (Math.abs(point.y - y) < 0.0000001);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}