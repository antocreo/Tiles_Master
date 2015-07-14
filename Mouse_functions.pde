

/////////////////////////MOUSE RELEASED///////////////////////////////

//this function will rotate the tiles at each mouse realeased
void mouseReleased() {
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
        
//        if (myTile[r][c].angle>90*4) {
//          myTile[r+1][c].angle+=90;  
//          myTile[r][c+1].angle+=90;
//          myTile[r-1][c].angle+=90;
//          myTile[r][c-1].angle+=90;
//        }
        
        //        randomChoice();        // random choice now integrated with the spacebar
        //calculating and printing the tile index
        int index = c*row+r+1;
        println("Tile num " + index);
        println(myTile[r][c].angle);

        tileChanged=true;
      }
    }
  }
}


/////////////////////////MOUSE DRAGGED///////////////////////////////

//this function will rotate the tiles at each mouse realeased
void mouseDragged() {
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

