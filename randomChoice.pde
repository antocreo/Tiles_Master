//////////////////////////RANDOM CHOICE//////////////////////

void randomChoice() {

  //  randomello = (int)random(1, row/2+random(1, col/2)); //CREATING A RANDOM NUMBER THAT IS USED AS A REMAINDER
  randomello = (int)random(1, maxRandomello); //CREATING A RANDOM NUMBER THAT IS USED AS A REMAINDER

  if (indentRandom) {
    indent=int(random(0, cellSize/2));//RANDOMISING THE INDENT OF THE TILES
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

