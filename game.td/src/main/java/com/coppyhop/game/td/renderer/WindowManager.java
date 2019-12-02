package com.coppyhop.game.td.renderer;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * WindowManager
 * 
 * This is the class that creates and manages windows. This is the entry point 
 * for the engine. It can be used to call upon a specific window to be created,
 * updated, or closed. It also can be used to poll the window to see if a close
 * has been requested by the system or the user outside the confines of the
 * engine's area.
 * 
 * @author kyle
 *
 */
public class WindowManager {

    public static void init(){
        GLFWErrorCallback errorCallback = 
        		GLFWErrorCallback.createPrint(System.err);
        glfwSetErrorCallback(errorCallback);
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
    }

    public static long createWindow(int width, int height, 
    		String title, boolean vSync){
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        glfwWindowHint(GLFW_DECORATED, GLFW_TRUE);
        long window = glfwCreateWindow(width, height, title, NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");
        glfwSetKeyCallback(window, (theWindow, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(theWindow, true);
        });
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);
            glfwGetWindowSize(window, pWidth, pHeight);
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }
        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        if(vSync){
            glfwSwapInterval(1);
        }
        glfwShowWindow(window);
        return window;
    }

    public static boolean shouldWindowClose(long window){
        return glfwWindowShouldClose(window);
    }

    public static void update(long window){
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    public static void destroyWindow(long window){
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

}