var img = new SimpleImage(200,200);
for (var px of img.values()){
  var x = px.getX();
  var y = px.getY();
  if (x < img.getWidth()/2){
    px.setRed(255);
  }
  if (y>img.getHeight()/2){
    px.setBlue(255);
  }
  //else { //The else here is wrong, you are setting all y < half the image height to green, leading to the yellow
  if (x > img.getWidth()/2 && y < img.getHeight()/2) {
    px.setGreen(255);
  }
}
print (img);

// ========================================================================================

function setBlack(pixel){
    pixel.setRed(255)
    pixel.setGreen(255)
    pixel.setBlue(255)
    return pixel
}

function addBorder(img, borderSize){
    for (var pixel of img.values()){
        if (pixel.getX() < borderSize || pixel.getX() > img.getWidth()-borderSize) {
            setBlack(pixel)
        }
        else if (pixel.getY() < borderSize || pixel.getY() > img.getHeight()-borderSize) {
            setBlack(pixel)
        }
        else {
            continue
        }
    }
}