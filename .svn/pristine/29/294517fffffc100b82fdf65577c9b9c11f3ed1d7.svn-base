/* MineWorld
 * A proof of concept minecraft-like world.
 */
package com.elezeta.mineworld;

import com.elezeta.mineworld.resources.Resources;

/**
 *
 * @author elezeta
 */
public final class World {

    private Block[] world;
    private Block voidBlock;
    private int width;
    private int height;
    private int length;

    public World(int width, int height, int length) {
        this.width = width;
        this.height = height;
        this.length = length;
        
        BlockType[] blockTypes = Resources.getBlockTypes();

        voidBlock = new Block(blockTypes[Resources.BID.VOID]);
        
        world = new Block[this.width*this.height*this.length];
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                for (int k = 0; k < this.length; k++) {
                    BlockType ty;
                    if (j > 4) {
                        ty = blockTypes[Resources.BID.AIR];
                    } else if (j == 4) {
                        ty = blockTypes[Resources.BID.GRASS];
                    } else if (j >= 3) {
                        ty = blockTypes[Resources.BID.DIRT];
                    } else if (j >= 1) {
                        ty = blockTypes[Resources.BID.STONE];
                    } else {
                        ty = blockTypes[Resources.BID.BEDROCK];
                    }
                    set(i,j,k,new Block(ty));
                }
            }
        }

        BlockType ty;
        ty = blockTypes[Resources.BID.DIRT];
        set(6,5,3,new Block(ty));
        set(6,5,4,new Block(ty));
        set(7,5,5,new Block(ty));
        set(10,5,10,new Block(ty));
        set(11,5,10,new Block(ty));
        set(11,5,11,new Block(ty));
        set(10,5,11,new Block(ty));
        ty = blockTypes[Resources.BID.GRASS];
        set(6,6,3,new Block(ty));
        set(6,6,4,new Block(ty));
        set(7,6,5,new Block(ty));
        set(5,5,3,new Block(ty));
        set(5,5,4,new Block(ty));
        ty = blockTypes[Resources.BID.TREE1TRUNK];
        set(7,5,11,new Block(ty));
        set(7,6,11,new Block(ty));
        set(7,7,11,new Block(ty));
        set(7,8,11,new Block(ty));
        set(7,9,11,new Block(ty));
        set(7,10,11,new Block(ty));
        set(7,11,11,new Block(ty));
        ty = blockTypes[Resources.BID.TREE1LEAVES];
        set(6,10,11,new Block(ty));
        set(8,10,11,new Block(ty));
        set(7,10,10,new Block(ty));
        set(7,10,12,new Block(ty));
        set(6,10,10,new Block(ty));
        set(6,10,12,new Block(ty));
        set(8,10,10,new Block(ty));
        set(8,10,12,new Block(ty));
        set(6,11,11,new Block(ty));
        set(8,11,11,new Block(ty));
        set(7,11,10,new Block(ty));
        set(7,11,12,new Block(ty));
        set(5,11,12,new Block(ty));
        set(4,11,12,new Block(ty));
        ty = blockTypes[Resources.BID.STONE];
        set(9,7,4,new Block(ty));
        set(9,7,5,new Block(ty));
        set(10,7,4,new Block(ty));
        set(10,7,5,new Block(ty));
        set(11,7,4,new Block(ty));
        set(11,7,5,new Block(ty));
        set(12,7,4,new Block(ty));
        set(12,7,5,new Block(ty));
        set(13,7,4,new Block(ty));
        set(13,7,5,new Block(ty));
        ty = blockTypes[Resources.BID.STONE];
        set(9,8,6,new Block(ty));
        set(9,8,7,new Block(ty));
        set(10,8,6,new Block(ty));
        set(10,8,7,new Block(ty));
        set(11,8,6,new Block(ty));
        set(11,8,7,new Block(ty));
        set(12,8,6,new Block(ty));
        set(12,8,7,new Block(ty));
        set(13,8,6,new Block(ty));
        set(5,7,7,new Block(ty));        
        set(13,8,6,new Block(ty));
        
    }

    /**
     * @return the width
     */
    public int getWorldWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public int getWorldHeight() {
        return height;
    }

    /**
     * @return the length
     */
    public int getWorldLength() {
        return length;
    }

    public Block get(int x, int y, int z) {
        if (x < 0 | x >= width |
           y < 0 | y >= height |
           z < 0 | z >= length)
                return voidBlock;
        else
            return world[x*height*length+y*length+z];
    }
    
    public void set(int x, int y, int z,Block block) {
        world[x*height*length+y*length+z] = block;
    }
}
