package ca.benbingham.spritegenerator;

import ca.benbingham.spritegenerator.colourprocessing.ColourSet;
import ca.benbingham.spritegenerator.imageprocessing.Image;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class MainApp {
    public static void main(String[] args) throws IOException {
        int inputR = 0;
        int inputG = 0;
        int inputB = 0;
        String materialName = "";

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--color")) {
                try {
                    inputR = Integer.parseInt(args[i + 1]);
                    inputG = Integer.parseInt(args[i + 2]);
                    inputB = Integer.parseInt(args[i + 3]);
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Not enough colour values given.");
                    System.exit(1);
                }
            }
            if (args[i].equals("--name")) {
                try {
                    materialName = args[i + 1];
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("No material name given.");
                    System.exit(1);
                }
            }
        }
        if (inputR == 0 || inputG == 0 || inputB == 0 || materialName.equals("")) {
            System.out.println("Not enough arguments given. Make sure to include both \"--color\", \"--name\" and there respective parameters.");
            System.exit(1);
        }

        ColourSet colourSet = new ColourSet(inputR, inputG, inputB);

        // Colours that will not change between all sprites, by default the wooden handles of tools and the stone texture of ore.
        colourSet.resetColor(new Color(73, 54, 21));
        colourSet.resetColor(new Color(104,78,30));
        colourSet.resetColor(new Color(137,103,39));
        colourSet.resetColor(new Color(40,30,11));
        colourSet.resetColor(new Color(116, 116, 117));
        colourSet.resetColor(new Color(143, 143, 144));
        colourSet.resetColor(new Color(127, 127, 128));
        colourSet.resetColor(new Color(104, 104, 105));

        List<String> fileList = new ArrayList<>();

        final String path = "Input";
        final File jarFile = new File(MainApp.class.getProtectionDomain().getCodeSource().getLocation().getPath());

        if(jarFile.isFile()) {
            final JarFile jar = new JarFile(jarFile);
            final Enumeration<JarEntry> entries = jar.entries();
            while(entries.hasMoreElements()) {
                final String name = entries.nextElement().getName();
                if (name.startsWith(path + "/") && name.endsWith("png")) {
                    System.out.println(name);
                    fileList.add(name);
                }
            }
            jar.close();
        }

        for (String file : fileList) {
            ClassLoader cl = MainApp.class.getClassLoader();
            InputStream inputStream = cl.getResourceAsStream(file);

            String itemType = splitStringAtUnderscore(file)[1];
            Image image = new Image(16, 16, inputStream);
            image.createImage(colourSet);
            image.writeToNewFile(materialName + "_" + itemType);
        }
    }

    static private String[] splitStringAtUnderscore(String string) {
        return string.split("_");
    }
}
