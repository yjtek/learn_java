function makeRed(imgPath){
    var image = new SimpleImage(imgPath);
    for (var pixel of image.values()){
        pixel.setRed(255);
    }
}

function removeRed(imgPath){
    var image = new SimpleImage(imgPath);
    for (var pixel of image.values()){
        pixel.setRed(0);
    }
}

function lowerRed(imgPath){
    var image = new SimpleImage(imgPath);
    for (var pixel of image.values()){
        if (pixel.getRed() > 70) {
            pixel.setRed(70)
        }
        else {
            continue
        }
    }
}

function addThickBlackLineBelow(imgPath){
    var image = new SimpleImage(imgPath);
    for (var pixel of image.values()){
        if (pixel.getY() >= height - 10) {
            pixel.setRed(0)
            pixel.setGreen(0)
            pixel.setBlue(0)
        }
        else {
            continue
        }
    }
}

function addGreenSquareTopLeft(imgPath){
    var image = new SimpleImage(imgPath);

    height=image.getHeight()

    for (var pixel of image.values()){
        if ((pixel.getY() < 50) && (pixel.getX() < 50)){
            pixel.setRed(0)
            pixel.setGreen(255)
            pixel.setBlue(0)
        }
        else {
            continue
        }
    }
}

function topRightCorner(cornerWidth, cornerHeight, someImage, red, green, blue){
    var width = someImage.getWidth();
    for (var pixel of image.values()){
        if ((pixel.getY() < cornerHeight) && (pixel.getX() > width - cornerWidth)){
            pixel.setRed(red)
            pixel.setGreen(green)
            pixel.setBlue(blue)
        }
        else {
            continue
        }
    }
}

function changeRed(width, height) {
    //    
}

function changeRedFixBlueGreen(width, height, blue, green) {
    
}
