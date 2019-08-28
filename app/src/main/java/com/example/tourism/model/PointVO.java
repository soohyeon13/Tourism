package com.example.tourism.model;

public class PointVO {
    public double x;
    public double y;
    public String address;

    public boolean havePoint;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("x: ");
        builder.append(x);
        builder.append("y: ");
        builder.append(y);

        return builder.toString();
    }
}
