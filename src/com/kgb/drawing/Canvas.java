package com.kgb.drawing;

import org.lwjgl.opengl.GL11;

/**
 * Created by k.betlej on 1/8/16.
 */
public class Canvas {
    private final int mLeft;
    private final int mTop;
    private final int mRight;
    private final int mBottom;
    private Color mColor;

    public Canvas(int left, int top, int right, int bottom, Color color) {

        mLeft = left;
        mTop = top;
        mRight = right;
        mBottom = bottom;
        mColor = color;
    }

    public Color getColor() {
        return mColor;
    }

    public void setColor(Color color) {
        mColor = color;
        GL11.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }
}
