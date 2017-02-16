package game;

import java.awt.event.*;

import network.Sender;

import java.awt.Point;

public class ClientInputListener implements MouseMotionListener, MouseListener, KeyListener {

	private static final int KEY_COUNT = 256;
	
	public Sender sender;

	public ClientInputListener(Sender sender) {

		this.sender = sender;
	}

	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			System.err.println("adding 3, -1, -1");
			sender.addToQueue(3);
			sender.addToQueue(-1);
			sender.addToQueue(-1);
			break;
		case MouseEvent.BUTTON3:
			System.err.println("adding 5, -1, -1");
			sender.addToQueue(5);
			sender.addToQueue(-1);
			sender.addToQueue(-1);
			break;
		}
	}

	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			System.err.println("adding 2, -1, -1");
			sender.addToQueue(2);
			sender.addToQueue(-1);
			sender.addToQueue(-1);
			break;
		case MouseEvent.BUTTON3:
			System.err.println("adding 4, -1, -1");
			sender.addToQueue(4);
			sender.addToQueue(-1);
			sender.addToQueue(-1);
			break;
		}
	}

	public void mouseMoved(MouseEvent e) {
		Point pos = e.getPoint();
			System.err.println("adding 6, x, y");

		sender.addToQueue(6);
		sender.addToQueue(pos.x);
		sender.addToQueue(pos.y);
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
		Point pos = e.getPoint();
			System.err.println("adding 6, x, y");

		sender.addToQueue(6);
		sender.addToQueue(pos.x);
		sender.addToQueue(pos.y);
	}

	public synchronized void keyPressed(KeyEvent e) {

		int keyCode = e.getKeyCode();

		if (keyCode >= 0 && keyCode < KEY_COUNT) {

				System.err.println("adding 1, kc, -1");

			sender.addToQueue(1);
			sender.addToQueue(keyCode);
			sender.addToQueue(-1);

		}

	}

	public synchronized void keyReleased(KeyEvent e) {

		int keyCode = e.getKeyCode();

		if (keyCode >= 0 && keyCode < KEY_COUNT) {

				System.err.println("adding 0, kc, -1");
			
			sender.addToQueue(0);
			sender.addToQueue(keyCode);
			sender.addToQueue(-1);

		}

	}

	public void keyTyped(KeyEvent e) {

		// Not needed

	}
}
