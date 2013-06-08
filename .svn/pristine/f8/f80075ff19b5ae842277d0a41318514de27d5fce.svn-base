/* MineWorld
 * A proof of concept minecraft-like world.
 */
package com.elezeta.mineworld;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author elezeta
 */
class Block {
    private BlockType type;
    private int displayList;
    
    public Block(BlockType type) {
        this.type = type;
        this.displayList = -1;
    }

    /**
     * @return the type
     */
    public BlockType getType() {
        return type;
    }
    
    /**
     * @return the type
     */
    public int getDisplayList() {
        return displayList;
    }

    /**
     * @return the type
     */
    public void setDisplayList(int displayList) {
        this.displayList = displayList;
    }    

    public static void draw(World world,Player player,int x,int y,int z,float r,float g,float b,float var) {
        Block block = world.get(x,y,z);
        BlockType btype = block.getType();
        if (btype.getVisibility()==Visibility.AIR||btype.getVisibility()==Visibility.VOID)
            return;
        int displayList = block.getDisplayList();
                
        
        if (displayList == -1) {
            Texture texin;
            r += (float)(Math.random()-0.5)*var;
            g += (float)(Math.random()-0.5)*var;
            b += (float)(Math.random()-0.5)*var;
            float fx = (float)x;
            float fy = (float)y;
            float fz = (float)z;
            float v = 0.5f;
            displayList = glGenLists(1);
            glNewList(displayList,GL_COMPILE);
                glBegin(GL_QUADS);
                    //BACK
                    if (checkVisibility(btype,world.get(x,y,z+1).getType())) {
                        texin = btype.getTextureBack();
                        if (!btype.getColorMaskBack())
                            glColor3f(1f,1f,1f);
                        else
                            glColor3f(r,g,b);
                        glTexCoord2f(texin.getX2(),texin.getY2());
                        glVertex3f(fx-v,fy-v,-fz-v);
                        glTexCoord2f(texin.getX1(),texin.getY2());
                        glVertex3f(fx+v,fy-v,-fz-v);
                        glTexCoord2f(texin.getX1(),texin.getY1());
                        glVertex3f(fx+v,fy+v,-fz-v);
                        glTexCoord2f(texin.getX2(),texin.getY1());
                        glVertex3f(fx-v,fy+v,-fz-v);
                    }
                    //FRONT
                    if (checkVisibility(btype,world.get(x,y,z-1).getType())) {
                        texin = btype.getTextureFront();
                        if (!btype.getColorMaskFront())
                            glColor3f(1f,1f,1f);
                        else
                            glColor3f(r,g,b);
                        glTexCoord2f(texin.getX2(),texin.getY2());
                        glVertex3f(fx-v,fy-v,-fz+v);
                        glTexCoord2f(texin.getX1(),texin.getY2());
                        glVertex3f(fx+v,fy-v,-fz+v);
                        glTexCoord2f(texin.getX1(),texin.getY1());
                        glVertex3f(fx+v,fy+v,-fz+v);
                        glTexCoord2f(texin.getX2(),texin.getY1());
                        glVertex3f(fx-v,fy+v,-fz+v);
                    }

                    //RIGHT
                    if (checkVisibility(btype,world.get(x+1,y,z).getType())) {
                        texin = btype.getTextureRight();
                        if (!btype.getColorMaskRight())
                            glColor3f(1f,1f,1f);
                        else
                            glColor3f(r,g,b);
                        glTexCoord2f(texin.getX2(),texin.getY2());
                        glVertex3f(fx+v,fy-v,-fz+v);
                        glTexCoord2f(texin.getX2(),texin.getY1());
                        glVertex3f(fx+v,fy+v,-fz+v);
                        glTexCoord2f(texin.getX1(),texin.getY1());
                        glVertex3f(fx+v,fy+v,-fz-v);
                        glTexCoord2f(texin.getX1(),texin.getY2());
                        glVertex3f(fx+v,fy-v,-fz-v);
                    }                    

                    //LEFT
                    if (checkVisibility(btype,world.get(x-1,y,z).getType())) {
                        texin = btype.getTextureLeft();
                        if (!btype.getColorMaskLeft())
                            glColor3f(1f,1f,1f);
                        else
                            glColor3f(r,g,b);
                        glTexCoord2f(texin.getX2(),texin.getY2());
                        glVertex3f(fx-v,fy-v,-fz+v);
                        glTexCoord2f(texin.getX2(),texin.getY1());
                        glVertex3f(fx-v,fy+v,-fz+v);
                        glTexCoord2f(texin.getX1(),texin.getY1());
                        glVertex3f(fx-v,fy+v,-fz-v);
                        glTexCoord2f(texin.getX1(),texin.getY2());
                        glVertex3f(fx-v,fy-v,-fz-v);
                    }              
                    
                    //UP
                    if (checkVisibility(btype,world.get(x,y+1,z).getType())) {
                        texin = btype.getTextureUp();
                        if (!btype.getColorMaskUp())
                            glColor3f(1f,1f,1f);
                        else
                            glColor3f(r,g,b);
                        glTexCoord2f(texin.getX2(),texin.getY2());
                        glVertex3f(fx-v,fy+v,-fz+v);
                        glTexCoord2f(texin.getX2(),texin.getY1());
                        glVertex3f(fx+v,fy+v,-fz+v);
                        glTexCoord2f(texin.getX1(),texin.getY1());
                        glVertex3f(fx+v,fy+v,-fz-v);
                        glTexCoord2f(texin.getX1(),texin.getY2());
                        glVertex3f(fx-v,fy+v,-fz-v);
                    }          
                    
                    //DOWN
                    if (checkVisibility(btype,world.get(x,y-1,z).getType())) {
                        texin = btype.getTextureUp();
                        if (!btype.getColorMaskUp())
                            glColor3f(1f,1f,1f);
                        else
                            glColor3f(r,g,b);
                        glTexCoord2f(texin.getX2(),texin.getY2());
                        glVertex3f(fx-v,fy-v,-fz+v);
                        glTexCoord2f(texin.getX2(),texin.getY1());
                        glVertex3f(fx+v,fy-v,-fz+v);
                        glTexCoord2f(texin.getX1(),texin.getY1());
                        glVertex3f(fx+v,fy-v,-fz-v);
                        glTexCoord2f(texin.getX1(),texin.getY2());
                        glVertex3f(fx-v,fy-v,-fz-v);
                    }          
                                 
                glEnd();
            glEndList();
            block.setDisplayList(displayList);
        }
        glCallList(displayList);
    }
                
    private static boolean checkVisibility(BlockType drawing, BlockType next) {
        if (drawing.getVisibility() == Visibility.SOLID && next.getVisibility() == Visibility.SOLID)
            return false;
        if (drawing.getVisibility() == Visibility.TRANSPARENT && next.equals(drawing))
            return false;
        return true;
    }
        
}
