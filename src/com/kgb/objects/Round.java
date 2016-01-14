package com.kgb.objects;

import com.kgb.drawing.Canvas;
import com.kgb.drawing.Color;
import com.kgb.drawing.Point;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by k.betlej on 1/8/16.
 */
public class Round extends DrawableObject {
    private static final int ROUND_DRAWING_ACCURACY = 100;

    private int mRadius;
    private Color mColor;

    public Round(int x, int y, int radius, Color color) {
        super(x, y);
        mRadius = radius;
        mColor = color;
    }

    @Override
    public void draw(Canvas canvas) {
        Color currColor = canvas.getColor();
        canvas.setColor(mColor);
        int centerX = mX + mRadius;
        int centerY = mY + mRadius;
        double twicePi = 2.0 * Math.PI;
        glPushMatrix();
        glBegin(GL_TRIANGLE_FAN); //BEGIN ROUND
        glVertex2f(centerX, centerY); // center of circle
        for (int i = 0; i <= ROUND_DRAWING_ACCURACY; i++)   {
            glVertex2f (
                    (float)(centerX + (mRadius * Math.cos(i * twicePi / ROUND_DRAWING_ACCURACY))),
                    (float)(centerY + (mRadius * Math.sin(i * twicePi / ROUND_DRAWING_ACCURACY)))
            );
        }
        glEnd(); //END
        glPopMatrix();
        canvas.setColor(currColor);
    }


    @Override
    public Point getCenterPoint() {
        return new Point(mX + mRadius, mY + mRadius);
    }

    @Override
    public boolean contains(Point target) {
        return mX <= target.x && mY <= target.y && mX + width() >= target.x && mY + height() >= target.y;
    }

    private int height() {
        return mRadius * 2;
    }

    private int width() {
        return mRadius * 2;
    }
}
