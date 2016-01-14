package com.kgb.drawing;

/**
 * Created by k.betlej on 1/11/16.
 */
public class Rect {
    public int left;
    public int right;
    public int top;
    public int bottom;

    public Rect(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public int widht() {
        return right - left;
    }

    public int height() {
        return bottom - top;
    }
}
