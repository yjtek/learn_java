// We want to remove green screen from foreground image, and replace it with background image

var fgImage = new SimpleImage("somepic.png");
var bgImage = new SimpleImage("dinos.jpg");
var output = new SimpleImage(fgImage.getWidth(), fgImage.getHeight());

for (var pixel of fgImage.values()) {
    if (pixel.getGreen() > (pixel.getRed() + pixel.getBlue())) {
        var x = pixel.getX();
        var y = pixel.getY();
        var bgPixel = bgImage.getPixel(x, y);

        output.setPixel(x, y, bgPixel)
    }
    else {
        output.setPixel(pixel.getX(), pixel.getY(), pixel)
    }
}