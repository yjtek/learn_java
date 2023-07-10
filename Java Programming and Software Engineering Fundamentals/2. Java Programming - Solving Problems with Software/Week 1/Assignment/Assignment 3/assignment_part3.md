## Introduction
In this assignment you will find the largest perimeter over several shapes by examining several files representing shapes, calculating the size of the largest perimeter and also the name of the file with the largest perimeter. You will add new methods to the PerimeterAssignmentRunner class. You may find it helpful to review the DirectoryResource and FileResource documentation.

The PerimeterAssignmentRunner class already includes the printFileNames method. You should understand the following: The printFileNames method has no parameters and no return value, hence return type void. This method first creates a DirectoryResource. When this happens you are prompted to select a file or files. You can select a bunch of files together by clicking on the name of one file, and then hold down the shift key and select a second file. All the files between the first and second file will be highlighted. The code then iterates over all the files you have selected using a for loop and the selectedFiles method, printing out the filename for each file.

For this assignment, you will add or modify several methods in the PerimeterAssigmentRunner class. The Goals for this exercise are as follows

1. Complete writing the method getLargestPerimeterMultipleFiles that has no parameters. This method creates a DirectoryResource (so you can select multiple files) and then iterates over these files. For each File f, it converts the file into a FileResource with the line 

`FileResource fr = new FileResource(f);`

Then it should create a Shape from the FileResource and calculate that shape's perimeter, and return the largest perimeter over all the shapes in the files you have selected.

2. Finish writing the void method testPerimeterMultipleFiles to call getLargestPerimeterMultipleFiles and to print out the largest such perimeter. This method has no parameters and no return value. You will select the files when you run this method (hint: see our documentation for the DirectoryResource class).

3. Finish writing the method getFileWithLargestPerimeter that has no parameters. This method should create its own Directory Resource, except that this new method returns the name of the File that has the largest such perimeter, so it has return type String.

4. Add code to the method testFileWithLargestPerimeter to call getFileWithLargestPerimeter. For the File that is returned, print the name of that file.

## Discussion

### Complete the method getLargestPerimeterMultipleFiles
Immediately, we know that this method should return a double-type variable, as it should be the largest perimeter from a selection of files. Before we try to create this method from scratch, however, let's take a look at the printFileNames method. It has no return value, hence the type void, but it gives us the syntax for creating a DirectoryResource object (which allows us to select multiple files), and the syntax to create a “for each” loop that iterates through each selected file. Sometimes it's useful to read the “for each” loop aloud, and in the printFileNames method it reads as “for each File f in the directory object, execute this code:”, which reminds us that there is a File object named f, that we can reference if needed. That's why we'll use the line mentioned in the goals above:

```java
FileResource fr = new FileResource(f);
```

When placed inside the DirectoryResource “for each” loop we see in the printFileNames method, we can have the program make a new FileResource object for each File object, f. After this, we simply need to use each FileResource object to make a new shape and find its perimeter (Hint: if you're confused about this step, see the testPerimeter method we've used in the last few exercises). After we have the perimeter for any given shape in a file resource, we just need to compare them to find which file has the largest perimeter (similar to the previous exercise where we compared the largest sides / x-values, and only kept track of the largest). When this method has finished running, it should return a double-type variable of the largest perimeter across all selected files. 

### Complete the void method testPerimeterMultipleFiles
This is the method that will receive and print the data returned from the getLargestPerimeterMultipleFiles method, so we know that immediately we'll need to initialize a variable of the type returned by getLargestPerimeterMultipleFiles. In this case, that's a double-type variable, which we'll initialize as the output of the getLargestPerimeterMultipleFiles method. From this point, you'll just need to write the code to have the system output the results, as you have in the past few exercises we've worked on. In this case, your results will vary based on which files you choose to test your program with. However, you should be able to verify the validity of these results by cross referencing the results with the output from your functional testPerimeter method. 

One very important thing to remember is that the compiled program always runs starting from the main method (Tip: If you're not seeing the output of your testPerimeterMultipleFiles method, make sure that you are calling that method in the main method. This will use similar syntax to calling the testPerimeter method that we've been using for the last two exercises).

### Complete the getFileWithLargestPerimeter method
The first thing you'll notice about this method is that there is already some code started for you. The following line is initializing an object with an empty value, presumably so that we can later update this value with something meaningful. 

```java
File temp = null;
```

The end goal of this method is to do the same thing as the getLargestPerimeterMultipleFiles method, except that we want to return the String-type value that represents the filename which contains the shape with the largest perimeter. With this in mind, we should be able to borrow much of the syntax from getLargestPerimeterMultipleFiles, except for this method we need to update a file object every time we find a shape that has a larger perimeter than the current maximum. We've updated variables in a loop using conditionals in the last exercise, and updating objects works the same way. 

At the end of this method, we also see that it is returning the output of the getName method on the File-type object temp (for more information, check out the documentation for the File Class). The important thing for you to know about the getName method is that it outputs the name of a File object as a String, which is the type of output we want for the getFileWithLargestPerimeter method. 

### Complete the void method testFileWithLargestPerimeter
This will be the method that we use to call the getFileWithLargestPerimeter method, so we will need to initialize a variable as the output of the getFileWithLargestPerimeter method. After that, all you need to do is write the code to have the system output the results, the same as the past few exercises. Once again, your answers will vary depending on which files you've selected to test your program with, but you should be able to verify your results with your functional testPerimeter program. 

Again, don't forget to call testFileWithLargestPerimeter in the main method, or you will not be seeing the output of this method when you run your file. Once you've verified that your program functions correctly, you can save your files, and you're ready to take the quiz! 