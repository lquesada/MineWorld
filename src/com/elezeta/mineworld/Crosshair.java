/* MineWorld
 * A proof of concept minecraft-like world.
 */
package com.elezeta.mineworld;

import com.elezeta.mineworld.resources.Resources;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author elezeta
 */
public abstract class Crosshair {

    private Crosshair() {

    }

    private static int crosshairBigDisplayList = -1;

    private static int crosshairSmallDisplayList = -1;

    public static void drawBig(int width,int height) {
        if (crosshairBigDisplayList == -1) {
            crosshairBigDisplayList = glGenLists(1);
            float widthcenter = ((float)width)/2f;
            float heightcenter = ((float)height)/2f;
            glNewList(crosshairBigDisplayList,GL_COMPILE);
            Texture texin = Resources.getGuiTextures()[Resources.GID.CROSSHAIR];
            glBegin(GL_QUADS);
                glColor3f(1f,1f,1f);
                glTexCoord2f(texin.getX2(),texin.getY2());
                glVertex2f(widthcenter+8f,heightcenter+8f);
                glTexCoord2f(texin.getX2(),texin.getY1());
                glVertex2f(widthcenter+8f,heightcenter-8f);
                glTexCoord2f(texin.getX1(),texin.getY1());
                glVertex2f(widthcenter-8f,heightcenter-8f);
                glTexCoord2f(texin.getX1(),texin.getY2());
                glVertex2f(widthcenter-8f,heightcenter+8f);
            glEnd();
            glEndList();
        }
        glCallList(crosshairBigDisplayList);
    }

    public static void drawSmall(int width,int height) {
        if (crosshairSmallDisplayList == -1) {
            crosshairSmallDisplayList = glGenLists(1);
            float widthcenter = ((float)width)/2f;
            float heightcenter = ((float)height)/2f;
            glNewList(crosshairSmallDisplayList,GL_COMPILE);
            Texture texin = Resources.getGuiTextures()[Resources.GID.CROSSHAIRDOT];
            glBegin(GL_QUADS);
                glColor3f(1f,1f,1f);
                glTexCoord2f(texin.getX2(),texin.getY2());
                glVertex2f(widthcenter+8f,heightcenter+8f);
                glTexCoord2f(texin.getX2(),texin.getY1());
                glVertex2f(widthcenter+8f,heightcenter-8f);
                glTexCoord2f(texin.getX1(),texin.getY1());
                glVertex2f(widthcenter-8f,heightcenter-8f);
                glTexCoord2f(texin.getX1(),texin.getY2());
                glVertex2f(widthcenter-8f,heightcenter+8f);
            glEnd();
            glEndList();
        }
        glCallList(crosshairSmallDisplayList);
    }

}
