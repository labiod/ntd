package com.kgb.objects;

import com.kgb.drawing.Canvas;
import com.kgb.drawing.Color;
import com.kgb.drawing.Rect;

import static org.lwjgl.opengl.GL11.*;

public class HealthBar extends Rectangle {
    private final int mMaxHelth;
    private int mHealth;

    public HealthBar(int left, int top, int right, int bottom, int maxHelth) {
        this(new Rect(left, top, right, bottom), maxHelth);
    }

    public HealthBar(Rect rect, int maxHelth) {
        super(rect, Color.WHITE);
        mMaxHelth = maxHelth;
        mHealth = mMaxHelth;
    }

    public void decreaseHelth(int value) {
        mHealth = mHealth - value >= 0 ? mHealth - value : 0;
    }

    public void increaseHelth(int value) {
        mHealth = mHealth + value <= mMaxHelth ? mHealth + value : mMaxHelth;
    }

    @Override
    public void draw(Canvas canvas) {

        Color currColor = canvas.getColor();

        canvas.setColor(Color.GREEN);
        int width = mRect.widht() * mHealth/mMaxHelth;
        glPushMatrix();
        glTranslated(mX, mY, 0);
        glBegin(GL_QUADS);
        glVertex2d(1, 1);
        glVertex2d(width, 1);
        glVertex2d(width, mRect.height()-1);
        glVertex2d(1, mRect.height()-1);
        glEnd();
        glTranslated(0, 0, 0);
        glPopMatrix();
        canvas.setColor(currColor);

        canvas.setColor(Color.RED);
        glPushMatrix();
        glTranslated(mX, mY, 0);
        glBegin(GL_QUADS);
        glVertex2d(1, 1);
        glVertex2d(mRect.widht() -1, 1);
        glVertex2d(mRect.widht() - 1, mRect.height()-1);
        glVertex2d(1, mRect.height()-1);
        glEnd();
        glTranslated(0, 0, 0);
        glPopMatrix();
        canvas.setColor(currColor);

        super.draw(canvas);
    }

    public int getHealth() {
        return mHealth;
    }
}
