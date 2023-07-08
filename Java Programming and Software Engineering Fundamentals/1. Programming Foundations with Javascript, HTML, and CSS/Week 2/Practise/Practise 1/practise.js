
var x = 3;
var y = 4;
var z = x + 2*y;
console.log(x,y,z)

var x = 3;
var y = 2;
y = x;
// print(x); //will not run
// print(y); //will not run

var image = new SimpleImage("someimage.png");

// Part 1

function square(x){
    var ans=x*x;
    return ans
}
var y = square(4);

function cube(x){
    var ans=x*x*x
    return ans
}

function sum3(x){
    var ans=x+x+x
    return ans
}

function sum_string(x_string, y_string){
    var ans=x_string+y_string
    return ans
}

function print_height_width(image){
    h = image.getHeight()
    w = image.getWidth()
    console.log(h,w)
}

// Part 2

