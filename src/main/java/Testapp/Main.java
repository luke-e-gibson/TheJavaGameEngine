package Testapp;

import com.tjge.Window;

public class Main {
    public static void main(String[] args) {
        // Create Window Singleton
        Window window = Window.get();
        // Run Window
        window.run();
    }
}