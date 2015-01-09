////GUI
import java.awt.Frame;
import java.awt.BorderLayout;
import controlP5.*;

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



