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

