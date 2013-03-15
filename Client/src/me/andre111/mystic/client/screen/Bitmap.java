package me.andre111.mystic.client.screen;

import java.util.*;

public class Bitmap {
    public int w, h;
    public int[] pixels;

    public Bitmap(int w, int h) {
        this.w = w;
        this.h = h;
        pixels = new int[w * h];
    }

    public void clear(int color) {
        Arrays.fill(pixels, color);
    }

    public void blit(Bitmap bitmap, int x, int y) {
        int x0 = x;
        int x1 = x + bitmap.w;
        int y0 = y;
        int y1 = y + bitmap.h;
        if (x0 < 0) x0 = 0;
        if (y0 < 0) y0 = 0;
        if (x1 > w) x1 = w;
        if (y1 > h) y1 = h;
        int ww = x1 - x0;

        for (int yy = y0; yy < y1; yy++) {
            int tp = yy * w + x0;
            int sp = (yy - y) * bitmap.w + (x0 - x);
            tp -= sp;
            for (int xx = sp; xx < sp + ww; xx++) {
                int col = bitmap.pixels[xx];
                if (col < 0) pixels[tp + xx] = col;
            }
        }
    }

    public void blit(Bitmap bitmap, int x, int y, int www, int hhh) {
        int x0 = x;
        int x1 = x + www;
        int y0 = y;
        int y1 = y + hhh;
        if (x0 < 0) x0 = 0;
        if (y0 < 0) y0 = 0;
        if (x1 > w) x1 = w;
        if (y1 > h) y1 = h;
        int ww = x1 - x0;

        for (int yy = y0; yy < y1; yy++) {
            int tp = yy * w + x0;
            int sp = (yy - y) * bitmap.w + (x0 - x);
            tp -= sp;
            for (int xx = sp; xx < sp + ww; xx++) {
                int col = bitmap.pixels[xx];
                if (col < 0) pixels[tp + xx] = col;
            }
        }
    }

    public void colorBlit(Bitmap bitmap, int x, int y, int color) {
        int x0 = x;
        int x1 = x + bitmap.w;
        int y0 = y;
        int y1 = y + bitmap.h;
        if (x0 < 0) x0 = 0;
        if (y0 < 0) y0 = 0;
        if (x1 > w) x1 = w;
        if (y1 > h) y1 = h;
        int ww = x1 - x0;

        int a2 = (color >> 24) & 0xff;
        int a1 = 256 - a2;

        int rr = color & 0xff0000;
        int gg = color & 0xff00;
        int bb = color & 0xff;

        for (int yy = y0; yy < y1; yy++) {
            int tp = yy * w + x0;
            int sp = (yy - y) * bitmap.w + (x0 - x);
            for (int xx = 0; xx < ww; xx++) {
                int col = bitmap.pixels[sp + xx];
                if (col < 0) {
                    int r = (col & 0xff0000);
                    int g = (col & 0xff00);
                    int b = (col & 0xff);

                    r = ((r * a1 + rr * a2) >> 8) & 0xff0000;
                    g = ((g * a1 + gg * a2) >> 8) & 0xff00;
                    b = ((b * a1 + bb * a2) >> 8) & 0xff;
                    pixels[tp + xx] = 0xff000000 | r | g | b;
                }
            }
        }
    }

    public void fill(int x, int y, int bw, int bh, int color) {
        int x0 = x;
        int x1 = x + bw;
        int y0 = y;
        int y1 = y + bh;
        if (x0 < 0) x0 = 0;
        if (y0 < 0) y0 = 0;
        if (x1 > w) x1 = w;
        if (y1 > h) y1 = h;
        int ww = x1 - x0;

        for (int yy = y0; yy < y1; yy++) {
            int tp = yy * w + x0;
            for (int xx = 0; xx < ww; xx++) {
                pixels[tp + xx] = color;
            }
        }
    }
    
    public int blendPixels(int backgroundColor, int pixelToBlendColor) {

		int alpha_blend = (pixelToBlendColor >> 24) & 0xff;

		int alpha_background = 256 - alpha_blend;

		int rr = backgroundColor & 0xff0000;
		int gg = backgroundColor & 0xff00;
		int bb = backgroundColor & 0xff;

		int r = (pixelToBlendColor & 0xff0000);
		int g = (pixelToBlendColor & 0xff00);
		int b = (pixelToBlendColor & 0xff);

		r = ((r * alpha_blend + rr * alpha_background) >> 8) & 0xff0000;
		g = ((g * alpha_blend + gg * alpha_background) >> 8) & 0xff00;
		b = ((b * alpha_blend + bb * alpha_background) >> 8) & 0xff;

		return 0xff000000 | r | g | b;
	}
    
    private void adjustBlitArea(Rect blitArea){

	    if (blitArea.topLeftX < 0) blitArea.topLeftX = 0;
        if (blitArea.topLeftY < 0) blitArea.topLeftY = 0;
        if (blitArea.bottomRightX > w) blitArea.bottomRightX = w;
        if (blitArea.bottomRightY > h) blitArea.bottomRightY = h;
	}
    
    public void alphaBlit(Bitmap bitmap, int x, int y, int alpha) {

        if(alpha == 255)
        {
            this.blit(bitmap, x, y);
            return;
        }
        
        Rect blitArea = new Rect(x, y, bitmap.w, bitmap.h);
        adjustBlitArea(blitArea);
                
        int blitWidth = blitArea.bottomRightX - blitArea.topLeftX;

        for (int yy = blitArea.topLeftY; yy < blitArea.bottomRightY; yy++) {
            int tp = yy * w + blitArea.topLeftX;
            int sp = (yy - y) * bitmap.w + (blitArea.topLeftX - x);
            for (int xx = 0; xx < blitWidth; xx++) {
                int col = bitmap.pixels[sp + xx];
                if (col < 0) {
                    
                    int r = (col & 0xff0000);
                    int g = (col & 0xff00);
                    int b = (col & 0xff);
                    col = (alpha << 24) | r | g | b;
                    int color = pixels[tp + xx];
                    pixels[tp + xx] = this.blendPixels(color, col);
                }
            }
        }
    }
    
    public void alphaFill(int x, int y, int width, int height, int color, int alpha) {

        if(alpha == 255)
        {
            this.fill(x, y, width, height, color);
            return;
        }
        
        Bitmap bmp = new Bitmap(width, height);
        bmp.fill(0, 0, width, height, color);
        
        this.alphaBlit(bmp, x, y, alpha);
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, int width, int height) {
        Bitmap scaledBitmap = new Bitmap(width, height);

        int scaleRatioWidth = ((bitmap.w << 16) / width);
        int scaleRatioHeight = ((bitmap.h << 16) / height);

        int i = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                scaledBitmap.pixels[i++] = bitmap.pixels[(bitmap.w * ((y * scaleRatioHeight) >> 16)) + ((x * scaleRatioWidth) >> 16)];
            }
        }

        return scaledBitmap;
    }
}