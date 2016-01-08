package com.kgb;

import com.kgb.drawing.Canvas;
import com.kgb.drawing.Color;
import com.kgb.drawing.Paint;
import com.kgb.objects.ObjectManager;
import com.kgb.objects.Rectangle;
import com.kgb.objects.Round;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * Created by k.betlej on 1/5/16.
 */
public class HelloWorld {

    private GLFWErrorCallback mErrorCallback;
    private GLFWKeyCallback mKeyCallback;
    private ObjectManager mObjectManager;

    private long mWindow;
    private int mWIDTH;
    private int mHEIGHT;
    private Paint mMainPaint;
    private Canvas mCanvas;

    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");
        try {
            init();
            loop();

            //Release window and callbacks
            glfwDestroyWindow(mWindow);
            mKeyCallback.release();

        } finally {
            glfwTerminate();
            mErrorCallback.release();
        }
    }

    private void init() {
        mMainPaint = new Paint();
        mMainPaint.setColor(Color.WHITE);
        mObjectManager = new ObjectManager();
        mObjectManager.addObject(new Round(400, 400, 100));
        mObjectManager.addObject(new Rectangle(100, 100, 300, 250));
        mErrorCallback = GLFWErrorCallback.createPrint(System.err);
        glfwSetErrorCallback(mErrorCallback);

        if(glfwInit() != GLFW_TRUE) {
            throw  new IllegalStateException("Unable to initialize GLFW");
        }

        glfwDefaultWindowHints();
        // the window will stay hidden after creation
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        // the window will be resizable
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        mWIDTH = 1024;
        mHEIGHT = 768;

        // Create the window
        mWindow = glfwCreateWindow(mWIDTH, mHEIGHT, "Hello World!", NULL, NULL);
        if(mWindow == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        mKeyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                switch (key) {
                    case GLFW_KEY_ESCAPE:
                        if(action == GLFW_RELEASE) {
                            // We will detect this in our rendering loop
                            glfwSetWindowShouldClose(mWindow, GLFW_TRUE);
                        }
                        break;
                    case GLFW_KEY_W:
                        if(action ==GLFW_PRESS) {
                            System.out.println("W key was pressed");
                        }
                        break;
                    case GLFW_KEY_S:
                        if(action ==GLFW_PRESS) {
                            System.out.println("S key was pressed");
                        }
                        break;
                    case GLFW_KEY_A:
                        if(action ==GLFW_PRESS) {
                            System.out.println("A key was pressed");
                        }
                        break;
                    case GLFW_KEY_D:
                        if(action ==GLFW_PRESS) {
                            System.out.println("D key was pressed");
                        }
                        break;
                }

            }
        };
        glfwSetKeyCallback(mWindow, mKeyCallback);

        // Get the resolution of the primary monitor
        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(mWindow,
                (vidMode.width() - mWIDTH) / 2,
                (vidMode.height() - mHEIGHT) / 2);

        // Make the OpenGL context current
        glfwMakeContextCurrent(mWindow);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(mWindow);
        glfwSetInputMode(mWindow, GLFW_CURSOR, GLFW_CURSOR_HIDDEN);
    }


    private void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        //Clear to default color
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glEnable(GL_DEPTH_TEST);
        System.out.println("OpenGL: " + glGetString(GL_VERSION));

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, mWIDTH, mHEIGHT, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        mCanvas = new Canvas(0, 0, mWIDTH, mHEIGHT, Color.WHITE);

        while( glfwWindowShouldClose(mWindow) == GLFW_FALSE) {
            update();
            render();
//            mObjectManager.drawObjects();


            glPopMatrix();
        }
    }

    private void render() {
        // clear the framebuffer
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glColor3f(0.5f, 0.5f, 1.0f);

        mObjectManager.drawObjects(mCanvas);
        // swap the color buffers
        glfwSwapBuffers(mWindow);

    }

    private void drawCircle(int centerX, int centerY, int radius, int numSegment) {
        glPushMatrix();
        glBegin(GL_LINE_LOOP);
        for(int ii = 0; ii < numSegment; ++ii) {
            float theta = (float) ((2.0f * Math.PI * ii) / numSegment);
            float x = (float) (radius * Math.cos(theta));
            float y = (float) (radius * Math.sin(theta));
            glVertex2f(x + centerX, y + centerY);
        }
        glEnd();
        glPopMatrix();
    }

    private void update() {
        glfwPollEvents();
    }

    public static void main(String[] args) {
        new HelloWorld().run();
    }

}
