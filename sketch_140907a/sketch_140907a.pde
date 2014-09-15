/**
* Sine Console
* Processing: Creative Coding and Computational Art
* By Ira Greenberg
* Adapted by René Samselnig
* PNG to GIF: for i in *.png; do convert $i ${i%.*}.gif; done
* Animated GIF with: gifsicle --colors 256 --loopcount=0 --delay 4 -i image-*.gif > sin.gif
*/

float px, py;
float prevpx = 0;
float angle;
float radius, left;
float frequency = 0.5;
float x;
boolean export = false;
boolean interactive = false;
PShape circle;
PShape path;
PShape triangle;

// used to create font
PFont myFont;

void setup(){
  size(360*2, 450, P2D);
  background (255, 255, 255, 0);
  smooth(8);
  // generate processing font from system font
  myFont = createFont("verdana", 12);
  textFont(myFont);

  radius = height/2-55;
  left = -radius;

  circle = createShape(ELLIPSE, 0, 0, radius*2, radius*2);
  circle.setStroke(color(192, 0, 0));
  circle.setFill(color(0,0,0,0));
  circle.setStrokeWeight(2);

  path = createShape();
  path.beginShape();
  path.stroke(0, 192, 0);
  // Calculate the path as a sine wave
  for (int i = 0; i < width; i++)
    path.vertex(left+radius+i, height/2 + sin(radians(frequency*i))*(radius));
  path.strokeWeight(1);
  path.noFill();
  path.endShape();
}

void draw() {
  background (255);
  
  // Draw x-axis ticks and labels
  stroke(0);
  strokeWeight(1);
  fill(0);
  textAlign(LEFT, TOP);
  for(float i = 0; i < width; i+=90/frequency) {
    line(i, height/2-3, i, height/2+3);
    if (i%180==0.0)
      text(String.format("%.0f°", i*frequency), i+2, height/2+5);
  }

  // Draw y-axis labels
  textAlign(RIGHT, CENTER);
  line(0, height/2-radius, width-22, height/2-radius);
  text("+1", width-2, height/2-radius-2);
  line(0, height/2, width-13, height/2);
  text("0", width-2, height/2-2);
  line(0, height/2+radius, width-18, height/2+radius);
  text("-1", width-2, height/2+radius-2);

  // Draw sine curve
  shape(path);

  // Draw the triangle
  px = cos(radians(angle))*radius;
  py = sin(radians(angle))*radius;

  triangle = createShape();
  triangle.beginShape(TRIANGLE);
  triangle.stroke(50);
  triangle.strokeWeight(1);
  triangle.noFill();
  triangle.vertex(0, 0);
  triangle.vertex(0, py);
  triangle.vertex(px, py);
  triangle.endShape();

  pushMatrix();
  translate((x+width/2)%width, height/2);
  // left triancle
  translate(-width/2, 0);
  shape(triangle);
  // right triangle
  translate(width, 0);
  shape(triangle);
  // left circle
  translate(-width-radius, -radius);
  shape(circle);
  // right circle
  translate(width, 0);
  shape(circle);
  popMatrix();

  // output some calculations
  fill(0);
  textAlign(LEFT, BOTTOM);
  text("y = sin x", 20, height-15);
  text(String.format("x = %.0f°", x*frequency), 100, height-15);
  text(String.format("y = %.1f", -py/radius), 180, height-15);

  prevpx = px;
  if(!interactive)
    x = (x+frequency*2)%width;
  angle = x*frequency;

  if (export) {
    if (frameCount >= width)
      export = false;
    if (frameCount % 4 == 0 && frameCount < width)
      saveFrame("image-####.png");
  }
}

void keyPressed() {
  if(key == 'x') {
    export = true;
    frameCount = 0;
    x = 0;
  }
}

void mousePressed() {
  interactive = !interactive;
  x = mouseX;
}

void mouseMoved() {
  if(interactive)
    x = mouseX;
}

