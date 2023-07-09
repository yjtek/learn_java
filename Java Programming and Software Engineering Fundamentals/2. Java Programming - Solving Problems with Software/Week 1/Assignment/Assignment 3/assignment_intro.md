In the next few readings, you're going to use what you've learned in the past few lessons to create a working Java program that can calculate the perimeter of a shape, from some given points. Before we get started with that, however, we thought it would be best to go through the sample code that you'll be using as the foundation for your Java Program!

## Sample Code Explanation and First Steps
Note: You must have downloaded BlueJ on or after September 7, 2017, from http://www.dukelearntoprogram.com/ in order to access the Shape and Point Java classes used in the following programming assignments.

In these exercises, you will use the Shape and Point classes to answer questions about a Shape that is made up of a collection of points from the x-y plane, as shown in this lesson. The shape is defined by drawing a line between two adjacent points, for every pair of adjacent points, and also a line between the first and last point. Be sure to consult the documentation on DukeLearnToProgram to understand how the Shape, Point, DirectoryResource and FileResource classes work: http://www.dukelearntoprogram.com/course2/doc/javadoc/index.html?course=2

### Calculating information about shapes
In this assignment, you will complete the PerimeterAssignmentRunner class to calculate lots of interesting facts about shapes. This class has been started for you in the BlueJ project called PerimeterAssignmentRunner (go to: http://www.dukelearntoprogram.com/course2/files.php and download the Quiz - Calculating the Perimeter of a Shape BlueJ project). This project also contains several data files. In addition, you will need to look at the documentation for the Shape class and the Point class.

The PerimeterAssignmentRunner class already includes the following complete methods:
1. The getPerimeter method has one parameter s of type Shape. Given a shape, this method returns the perimeter of the shape.
2. The testPerimeter method has no return value, hence its return type is void. This method is used to select a data file by using the FileResource class, create a shape based on the points from that data file, and then calculate the perimeter of the shape and output its value.
3. The triangle method has no return value and creates a triangle that you can use to test the methods you will create in this assignment.
4. The printFileNames method, which we will discuss in a future lesson.
5. The main method.

For this assignment, you will add or modify several methods in the PerimeterAssignmentRunner class.

### A few ground rules for Shapes
For the sake of familiarizing ourselves with the abstract concept of a Shape, we're going to take a look at an example. We can think of a shape as a collection of points that are connected together by lines, so for our example, we're going to use a shape with four random points. Here is an example Shape that has 4 points in this order: 

- (2.0, 2.0) 
- (7.0, 3.0)
- (6.0, 9.0)
- (3.0, 5.0)

By defining the shape with the points in this order, that
means the shape has these lines: 

- a line from (2.0, 2.0) to (7.0, 3.0),
- a line from (7.0, 3.0) to (6.0, 9.0), 
- a line from (6.0, 9.0) to (3.0, 5.0), and finally, 
- there is a line from the last point (3.0, 5.0) to the first point (2.0, 2.0).

### An Explanation of the complete getPerimeter method:
Here is the idea of how the getPerimeter method works with our example. Remember the Shape is defined as these four points:

- (2.0, 2.0) 
- (7.0, 3.0) 
- (6.0, 9.0) 
- (3.0, 5.0)

To calculate the perimeter, we would loop over all the points in Shape, and for each point calculate the length of the line formed with the previous point. We're just using the distance formula for these calculations, but we'll actually be able to get our program to calculate that for us in the code without having to remember the distance formula itself. 

Starting with the first point (2.0, 2.0) we would need the last point in the Shape with it, (3.0, 5.0) and calculate its length as 3.16 
- For point (7.0, 3.0), calculate its length with (2.0, 2.0) which is 5.09. 
- For point (6.0, 9.0), calculate its length with (7.0, 3.0) which is 6.08.  
- For point (3.0, 5.0), calculate its length with (6.0, 9.0) which is 5.  

We return the sum of these, which is 3.16 + 5.09 + 6.08 + 5, which is 19.33.

Now let's see how the code for getPerimeter implements this idea. The getPerimeter method reads as follows:

```java
public double getPerimeter (Shape s) {
    // Start with totalPerim = 0
    double totalPerim = 0.0;
    // Start wth prevPt = the last point 
    Point prevPt = s.getLastPoint();
    // For each point currPt in the shape,
    for (Point currPt : s.getPoints()) {
        // Find distance from prevPt point to currPt 
        double currDist = prevPt.distance(currPt);
        // Update totalPerim by currDist
        totalPerim = totalPerim + currDist;
        // Update prevPt to be currPt
        prevPt = currPt;
    }
    // totalPerim is the answer
    return totalPerim;
}
```

So immediately, we see some other classes and methods being called here. The “Point” class is something that is being used here and it was loaded in the duke package at the top of the project, with the line import edu.duke*; . To find out more about this class, you can reference 
the documentation on the Duke site. Within that page, you'll have to select the edu.duke package, and then select the “Point” class. This documentation shows that this class can make use of the “distance” method, which outputs a double-type value. You will also see the “distance” method being called in the above “getPerimeter” method. To run through the code sample above, we proceed as follows:

1. This method “getPerimeter” first has a parameter, s, which is of the type Shape. 
2. We then create a double-type variable named totalPerim and initialize it as 0. This variable needs to be double-type, as the distance between two points is not always an integer, and subsequently the perimeter will not always be an integer. 
3. Next, we create a new Point object named prevPt. This object is initialized as the output of the getLastPoint method from the object s. As it is listed in the documentation for the Shape Class, getLastPoint is a Point-type method that returns to the last point added to a selected object/shape (checking the documentation on all these methods is essential).
4. We next enter a “for each” loop, which initializes the Point-type variable currPt for each entry in the iterable getPoints. In the documentation for the Shape class, we see that the iterable getPoints allows access to a shape one point at a time. 
5. Next, we create a double-type variable currDist, and initialize it by referencing another method, distance. This variable will be initialized as the distance from prevPt to currPt. 
6. We then update the variable totalPerim (which was previously initialized at 0.0) to be equal to totalPerim + currDist. We do this, so that we can keep a running total. Each time this loop runs (which, as an iterable of the getPoints method, will be as many times as there are points in the Shape Object, s) totalPerim will be with the currDist between the next two points. 
7. When the loop iterates through every point in s, we exit, and return the double-type variable totalPerim as the output of the “getPerimeter” Method

### An Explanation of the complete testPerimeter and main Methods:

```java
public void testPerimeter () {
    FileResource fr = new FileResource();
    Shape s = new Shape(fr);
    double length = getPerimeter(s);
    System.out.println("perimeter = " + length);
}
```

First of all, we see that the testPerimeter method has no type, and so it gets the designation void. Proceeding from there, the code proceeds as follows

1. We initialize a FileResource object named fr, using the new keyword and the FileResource class (this was imported with the edu.duke package). 
2. From the duke documentation on the FileResource class, we are shown that when called without a parameter, the FileResource class will open a dialogue box and allow us to select a file. This file will be stored as the variable fr.
3. Next, we initialize a Shape-type object named s (this is the object the code above referenced so many times), as a new object and call the Shape class, using the variable fr (the FileResource Object we just created) as the parameter for this shape object. This means that we will use the contents of fr (the file we selected in a dialogue box) to create a shape object. 
4. We create a double-type variable named length, and initialize it as the output of the getPerimeter method, with the parameter of s (the shape object we just created). This will pass the Shape s to the getPerimeter method (detailed above), and initialize the variable length as the output of that method. If you recall, getPerimeter accepts a shape-type object as a parameter, and outputs a double-type variable. 
5. We then issue a system  output command to print the string “perimeter = “ and the variable “length”.

And for the final bit of this sample file code that we're examining in this exercise, the main method. This is the area that our program will execute by default after we compile and run it. For that reason, it's important to take a look at anything in the main method.

```java
public static void main (String[] args) {
    PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
    pr.testPerimeter();
}
```

Reading through this code we see that it simply creates a new PerimeterAssignmentRunner-type object, pr, and then calls the method testPerimeter using the object pr, which we outlined above. 

### Running through the program as it is currently written

So as a brief summary, if we were to compile and run this program, it would progress as follows:

1. Execution starts in the main method, creates a new PerimeterAssignmentRunner-type object
2. Using the new PerimeterAssignmentRunner-type object, call the method testPerimeter
3. The testPerimeter method creates a new FileResource object, fr,  prompting us to select a file via a dialogue box
4. We use this FileResource object to create a new Shape object, s.
5. Create a variable called length, and initialize it to be “the result of calling the getPerimeter method on s”, the shape object we just created. 
6. The getPerimeter method creates a new double-type variable called totalPerim, and creates/initializes the point-type variable prevPt by calling the getLastPoint method on our shape, s.
7. We enter a “for each” loop by calling the getPoints method on our shape, s, and initializing the variable currPt to to be the current point in the iterable. This will loop for as many points as there are in shape s
8. We proceed through the “for each” loop, adding the distance between the last two points (currDist) to totalPerim as we go, and updating currPt at the end of each loop. 
9. When all the loops are done, getPerimeter returns the variable totalPerim to the testPerimeter method, thereby initializing the variable length.
10. testPerimeter then uses the system output command to print a string, and the variable length, finishing the testPerimeter method and returning to the main method.
11. The main method finishes executing and terminates.

### Conclusion

There's a great deal of information to encode here, but there's a few major takeaways in terms of programming ideas that are very important.

1. We were able to use a “for each” loop with an iterable method to easily loop through every entry in a text file (this is very important and you'll be asked to do this on your own in future activities).
2. We initialized a variable outside of a loop and then updated its value throughout a loop to keep track of a running tally (we used this to keep track of a total perimeter of a shape, but it could just as easily be used to track the number of iterations throughout a loop, or a number of other functions).
3. We saw how you can use multiple methods and classes that are loaded into a project through an import , and how to call methods inside and outside of the main method.

We definitely won't go through future example code in this level of detail, but we felt it was necessary to further explain the example files given, and to jumpstart your imagination about how you might think about writing code on your own. In the next few exercises, you'll need to utilize everything you've learned in the lecture videos, and what you've experienced firsthand in this example code. When you feel comfortable with the information in this reading, proceed on to the next activity!
