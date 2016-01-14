package com.kgb.objects;

import com.kgb.Task;
import com.kgb.ThreadManager;
import com.kgb.drawing.Canvas;
import com.kgb.drawing.Color;
import com.kgb.drawing.Point;
import com.kgb.drawing.Rect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tower extends Rectangle {
    private Rectangle mTowerHead;
    private Rectangle mTowerBody;
    private List<Arrow> mArrows;

    private Monster mTarget;

    private final Object mLock = new Object();

    public Tower(int left, int top, int right, int bottom, Color color) {
        super(left, top, right, bottom, color);
        mTowerHead = new Rectangle(left, top, right, top + mRect.height()/3, Color.RED);
        mTowerBody = new Rectangle(left + 2, top + mRect.height()/3, right - 2, bottom, color);
        mArrows = new ArrayList<>();
    }

    public Tower(Rect rect, Color color) {
        super(rect, color);
    }

    public void setTarget(Monster target) {
        mTarget = target;
        beginShooting();
    }

    private void beginShooting() {
        ThreadManager.getInstance().addTask("tower:arrow", new Task() {
            private int loopCount = 55;

            @Override
            public void init() {

            }

            @Override
            public void loop() {
                Point target = mTarget.getCenterPoint();
                synchronized (mLock) {
                    if(!mTarget.isAlive()) {
                        endLoop();
                        mTarget = null;
                        Iterator<Arrow> iterator = mArrows.iterator();
                        while (iterator.hasNext()) {
                            iterator.next();
                            iterator.remove();
                            System.out.println("remove arrow");
                        }
                    }
                    if (loopCount >= 55) {
                        System.out.println("add arrow");
                        mArrows.add(new Arrow(mRect.left, mRect.top, 3, Color.RED, target));
                        loopCount = 0;
                    }
                    Iterator<Arrow> iterator = mArrows.iterator();
                    while (iterator.hasNext()) {
                        Arrow next = iterator.next();
                        next.moveToTarget();
                        if (next.isTargetComplete()) {
                            ObjectManager.getInstance().hitTarget(next.getPower(), next.getTarget());
                            iterator.remove();
                            System.out.println("remove arrow");
                        }
                    }
                }
                loopCount++;
            }

        });
    }

    @Override
    public void draw(Canvas canvas) {
        mTowerHead.draw(canvas);
        mTowerBody.draw(canvas);
        synchronized (mLock) {
            Iterator<Arrow> iterator = mArrows.iterator();
            while (iterator.hasNext()) {
                iterator.next().draw(canvas);
            }
        }
    }
}
