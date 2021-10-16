/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

    private LineSegment[] initSegments;
    private int size = 0;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("no null points");
        }

        initSegments = new LineSegment[1];

        int sizeOfSegments = points.length;
        for (int i = 0; i < sizeOfSegments; i++)
            for (int j = i + 1; j < sizeOfSegments; j++)
                for (int k = j + 1; k < sizeOfSegments; k++)
                    for (int m = k + 1; m < sizeOfSegments; m++) {
                        Point p = points[i];
                        Point q = points[j];
                        Point r = points[k];
                        Point s = points[m];
                        if (p == null || q == null || r == null || s == null) {
                            throw new IllegalArgumentException("no null points permitted");
                        }
                        if (p.equals(q) || p.equals(r) || p.equals(s)) {
                            throw new IllegalArgumentException("no equal points permitted");
                        }
                        double first = p.slopeTo(q);
                        double second = p.slopeTo(r);
                        double third = p.slopeTo(s);
                        Point smallestPoint = getSmallestPoint(new Point[]{p, q, r, s});
                        Point largestPoint = getLargestPoint(new Point[]{p, q, r, s});
                        if (first == second && second == third) {
                            LineSegment lineSegment = new LineSegment(smallestPoint, largestPoint);
                            addToSegment(lineSegment);
                        }
                    }
    }

    private Point getSmallestPoint(Point[] points) {
        Point smallestPoint = points[0];
        for (Point currentPoint: points) {
            if (smallestPoint.compareTo(currentPoint) > 0) {
                smallestPoint = currentPoint;
            }
        }
        return smallestPoint;
    }

    private Point getLargestPoint(Point[] points) {
        Point largestPoint = points[0];
        for (Point currentPoint: points) {
            if (largestPoint.compareTo(currentPoint) < 0) {
                largestPoint = currentPoint;
            }
        }
        return largestPoint;
    }

    private void addToSegment(LineSegment item) {
        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }
        if (size == initSegments.length) resize(2 * initSegments.length);
        initSegments[size++] = item;
    }

    private void resize(int capacity) {
        LineSegment[] copy = new LineSegment[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = initSegments[i];
        }
        initSegments = copy;
    }

    public int numberOfSegments() {
        return initSegments.length;
    }

    public LineSegment[] segments() {
        return initSegments;
    }

    public static void main(String[] args) {

        // read the n points from a file

        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            // segment.draw();
        }
        StdDraw.show();
    }
}
