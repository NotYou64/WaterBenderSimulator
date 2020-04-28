import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Water_Gravity extends PApplet {

// Cole Delong
// Water Bender Simulator
// 4-27-20

// Globals
Mover[] m;
PVector center;

// setup function
public void setup() {
  
  // set up screen
  
  
  
  // create water particle objects
  m = new Mover[1750];
  for (int i = 0; i < m.length; i++) {
    m[i] = new Mover(random(width)*10-width*5, random(width)*10-width*5);
  }
  
  // create the center of gravity
  center = new PVector(mouseX, mouseY);
    
}

// loop
public void draw() {
  
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
  public void update(float tarX, float tarY, boolean away) {
    
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
    velocity.mult(0.99995f);
  }

  // display the water particle on the screen
  public void show() {
    noStroke();
    fill(saturation/2,saturation/2,saturation,100);
    ellipse(location.x, location.y, size, size);
  }
}
  public void settings() {  size(1500,1000);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Water_Gravity" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
