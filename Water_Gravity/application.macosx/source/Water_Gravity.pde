// Cole Delong
// Water Bender Simulator
// 4-27-20

// Globals
Mover[] m;
PVector center;

// setup function
void setup() {
  
  // set up screen
  smooth();
  size(1500,1000);
  
  // create water particle objects
  m = new Mover[1750];
  for (int i = 0; i < m.length; i++) {
    m[i] = new Mover(random(width)*10-width*5, random(width)*10-width*5);
  }
  
  // create the center of gravity
  center = new PVector(mouseX, mouseY);
    
}

// loop
void draw() {
  
  // reset background
  background(255);
  
  // set the center of gravity to the mouse
  center.set(mouseX, mouseY);
  
  // update and show the water particles with the center of gravity and whether the mouse is pressed or not
  for (int i = 0; i < m.length; i++) {
    m[i].update(center.x, center.y, mousePressed);
    m[i].show();
  }

}
