package com.kgb.objects;

import com.kgb.drawing.Canvas;
import com.kgb.drawing.Color;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by k.betlej on 1/8/16.
 */
public class Rectangle extends DrawalbeObject {

    private final int mLeft;
    private final int mTop;
    private final int mRight;
    private final int mBottom;

    public Rectangle(int left, int top, int right, int bottom) {

        mLeft = left;
        mTop = top;
        mRight = right;
        mBottom = bottom;
    }
    @Override
    public void draw(Canvas canvas) {
        Color currColor = canvas.getColor();
//        canvas.setColor(Color.RED);
        glPushMatrix();
        glBegin(GL_QUADS);
        glVertex2d(mLeft, mTop);
        glVertex2d(mRight, mTop);
        glVertex2d(mRight, mBottom);
        glVertex2d(mLeft, mBottom);
        glEnd();
        glPopMatrix();
        canvas.setColor(currColor);

    }
}
