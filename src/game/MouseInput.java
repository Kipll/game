package game;
import java.awt.event.*;
import java.awt.Point;

public class MouseInput implements MouseMotionListener, MouseListener {
	
	private enum ButtonState {
	RELEASED, // Not down
	PRESSED,  // Down, but not the first time
	ONCE      // Down for the first time
	}
	
	private boolean leftPressed;
	private boolean rightPressed;
	private ButtonState leftState;
	private ButtonState rightState;
	private Point pos;
	
	public MouseInput(){
		leftPressed = false;
		rightPressed = false;
		leftState = ButtonState.RELEASED;
		rightState = ButtonState.RELEASED;
		pos = new Point(0,0);
	}
	
    public void mousePressed(MouseEvent e) {
    	switch(e.getButton()){
    		case MouseEvent.BUTTON1:
    			leftPressed = true;
    		break;
    		case MouseEvent.BUTTON3:
    			rightPressed = true;
    		break;
    	}
    }

    public void mouseReleased(MouseEvent e) {
    	switch(e.getButton()){
    		case MouseEvent.BUTTON1:
    			leftPressed = false;
    		break;
    		case MouseEvent.BUTTON3:
    			rightPressed = false;
    		break;
    	}
    }
    
    public void mouseCodePressed(int button) {
    	switch(button){
    		case 0:
    			leftPressed = true;
    		break;
    		case 1:
    			rightPressed = true;
    		break;
    	}
    }

    public void mouseCodeReleased(int button) {
    	switch(button){
    		case 0:
    			leftPressed = false;
    		break;
    		case 1:
    			rightPressed = false;
    		break;
    	}
    }
    
    public void mouseMoved(MouseEvent e) {
    	pos = e.getPoint();
    }
    
    public void poll(){
    	if(leftPressed){
    		if(leftState == ButtonState.RELEASED)leftState = ButtonState.ONCE;
    		else leftState = ButtonState.PRESSED;
    	}
    	else leftState = ButtonState.RELEASED;
    	
    	if(rightPressed){
    		if(rightState == ButtonState.RELEASED)rightState = ButtonState.ONCE;
    		else rightState = ButtonState.PRESSED;
    	}
    	else rightState = ButtonState.RELEASED;
    }
    
    public void setPos(int x, int y){
    	
    	pos.x = x;
    	pos.y = y;
    }
    
    public Point getPos(){
    	return pos;
    }
    
    public boolean isPressed(int button){
    	switch(button){
    		case 0:
    			return (leftState == ButtonState.ONCE) || (leftState == ButtonState.PRESSED);
    		case 1:
    			return (rightState == ButtonState.ONCE) || (rightState == ButtonState.PRESSED);
    		default:
    			return false;
    	}
    }
    
    public boolean isPressedOnce(int button){
    	switch(button){
    		case 0:
    			return leftState == ButtonState.ONCE;
    		case 1:
    			return rightState == ButtonState.ONCE;
    		default:
    			return false;
    	}
    }
    
    public boolean isReleased(int button){
    	switch(button){
    		case 0:
    			return leftState == ButtonState.RELEASED;
    		case 1:
    			return rightState == ButtonState.RELEASED;
    		default:
    			return true;
    	}
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }
    
    public void mouseDragged(MouseEvent e) {
    	pos = e.getPoint();
    }
}
