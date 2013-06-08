/* MineWorld
 * A proof of concept minecraft-like world.
 */
package com.elezeta.mineworld;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;
 
/**
 *
 * @author elezeta
 */
public class Player {
    private float x;
    private float y;
    private float z;
    private float vx;
    private float vy;
    private float vz;
    private float yaw;
    private float pitch;
    private float jumpCounter;
    private float jumpCounterDone;
    private boolean onGround;
    
    public Player(float x,float y,float z,float yaw,float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.vx = 0;
        this.vy = 0;
        this.vz = 0;
        this.jumpCounter = 0;
        this.jumpCounterDone = 0;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = false;
    }

    private void acelUp(float distance) {
        vy += distance;
    }

    private void acelDown(float distance) {
        vy -= distance;
    }

    private void acelFront(float distance) {
        vx += distance * (float)Math.sin(Math.toRadians(yaw));
        vz += distance * (float)Math.cos(Math.toRadians(yaw));
    }

    private void acelBack(float distance) {
        vx -= distance * (float)Math.sin(Math.toRadians(yaw));
        vz -= distance * (float)Math.cos(Math.toRadians(yaw));
    }

    private void acelLeft(float distance) {
        vx += distance * (float)Math.sin(Math.toRadians(yaw-90));
        vz += distance * (float)Math.cos(Math.toRadians(yaw-90));
    }

    private void acelRight(float distance) {
        vx += distance * (float)Math.sin(Math.toRadians(yaw+90));
        vz += distance * (float)Math.cos(Math.toRadians(yaw+90));
    }    

    private void yaw(float amount) {
        yaw += amount;
    }

    private void pitch(float amount) {
        pitch += amount;
        if (pitch > 90f)
            pitch = 90f;
        if (pitch < -90f)
            pitch = -90f;
                    
    }

    public void lookThrough() {
        glRotatef(pitch, 1.0f, 0.0f, 0.0f);
        glRotatef(yaw, 0.0f, 1.0f, 0.0f);
        glTranslatef(-x,-y, z);
    }  
    
    public void move(World world,float delta,float mouseSensitivity,float acelFactor,float decelFactor,float gravity,float jump,float jumpCounterMax,float jumpCounterMin) {
        float dx = Mouse.getDX();
        float dy = Mouse.getDY();
        yaw(dx*mouseSensitivity);
        pitch(-dy*mouseSensitivity);
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            acelFront(acelFactor*delta);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            if (onGround) {
                onGround = false;
                jumpCounter = jumpCounterMax;
                jumpCounterDone = 0;
            }
        }

        if (!Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            if (jumpCounterDone>jumpCounterMin)
            jumpCounter = 0;
        }
        
        if (jumpCounter>0) {
            acelUp(jump*jumpCounter*delta+gravity*delta);
            jumpCounter-=delta;
            jumpCounterDone+=delta;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            acelBack(acelFactor*delta);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            acelLeft(acelFactor*delta);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            acelRight(acelFactor*delta);
        }
        
        acelDown(gravity*delta);
        
        float reductionx = (vx*decelFactor*delta);
        if (Math.abs(reductionx) < Math.abs(vx))
        	vx = vx-reductionx;
        else
        	vx = 0;
        float reductionz = (vz*decelFactor*delta);
        if (Math.abs(reductionz) < Math.abs(vz))
        	vz = vz-reductionz;
        else
        	vz = 0;

        moveUnitX(world,delta);
        moveUnitY(world,delta);
        moveUnitZ(world,delta);
    }
           
    private int floor(float i) {
        if (i >= 0)
            return (int)i;
        else
            return ((int)i)-1;
    }
    private boolean checkCollision(float x,float y,float z,World world) {
        Visibility vis = world.get(floor(x),floor(y),floor(z)).getType().getVisibility();
        return !vis.equals(Visibility.AIR);
    }
    
    private void moveUnitX(World world,float delta) {
        float v = vx;
        if (v > 0f) {
            while (v > 1f) {
                v-=1f;
                if (!moveX(world,1f*delta)) {
                    vx = 0f;
                    v = 0f;
                }
            }
            if (!moveX(world,v*delta)) {
                vx = 0f;
                v = 0f;
            }
        }
        else if (v < 0) {
            while (v < -1f) {
                v+=1f;
                if (!moveX(world,-1f*delta)) {
                    vx = 0f;
                    v = 0f;
                }
            }
            if (!moveX(world,v*delta)) {
                vx = 0f;
                v = 0f;
            }
        }
    }
    private void moveUnitY(World world,float delta) {
        float v = vy;
        if (v > 0f) {
            while (v > 1f) {
                v-=1f;
                if (!moveY(world,1f*delta)) {
                    vy = 0f;
                    v = 0f;
                }
            }
            if (!moveY(world,v*delta)) {
                vy = 0f;
                v = 0f;
            }
        }
        else if (v < 0) {
            while (v < -1f) {
                v+=1f;
                if (!moveY(world,-1f*delta)) {
                    vy = 0f;
                    v = 0f;
                }
            }
            if (!moveY(world,v*delta)) {
                vy = 0f;
                v = 0f;
            }
        }
    }

    private void moveUnitZ(World world,float delta) {
        float v = vz;
        if (v > 0f) {
            while (v > 1f) {
                v-=1f;
                if (!moveZ(world,1f*delta)) {
                    vz = 0f;
                    v = 0f;
                }
            }
            if (!moveZ(world,v*delta)) {
                vz = 0f;
                v = 0f;
            }
        }
        else if (v < 0) {
            while (v < -1f) {
                v+=1f;
                if (!moveZ(world,-1f*delta)) {
                    vz = 0f;
                    v = 0f;
                }
            }
            if (!moveZ(world,v*delta)) {
                vz = 0f;
                v = 0f;
            }
        }
    }   
    
    float blockBorder = 0.25f;
    float blockBorderOut = 0.70f;
    float blockBorderUp = 0.2f;
    float blockBorderMed = -0.675f;
    float blockBorderDown = -1.14f;
    
    private boolean moveX(World world,float v) {
        float oldx = x;
        float boundx;
        float auxx;
        x += v;
        if (v > 0) {
            boundx = floor(oldx)+blockBorder;
            auxx = x+1-blockBorder;
            if (
                checkCollision(auxx,y+blockBorderUp,z+blockBorder,world) |
                checkCollision(auxx,y+blockBorderUp,z+blockBorderOut,world) |
                checkCollision(auxx,y+blockBorderDown,z+blockBorder,world) |
                checkCollision(auxx,y+blockBorderDown,z+blockBorderOut,world) |
                checkCollision(auxx,y+blockBorderMed,z+blockBorder,world) |
                checkCollision(auxx,y+blockBorderMed,z+blockBorderOut,world)
                    ) {
                if (x>boundx) {
                    x = boundx;
                    return false;
                }
            }
        }
//            System.out.println("old y is "+oldy+" new y is "+y+" aux y is "+auxy+" ("+Math.round(auxy-0.5f)+") boundy is "+boundy);
        else if (v < 0) {
            boundx = floor(oldx)+1-blockBorder;
            auxx = x+blockBorder;
            if (
                checkCollision(auxx,y+blockBorderUp,z+blockBorder,world) |
                checkCollision(auxx,y+blockBorderUp,z+blockBorderOut,world) |
                checkCollision(auxx,y+blockBorderDown,z+blockBorder,world) |
                checkCollision(auxx,y+blockBorderDown,z+blockBorderOut,world) |
                checkCollision(auxx,y+blockBorderMed,z+blockBorder,world) |
                checkCollision(auxx,y+blockBorderMed,z+blockBorderOut,world)
                    ) {
                if (x<boundx) {
                    x = boundx;
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean moveY(World world,float v) {
        float oldy = y;
        float boundy;
        float auxy;
        y += v;
        onGround = false;
        if (v > 0) {
            boundy = floor(oldy)+0.20f;
            auxy = y+0.80f;
            if (
                checkCollision(x+blockBorder,auxy,z+blockBorder,world) |
                checkCollision(x+blockBorder,auxy,z+blockBorderOut,world) |
                checkCollision(x+blockBorderOut,auxy,z+blockBorderOut,world) |
                checkCollision(x+blockBorderOut,auxy,z+blockBorder,world)
               ) {
                if (y>boundy) {
                    jumpCounter = 0;
                    y = boundy;
                    return false;
                }
            }
        }
//            System.out.println("old y is "+oldy+" new y is "+y+" aux y is "+auxy+" ("+Math.round(auxy-0.5f)+") boundy is "+boundy);

        else if (v < 0) {
            boundy = floor(oldy)+0.15f;
            auxy = y-1.15f;
            if (
                checkCollision(x+blockBorder,auxy,z+blockBorder,world) |
                checkCollision(x+blockBorder,auxy,z+blockBorderOut,world) |
                checkCollision(x+blockBorderOut,auxy,z+blockBorderOut,world) |
                checkCollision(x+blockBorderOut,auxy,z+blockBorder,world)
               ) {
                if (y<boundy) {
                    onGround = true;
                    jumpCounter = 0;
                    y = boundy;
                    return false;
                }
            }
            auxy = y-0.575f;
            if (
                checkCollision(x+blockBorder,auxy,z+blockBorder,world) |
                checkCollision(x+blockBorder,auxy,z+blockBorderOut,world) |
                checkCollision(x+blockBorderOut,auxy,z+blockBorderOut,world) |
                checkCollision(x+blockBorderOut,auxy,z+blockBorder,world)
               ) {
                if (y<boundy) {
                    onGround = true;
                    jumpCounter = 0;
                    y = boundy;
                    return false;
                }
            }
        }
        return true;
    }
    

    private boolean moveZ(World world,float v) {
        float oldz = z;
        float boundz;
        float auzz;
        z += v;
        if (v > 0) {
            boundz = floor(oldz)+blockBorder;
            auzz = z+1-blockBorder;
            if (
                checkCollision(x+blockBorderOut,y+blockBorderUp,auzz,world) |
                checkCollision(x+blockBorder,y+blockBorderUp,auzz,world) |
                checkCollision(x+blockBorderOut,y+blockBorderDown,auzz,world) |
                checkCollision(x+blockBorder,y+blockBorderDown,auzz,world) |
                checkCollision(x+blockBorderOut,y+blockBorderMed,auzz,world) |
                checkCollision(x+blockBorder,y+blockBorderMed,auzz,world) 
               ) {
                if (z>boundz) {
                    z = boundz;
                    return false;
                }
            }
        }
//            System.out.println("old y is "+oldy+" new y is "+y+" auz y is "+auzy+" ("+Math.round(auzy-0.5f)+") boundy is "+boundy);
        else if (v < 0) {
            boundz = floor(oldz)+1-blockBorder;
            auzz = z+blockBorder;
            if (
                checkCollision(x+blockBorderOut,y+blockBorderUp,auzz,world) |
                checkCollision(x+blockBorder,y+blockBorderUp,auzz,world) |
                checkCollision(x+blockBorderOut,y+blockBorderDown,auzz,world) |
                checkCollision(x+blockBorder,y+blockBorderDown,auzz,world) |
                checkCollision(x+blockBorderOut,y+blockBorderMed,auzz,world) |
                checkCollision(x+blockBorder,y+blockBorderMed,auzz,world) 
               ) {
                if (z<boundz) {
                    z = boundz;
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * @return the x
     */
    public float getX() {
        return x;
    }

    /**
     * @return the y
     */
    public float getY() {
        return y;
    }

    /**
     * @return the z
     */
    public float getZ() {
        return z;
    }
    
}
