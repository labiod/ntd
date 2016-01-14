package com.kgb.drawing;

/**
 * Created by k.betlej on 1/8/16.
 */
public class Color {
    public static final Color RED = new Color(1.0f, 0.0f, 0.0f);
    public static final Color GREEN = new Color(0.0f, 1.0f, 0.0f);
    public static final Color BLUE = new Color(0.0f, 0.0f, 1.0f);
    public static final Color WHITE = new Color(1.0f, 1.0f, 1.0f);
    public static final Color BLACK = new Color(0.0f, 0.0f, 0.0f);

    private float mRed;
    private float mGreen;
    private float mBlue;
    private float mAlpha;

    public Color(float red, float green, float blue) {
        this(red, green, blue, 1.0f);
    }

    public Color(float red, float green, float blue, float alpha) {
        mRed = red;
        mGreen = green;
        mBlue = blue;
        mAlpha = alpha;
    }

    public float getRed() {
        return mRed;
    }

    public float getGreen() {
        return mGreen;
    }

    public float getBlue() {
        return mBlue;
    }

    public float getAlpha() {
        return mAlpha;
    }
}
