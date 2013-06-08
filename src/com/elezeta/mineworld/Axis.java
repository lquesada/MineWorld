/* MineWorld
 * A proof of concept minecraft-like world.
 */
package com.elezeta.mineworld;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author elezeta
 */
public abstract class Axis {
    
    private Axis() {
        
    }
    
    private static int axisDisplayList = -1;
    
    public static void draw() {
        if (axisDisplayList == -1) {
            axisDisplayList = glGenLists(1);
            glNewList(axisDisplayList,GL_COMPILE);
            glLineWidth(7.0f);
            glColor3f(1f,0f,0f); // X axis is red.
            glBegin(GL_LINES);
                glVertex3f(0f,0f,0f);
                glVertex3f(2f,0f,0f); 
            glColor3f(0.5f,0f,0f);
                glVertex3f(-2f,0f,0f);
                glVertex3f(0f,0f,0f); 

            glColor3f(0f,1f,0f); // Y axis is green.
                glVertex3f(0f,0f,0f);
                glVertex3f(0f,2f,0f); 
            glColor3f(0f,0.5f,0f);
                glVertex3f(0f,-2f,0f);
                glVertex3f(0f,0f,0f); 

            glColor3f(0f,0f,1f); // z axis is blue.
                glVertex3f(0f,0f,0f);
                glVertex3f(0f,0f,2f); 
            glColor3f(0f,0f,0.5f);
                glVertex3f(0f,0f,-2f);
                glVertex3f(0f,0f,0f); 
            glEnd();       
            glEndList();
        }
        glCallList(axisDisplayList);
    }
    
}
