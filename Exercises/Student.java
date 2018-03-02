/* Need to import java.io package to use the BufferedReader and
	 InputStreamReader.
*/
import java.io.*;

public class Student {

  private static BufferedReader stdIn = new BufferedReader(new
		InputStreamReader(System.in));

  private String name;
  private int age;

  public Student () {
    name = "";
    age = 0;
  }

  public void readName () throws IOException {
    System.out.print("Input your name: ");
    name = stdIn.readLine();
  }


  public void printName () {
    System.out.println("Name: " + name);
  }

  // Q1b, Q1d and Q1e
  public void readAge () throws IOException {
    System.out.print("Input your age: ");

    try {
      age = Integer.parseInt(stdIn.readLine());
      if (age < 0 || age > 150) {
        System.out.println("You must type in your real age (between 0 and 150).");
        readAge();
      }
    } catch (java.lang.NumberFormatException e) {
      age = -1;
      System.out.println("You must type your age in numbers.");
      readAge();
    } catch (IOException e) {
      System.out.println("Error");
    }

  }

  public void printAge () {
    System.out.println("Age: " + age);
  }

  // Q1c
  // If you type alphabetical characters instead of numbers for age, then you
  // get an exception (error message):
  // Exception in thread "main" java.lang.NumberFormatException: For input string: "p"

  public static void main (String[] args) throws IOException {
    Student me = new Student();
    me.readName();
    me.readAge();
    me.printName();
    me.printAge();

  }
}



