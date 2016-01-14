package com.kgb.objects;

import com.kgb.drawing.Canvas;
import com.kgb.drawing.Point;

/**
 * Created by k.betlej on 1/7/16.
 */
public abstract class DrawableObject {

    protected int mX;
    protected int mY;

    public DrawableObject(int x, int y) {
        mX = x;
        mY = y;
    }

    public abstract void draw(Canvas canvas);

    public Point getCenterPoint() {
        return new Point(mX, mY);
    }

    public void setX(int x) {
        mX = x;
    }

    public boolean contains(Point target) {
        return mX == target.x && mY == target.y;
    }

    public boolean isAlive() {
        return true;
    }
}
