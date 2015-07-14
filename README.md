# Tiles_Master

http://www.letitbrain.it/LIB_interactive/?page_id=347

![alt tag](http://www.letitbrain.it/LIB_interactive/wp-content/uploads/2015/06/Screen-Shot-2015-06-14-at-15.37.46-940x408.png)

![alt tag](http://www.letitbrain.it/LIB_interactive/wp-content/uploads/2015/06/tiles_1_942355-940x376.jpg)

![alt tag](http://www.letitbrain.it/LIB_interactive/wp-content/uploads/2015/06/tiles_18_942748-940x376.jpg)

/// VARIATION ON Q-TILING BY PABLO VALBUENA////
//Author: Anto+Creo
//EXPLORING CLASSES AND GRIDS. RANDOM PATTERNS AND TILING SYSTEMS.
//End of term assignment: Programming for Artists ::: MFA Computational arts 2014

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
 1,2,3,4,5,6,7   - type of tile</br>
 q,w,e       - type of random pattern</br>
 spacebar    - bang random pattern</br>
 backspace   - bang reset</br>
 i           - toggle random indent</br>
 r           - activates sandpile / random panic!</br>
 mouse click - rotate tile 90 degrees</br>
 mouse drag  - continuos rotation 90 degrees</br>
 
 */
