
abstract class Shape {
   // EX13 Q3
   public abstract String getName();

   // EX13 Q5
   public abstract double getArea();
}

class Circle extends Shape {

   // Properties of the class...
   public double radius;

   // Constructor of the class...
   public Circle(double aRadius) {
      radius = aRadius;
   }

   // Methods
   // EX13 Q2
   public String getName() {
      return "circle";
   }

   // EX13 Q4
   public double getArea() {
      return Math.PI * radius * radius;
   }
}

class Triangle extends Shape {

   // Properties of the class...
   public double base;
   public double height;

   // Constructor of the class...
   public Triangle(double aBase, double aHeight) {
      base = aBase;
      height = aHeight;
   }

   // Methods
   // EX13 Q2
   public String getName() {
      return "triangle";
   }

      // EX13 Q4
   public double getArea() {
      return 0.5 * base * height;
   }

}

// EX12 Q2
class Rectangle extends Shape {

   // Properties
   public double width;
   public double length;

   // Constructor
   public Rectangle(double aWidth, double aLength) {
      width = aWidth;
      length = aLength;
   }

   // Methods
   // EX13 Q2
   public String getName() {
      return "rectangle";
   }

      // EX13 Q4
   public double getArea() {
      return width*length;
   }
}

class ShapeTest {

   public Shape[] myShapes;

   public void printAreas() {

      for (int i=0; i<myShapes.length; i++) {

         System.out.print("Shape " + i + " has area: ");
         // EX13 Q5, if statements like below are replaced
         System.out.println(myShapes[i].getArea());

         // EX12 Q5
         //if (myShapes[i] instanceof Rectangle) {
         //   Rectangle r = (Rectangle) myShapes[i];
         //   System.out.println(r.width*r.length);
         //}
      }
   }

   public void printNames() {

      for (int i=0; i<myShapes.length; i++) {

         System.out.print("Shape " + i + " is a: ");

         // EX13 Q3, if statements like below are replaced
         System.out.println(myShapes[i].getName());

         // EX12 Q4
         //if (myShapes[i] instanceof Rectangle) {
         //   System.out.println("rectangle");
         //}
      }
   }

   public void doStuff() {

      // create an empty shapes array...
      myShapes = new Shape[4];

      // fill in the values of the elements...
      myShapes[0] = new Circle(12.0);
      myShapes[1] = new Circle(6.3);
      myShapes[2] = new Triangle(3,8);

      // EX12 Q3
      myShapes[3] = new Rectangle(2,5);

      printNames();
      printAreas();
   }


   // The main method is the point of entry into the program...
   public static void main(String[] args) {

      ShapeTest me = new ShapeTest();
      me.doStuff();

   }
}
