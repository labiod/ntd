package com.kgb.objects;

import com.kgb.drawing.Canvas;
import com.kgb.drawing.Color;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by k.betlej on 1/8/16.
 */
public class Round extends DrawalbeObject {
    private static final int ROUND_DRAWING_ACCURACY = 100;

    private int mCenterX;
    private int mCenterY;
    private int mRadius;

    public Round(int centerX, int centerY, int radius) {
        mCenterX = centerX;
        mCenterY = centerY;
        mRadius = radius;
    }

    @Override
    public void draw(Canvas canvas) {
        Color currColor = canvas.getColor();
        canvas.setColor(Color.RED);
        double twicePi = 2.0 * Math.PI;
        glBegin(GL_TRIANGLE_FAN); //BEGIN ROUND
        glVertex2f(mCenterX, mCenterY); // center of circle
        for (int i = 0; i <= ROUND_DRAWING_ACCURACY; i++)   {
            glVertex2f (
                    (float)(mCenterX + (mRadius * Math.cos(i * twicePi / ROUND_DRAWING_ACCURACY))),
                    (float)(mCenterY + (mRadius * Math.sin(i * twicePi / ROUND_DRAWING_ACCURACY)))
            );
        }
        glEnd(); //END
        canvas.setColor(currColor);
    }
}
