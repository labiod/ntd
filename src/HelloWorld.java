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

    private long mWindow;

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

        int WIDTH = 1024;
        int HEIGHT = 768;

        // Create the window
        mWindow = glfwCreateWindow(WIDTH, HEIGHT, "Hello World!", NULL, NULL);
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
                (vidMode.width() - WIDTH) / 2,
                (vidMode.height() - HEIGHT) / 2);

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
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while( glfwWindowShouldClose(mWindow) == GLFW_FALSE) {
            // clear the framebuffer
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // swap the color buffers
            glfwSwapBuffers(mWindow);

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

    public static void main(String[] args) {
        new HelloWorld().run();
    }

}
