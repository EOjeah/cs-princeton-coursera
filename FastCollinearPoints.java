/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] initSegments;
    private int size = 0;
    private Point[] collinearPoints;
    // private double[] seenSlopes;

    public FastCollinearPoints(Point[] points) {

        if (points == null) {
            throw new IllegalArgumentException("no null points");
        }

        initSegments = new LineSegment[1];
        // collinearPoiints = new Point[1];

        int sizeOfSegments = points.length;

        for (int i = 0; i < sizeOfSegments; i++) {
            Point origin = points[i];
            if (origin == null) throw new IllegalArgumentException("no null points allowed");

            Point[] pointCopy = new Point[sizeOfSegments - 1];
            double[] slopes = new double[sizeOfSegments - 1];
            int x = 0;

            for (int j = 0; j < sizeOfSegments; j++) {
                if (j == i) continue;
                Point q = points[j];
                if (q == null) throw new IllegalArgumentException("no null points allowed");
                slopes[x] = origin.slopeTo(q);
                pointCopy[x++] = q;
            }
            Arrays.sort(pointCopy, origin.slopeOrder());
            Arrays.sort(slopes);
            // for (Point y: pointCopy) {
            //     System.out.printf("point: %s slopeToOrigin: %s = %f\n" , y, origin, origin.slopeTo(y));
            // }
            // for (Double y: slopes) {
            //     System.out.printf("slopes %f\n" , y);
            // }
            int firstPointIndex = 0;
            boolean equalPoints = false;
            for (int k = 2; k < pointCopy.length; k++) {
                // System.out.printf("FPI: %d, k: %d\n", firstPointIndex, k);

                if (slopes[k] != slopes[firstPointIndex]) {

                    // System.out.printf("FPI: %d, k: %d not equal\n", firstPointIndex, k);
                    if (equalPoints) {
                        // System.out.printf("FPI: %d, k: %d are equal!\n", firstPointIndex, k - 1);
                        Point[] newPoints = new Point[k - firstPointIndex + 1];
                        // Point smallestPoint = getSmallestPoint(new Point[]{})
                        for (int m = firstPointIndex; m < k; m++) {
                            newPoints[m - firstPointIndex] = pointCopy[m];
                        }
                        // System.out.printf("firstPointIndex: %d, k: %d\n", firstPointIndex, k);
                        newPoints[newPoints.length - 1] = origin;

                        // for (Point y: newPoints) {
                        //     System.out.printf("new point: %s \n", y);
                        // }

                        Point smallestPoint = getSmallestPoint(newPoints);
                        Point largestPoint = getLargestPoint(newPoints);
                        // System.out.printf("smallest : %s, largest: %s, slope: %s\n", smallestPoint, largestPoint, slopes[firstPointIndex]);
                        // System.out.println("===========================================================");
                        LineSegment lineSegment = new LineSegment(smallestPoint, largestPoint);

                        addToSegment(lineSegment);
                        System.out.println(lineSegment);
                        // addToSegment(lineSegment);
                        // firstPointIndex++;
                        equalPoints = false;
                    }
                    firstPointIndex++;

                } else if (slopes[k] == slopes[firstPointIndex]) {
                    equalPoints = true;
                    // firstPointIndex++;
                }
            }
            // System.out.println("===========================================================");

            // int count = 0;
            // for (first k = 0; k < pointCopy.length; k++) {
            //
            // }

        }
    }

    // private void resize(int capacity) {
    //
    // }

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

    private void removeDuplicateLineSegments(LineSegment[] segments) {
        for (LineSegment segment: segments) {

        }
    }

    private void addToSegment(LineSegment item) {
        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }
        if (size == initSegments.length) resize(2 * initSegments.length);
        initSegments[size++] = item;
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            // segment.draw();
        }
        StdDraw.show();
    }
}
