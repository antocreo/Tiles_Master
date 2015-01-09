class Stage {
  int cellSize; //size of the cell

  //constructor that creates the stage
  Stage( int size ) {
    cellSize = size;
  }
  void createStage() {
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

