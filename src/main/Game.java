package main;

import handlers.Keys;
import handlers.Mouse;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import states.StateManager;
import util.Chronometre;

public class Game implements Runnable, MouseListener, KeyListener {
	
	// game info
	public static int WIDTH = 960;
	public static int HEIGHT = 640;
	
	// game components
	private JFrame frame;
	private JPanel panel;
	
	// game thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private int target = 1000 / FPS;
	private Chronometre timer;
	
	// graphics
	private Graphics2D g;
	private BufferedImage image;
	
	// states
	private StateManager sm;
	
	public Game() {
		configWindow();
	}
	
	public void init() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		sm = new StateManager();
		timer = new Chronometre();
		running = true;
		
		frame.addMouseListener(this);
		frame.addKeyListener(this);
		frame.setFocusable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setTitle("Breakout");
		
		thread = new Thread(this);
		thread.setDaemon(true);
		thread.start();
	}
	
	public void run() {
		long wait;
		
		while (running) {
			timer.start();
			
			sm.update();
			sm.draw(g);
			print();
			
			Mouse.update(panel.getMousePosition());
			Keys.update();
			
			wait = target - timer.getElapsedMilli();
			
			if (wait > 0) {
				try {
					Thread.sleep(wait);
				}
				catch (InterruptedException e) {
					System.out.println("Thread interrompu");
				}
			}
		}
	}
	
	private void print() {
		Graphics g2 = panel.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}
	
	private void configWindow() {
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		frame = new JFrame();
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
	}
	
	public void mouseClicked(MouseEvent e) { Mouse.setClick(); }
	public void mousePressed(MouseEvent e) { Mouse.setPress(); }
	public void mouseReleased(MouseEvent e) { Mouse.setReleased(); }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	
	public void keyPressed(KeyEvent e) { Keys.keySet(e.getKeyCode(), true); }
	public void keyReleased(KeyEvent e) { Keys.keySet(e.getKeyCode(), false); }
	public void keyTyped(KeyEvent e) {  }

	public static void main(String[] args) {
		Game game = new Game();
		game.init();
	}
}
