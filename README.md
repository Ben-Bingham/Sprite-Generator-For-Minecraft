# Sprite Generator For Minecraft

This is a simple tool that allows you to automatically generate sprites for Minecraft for a custom material. By default this tool will generate textures for the following items:
* Axe
* Block
* Boots
* Chestplate
* Helmet
* Hoe
* Ingot
* Leggings
* Nugget
* Ore
* Pickaxe
* Shovel
* Sword

## Basic setup:

1.) Download the included `.jar` and place it in a folder somewhere on your system.

2.) Open a terminal and `cd` into the folder that includes your `.jar` file.

3.) Execute the following command, be sure to change the name and the RGB values to custom values:

```
java -jar SpriteGeneratorMaven-1.0.jar --color 87 15 20 --name ruby
```
## Notes:

* This tool does not create a texture with the exact same colour has the one inputed.
* Darker colours will give you better results.
* To add more images to make sprites off of, simply add them to the `scr/main/resources/Input` and than recompile the project with Maven. (Note you will need Maven installed to recompile the project.) 
* Java needs to be installed on your system to use this tool, Java 16 is the only version I have tested.
