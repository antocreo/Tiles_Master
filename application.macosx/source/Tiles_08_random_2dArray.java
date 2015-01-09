import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.awt.Frame; 
import java.awt.BorderLayout; 
import controlP5.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Tiles_08_random_2dArray extends PApplet {




public void setup() {
  tile1=true;    //default tile
  pat1=true;      //default random pattern
  hasIndent=false;                        //we start witha  0 indent
  indentRandom=false;                    //the random indent is set on false
  
  size(cellSize*row, cellSize*col);     // size of the stage is conditioned by the size of the tile
  smooth();
  myTile = new Tile[row][col]; // creating a 2d array of objects Tile

//initialse the grid
  for (int r=0; r<row; r++) {
    for (int c=0; c<col; c++) {
      myTile[r][c] = new Tile(angle); // creating a 2d array of objects Tile
      myTile[r][c].angle = angle;
      println(myTile[r][c].angle);
    }
  }

  /////GUI///
  cp5 = new ControlP5(this);
  
  // by calling function addControlFrame() a
  // new frame is created and an instance of class
  // ControlFrame is instanziated.
  cf = addControlFrame("extra", 250,440);

  
}


public void draw() {
  frameRate(framerate);

  //creating the stage. this is the background where the tiles will be placed.
  myStage.createStage();

  //drawing tiles
  drawTiles();
    
}






/// VARIATION ON Q-TILING BY PABLO VALBUENA////
//Author: Anto+Creo
//EXPLORING CLASSES AND GRIDS. RANDOM PATTERNS AND TILING SYSTEMS.
//End of term assignment: Programming for Artists ::: MFA Cmputational arts 2014

////////////////////////////////////////////////////////////////////////
/*
 Aim
 ---
 The aim is using Processing to create a software that simulates the by Pablo Valbuena installation.
 The original project consists in creating a series of complex patterns by rotating each of the single module
 (a tile) placed on the floor in a grid of 20x20.
 
 The original project is visible here https://www.youtube.com/watch?v=OaVjXL9liX4
 
 Process
 -------
 To recreate the system I decided to use 2 main classes: Stage class that creates the floor and Tile class that
 stores the properties of each tile.
 In this way I can control the color of the background and creating more variations.
 The tiles are basically made witha  main black square on top of which is drawn a white rectangle. This is the main tile
 replication of the Valbuena project.
 I decided to explore more patterns and so I created 3 more tiles that can be called using the number 1 to 5 on the keyboard.
 I also implemented the basic tile with an "indent" that allows to se more background and give more variations to the pttern.
 THere are 3 main ways to draw the patterns.
 1- original way: rotating tiles one by one.
 2- dragging the mouse and rotating all the tiles. This is a faster but less precise method.
 3- random pattern using spacebar.
 The randomisation is actually made around a series of algorythms that calculate which tile rotating. 
 
 I created a tab for each section of the project so that could be easy for the reader ond for myslef to go around the code.
 
 
 Comments
 --------
 The program is working well and as expected.
 I managed to implement it with extra functions and extra patterns.
 I implemented some basic functions into an external GUI.
 There are margins of improvement for this code.
 I would like to add more control to the GUI in order to have full access to the 
 functionalities of the Tiles and the other options.
 I would also like to study more random complex patterns and different tile design.
 Overall I think I can be happy with this project as I acheived the pre-fixed results and overcame many issues
 regarding the behaviour of the tiles.
 
 Evaluation
 ----------
 Bugs: None. 
 Extensions:None
 
 Keybord Commands
 ----------
 1,2,3,4,5   - type of tile
 q,w,e       - type of random pattern  
 spacebar    - bang random pattern
 backspace   - bang reset
 i           - toggle random indent
 r           - random panic!
 mouse click - rotate tile 90 degrees
 mouse drag  - continuos rotation 90 degrees
 
 */

public ControlFrame addControlFrame(String theName, int theWidth, int theHeight) {
  Frame f = new Frame(theName);
  ControlFrame p = new ControlFrame(this, theWidth, theHeight);
  f.add(p);
  p.init();
  f.setTitle(theName);
  f.setSize(p.w, p.h);
  f.setLocation(10, 10);
  f.setResizable(false);
  f.setVisible(true);
  return p;
}


// the ControlFrame class extends PApplet, so we 
// are creating a new processing applet inside a
// new frame with a controlP5 object loaded
public class ControlFrame extends PApplet {

  int w, h;

  public void setup() {
    size(w, h);
    frameRate(25);
    background(0);
    cp5 = new ControlP5(this);
    //bgColor
    cp5.addSlider("bgColor").plugTo(parent, "bgColor").setRange(0, 255).setPosition(10, 10).setValue(200);
    //indent
    cp5.addSlider("indent").plugTo(parent, "indent").setRange(0, cellSize).setPosition(10, 30);
    // max randomello variable to control the random pattern
    cp5.addSlider("random pattern var").plugTo(parent, "maxRandomello").setRange(0, col).setPosition(10, 50).setValue(6);


    // random indent toggle switch
    randTog = cp5.addToggle("")
      .plugTo(parent, "indentRandom")
        .setPosition(155, 30)
          .setSize(10, 10)
            .setValue(false)
              .listen(indentRandom)
                .setUpdate(indentRandom)
                  //.setMode(ControlP5.SWITCH)
                  ;


    myTextlabelA = cp5.addTextlabel("label")            
      .setText("Random indent [i]")
        .setPosition(165, 30)
          ;

    // bang reset all
    resetBang=cp5.addBang("bang")
      .setPosition(10, 350)
        .setSize(20, 20)
          .setTriggerEvent(Bang.RELEASE)
            .setLabel("RESET ALL (BackSpace)")
              ;
    // bang random pattern         
    randomPattern=cp5.addBang("randPat")
      .setPosition(10, 310)
        .setSize(20, 20)
          .setTriggerEvent(Bang.RELEASE)
            .setLabel("RANDOM PATTERN (spaceBar)")
              ;
    /*
    //this bang saves the screenshot of the small GUI window// need sto be improved
    // for now I stick to the keyboard control
    
    // bang random pattern         
     screenShot=cp5.addBang("shot")
     .setPosition(10, 270)
     .setSize(20, 20)
     .setTriggerEvent(Bang.RELEASE)
     .setLabel("SAVE SCREENSHOT (s)")
     ;
     */
  }


  public void bang() {
    println("RESET");
    reset();
  }

  public void randPat() {
    println("RANDOM PATTERN");
    randomChoice();
    //at the end of each random shot I reset the value of this so that the condition at the beginning can be satisfied again!
    tileChanged=false;
  }

  public void shot() {
    println("screenshot taken!");
    imageCounter+=1;
    save("tiles_"+imageCounter +"_"+d+m+h+min+sec + ".jpg");
  }




  public void draw() {
    myTextlabelA.draw(this);
    fill(255);
    //    rect(10, 90, w-20, 160);
    //    rect(00, 90, w, 160);

    text("Keybord Commands------------------------------", 10, 80);
    //    fill(0);
    text("1,2,3,4,5   - type of tile", 10, 100);
    text("q,w,e       - type of random pattern", 10, 120);
    text("SPACEBAR    - bang random pattern", 10, 140);
    text("BACKSPACE   - bang reset", 10, 160);
    text("i           - toggle random indent", 10, 180);
    text("s           - save screenshot", 10, 200);

    text("MOUSE CLICK - rotate tile 90 degrees", 10, 220);
    text("MOUSE DRAG  - continuos rotation 90 degrees", 10, 240);
    fill(255);

    text("----------------------------------------------", 10, 260);

    //    text("r           - random panic!", 10, 180);
  }

  private ControlFrame() {
  }

  public ControlFrame(Object theParent, int theWidth, int theHeight) {
    parent = theParent;
    w = theWidth;
    h = theHeight;
  }


  public ControlP5 control() {
    return cp5;
  }


  ControlP5 cp5;

  Object parent;
}

/*
Keybord Commands
 ----------
 1,2,3,4,5   - type of tile
 q,w,e       - type of random pattern  
 spacebar    - bang random pattern
 backspace   - bang reset
 i           - toggle random indent
 r           - random panic!
 mouse click - rotate tile 90 degrees
 mouse drag  - continuos rotation 90 degrees
 
 */

////////////////////////// KEYPRESSED //////////////////////


public void keyPressed() {

  //spacebar random choice
  if (key == ' ' ) { //spacebar key
    randomChoice();
    //at the end of each random shot I reset the value of this so that the condition at the beginning can be satisfied again!
    tileChanged=false;
  }

  //saving files
  if (key == 's' ) {
    imageCounter+=1;
    save("screenshots_"+d+m+y+"/tiles_"+imageCounter +"_"+d+m+h+min+sec + ".jpg");
  }

  //triggering tiles
  if (key == '1' ) {
    tile1=true;
    tile2=false;
    tile3=false;
    tile4=false;
    tile5=false;
  }

  if (key == '2' ) {
    tile1=false;
    tile2=true;
    tile3=false;
    tile4=false;
    tile5=false;
  }
  if (key == '3' ) {
    tile1=false;
    tile2=false;
    tile3=true;
    tile4=false;
    tile5=false;
  }
  if (key == '4' ) {
    tile1=false;
    tile2=false;
    tile3=false;
    tile4=true;
    tile5=false;
  }

  if (key == '5' ) {
    tile1=false;
    tile2=false;
    tile3=false;
    tile4=false;
    tile5=true;
  }

  //triggering random patterns

  if (key == 'q' ) {
    pat1=true;
    pat2=false;
    pat3=false;
  }
  
   if (key == 'w' ) {
    pat1=false;
    pat2=true;
    pat3=false;
  }

 if (key == 'e' ) {
    pat1=false;
    pat2=false;
    pat3=true;
  }



  if (key == 'i' ) {
    //toggle the indent value
    if (indentRandom) {
      indentRandom=false;
      randTog.setState(false);
    } else {
      indentRandom=true;
      randTog.setState(true);
    }
    println(indentRandom);
  }

  if (key == BACKSPACE ) {
    reset();
  }
}


//////function that resets the canvas
public void reset() {

  indent=0;
  for (int r=0; r<row; r++) {
    for (int c=0; c<col; c++) {
      //        //this will reset the tiles before drawing the random pattern.
      //so that any previously rotated tiles will go back at 0 degrees.
      myTile[r][c].angle=0;
    }
  }
}



/////////////////////////MOUSE RELEASED///////////////////////////////

//this function will rotate the tiles at each mouse realeased
public void mouseReleased() {
  for (int r=0; r<row; r++) {
    for (int c=0; c<col; c++) {

      if (
      //limiting the mouse position to each tile
      mouseX >= myTile[r][c].x
        && mouseX < myTile[r][c].x + myTile[r][c].tileSize
        && mouseY >= myTile[r][c].y
        && mouseY < myTile[r][c].y + myTile[r][c].tileSize) {
        stroke(1);
        ellipse(r*cellSize, c*cellSize, 10, 10); //MOUSE POINTER
        //increasing the angle of 90 degreese
        
        myTile[r][c].angle+=90;
        
        //        randomChoice();        // random choice now integrated with the spacebar
        //calculating and printing the tile index
        int index = c*row+r+1;
        println("Tile num " + index);
        tileChanged=true;
      }
    }
  }
}


/////////////////////////MOUSE DRAGGED///////////////////////////////

//this function will rotate the tiles at each mouse realeased
public void mouseDragged() {
  for (int r=0; r<row; r++) {
    for (int c=0; c<col; c++) {

      if (
      //limiting the mouse position to each tile
      mouseX >= myTile[r][c].x
        && mouseX < myTile[r][c].x + myTile[r][c].tileSize
        && mouseY >= myTile[r][c].y
        && mouseY < myTile[r][c].y + myTile[r][c].tileSize) {
        stroke(1);
        ellipse(r*cellSize, c*cellSize, 10, 10); //MOUSE POINTER
        //increasing the angle of 90 degreese
        myTile[r][c].angle+=90;
        //        randomChoice();        // random choice now integrated with the spacebar
        //calculating and printing the tile index
        int index = c*row+r+1;
        println("Tile num " + index);
        tileChanged=true;

      }
    }
  }
}

class Stage {
  int cellSize; //size of the cell

  //constructor that creates the stage
  Stage( int size ) {
    cellSize = size;
  }
  public void createStage() {
    fill(120);
    stroke(0);
    translate(width/2-(cellSize*row)/2+cellSize/2, height/2-(cellSize*col)/2+cellSize/2);

    for (int r=0; r<row; r++) {
      for (int c=0; c<col; c++) {
        //normal BG color
        fill ((bgColor), (bgColor), (bgColor));

        //random BG color on R key pressed
        if (keyPressed) {
          if (key == 'r' || key == 'R') {
            fill (random(0,bgColor), random(0,bgColor), random(0,bgColor));
          }
        } 
        stroke (bgColor);
        strokeWeight(0);
        //noStroke();
        rectMode(CENTER);
        rect(r*cellSize, c*cellSize, cellSize, cellSize);
      }
    }
  }
}

class Tile {
  int angle;

  int tileSize;
  int ind;
  int x;
  int y;


  Tile(  int theta ) {
    angle=theta;
  }

  public void draw(int xPos, int yPos, int size, int indent) {
    noStroke();
    tileSize = size;
    ind = indent;
    x = xPos;
    y = yPos;
    rectMode(CENTER);
    //////////////////////DEFAULT TILE/////////////////


    pushMatrix();
    translate(x, y);
    rotate(radians(angle));
    //black rectangle
    fill(0, 0, 0);
    //triangle(0, 0, 0, tileSize, tileSize, tileSize);
    rect(0, 0, tileSize-ind, tileSize-ind);



    if (tile1) {

      //white triangle
      fill(255, 255, 255);
      //rect(0, 0, tileSize/3, tileSize-ind);
      beginShape();
      translate(-tileSize/2, -tileSize/2);
      vertex(0, 0);
      vertex(0, tileSize);
      vertex(tileSize, tileSize);
      endShape();
    }
    popMatrix();


    //arc 1
    if (tile2 || tile3) {
      pushMatrix();
      translate(x, y);
      rotate(radians(angle));
      fill(255, 255, 255);
      arc(-tileSize/2, -tileSize/2, tileSize*2-indent, tileSize*2-indent, 0, PI/2, PIE);
      popMatrix();
    }

    if (tile3) {
      //arc 2
      pushMatrix();
      translate(x, y);
      rotate(radians(angle+180));
      fill(0, 0, 0, 120);
      arc(-tileSize/2, -tileSize/2, tileSize*2-indent, tileSize*2-indent, 0, PI/2, PIE);
      popMatrix();
    }



    if (tile4) {
      //lomg line
      pushMatrix();
      translate(x, y);
      rotate(radians(angle));
      fill(225, 225, 225);
      rect(0, 0, tileSize-ind, tileSize/4-ind);
      popMatrix();
    }
    
    
    
    if (tile5) {
      //inner square
      pushMatrix();
      translate(x, y);
      rotate(radians(angle));
      fill(bgColor );
      rect(0, 0, tileSize-ind, tileSize-ind); //bg
      fill(255, 255, 255);
      rect(0, 0, tileSize-10-ind, tileSize/2-10-ind);
      fill(0, 0, 0);
      rect(0, 0, tileSize/2-10-ind, tileSize-10-ind);

      popMatrix();
    }
    
 

    //

    ////////////////////////////////////////////////////////////////
  }
}






//////////////////////////DRAWING TILES function//////////////////////this function is outside the class!!!

public void drawTiles() {
  //  //drawing tiles
  for (int r=0; r<row; r++) {
    for (int c=0; c<col; c++) {
      // this will draw the tiles on the stage
      myTile[r][c].draw(r*cellSize, c*cellSize, cellSize, indent);
    }
  }
}
////GUI




private ControlP5 cp5;

ControlFrame cf;
boolean toggleValue = false;

Toggle randTog;
Textlabel myTextlabelA;
Bang resetBang;
Bang randomPattern;
Bang screenShot;
///// end GUI///

int row = 20;
int col = 10;
int cellSize=50;
int indent = 0;
int angle=0;
int counter=0;

int bgColor;
int framerate = 24;
int randomello;
int maxRandomello;

//save image vars//
int imageCounter=0;
int d = day();    // Values from 1 - 31
int m = month();  // Values from 1 - 12
int y = year();   // 2003, 2004, 2005, etc.
int sec = second();  // Values from 0 - 59
int min = minute();  // Values from 0 - 59
int h = hour();    // Values from 0 - 23
//end save image vars//

boolean value, tile1, tile2, tile3, tile4, tile5;
boolean pat1,pat2,pat3;
boolean hasIndent,indentRandom;
boolean tileChanged=false;


//creating the stage
Stage myStage = new Stage(cellSize);

//creating the tiles
Tile[][] myTile; //define array 2d of Tile objects



//////////////////////////RANDOM CHOICE//////////////////////

public void randomChoice() {

  //  randomello = (int)random(1, row/2+random(1, col/2)); //CREATING A RANDOM NUMBER THAT IS USED AS A REMAINDER
  randomello = (int)random(1, maxRandomello); //CREATING A RANDOM NUMBER THAT IS USED AS A REMAINDER

  if (indentRandom) {
    indent=PApplet.parseInt(random(0, cellSize/2));//RANDOMISING THE INDENT OF THE TILES
  } else {
    indent=indent;
  }
  println(randomello);

  for (int r=0; r<row; r++) {
    for (int c=0; c<col; c++) {
      //this will reset the tiles before drawing the random pattern.
      //so that any previously rotated tiles will go back at 0 degrees.
      //i need a condition because otherwise it will reset the tiles at all times.
      //tileChanged checks if the mouse hs been pressed
      if (tileChanged) {
        myTile[r][c].angle=0;
      }

      //COUNTING THE COLUMNS and choising each tile according the reminder

      if (pat1) {
        if (c%randomello==0) {
          myTile[r][c].angle+=90;
        }

        //COUNTING THE ROWS and choising each tile according the reminder
        if (r%randomello!=0) {
          myTile[r][c].angle+=90;
        }
      }

      ///////////EXTRA BITS AND TRIALS ON FOR THE RANDOM TILES.
      //
      if (pat2) {

        if (r%2==0) {
          myTile[r][c].angle+=90;
        }
        //
        if (c%1==0) {
          myTile[r][c].angle+=90;
        }


//        if (c*r%8==0) {
//          myTile[r][c].angle+=90;
//        }
      }
      //      
      //    
      if (pat3) {

        if (r*c%2!=0) {
          myTile[r][c].angle-=90;
        }
        //      
        if (c*r%1==0) {
          myTile[r][c].angle+=90;
        }
      }
    }
  }
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Tiles_08_random_2dArray" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
