package me.andre111.mystic.server;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

import me.andre111.mystic.server.screen.Screen;

public class Runner extends Canvas implements Runnable, MouseMotionListener, MouseListener, KeyListener {
	private static final long serialVersionUID = 1L;
	
	private static String NAMEAPP = "";
	public static int GAME_WIDTH = 512;
    public static int GAME_HEIGHT = GAME_WIDTH * 9 / 16;
    public static int SCALE = 2;
    private boolean running = true;
    private double framerate = 60;
    public int fps;
    private Screen screen = new Screen(GAME_WIDTH, GAME_HEIGHT);
	
    public static Runner instance;
    private static JFrame guiFrame;
    
	public Runner(String name, int width, int height, int scale, int framesps) {
		Runner.NAMEAPP = name;
		Runner.GAME_WIDTH = width;
		Runner.GAME_HEIGHT = height;
		Runner.SCALE = scale;
		framerate = framesps;
		
        this.setPreferredSize(new Dimension(GAME_WIDTH * SCALE, GAME_HEIGHT * SCALE));
        this.setMinimumSize(new Dimension(GAME_WIDTH * SCALE, GAME_HEIGHT * SCALE));
        this.setMaximumSize(new Dimension(GAME_WIDTH * SCALE, GAME_HEIGHT * SCALE));
        
        this.addMouseMotionListener(this);
        this.addMouseListener(this);

        instance = this;
    }
	
	public static void create(Runner gm) {
        guiFrame = new JFrame();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(gm);
        guiFrame.setContentPane(panel);
        guiFrame.pack();
        guiFrame.setResizable(false);
        guiFrame.setLocationRelativeTo(null);
        guiFrame.setTitle(Runner.NAMEAPP);
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setVisible(true);
        gm.start();
    }
	
	public void start() {
		running = true;
	    Thread thread = new Thread(this);
	    thread.setPriority(Thread.NORM_PRIORITY);
	    thread.start();
	}
	
	public void stop() {
		running = false;
		shutdown();
	}

	public void run() {
		long lastTime = System.nanoTime();
        double unprocessed = 0;
        int frames = 0;
        long lastTimer1 = System.currentTimeMillis();

        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        int toTick = 0;

        long lastRenderTime = System.nanoTime();
        int min = 999999999;
        int max = 0;

        while (running) {
            if (!this.hasFocus()) {
            	looseFocus();
            }

            double nsPerTick = 1000000000.0 / framerate;
            boolean shouldRender = false;
            while (unprocessed >= 1) {
                toTick++;
                unprocessed -= 1;
            }

            int tickCount = toTick;
            if (toTick > 0 && toTick < 3) {
                tickCount = 1;
            }
            if (toTick > 20) {
                toTick = 20;
            }

            for (int i = 0; i < tickCount; i++) {
                toTick--;
                tick();
                shouldRender = true;
            }

            BufferStrategy bs = getBufferStrategy();
            if (bs == null) {
                createBufferStrategy(3);
                continue;
            }
            if (shouldRender) {
                frames++;
                Graphics g = bs.getDrawGraphics();

                render(g);

                long renderTime = System.nanoTime();
                int timePassed = (int) (renderTime - lastRenderTime);
                if (timePassed < min) {
                    min = timePassed;
                }
                if (timePassed > max) {
                    max = timePassed;
                }
                lastRenderTime = renderTime;
            }

            long now = System.nanoTime();
            unprocessed += (now - lastTime) / nsPerTick;
            lastTime = now;

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (shouldRender) {
                if (bs != null) {
                    bs.show();
                }
            }

            if (System.currentTimeMillis() - lastTimer1 > 1000) {
                lastTimer1 += 1000;
                fps = frames;
                frames = 0;
            }
        }
	}
	
	private synchronized void render(Graphics g) {
        draw(screen);

        g.setColor(Color.BLACK);

        g.fillRect(0, 0, getWidth(), getHeight());
        g.translate((getWidth() - GAME_WIDTH * SCALE) / 2, (getHeight() - GAME_HEIGHT * SCALE) / 2);
        g.clipRect(0, 0, GAME_WIDTH * SCALE, GAME_HEIGHT * SCALE);

        g.drawImage(screen.image, 0, 0, GAME_WIDTH * SCALE, GAME_HEIGHT * SCALE, null);
    }
	
	public void init() {
		
    }
	
	public void tick() {
		
	}
	
	public void draw(Screen screen) {
		
	}
	
	public void shutdown() {
		
	}
	
	public void looseFocus() {
	}
	
	public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent arg0) {
    }

    public void mouseMoved(MouseEvent arg0) {
    }
}