# LPOO_T81 - BOMBERMAN COOL VERSION

>Bomberman is a strategic, maze-based video game franchise in which the player holds an unlimited number of bombs and uses them to open his path on the maze and find the door that will lead to the next level, all this while evading bad monsters :(

>Our version will try to replicate the original game to some extent. This means the concept will be the same but with unlike most iterations of the game, if the players is caught up in his bomb's explosion he will lose a life. And that leads to another difference: lives. The player will have 3 lives that he will lose by exploding himself or getting in contact with the mosnters. There will also be coin random coin drops and may, just maybe some power-ups.

>This project is being made by Nuno Oliveira (up201806525@fe.up.pt) and Luis Pinto (up201806206@fe.up.pt) for LPOO 2019/2020.

## Implemented Features

### Movement and collision detection
>The player moves according to keyboard input (A-left; S-down; D-right; W-up).

>The monsters also move using a pseudorandom algorythm to generate the next position he will be at.

>There's also collision detection. For players if theres is an input that will force the character into a wall it simply won't happen. As for the monsters, if it would happen they'll just choose another position

### Bomb deployment and explosion
>The player can press 'p' to deploy a bomb. It still has some bugs but most of the time it will explode horizontally and vertically in a range of 4 for each side and destroy whatever it can (player, monsters, destructible blocks).

>For now the players only has 1 life point so he will immediatly loose if caught in the explosion.

### Difficulty
>Only easy implemented. It only affects monster movement each is generated at random (pseudo).

### Map Design
>Each try to map generated will have all indestructible block in the same place but the destructible will be placed at random and so will the monster.

>On the contrary, the player will always start on the top left

>There's also a white column on the right. It will be the place where in the future we will draw lives, clock and score.

### Loose condition
>If a monster happens to enter contact with the player he will loose.

### Win Condition
>The door will be hidden underneath one destructible block at random. If the player founds it after destroying the respective block he will win because, for now, there's only 1 level.

## Planned Features
>Make so that the player will have 3 lives as well as time to complete the level.

>Add a score system that will benefit from random coin drops after destructible blocks explose and fast clearence of levels.

>More difficulties that maybe change number of monsters, their deployment position and movement.

>If we have time we were also thinking about integrating power-ups like getting an extra life or invulnerability for x amount of time.

## Design

### 1. Code Architecture
#### The Problem
>On the very first class when we started to plan out the code, one of the main debates we had was on how to structure the code. Initially we just made different classes that would do everything related to them and put them into packages according to their category (for example: we had a class "Monster" that would manipulate and draw itself). It did work but it wasn't very clean, could be hard to read for other people and this way of coding clearly violates the Single Responsibility Principle.
#### The Design
>In order to solve this issue and separate responsibilities into different objects we adopted the MVC (Model - View - Controller) pattern. Doing so allowed us to make our code organized, easier to read and overall structured. We also benefict from the fact that changing or adding features is very much simpler. Since we only thought of implementing this design midway it took longer than expected but this is how it turned out...
#### The Implementation
>Make a basic uml representing and point to some files
#### The Consequences
> - Following some advantages already stated on "The Design" part like facilitating the change of one component alone the MVC will make simultaneous development easier since each component is technically independent. Another bonus we came across at a chance is the fact that a "model" can have multiple views which will be helpful with the bomb (ticking and fire).
> - Now the code not only respects the Single Responsibility Principle (each component has responsibility over a single part of the application) but also, in a way, the Open-Closed Principle because if some feature is added that requires a new class the amount of pre-existing code that needs changing is relatively small.
> - The only downside we think this pattern has is that the framework will become more complex in a way that even if it is more organized it also is much tiring to navigate.

### 2. Bomb Explosion
#### The Problem
>While implementing the bomb functionalities we had to decide on how to do the bomb explosion part. On one hand we could make each bomb responsible for handling its how explosion and on the other hand we could pass that duty to the field controller (in the way we designed the app the field model holds almost every other class) since we will have to one way or another to remove blocks in the explosion range. 
#### The Design
>We chose to go with the first option of letting each bomb handle itself and implementing a State Pattern since this was specially easier to do with the above mentioned MVC pattern. What this pattern does is allow us to represent different states of application with different subclasses. This pattern allowed us to address this simple problem because the bomb clearly has two different states: ticking and explosion. 
#### The Implementation
>add uml and stuff
#### The Consequences
> - The state changing is less complicated and doesn't rely on a bunch of different flags
> - It will make adding more states (like an actual ticking animation) undemanding
> - Saves us the trouble of using big if statements

### 3. Difficulties
#### The problem