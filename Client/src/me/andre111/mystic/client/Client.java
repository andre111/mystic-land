package me.andre111.mystic.client;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import me.andre111.mystic.client.screen.Screen;

public class Client extends Runner {
	private static final long serialVersionUID = 1L;
	
    private Cursor emptyCursor;
	private boolean mouseMoved = false;
    private boolean mouseHidden = false;
    private int mouseHideTime = 0;
    
    public Client() {
    	super("Mystic Land Client", 800, 600, 1, 60);
    }
    
    @Override
    public void init() {
    	try {
            emptyCursor = Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "empty");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        setFocusTraversalKeysEnabled(false);
        requestFocus();
    }

	@Override
	public void tick() {
		if (mouseMoved) {
		    mouseMoved = false;
		    mouseHideTime = 0;
		    if (mouseHidden) {
		    	mouseHidden = false;
		        setCursor(null);
		    }
		}
		if (mouseHideTime < 60) {
		    mouseHideTime++;
		    if (mouseHideTime == 60) {
		    	setCursor(emptyCursor);
		        mouseHidden = true;
		    }
		}
	}
	
	@Override
	public void draw(Screen screen) {

	}
	
	@Override
	public void shutdown() {

	}
	
	@Override
	public void looseFocus() {

	}

	@Override
	public void keyPressed(KeyEvent e) {

    }

	@Override
    public void keyReleased(KeyEvent e) {

    }

	@Override
    public void keyTyped(KeyEvent e) {

    }
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
    }

	@Override
    public void mousePressed(MouseEvent e) {
    }

	@Override
    public void mouseReleased(MouseEvent e) {
    }

	@Override
    public void mouseDragged(MouseEvent arg0) {
        mouseMoved = true;
    }

	@Override
    public void mouseMoved(MouseEvent arg0) {
        mouseMoved = true;
    }
	
	public static void main(String[] args) {
		create(new Client());
	}
}
