/**
 * This is the Ghosts class. It will contain all the information relevant for the ghosts
 * including the actions they will perform and the images associated with them.
 *
 * @author Louis Friedmann
 */

 /*
  * Import statements
  */

 import objectdraw.DrawingCanvas;
 import objectdraw.VisibleImage;
 import java.awt.Image;
 import objectdraw.ActiveObject;

/*
 * Class declaration
 */

public class Ghosts extends ActiveObject
{
	/*
	 * Constants
	 */

	private static final double DELAY = 100.0;
	public static final double WIDTH_OF_GHOSTS = PacManCharacter.WIDTH_OF_PACMAN;
	public static final double HEIGHT_OF_GHOSTS = PacManCharacter.HEIGHT_OF_PACMAN;

	/*
	 * Make enum arrays for all directions
	 */

	private enum Direction {LEFT, RIGHT, UP, DOWN, NONE}

	/*
	 * Instance fields
	 */

	private VisibleImage crntImage; // The ghost currently being moved
	private Direction direction; // The direction the ghosts are being moved

	/*
	 * Create the array of the red ghost, orange ghost, yellow ghost, and green ghost respectively
	 */

	public static VisibleImage[] ghost = new VisibleImage[4];

	/**
	 * Ghosts constructor with the images of the ghosts
	 *
	 * @param rGhost the image of the red ghost
	 * @param oGhost the image of the orange ghost
	 * @param yGhost the image of the yellow ghost
	 * @param gGhost the image of the green ghost
	 * @param x the X coordinate where all of the ghosts appears
	 * @param y the Y coordinate where all of the ghosts appears
	 * @param canvas the canvas in which the image is created
	 */

	public Ghosts (Image rGhost, Image oGhost, Image yGhost, Image gGhost, double x, double y, DrawingCanvas canvas)
	{
		ghost[0] = new VisibleImage (rGhost, x, y, canvas);
		ghost[0].setWidth(WIDTH_OF_GHOSTS);
		ghost[0].setHeight(HEIGHT_OF_GHOSTS);
		ghost[1] = new VisibleImage (oGhost, x , y, canvas);
		ghost[1].setWidth(WIDTH_OF_GHOSTS);
		ghost[1].setHeight(HEIGHT_OF_GHOSTS);
		ghost[2] = new VisibleImage (yGhost, x, y, canvas);
		ghost[2].setWidth(WIDTH_OF_GHOSTS);
		ghost[2].setHeight(HEIGHT_OF_GHOSTS);
		ghost[3] = new VisibleImage (gGhost, x, y, canvas);
		ghost[3].setWidth(WIDTH_OF_GHOSTS);
		ghost[3].setHeight(HEIGHT_OF_GHOSTS);

	    start(); // Set the objects in motion
	}

	/**
	 * Move the current image the appropriate x and y pixels and make sure it isn't in the boundaries and
	 * allow him to move through both openings in the borders and pop up on its corresponding opening
	 *@param dx the distance to move in the x direction
	 *@param dy the distance to move in the y direction
	 */

	public void move (double dx, double dy)
	{
		crntImage.moveTo(crntImage.getX () + dx, crntImage.getY () + dy);

		if (crntImage.getX () + crntImage.getWidth () > PacMan.RIGHT_BORDER)
		{
			 crntImage.moveTo (PacMan.LEFT_BOUNDARY_WIDTH - PacMan.DOT_DISTANCE + crntImage.getWidth() / 2, PacMan.TOP_BOUNDARY_AND_SCORE_HEIGHT + PacMan.DOT_DISTANCE * 6 - crntImage.getHeight() / 2);
		}
		else if (crntImage.getX () < PacMan.LEFT_BORDER)
		{
			crntImage.moveTo(PacMan.LEFT_BOUNDARY_WIDTH - PacMan.DOT_DISTANCE + PacMan.DOT_DISTANCE * 25 + crntImage.getWidth() / 2, PacMan.TOP_BOUNDARY_AND_SCORE_HEIGHT + PacMan.DOT_DISTANCE * 6 - crntImage.getHeight() / 2);
		}
	}

	 /**
	  * Move PacMan right
	  */

	 public void moveRight ()
	 {
	 	 this.move (PacManCharacter.DISTANCE, 0.0);
	 }


	 /**
	  * Move PacMan left
	  */

	 public void moveLeft ()
	 {
		 this.move(-PacManCharacter.DISTANCE, 0.0);
	 }

	 /**
	  * Move PacMan up
	  */

	 public void moveUp ()
	 {
		 this.move(0.0, -PacManCharacter.DISTANCE);
	 }

	 /**
	  * Move PacMan down
	  */

	 public void moveDown ()
     {
		this.move(0.0, PacManCharacter.DISTANCE);
	 }

	 /**
	  * Compute the X coordinate of the dot the ghost is on
	  *
	  * @return this X coordinate
	  * If it is not on a dot, return -1
	  */

	 public double dotOnGhostXCoord ()
	 {
		 double xCoordOfDot = -1;
		 for (int i = 0; i < PacMan.dotCounter; i++)
		 {
			 if (PacMan.dot[i].getX () > crntImage.getX () && PacMan.dot[i].getX () < crntImage.getX () + crntImage.getWidth() &&
			 	PacMan.dot[i].getY () > crntImage.getY () && PacMan.dot[i].getY () < crntImage.getY () + crntImage.getHeight ())
			 	{
					xCoordOfDot = PacMan.dot[i].getX ();
			    }
		 }
		 return xCoordOfDot;
	 }

	 /**
	  * Compute the Y coordinate of the dot the ghost is on
	  *
	  * @return this Y coordinate
	  * If it is not on a dot, return -1
	  */

	 public double dotOnGhostYCoord ()
	 {
	 	 double yCoordOfDot = -1;
	 	 for (int i = 0; i < PacMan.dotCounter; i++)
	 	 {
	 		 if (PacMan.dot[i].getX () > crntImage.getX () && PacMan.dot[i].getX () < crntImage.getX () + crntImage.getWidth() &&
	 		 	PacMan.dot[i].getY () > crntImage.getY () && PacMan.dot[i].getY () < crntImage.getY () + crntImage.getHeight ())
	 		 	{
	 				yCoordOfDot = PacMan.dot[i].getY ();
	 		    }
	 	 }
	 	 return yCoordOfDot;
	 }


	  /**
	   * Determine if the ghost is at any right boundary
	   *
	   * @return true or false depending upon whether or not the ghost is at a right boundary
	   */

	  public boolean atRightBoundary ()
	  {
		  boolean isAtRightBoundary = true;
		  for (int i = 0; i < PacMan.dotCounter; i++)
		  {
		     if (dotOnGhostXCoord() + PacMan.DOT_DISTANCE == PacMan.dot[i].getX () && dotOnGhostYCoord() == PacMan.dot[i].getY () && dotOnGhostYCoord() != -1)
		  	 {
			   	isAtRightBoundary = false;
		  	 }
		  }
		  return isAtRightBoundary;
	  }

	  /**
	   * Determine if the ghost is at any left boundary
	   *
	   * @return true or false depending upon whether or not the ghost is at a left boundary
	   */

	  public boolean atLeftBoundary ()
	  {
		  boolean isAtLeftBoundary = true;
		  for (int i = 0; i < PacMan.dotCounter; i++)
		  {
			  if (dotOnGhostXCoord() - PacMan.DOT_DISTANCE == PacMan.dot[i].getX () && dotOnGhostYCoord() == PacMan.dot[i].getY () && dotOnGhostYCoord() != -1)
			  {
				  isAtLeftBoundary = false;
			  }
		  }
		  return isAtLeftBoundary;
	  }

	  /**
	   * Determine if the ghost is at any bottom boundary
	   *
	   * @return true or false depending upon whether or not the ghost is at a bottom boundary
	   */

	  public boolean atBottomBoundary ()
	  {
	 	  boolean isAtBottomBoundary = true;
	  	  for (int i = 0; i < PacMan.dotCounter; i++)
	  	  {
	  		  if (dotOnGhostYCoord() + PacMan.DOT_DISTANCE == PacMan.dot[i].getY () && dotOnGhostXCoord() == PacMan.dot[i].getX () && dotOnGhostXCoord() != -1)
	  		  {
	  			  isAtBottomBoundary = false;
	  		  }
	  	  }
	  	 return isAtBottomBoundary;
	  }

	  /**
	   * Determine if the ghost is at any top boundary
	   *
	   * @return true or false depending upon whether or not the ghost is at a top boundary
	   */

	  public boolean atTopBoundary ()
	  {
	  	  boolean isAtTopBoundary = true;
	  	  for (int i = 0; i < PacMan.dotCounter; i++)
	  	  {
	  		  if (dotOnGhostYCoord() - PacMan.DOT_DISTANCE == PacMan.dot[i].getY () && dotOnGhostXCoord() == PacMan.dot[i].getX () && dotOnGhostXCoord() != -1)
	  		  {
	  			  isAtTopBoundary = false;
	  		  }
	  	  }
	  	  return isAtTopBoundary;
	  }

	  /**
	   * Determine if a ghost is at an at least two way intersection
	   *
	   * @return true or false depending upon whether or not the ghosts are at an intersection
	   */

	  public boolean atIntersection ()
	  {
		  boolean isAtIntersection = false;
		  if ((!this.atTopBoundary() && !this.atRightBoundary()) || (!this.atRightBoundary() && !this.atBottomBoundary()) ||
		  	  (!this.atLeftBoundary() && !this.atBottomBoundary()) || (!this.atLeftBoundary() && !this.atTopBoundary()))
		  {
			  isAtIntersection = true;
		  }
		  return isAtIntersection;
	  }


	 /**
	  * Run the ghosts, execute their behavior and the game's behavior
	  */

	 public void run ()
	 {
		/*
		 * Declare and initialize an array to save the directions of each ghost respectively
		 */

	     String[] directions = {"down", "down", "up", "up"};

		 while (true)
		 {
			 /*
			  * Move the ghosts randomly and make sure they moves exactly once per loop
			  */

			 for (int n = 0; n < ghost.length; n++)
			 {
				crntImage = ghost[n];
			 	int randNum = (int)(Math.random() * 4) + 1;
			 	if (directions[n] == "right")
			 	{
					direction = Direction.RIGHT;
				}
				else if (directions[n] == "left")
				{
					direction = Direction.LEFT;
				}
				else if (directions[n] == "up")
				{
					direction = Direction.UP;
				}
				else
				{
					direction = Direction.DOWN;
				}
			 	if (this.atIntersection())
			 	{
					if (randNum == 1)
					{
						if (!this.atTopBoundary() && direction != Direction.DOWN)
						{
							this.moveUp();
							directions[n] = "up";
						}
						else if (!this.atBottomBoundary() && direction != Direction.UP)
						{
							this.moveDown();
							directions[n] = "down";
						}
						else if (!this.atLeftBoundary() && direction != Direction.RIGHT)
						{
							this.moveLeft();
							directions[n] = "left";
						}
						else
						{
							this.moveRight();
							directions[n] = "right";
						}
					}
					else if (randNum == 2)
					{
						if (!this.atRightBoundary() && direction != Direction.LEFT)
						{
							this.moveRight();
							directions[n] = "right";
					    }
						else if (!this.atTopBoundary() && direction != Direction.DOWN)
						{
							this.moveUp();
							directions[n] = "up";
						}
						else if (!this.atBottomBoundary() && direction != Direction.UP)
						{
							this.moveDown();
							directions[n] = "down";
						}
						else
						{
							this.moveLeft();
							directions[n] = "left";
						}
					}
					else if (randNum == 3)
					{
						if (!this.atLeftBoundary() && direction != Direction.RIGHT)
						{
							this.moveLeft();
							directions[n] = "left";
						}
						else if (!this.atRightBoundary() && direction != Direction.LEFT)
						{
							this.moveRight();
							directions[n] = "right";
						}
						else if (!this.atTopBoundary() && direction != Direction.DOWN)
						{
							this.moveUp();
							directions[n] = "up";
						}
						else
						{
							this.moveDown();
							directions[n] = "down";
						}
					}
					else
					{
						if (!this.atBottomBoundary() && direction != Direction.UP)
						{
							this.moveDown();
							directions[n] = "down";
						}
						else if (!this.atLeftBoundary() && direction != Direction.RIGHT)
						{
							this.moveLeft();
							directions[n] = "left";
						}
						else if (!this.atRightBoundary() && direction != Direction.LEFT)
						{
							this.moveRight();
							directions[n] = "right";
						}
						else
						{
							this.moveUp();
							directions[n] = "up";
						}
					}
				}
			 	else if (direction == Direction.RIGHT)
			 	{
					this.moveRight();
				}
				else if (direction == Direction.LEFT)
				{
					this.moveLeft();
				}
				else if (direction == Direction.UP)
				{
					this.moveUp();
				}
				else if (direction == Direction.DOWN)
				{
					this.moveDown();
			    }
			pause(DELAY);
		   }
		 }
	 }
}