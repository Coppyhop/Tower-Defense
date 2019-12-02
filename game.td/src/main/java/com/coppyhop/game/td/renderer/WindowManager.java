package com.coppyhop.game.td.renderer;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
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

    /**
     * createWindow
     * 
     * Uses GLFW to create a window on the user's screen with the given 
     * information. It also creates an openGL context for the rest of the
     * engine to draw to on that window, giving us effectively what the user
     * will perceive as the game.
     * 
     * @param width The starting width of the game canvas
     * @param height The starting height of the game canvas
     * @param title The title of the window to be shown to the user
     * @param vSync Sync to the vertical blank
     * @return long The ID of the window so that it can be managed with other
     *              glfw methods
     */
    public static long createWindow(int width, int height, 
    		String title, boolean vSync){
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        glfwWindowHint(GLFW_DECORATED, GLFW_TRUE);
        //Make sure the client has at least openGL 2 for our features
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 2);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 0);
        long window = glfwCreateWindow(width, height, title, NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");
        glfwSetKeyCallback(window, (theWindow, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(theWindow, true);
        });
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