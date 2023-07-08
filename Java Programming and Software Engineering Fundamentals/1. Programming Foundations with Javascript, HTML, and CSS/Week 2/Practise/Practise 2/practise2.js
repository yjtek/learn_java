function addStripes(img){
    for (var pixel of img.values()){
        if (pixel.getX() < img.getWidth()/3){
            pixel.setRed(255)
        }
        else if ((pixel.getX() >= img.getWidth()/3) && (pixel.getX() < 2*img.getWidth()/3)){
            pixel.setGreen(255)
        }
        else if ((pixel.getX() >= 2*img.getWidth()/3) && (pixel.getX() <= img.getWidth())){
            pixel.setBlue(255)
        }
    }
}

function swapRedGreen(pixel){
    redval = pixel.getRed()
    greenval = pixel.getGreen()
    pixel.setRed(greenval)
    pixel.setGreen(redval)
}

function swapBlueToYellow(img){
    for (var pixel of img.values()){
        if ((pixel.getBlue() == 255) & (pixel.getGreen() == 0) & (pixel.getRed() == 0)){
            pixel.setRed(255)
            pixel.setGreen(255)
            pixel.setBlue(0)
        }
    }
}