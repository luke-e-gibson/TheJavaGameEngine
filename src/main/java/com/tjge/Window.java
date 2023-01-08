package com.tjge;


import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
        private int width, height;
        private String title;
        private static Window window;
        private long glfwWindow;

        // Create Privet constructor
        private Window() {
            this.width = 1920;
            this.height = 1080;
            this.title = "The Jave Game Engine";
        }

        public static Window get(){
            //check if windows(6) is initialized ease create window
            if(Window.window == null){
                Window.window = new Window();
            }
            // Return Window
            return Window.window;
        }

        public void run(){
            System.out.println("LWJGL ver: " + Version.getVersion());
            System.out.println("LWJGL Build: " + Version.BUILD_TYPE);

            init();
            loop();
        }

        public void init(){
            //Setup an Error Callback
            GLFWErrorCallback.createPrint(System.err).set();

            // Init GLFW
            if (!glfwInit()){
                throw new IllegalStateException("Unable To Init GLFW.");
            }

            //Configer GLFW
            glfwDefaultWindowHints();
            glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
            glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
            glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

            //Create Window
             glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);

             if(glfwWindow == NULL){
                 throw new IllegalStateException("Faild To Create GLFW Window");
             }

             // Make the OpenGl Context Current
            glfwMakeContextCurrent(glfwWindow);
             //Enable Vsync
            glfwSwapInterval(1);

            //Show Window
            glfwShowWindow(glfwWindow);

            // This line is critical for LWJGL's interoperation with GLFW's
            // OpenGL context, or any context that is managed externally.
            // LWJGL detects the context that is current in the current thread,
            // creates the GLCapabilities instance and makes the OpenGL
            // bindings available for use.
            // From https://www.lwjgl.org/guide
            GL.createCapabilities();

        }
        public void loop(){
            while (!glfwWindowShouldClose(glfwWindow)){
                // Poll Events
                glfwPollEvents();

                glClearColor(0.0f, 0.4f, 0.75f, 1.0f);
                glClear(GL_COLOR_BUFFER_BIT);

                glfwSwapBuffers(glfwWindow);
            }
        }
}
