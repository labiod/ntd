package com.kgb.objects;

import com.kgb.drawing.Canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by k.betlej on 1/7/16.
 */
public class ObjectManager {
    List<DrawalbeObject> mObjects = new ArrayList<DrawalbeObject>();

    public void addObject(DrawalbeObject object) {
        mObjects.add(object);
    }

    public void drawObjects(Canvas cannvas) {
        for(DrawalbeObject object : mObjects) {
            object.draw(cannvas);
        }
    }
}
