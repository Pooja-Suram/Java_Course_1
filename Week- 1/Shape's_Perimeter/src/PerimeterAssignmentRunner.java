//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import edu.duke.Point;
import edu.duke.Shape;
import java.io.File;
import java.util.Iterator;

public class PerimeterAssignmentRunner {
    public PerimeterAssignmentRunner() {
    }

    public double getPerimeter(Shape s) {
        double totalPerim = 0.0D;
        Point prevPt = s.getLastPoint();

        Point currPt;
        for(Iterator var5 = s.getPoints().iterator(); var5.hasNext(); prevPt = currPt) {
            currPt = (Point)var5.next();
            double currDist = prevPt.distance(currPt);
            totalPerim += currDist;
        }

        return totalPerim;
    }

    //this method returns number of points in a shape
    public int getNumPoints(Shape s) {
        int numPoints = 0;

        for(Iterator var3 = s.getPoints().iterator(); var3.hasNext(); ++numPoints) {
            Point pt = (Point)var3.next();
        }

        return numPoints;
    }


    //returns average of all sides lengths
    public double getAverageLength(Shape s) {
        double perimeter = getPerimeter(s);
        int numberOfPoints = getNumPoints(s);
        return perimeter / (double)numberOfPoints;
    }

    //this method returns longest side in given shape S
    public double getLargestSide(Shape s) {
        double largeSide = 0.0D;
        Point prevPoint = s.getLastPoint();
        Iterator var5 = s.getPoints().iterator();

        while(var5.hasNext()) {
            Point currentPoint = (Point)var5.next();
            double distance = prevPoint.distance(currentPoint);
            prevPoint = currentPoint;
            if (largeSide < distance) {
                largeSide = distance;
            }
        }

        return largeSide;
    }

    /*this returns largest x coordinate
    of all points in given shape*/
    public double getLargestX(Shape s) {
        double largeXValue = 0.0D;
        Iterator var4 = s.getPoints().iterator();

        while(var4.hasNext()) {
            Point point = (Point)var4.next();
            double value = (double)point.getX();
            if (largeXValue < value) {
                largeXValue = value;
            }
        }

        return largeXValue;
    }

    /*1.This method asks for multiple files,
    * 2. creates shape from given file
    * 3. returns largest perimeter of all shapes
    *    from all selected files*/
    public double getLargestPerimeterMultipleFiles() {
        double largePerim = 0.0D;
        DirectoryResource dr = new DirectoryResource();
        Iterator var4 = dr.selectedFiles().iterator();

        while(var4.hasNext()) {
            File f = (File)var4.next();
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = this.getPerimeter(s);
            if (largePerim < perimeter) {
                largePerim = perimeter;
            }
        }

        return largePerim;
    }

    /*1. Aks to select multiple files
    * 2. Forms shapes from points given in files
    * 3. returns file name which has largest perimeter */
    public String getFileWithLargestPerimeter() {
        double largePerim = 0.0D;
        File temp = null;
        DirectoryResource dr = new DirectoryResource();
        Iterator var5 = dr.selectedFiles().iterator();

        while(var5.hasNext()) {
            File f = (File)var5.next();
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = this.getPerimeter(s);
            if (largePerim < perimeter) {
                largePerim = perimeter;
                temp = f;
            }
        }

        return temp.getName();
    }

    public void testPerimeter() {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = this.getPerimeter(s);
        System.out.println("perimeter = " + length);
        int numPoints = this.getNumPoints(s);
        System.out.println("number of points are =" + numPoints);
        double avgLength = this.getAverageLength(s);
        System.out.println("Average length of sides is = " + avgLength);
        double largestSide = this.getLargestSide(s);
        System.out.println("Largest Side is =" + largestSide);
        double longestXValue = this.getLargestX(s);
        System.out.println("Longest X value is = " + longestXValue);
        this.testPerimeterMultipleFiles();
        this.testFileWithLargestPerimeter();
    }

    public void testPerimeterMultipleFiles() {
        double largestPerim = this.getLargestPerimeterMultipleFiles();
        System.out.println("Largest Perimeter of all shapes = " + largestPerim);
    }

    public void testFileWithLargestPerimeter() {
        String fileWithLargestPerimeter = this.getFileWithLargestPerimeter();
        System.out.println("File with largest perimeter is = " + fileWithLargestPerimeter);
    }

    public void triangle() {
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0, 0));
        triangle.addPoint(new Point(6, 0));
        triangle.addPoint(new Point(3, 6));
        Iterator var2 = triangle.getPoints().iterator();

        while(var2.hasNext()) {
            Point p = (Point)var2.next();
            System.out.println(p);
        }

        double peri = this.getPerimeter(triangle);
        System.out.println("perimeter = " + peri);
    }

    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        Iterator var2 = dr.selectedFiles().iterator();

        while(var2.hasNext()) {
            File f = (File)var2.next();
            System.out.println(f);
        }

    }

    public static void main(String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
