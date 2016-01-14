package com.kgb.objects;

import com.kgb.drawing.Canvas;
import com.kgb.drawing.Color;
import com.kgb.drawing.Point;
import com.kgb.drawing.Rect;

/**
 * Created by k.betlej on 1/13/16.
 */
public class Monster extends Rectangle {

    private HealthBar mHealthBar;
    private final Round mRound;
    private int mLoop = 0;

    public Monster(int left, int top, int right, int bottom, Color color, int health) {
        this(new Rect(left, top, right, bottom), color, health);
    }

    public Monster(Rect rect, Color color, int health) {
        super(rect, color);
        mHealthBar = new HealthBar(mX, mY, rect.right, mY + 7, health);
        mRound = new Round(mX , mY + 10, rect.widht() /2, color);
    }

    @Override
    public void draw(Canvas canvas) {
        mHealthBar.draw(canvas);
        mRound.draw(canvas);
    }

    @Override
    public Point getCenterPoint() {
        return mRound.getCenterPoint();
    }

    public void hit(int value) {
        mHealthBar.decreaseHelth(value);
    }

    public void move() {
        if(mLoop >= 5) {
            mX++;
            mLoop = 0;
            mRound.setX(mX);
            mHealthBar.setX(mX);
        }
        mLoop++;
    }

    @Override
    public boolean contains(Point target) {
        return mRound.contains(target);
    }

    @Override
    public boolean isAlive() {
        return mHealthBar.getHealth() > 0;
    }
}
