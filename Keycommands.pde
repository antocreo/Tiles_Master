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


void keyPressed() {

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
    tile6=false;
    tile7=false;
  }

  if (key == '2' ) {
    tile1=false;
    tile2=true;
    tile3=false;
    tile4=false;
    tile5=false;
    tile6=false;
    tile7=false;
  }
  if (key == '3' ) {
    tile1=false;
    tile2=false;
    tile3=true;
    tile4=false;
    tile5=false;
    tile6=false;
    tile7=false;
  }
  if (key == '4' ) {
    tile1=false;
    tile2=false;
    tile3=false;
    tile4=true;
    tile5=false;
    tile6=false;
    tile7=false;
  }

  if (key == '5' ) {
    tile1=false;
    tile2=false;
    tile3=false;
    tile4=false;
    tile5=true;
    tile6=false;
    tile7=false;
  }

  if (key == '6' ) {
    tile1=false;
    tile2=false;
    tile3=false;
    tile4=false;
    tile5=false;
    tile6=true;
    tile7=false;
  }

  if (key == '7' ) {
    tile1=false;
    tile2=false;
    tile3=false;
    tile4=false;
    tile5=false;
    tile6=false;
    tile7=true;
  }

  //triggering random patterns

  if (key == 'q' ) {
    pat1=true;
    pat2=false;
    pat3=false;
    sandpile = false;
  }

  if (key == 'w' ) {
    pat1=false;
    pat2=true;
    pat3=false;
    sandpile = false;
  }

  if (key == 'e' ) {
    pat1=false;
    pat2=false;
    pat3=true;
    sandpile = false;
  }

  if (key == 'r' ) {

    sandpile = !sandpile;
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
void reset() {

  indent=0;
  for (int r=0; r<row; r++) {
    for (int c=0; c<col; c++) {
      //        //this will reset the tiles before drawing the random pattern.
      //so that any previously rotated tiles will go back at 0 degrees.
      myTile[r][c].angle=0;
    }
  }
}

