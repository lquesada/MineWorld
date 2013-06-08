/* MineWorld
 * A proof of concept minecraft-like world.
 */
package com.elezeta.mineworld.resources;

import org.newdawn.slick.opengl.TextureLoader;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import com.elezeta.mineworld.BlockType;
import com.elezeta.mineworld.Texture;
import com.elezeta.mineworld.Visibility;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author elezeta
 */
public abstract class Resources {
    
    /* RESOURCES */
    private static TrueTypeFont infoFont;
    private static Texture[] terrainTextures;
    private static BlockType[] blockTypes;
    private static org.newdawn.slick.opengl.Texture terrainTextureAtlas;
    private static org.newdawn.slick.opengl.Texture guiTextureAtlas;
    private static Texture[] guiTextures;

    /**
     * @return the terrainTextureAtlas
     */
    public static org.newdawn.slick.opengl.Texture getTerrainTextureAtlas() {
        return terrainTextureAtlas;
    }

    /**
     * @return the guiTextureAtlas
     */
    public static org.newdawn.slick.opengl.Texture getGuiTextureAtlas() {
        return guiTextureAtlas;
    }

    /**
     * @return the guiTextures
     */
    public static Texture[] getGuiTextures() {
        return guiTextures;
    }

    public static abstract class BID {
        public static final int VOID = 0;
        public static final int AIR = 1;
        public static final int STONE = 2;
        public static final int DIRT = 3;
        public static final int GRASS = 4;
        public static final int BEDROCK = 5;
        public static final int TREE1TRUNK = 7;
        public static final int TREE1LEAVES = 8;
    };
     
    public static abstract class TID {
        public static final int GRASS = 0;
        public static final int STONE = 1;
        public static final int DIRT = 2;
        public static final int DIRTGRASS = 3;
        public static final int TREE1BARK = 4;
        public static final int TREE1TRUNK = 5;
        public static final int TREE1LEAVES = 6;
        public static final int BEDROCK = 7;
    }
    
    public static abstract class GID {
        public static final int CROSSHAIR = 0;
        public static final int CROSSHAIRDOT = 1;
    }
    
    private Resources() {
        
    }
    
    public static void load() throws FontFormatException, IOException {
        infoFont = loadFont("com/elezeta/mineworld/resources/LucidaTypewriterRegular.ttf",false,10);
        terrainTextureAtlas = loadTexture("com/elezeta/mineworld/resources/terrain.png");
        terrainTextures = sliceTexture(terrainTextureAtlas,64);
        guiTextureAtlas = loadTexture("com/elezeta/mineworld/resources/gui.png");
        guiTextures = new Texture[2];
        guiTextures[GID.CROSSHAIR] = new Texture(0f/16f,0f/16f,8f/16f,8f/16f);
        guiTextures[GID.CROSSHAIRDOT] = new Texture(8f/16f,0f/16f,16f/16f,8f/16f);

        blockTypes = new BlockType[256];

        blockTypes[BID.VOID] = new BlockType(Visibility.VOID);
        blockTypes[BID.AIR] = new BlockType();
        blockTypes[BID.STONE] = new BlockType(
                getTerrainTextures()[TID.STONE],false,
                getTerrainTextures()[TID.STONE],false,
                getTerrainTextures()[TID.STONE],false,
                getTerrainTextures()[TID.STONE],false,
                getTerrainTextures()[TID.STONE],false,
                getTerrainTextures()[TID.STONE],false,
                Visibility.SOLID);
        blockTypes[BID.DIRT] = new BlockType(
                getTerrainTextures()[TID.DIRT],false,
                getTerrainTextures()[TID.DIRT],false,
                getTerrainTextures()[TID.DIRT],false,
                getTerrainTextures()[TID.DIRT],false,
                getTerrainTextures()[TID.DIRT],false,
                getTerrainTextures()[TID.DIRT],false,
                Visibility.SOLID);
        blockTypes[BID.GRASS] = new BlockType(
                getTerrainTextures()[TID.GRASS],true,
                getTerrainTextures()[TID.DIRT],false,
                getTerrainTextures()[TID.DIRTGRASS],false,
                getTerrainTextures()[TID.DIRTGRASS],false,
                getTerrainTextures()[TID.DIRTGRASS],false,
                getTerrainTextures()[TID.DIRTGRASS],false,
                Visibility.SOLID);
        blockTypes[BID.BEDROCK] = new BlockType(
                getTerrainTextures()[TID.BEDROCK],false,
                getTerrainTextures()[TID.BEDROCK],false,
                getTerrainTextures()[TID.BEDROCK],false,
                getTerrainTextures()[TID.BEDROCK],false,
                getTerrainTextures()[TID.BEDROCK],false,
                getTerrainTextures()[TID.BEDROCK],false,
                Visibility.SOLID);
        blockTypes[BID.TREE1TRUNK] = new BlockType(
                getTerrainTextures()[TID.TREE1TRUNK],false,
                getTerrainTextures()[TID.TREE1TRUNK],false,
                getTerrainTextures()[TID.TREE1BARK],false,
                getTerrainTextures()[TID.TREE1BARK],false,
                getTerrainTextures()[TID.TREE1BARK],false,
                getTerrainTextures()[TID.TREE1BARK],false,
                Visibility.SOLID);
        blockTypes[BID.TREE1LEAVES] = new BlockType(
                getTerrainTextures()[TID.TREE1LEAVES],true,
                getTerrainTextures()[TID.TREE1LEAVES],true,
                getTerrainTextures()[TID.TREE1LEAVES],true,
                getTerrainTextures()[TID.TREE1LEAVES],true,
                getTerrainTextures()[TID.TREE1LEAVES],true,
                getTerrainTextures()[TID.TREE1LEAVES],true,
                Visibility.TRANSPARENT);
    }


    private static TrueTypeFont loadFont(String resourceName,boolean antialias,float size) throws FontFormatException, IOException {
        return new TrueTypeFont(Font.createFont(Font.TRUETYPE_FONT,ResourceLoader.getResourceAsStream(resourceName)).deriveFont(size),antialias);
    }
    
    private static org.newdawn.slick.opengl.Texture loadTexture(String resourceName) throws IOException {
        return TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream(resourceName),GL_NEAREST);
    }
    private static Texture[] sliceTexture(org.newdawn.slick.opengl.Texture texture,float size) throws IOException {
        int w = texture.getImageWidth();
        int h = texture.getImageHeight();
        int cols = w/(int)size;
        int rows = h/(int)size;
        int n = cols*rows;
        Texture[] texts = new Texture[n];
        float row;
        float col;
        for (int i = 0;i < n;i++) {
            row = (i/cols);
            col = (i%cols);
            texts[i] = new Texture((col*size)/w,(row*size)/h,((col*size)+size-1f)/w,((row*size)+size-1f)/h);
        }
        return texts;
    }

    /**
     * @return the infoFont
     */
    public static TrueTypeFont getInfoFont() {
        return infoFont;
    }

    /**
     * @return the terrainTextures
     */
    public static Texture[] getTerrainTextures() {
        return terrainTextures;
    }

    /**
     * @return the blockTypes
     */
    public static BlockType[] getBlockTypes() {
        return blockTypes;
    }
    
}
