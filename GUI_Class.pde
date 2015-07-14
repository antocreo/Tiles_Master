
ControlFrame addControlFrame(String theName, int theWidth, int theHeight) {
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
    text("1,2,3,4,5,6 - type of tile", 10, 100);
    text("q,w,e,r - type of random pattern - r activates sandpile", 10, 120);
    text("SPACEBAR - bang random pattern", 10, 140);
    text("BACKSPACE - bang reset", 10, 160);
    text("i - toggle random indent", 10, 180);
    text("s - save screenshot", 10, 200);

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

