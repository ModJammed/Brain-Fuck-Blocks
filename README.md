BrainFuck Blocks for Minecraft 1.5.1
=============

This mod adds the interesting programming language known as BrainFuck into Minecraft in the form of blocks. It contains a fully featured BrainFuck interpreter, and allows for the creation of simplistic programs using nothing but blocks.

Information about BrainFuck can be found [here](http://en.wikipedia.org/wiki/Brainfuck), but I'll explain the basics of the language here anyway.

### Commands
The eight language commands, each consisting of a single character:

| Character | Meaning                                                                                                                                                                           |
|:----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
|     >     | increment the data pointer (to point to the next cell to the right).                                                                                                              |
|     <     | decrement the data pointer (to point to the next cell to the left).                                                                                                               |
|     +     | increment (increase by one) the byte at the data pointer.                                                                                                                         |
|     -     | decrement (decrease by one) the byte at the data pointer.                                                                                                                         |
|     .     | output a character, the ASCII value of which being the byte at the data pointer.                                                                                                  |
|     ,     | accept one byte of input, storing its value in the byte at the data pointer.                                                                                                      |
|     [     | if the byte at the data pointer is zero, then instead of moving the instruction pointer forward to the next command, jump it forward to the command after the matching ] command*.|
|     ]     | if the byte at the data pointer is nonzero, then instead of moving the instruction pointer forward to the next command, jump it back to the command after the matching [ command*.|

### Mod Usage
Everything in this mod starts with a CPU. The CPU is what all the code blocks will be attached to, and it is what actually interprets and manages the code. Attaching blocks to the CPU is simple. The CPU (and all blocks) have an output face with a red square on it. That face is where the CPU will look for the next block in the chain. If there's no connecting block there, the interpreter will assume the code chain has ended.

When placed, any code block (or CPU) will face the player who placed it, with the output side being to their right. Blocks can be rotated with the wrench however. (Recipe below)

If you want to build away from the CPU, or form a 3D code structure, the Wire block exists. When placed, the output side will face you, and it's capable of facing in all six directions, allowing you to build up or down, as well as in various directions.

Once you've built the code the way you want it, simply right click on the CPU to begin the compilation process.

## Peripherals
So far there are three peripherals that can be used to interact with the internal BrainFuck interpreter in various ways. While this isn't something that's present in the BrainFuck language itself, it was added in order to give this mod more purpose in a Minecraft setting.

Using a peripheral is simple. Simply attach it to an Output/Input block in your code chain and when that point is reached, it will use said peripheral. The tooltip of the peripheral blocks will say whether it should be attached to an input or an output block.

#### Redstone Data Interpreter
This peripheral will output a redstone signal equal to the outputed byte value. If the value outputed is greater than 15, it will just output a maximum strength signal.

#### Redstone Input Interpreter
This peripheral will take the signal strength of any wire attached and set the data of the currently selected cell to that value.

#### Chat Data Interpreter
This peripheral will output whatever was in the current cell to chat, and everyone in the current dimension will see it.

## Tools
### Wrench
This will rotate any block in a clockwise direction
### Code Writer
This is only enabled if crafting recipes for code blocks are disabled. Shift-clicking with it in your hand will cycle through the available code blocks, and right clicking will place the selected block.

## Recipes
### Code Blocks
#### Data Pointer Increment
![Data Pointer Increment](http://i.imgur.com/pPoZLka.png)
#### Data Pointer Decrement
![Data Pointer Decrement](http://i.imgur.com/R2Nlyqd.png)
#### Byte Incrememnt
![Byte Increment](http://i.imgur.com/rfUKu6Y.png)
#### Byte Decrement
![Byte Decrement](http://i.imgur.com/7R7rXUF.png)
#### Byte Output
![Byte Output](http://i.imgur.com/kaYv3Aq.png)
#### Byte Input
![Byte Input](http://i.imgur.com/7R7rXUF.png)
#### Bracket Open
![Bracket Open](http://i.imgur.com/4k7x2xX.png)
#### Bracket Close
![Bracket Close](http://i.imgur.com/pij5Rse.png)

### Other Blocks
#### Machine Casing
![Machine Casing](http://i.imgur.com/I4g0H7T.png)
#### CPU
![CPU](http://i.imgur.com/hSmaeXy.png)
#### Wire
![Wire](http://i.imgur.com/bYqzLtJ.png)

### Tools
#### Wrench
![Wrench](http://i.imgur.com/T8PSFKv.png)
#### Code Writer
![Code Writer](http://i.imgur.com/J0Mawjw.png)

### Crafting Components
#### Metal Spool
![Metal Spool](http://i.imgur.com/mGhatgu.png)
#### Paper Strip
![Paper Strip](http://i.imgur.com/2bGaYI4.png)
#### Circut
![Circut](http://i.imgur.com/5ladUlB.png)
#### Advanced Circut
![Advanced Circut](http://i.imgur.com/1lhEhdh.png)
#### Data Tape
![Data Tape](http://i.imgur.com/BEz8zxp.png)
