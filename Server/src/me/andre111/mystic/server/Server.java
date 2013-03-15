package me.andre111.mystic.server;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import me.andre111.mystic.server.screen.Screen;

public class Server extends Runner {
	private static final long serialVersionUID = 1L;
    
    public Server() {
    	super("Mystic Land Server", 800, 600, 1, 60);
    }
    
    @Override
    public void init() {
        setFocusTraversalKeysEnabled(false);
        requestFocus();
    }

	@Override
	public void tick() {
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
    }

	@Override
    public void mouseMoved(MouseEvent arg0) {
    }
	
	public static void main(String[] args) {
		create(new Server());
	}
}