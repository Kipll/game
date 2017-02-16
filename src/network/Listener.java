package network;

import java.io.DataInputStream;
import java.io.IOException;

import game.KeyboardInput;
import game.MouseInput;

public class Listener extends Thread {

	private DataInputStream in;

	public KeyboardInput k;
	public MouseInput m;

	public Listener(DataInputStream in, KeyboardInput k, MouseInput m) {
		this.in = in;
		this.k = k;
		this.m = m;
	}

	public void run() {
		try {
			while (true) {
				int flag = in.readInt();
				int keycodeOrCoordX = in.readInt();
				int coordY = in.readInt();
				System.err.println(flag +  " " + keycodeOrCoordX + " " + coordY );
				switch(flag){
				case 0: //key released
					k.keyCodeReleased(keycodeOrCoordX);
					break;
				case 1: //key pressed
					k.keyCodePressed(keycodeOrCoordX);
					break;
				case 2: //lmb released
					m.mouseCodeReleased(0);
					break;
				case 3: //lmb pressed
					m.mouseCodePressed(0);
					break;
				case 4: //rmb released
					m.mouseCodeReleased(1);
					break;
				case 5: //rmb pressed
					m.mouseCodePressed(1);
					break;
				case 6: //mouse moved
					m.setPos(keycodeOrCoordX, coordY);
					break;
				default:
				}
			}
		}
		catch (IOException e) {
			System.out.println(e + " : Connection was lost");

		}
	}
}
