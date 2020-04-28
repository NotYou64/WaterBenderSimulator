// Cole Delong
// Water particle class
// 4-27-20

//  Water particle class
class Mover {
  
  // declare locals
  PVector location;
  PVector velocity;
  PVector acceleration;
  float size;
  PVector inertia;
  float saturation;

  // constructor function
  Mover(float x, float y) {
    
    // define locals
    location = new PVector(x, y);
    velocity = new PVector(0, 0);
    acceleration = new PVector(mouseX, mouseY);
    size = random(10) + 5;
    inertia = new PVector(size/120, size/120);  
    saturation = 325-map(size, 10, 15, 100, 150);
    
  }

  // update position by accelerating based off of center of gravity and mouse
  void update(float tarX, float tarY, boolean away) {
    
    // if left mouse pressed, accelerate away from center of gravity
    if (away && mouseButton == LEFT) {
      acceleration = new PVector(location.x-tarX, location.y-tarY);
      acceleration.normalize();
      acceleration.sub(inertia);
    }
    // if right mouse pressed, don't accelerate
    else if (away && mouseButton == RIGHT) acceleration = new PVector(0,0);
    // if mouse isn't pressed, accelerate towards the center of gravity
    else {
      acceleration = new PVector(tarX-location.x, tarY-location.y);
      acceleration.normalize();
      acceleration.sub(inertia);
    }
    
    // add acceleration to velocity with a max of 20 and slowly decay the velocity for when right mouse is pressed
    velocity.add(acceleration);
    velocity.limit(20);
    location.add(velocity);
    velocity.mult(0.99995);
  }

  // display the water particle on the screen
  void show() {
    noStroke();
    fill(saturation/2,saturation/2,saturation,100);
    ellipse(location.x, location.y, size, size);
  }
}
