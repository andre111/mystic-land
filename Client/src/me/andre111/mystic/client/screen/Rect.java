package me.andre111.mystic.client.screen;

public class Rect {
    public int topLeftX, topLeftY;
    public int bottomRightX, bottomRightY;
    public int width , height;
    
    public Rect(int x, int y, int w, int h) {
        topLeftX = x;
        topLeftY = y;
        width = w;
        height = h;
        bottomRightX = topLeftX + width;
        bottomRightY = topLeftY + height;
    }
}