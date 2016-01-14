package com.kgb.objects;

import com.kgb.drawing.Canvas;
import com.kgb.drawing.Color;
import com.kgb.drawing.Rect;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by k.betlej on 1/8/16.
 */
public class Rectangle extends DrawableObject {

    protected final Rect mRect;
    private Color mColor;

    public Rectangle(int left, int top, int right, int bottom, Color color) {
        this(new Rect(left, top, right, bottom), color);
    }

    public Rectangle(Rect rect, Color color) {
        super(rect.left, rect.top);
        mRect = rect;
        mColor = color;
    }

    @Override
    public void draw(Canvas canvas) {
        Color currColor = canvas.getColor();
        canvas.setColor(mColor);
        glPushMatrix();
        glTranslated(mX, mY, 0);
        glBegin(GL_QUADS);
        glVertex2d(0, 0);
        glVertex2d(mRect.widht(), 0);
        glVertex2d(mRect.widht(), mRect.height());
        glVertex2d(0, mRect.height());
        glEnd();
        glTranslated(0, 0, 0);
        glPopMatrix();
        canvas.setColor(currColor);
    }


    @Override
    public void setX(int x) {
        super.setX(x);
        int width = mRect.widht();
        mRect.left = mX;
        mRect.right = mX + width;

    }

}
