package com.kgb.objects;

import com.kgb.drawing.Canvas;
import com.kgb.drawing.Color;
import com.kgb.drawing.Point;

import static org.lwjgl.opengl.GL11.*;

public class Arrow extends DrawableObject {

    private int mSpeed = 5;
    private int mPower = 7;
    private Color mColor;
    private Point mTarget;
    private float mA;
    private float mB;
    private int mLastX;
    private int mLastY;

    public Arrow(int x, int y, int speed, Color color, Point target) {
        super(x, y);
        mSpeed = speed;
        mColor = color;
        mTarget = target;
        mA = (float)(target.y - mY) / (target.x - mX);
        mB = mY - mA * mX;
        mLastX = mX;
        mLastY = mY;
    }
    @Override
    public void draw(Canvas canvas) {
        Color currColor = canvas.getColor();
        canvas.setColor(mColor);
        glPushMatrix();
        glBegin(GL_LINES);
        glVertex2d(mX, mY);
        glVertex2d(mLastX, mLastY);
        glEnd();
        glPopMatrix();
        canvas.setColor(currColor);
    }

    public void moveToTarget() {
        mLastX = mX;
        mLastY = mY;
        if(mX < mTarget.x) {
            mX = mX + mSpeed > mTarget.x ? mTarget.x : mX + mSpeed;
            mY = (int) (mA * mX + mB);
        } else if(mX > mTarget.x){
            mX = mX - mSpeed < mTarget.x ? mTarget.x : mX - mSpeed;
            mY = (int) (mA * mX + mB);
        } else {
            mY++;
        }

    }

    public boolean isTargetComplete() {
        return mX == mTarget.x && mY == mTarget.y;
    }

    public int getPower() {
        return mPower;
    }

    public Point getTarget() {
        return mTarget;
    }
}
