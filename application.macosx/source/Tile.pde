class Tile {
  int angle;

  int tileSize;
  int ind;
  int x;
  int y;


  Tile(  int theta ) {
    angle=theta;
  }

  void draw(int xPos, int yPos, int size, int indent) {
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

void drawTiles() {
  //  //drawing tiles
  for (int r=0; r<row; r++) {
    for (int c=0; c<col; c++) {
      // this will draw the tiles on the stage
      myTile[r][c].draw(r*cellSize, c*cellSize, cellSize, indent);
    }
  }
}
