package com.epam.rd.autotasks.segments;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

class Segment {

    private final Point start;
    private final Point end;

    public Segment(Point start, Point end) {
        if (start == null || end == null){
            throw new IllegalArgumentException();}
        if (start.equals(end)){
            throw new IllegalArgumentException();}
        if ((start.getX() == end.getX())&&(start.getY() == end.getY())){
            throw new IllegalArgumentException();}
        this.start = start;
        this.end = end;
    }

    double length() {
        double xDistanceSquare = Math.pow(start.getX() - end.getX(), 2);
        double yDistanceSquare = Math.pow(start.getY() - end.getY(), 2);
        return Math.sqrt(xDistanceSquare + yDistanceSquare);
    }

    Point middle() {
        return new Point( (start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2 );
    }

    Point intersection(Segment another) {

        double k1 = (end.getY()-start.getY())/(end.getX()-start.getX());
        double b1 = start.getY() - (end.getY()-start.getY())/(end.getX()-start.getX())*start.getX();
        double k2 = (another.end.getY()-another.start.getY())/(another.end.getX()-another.start.getX());
        double b2 = another.start.getY() - (another.end.getY()-another.start.getY())/(another.end.getX()-another.start.getX())*another.start.getX();
        double x = (b2 - b1)/(k1 - k2);
        double y = k2*(b2 - b1)/(k1 - k2) + b2;

        boolean xabba = (((x<=start.getX())&&(x>=end.getX()))||((x>=start.getX())&&(x<=end.getX())));
        boolean xcddc = ((x<=another.start.getX())&&(x>=another.end.getX()))||((x>=another.start.getX())&&(x<=another.end.getX()));
        boolean yabba = ((y<=start.getY())&&(y>=end.getY()))||((y>=start.getY())&&(y<=end.getY()));
        boolean ycddc = ((y<=another.start.getY())&&(y>=another.end.getY()))||((y>=another.start.getY())&&(y<=another.end.getY()));
        if (!(xabba && xcddc)&&!(yabba && ycddc)){return null;}
        else if ((k1 == k2)){return null;}
        else return new Point(x, y);

    }

}

