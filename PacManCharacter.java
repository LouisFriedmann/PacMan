/**
 * This is the PacManCharacter class. It will contain all the information relevant for pacman
 * including the actions he will perform and the images associated with him.
 *
 * @author Louis Friedmann
 */

 /*
  * Import statements
  */

 import objectdraw.DrawingCanvas;
 import objectdraw.VisibleImage;
 import java.awt.Image;
 import java.awt.event.KeyListener;
 import objectdraw.ActiveObject;
 import java.awt.event.KeyEvent;
 import java.awt.Component;
 import objectdraw.FilledRect;


 /*
  * class declaration
  */

 public class PacManCharacter extends ActiveObject implements KeyListener
 {
	/*
	 * Constants
	 */

	public static final double DISTANCE = PacMan.DOT_DISTANCE / 2; // The distance that PacMan will travel
	public static final double HEIGHT_OF_PACMAN = 50.0;
	public static final double WIDTH_OF_PACMAN = 50.0;

	/*
	 * Instance fields
	 */

	public static VisibleImage pacMan; // The visible image of pacman

	/**
	 * PacManCharacter constructor with the image of pacman
	 *
	 * @param image the image of pacman
	 * @param x the X coordinate where the alien appears
	 * @param y the Y coordinate where the alien appears
	 * @param keyListener the component to use to listen to key events
	 * @param canvas the canvas in which the image is created
	 */

	 public PacManCharacter (Image image, double x, double y, Component keyListener, DrawingCanvas canvas)
	 {
		 pacMan = new VisibleImage (image, x, y, canvas);
		 pacMan.setHeight(HEIGHT_OF_PACMAN);
		 pacMan.setWidth(WIDTH_OF_PACMAN);

		 canvas.addKeyListener(this);		// add key listener to the canvas
		 keyListener.addKeyListener(this);	// add key listener to the active window
	  	 keyListener.setFocusable(true);	// inform system that our applet gains focus
	 }

	 /*

	 /**
	  * Move the visible image the appropriate x and y pixels and make sure PacMan isn't in the boundaries and
	  * allow PacMan to move through both openings in the borders and pop up on its corresponding opening
	  */

	 public void move (double dx, double dy)
	 {
		 int timesInBoundary = 0; // Number of times PacMan would go in a boundary
		 for (int i = 0; i < PacMan.boundary.length; i++)
		 {
		 	if ((pacMan.getX () + dx >= PacMan.boundary[i].getX () ||
		 	     pacMan.getX () + dx + pacMan.getWidth() >= PacMan.boundary[i].getX ()) && (pacMan.getX () + dx <= PacMan.boundary[i].getX () + PacMan.boundary[i].getWidth () ||
		 	     pacMan.getX () + dx + pacMan.getWidth() <= PacMan.boundary[i].getX () + PacMan.boundary[i].getWidth()) &
		    	(pacMan.getY () + dy >= PacMan.boundary[i].getY () || pacMan.getY () + dy + pacMan.getHeight() >= PacMan.boundary[i].getY ()) && (pacMan.getY () + dy <= PacMan.boundary[i].getY () + PacMan.boundary[i].getHeight () ||
		    	pacMan.getY () + dy + pacMan.getHeight() <= PacMan.boundary[i].getY () + PacMan.boundary[i].getHeight ()))
		 	{
		 	 	timesInBoundary++;
		 	}
		 }
		 if (timesInBoundary == 0)
		 {
		 	 pacMan.moveTo(pacMan.getX () + dx, pacMan.getY () + dy);
		 }
		 if (pacMan.getX () + pacMan.getWidth () > PacMan.RIGHT_BORDER)
		 {
			 pacMan.moveTo (PacMan.LEFT_BOUNDARY_WIDTH - PacMan.DOT_DISTANCE + pacMan.getWidth() / 2, PacMan.TOP_BOUNDARY_AND_SCORE_HEIGHT + PacMan.DOT_DISTANCE * 6 - pacMan.getHeight() / 2);
		 }
		 else if (pacMan.getX () < PacMan.LEFT_BORDER)
		 {
			 pacMan.moveTo(PacMan.LEFT_BOUNDARY_WIDTH - PacMan.DOT_DISTANCE + PacMan.DOT_DISTANCE * 25 + pacMan.getWidth() / 2, PacMan.TOP_BOUNDARY_AND_SCORE_HEIGHT + PacMan.DOT_DISTANCE * 6 - pacMan.getHeight() / 2);
		 }
	 }

	 /**
	  * Move PacMan right
	  */

	 public void moveRight ()
	 {
		 this.move (DISTANCE, 0.0);
	 }

	 /**
	  * Move PacMan left
	  */

	 public void moveLeft ()
	 {
		 this.move (-DISTANCE, 0.0);
	 }

	 /**
	  * Move PacMan up
	  */

	 public void moveUp ()
	 {
		 this.move (0.0, -DISTANCE);
	 }

	 /**
	  * Move PacMan down
	  */

	 public void moveDown ()
	 {
		 this.move (0.0, DISTANCE);
	 }

	 /**
	  * Activates action when a key is pressed.
	  *
	  * @param e the key that was activated
	  */

	 public void keyPressed(KeyEvent e)
	 {
	 	switch (e.getKeyCode())
	 	{
	 		case KeyEvent.VK_LEFT:
				moveLeft();
	 			break;
	 		case KeyEvent.VK_RIGHT:
	 			moveRight();
	 			break;
	 			case KeyEvent.VK_UP:
	 			moveUp();
	 			break;
	 		case KeyEvent.VK_DOWN:
	 			moveDown();
	 			break;
	    }
	}

	/**
	 * Activates action when a key is released.
	 *
	 * @param e the key that was activated
	 */

	public void keyReleased(KeyEvent e)
	{
	}

	/**
	 * Activates action when a key is pressed and released.
	 *
	 * @param e the key that was activated
	 */

	public void keyTyped(KeyEvent e)
	{
	}
 }