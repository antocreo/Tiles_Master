


void setup() {
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


void draw() {
  frameRate(framerate);

  //creating the stage. this is the background where the tiles will be placed.
  myStage.createStage();

  //drawing tiles
  drawTiles();
  

    
}






