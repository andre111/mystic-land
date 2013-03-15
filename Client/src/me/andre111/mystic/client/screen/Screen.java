package me.andre111.mystic.client.screen;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Screen extends Bitmap {
    public BufferedImage image;
    private int xOffset, yOffset;

    public Screen(int w, int h) {
        super(w, h);
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void blit(Bitmap bitmap, double x, double y) {
        blit(bitmap, (int) x + xOffset, (int) y + yOffset);
    }

    @Override
    public void blit(Bitmap bitmap, int x, int y) {
        super.blit(bitmap, x + xOffset, y + yOffset);
    }

    @Override
    public void blit(Bitmap bitmap, int x, int y, int w, int h) {
        super.blit(bitmap, x + xOffset, y + yOffset, w, h);
    }

    public void colorBlit(Bitmap bitmap, double x, double y, int color) {
        colorBlit(bitmap, (int) x + xOffset, (int) y + yOffset, color);
    }

    @Override
    public void colorBlit(Bitmap bitmap, int x, int y, int color) {
        super.colorBlit(bitmap, x + xOffset, y + yOffset, color);
    }

    @Override
    public void fill(int x, int y, int width, int height, int color) {
        super.fill(x + xOffset, y + yOffset, width, height, color);
    }
    
    @Override
    public void alphaBlit(Bitmap bitmap, int x, int y, int alpha) {
		super.alphaBlit(bitmap, x + xOffset, y + yOffset, alpha);
	}
}