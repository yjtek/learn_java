function phrase3words(x_str, y_str, z_str){
    console.log(x_str + ' ' + y_str + ' ' + z_str)
}

function reformatName(first_str, last_str){
    var formatted = last_str + ', ' + first_str
    console.log(formatted)
}

function numberPixels(namefile){
    var someImg = new SimpleImage(namefile);
    var height = someImg.getHeight();
    var width = someImg.getWidth();
    return height*width
}

function perimeter(imageName){
    var someImg = new SimpleImage(namefile);
    var height = someImg.getHeight();
    var width = someImg.getWidth();
    return (height*2) + (width*2)

}

function printPixel(nameImage, xpos, ypos) {
    var someImage = new SimpleImage(nameImage) ;
    print("red is " + someImage.getRed(xpos,ypos));
    print("green is " + someImage.getGreen(xpos,ypos));
    print("blue is " + someImage.getBlue(xpos,ypos));
}

function sumPixel(nameOfImage, xpos, ypos) {
    var theImage = new SimpleImage(nameOfImage) ;
    var redNumber = theImage.getRed(xpos,ypos);
    var blueNumber = theImage.getBlue(xpos,ypos);
    var greenNumber = theImage.getGreen(xpos,ypos);
    return redNumber + blueNumber + greenNumber;
}