/* MineWorld
 * A proof of concept minecraft-like world.
 */
package com.elezeta.mineworld;

import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Color;

import com.elezeta.mineworld.resources.Resources;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.Sys.*;

/**
 *
 * @author elezeta
 */
class MineWorld {
  
    /* COUNTERS */
    private long lastFrame;
    private float delta;
    private float fps;
    private float fpsSoft;
    private float deltaCarry;
    
    /* ENTITIES */
    private World world;
    private Player player;

    /* CONFIG WORLD */
    private int worldWidth = 15;
    private int worldLength = 15;
    private int worldHeight = 180;
    
    /* CONFIG SCREEN */
    private int maxFPS = 100;
    private int width = 850;
    private int height = 480;
    private float mouseSensitivity = 0.2f;

    /* CONFIG VIEW */
    private float wview = (0.11f)/2f;
    private float hview = (0.11f*((float)height)/((float)width))/2f;
    private float viewmin = 0.05f;
    
    /* CONFIG PHYSICS */
    private float acelFactor = 60f;
    private float decelFactor = 10f;
    private float gravity = 60f;
    private float jump = 260f;
    private float jumpCounter = 0.22f;
    private float jumpCounterMinimum = 0.1f;

    public void run() {
        try {
            initDisplay(width,height);
            Resources.load();
            world = new World(worldWidth,worldHeight,worldLength);
        }
        catch (LWJGLException ex) {
            Logger.getLogger(MineWorld.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            System.exit(0);
        } catch (FontFormatException ex) {
            Logger.getLogger(MineWorld.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(MineWorld.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            System.exit(0);
        }
        lastFrame = getTime();
        fps = maxFPS;
        fpsSoft = maxFPS;
        delta = 0f;
        deltaCarry = 0f;
               
        Mouse.setGrabbed(true);
        player = new Player(1f,6.15f,1f,40f,15f);

	while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            calculateDelta();
            calculateFPS();
            player.move(world,delta,mouseSensitivity,acelFactor,decelFactor,gravity,jump,jumpCounter,jumpCounterMinimum);
            glClear(GL_DEPTH_BUFFER_BIT|GL_COLOR_BUFFER_BIT);
            render();
            Display.update();
            Display.sync(maxFPS);
        }
        Display.destroy();
    }

    private void initDisplay(int width, int height) throws LWJGLException {
        Display.setDisplayMode(new DisplayMode(width,height));
        Display.setTitle("MineWorld");
        Display.create();
        Display.setVSyncEnabled(false);

        glShadeModel(GL_FLAT);        
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_ALPHA_TEST);
        glAlphaFunc(GL_GREATER,0f);
        glDisable(GL_LIGHTING);                    
        glEnable(GL_TEXTURE_2D);
        glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_NEAREST);
        glEnable(GL_BLEND);

        glClearColor(0.53f, 0.70f, 0.93f, 0.0f);                
        glClearDepth(1d);                                       
    }
    
    
    private void render() {

        // WORLD
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glFrustum(-wview,wview,-hview,hview,viewmin, 2000f);

        glMatrixMode(GL_MODELVIEW);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glLoadIdentity();

        player.lookThrough();
        
        Resources.getTerrainTextureAtlas().bind();
        for (int x = 0;x < worldWidth;x++) {
            for (int y = 0;y < worldHeight;y++) {
                for (int z = 0;z < worldLength;z++) {
                    Block.draw(world,player,x,y,z,0.55f,0.88f,0.33f,0.03f);
                }
            }
        }
        glTranslatef(4f,10f,-4f);
        Axis.draw();
        
        // HUD
        glMatrixMode(GL_PROJECTION);
        glPushMatrix();
        glLoadIdentity();
        glOrtho(0,width,height,0, 1, -1);
        glClear(GL_DEPTH_BUFFER_BIT);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glColor3f(1f,1f,1f);


        Resources.getInfoFont().drawString(3,3,"FPS: "+(int)fpsSoft, Color.white);
        Resources.getInfoFont().drawString(3,15,"X: "+player.getX(), Color.white);
        Resources.getInfoFont().drawString(3,27,"Y: "+player.getY(), Color.white);
        Resources.getInfoFont().drawString(3,39,"Z: "+player.getZ(), Color.white);

        Resources.getGuiTextureAtlas().bind();
        glBlendFunc(GL_ONE_MINUS_DST_COLOR, GL_ONE_MINUS_SRC_ALPHA);
        Crosshair.drawBig(width,height);
        glClear(GL_DEPTH_BUFFER_BIT);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        Crosshair.drawSmall(width,height);
        
        glMatrixMode(GL_PROJECTION);
        glPopMatrix();
        
    }
    
    public void calculateDelta() {
        long time = getTime();
        delta = (time - lastFrame)/1000f;
        lastFrame = time;
    }
    
    public void calculateFPS() {
        if (delta == 0f)
            fps = maxFPS;
        else
            fps = 1f/delta;
        deltaCarry += delta;
        if (deltaCarry >= 0.5) {
            deltaCarry %= 0.5;
            fpsSoft = fps;
        }        
    }


}
