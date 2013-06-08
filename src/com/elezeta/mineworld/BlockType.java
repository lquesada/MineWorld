/* MineWorld
 * A proof of concept minecraft-like world.
 */
package com.elezeta.mineworld;

/**
 *
 * @author elezeta
 */
public class BlockType {
    
    private Texture textureUp;

    private boolean colorMaskUp;
    
    private Texture textureDown;

    private boolean colorMaskDown;
    
    private Texture textureLeft;

    private boolean colorMaskLeft;
    
    private Texture textureRight;

    private boolean colorMaskRight;
    
    private Texture textureBack;

    private boolean colorMaskBack;
    
    private Texture textureFront;

    private boolean colorMaskFront;

    private boolean anyColorMask;
    
    
    private Visibility visibility;

    /**
     * @return the colorMaskUp
     */
    public boolean getAnyColorMask() {
        return anyColorMask;
    }

    /**
     * @return the colorMaskUp
     */
    public boolean getColorMaskUp() {
        return colorMaskUp;
    }

    /**
     * @return the colorMaskDown
     */
    public boolean getColorMaskDown() {
        return colorMaskDown;
    }

    /**
     * @return the colorMaskLeft
     */
    public boolean getColorMaskLeft() {
        return colorMaskLeft;
    }

    /**
     * @return the colorMaskRight
     */
    public boolean getColorMaskRight() {
        return colorMaskRight;
    }

    /**
     * @return the colorMaskBack
     */
    public boolean getColorMaskBack() {
        return colorMaskBack;
    }

    /**
     * @return the colorMaskFront
     */
    public boolean getColorMaskFront() {
        return colorMaskFront;
    }

    /**
     * @return the visibility
     */
    public Visibility getVisibility() {
        return visibility;
    }
    
    public BlockType() {
        this.visibility = Visibility.AIR;
    }
    
    public BlockType(Visibility visibility) {
        this.visibility = visibility;
    }
    
    public BlockType(Texture textureUp,boolean colorMaskUp,Texture textureDown,boolean colorMaskDown,Texture textureLeft,boolean colorMaskLeft,Texture textureRight,boolean colorMaskRight,Texture textureFront,boolean colorMaskFront,Texture textureBack,boolean colorMaskBack,Visibility visibility) {
        this.visibility = Visibility.SOLID;
        this.textureUp = textureUp;
        this.colorMaskUp = colorMaskUp;
        this.textureDown = textureDown;
        this.colorMaskDown = colorMaskDown;
        this.textureLeft = textureLeft;
        this.colorMaskLeft = colorMaskLeft;
        this.textureRight = textureRight;
        this.colorMaskRight = colorMaskRight;
        this.textureFront = textureFront;
        this.colorMaskFront = colorMaskFront;
        this.textureBack = textureBack;
        this.colorMaskBack = colorMaskBack;
        this.anyColorMask = colorMaskUp|colorMaskDown|colorMaskLeft|colorMaskRight|colorMaskFront|colorMaskBack;
        this.visibility = visibility;
    }

    /**
     * @return the textureUp
     */
    public Texture getTextureUp() {
        return textureUp;
    }

    /**
     * @return the textureDown
     */
    public Texture getTextureDown() {
        return textureDown;
    }

    /**
     * @return the textureLeft
     */
    public Texture getTextureLeft() {
        return textureLeft;
    }

    /**
     * @return the textureRight
     */
    public Texture getTextureRight() {
        return textureRight;
    }

    /**
     * @return the textureBack
     */
    public Texture getTextureBack() {
        return textureBack;
    }

    /**
     * @return the textureFront
     */
    public Texture getTextureFront() {
        return textureFront;
    }

}
