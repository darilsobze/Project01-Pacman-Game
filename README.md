# Introduction

 The concept of this project is inspired by a scene from the movie "Wall-E" in which a cleaning robot tries to remove the
 dirty tire tracks that Wall-E leaves behind, and to clean it afterwards. Our goal is to create a similar
 scenario using the FOPBot framework.
 The world is a labyrinth consisting of walls, where every space in the labyrinth is accessible, but the movement
 freedom is restricted by the placement of the walls.
 There are two types of robots, the "Contaminants", Contaminant1 and Contaminant2 and the "Cleaner" .
 The contaminants move around the labyrinth according to a predefined pattern and "pollute" the world by placing coins.
 The cleaner is controlled by keystrokes. 
 He can make coins but carry a maximum of GameConstants.CLEANER_CAPACITY coins at the same time.
 The task of the cleaner is to bring as many coins as possible into a predefined field marked in yellow,
 which we call the unloading zone. The unloading zone is always at (0, World.getHeight()- 1.

 The game has clear winning conditions: The Contaminants win if at least 50% of the playing area is occupied by coins.
 The cleaner, on the other hand, wins if he has either placed 200 coins in the unloading zone or if the contaminants run out of coins. (these then turn themselves off)
