package com.kgb.objects;

import com.kgb.drawing.Canvas;
import com.kgb.drawing.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by k.betlej on 1/7/16.
 */
public class ObjectManager {
    private static final ObjectManager sInstance = new ObjectManager();
    private List<DrawableObject> mObjects = new ArrayList<DrawableObject>();

    public static ObjectManager getInstance() {
        return sInstance;
    }

    private ObjectManager() {

    }

    public void addObject(DrawableObject object) {
        mObjects.add(object);
    }

    public void drawObjects(Canvas cannvas) {
        for(DrawableObject object : mObjects) {
            object.draw(cannvas);
        }
    }

    public void hitTarget(int power, Point target) {
        List<DrawableObject> objectToRemove = new ArrayList<>();
        for(DrawableObject object : mObjects) {
            if(object instanceof Monster) {
                if (object.contains(target)) {
                    ((Monster)object).hit(power);
                    if(!((Monster) object).isAlive()) {
                        objectToRemove.add(object);
                    }
                }
            }
        }
        for(DrawableObject object : objectToRemove) {
            mObjects.remove(object);
        }
    }
}
