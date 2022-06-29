/**
 * This program is designed to simulate the game of PacMan. It will create all
 * of the necessary images and objects relevant to the game.
 *
 * @author Louis Friedmann
 */

 /*
  * Import statements
  */

 import java.awt.Image;

 import objectdraw.FilledRect;
 import objectdraw.WindowController;
 import java.awt.Color;
 import objectdraw.ActiveObject;
 import objectdraw.VisibleImage;
 import objectdraw.DrawingCanvas;

 /*
  * class declaration
  */

 public class PacMan extends WindowController
 {
 	/*
 	 * constants
 	 */

 	public static final int LEFT_BORDER = 0;
 	public static final int RIGHT_BORDER = 1368;

 	public static final int TOP_BORDER = 0;
 	public static final int BOTTOM_BORDER = 798;
 	public static final int DOT_DISTANCE = 50; // the distance each dot that pacman eats will be away from each other
 	public static final int SCORE_HEIGHT = 60; // Height of the score
 	public static final int TOP_BOUNDARY_HEIGHT = 50; // Height of top boundary
	public static final int TOP_BOUNDARY_AND_SCORE_HEIGHT = SCORE_HEIGHT + TOP_BOUNDARY_HEIGHT; // Height of the boundary and the score
	public static final int LEFT_BOUNDARY_WIDTH = 59; // Width of left boundary
	public static final int RIGHT_BOUNDARY_WIDTH = 59; // Width of right boundary
	public static final int BOTTOM_BOUNDARY_HEIGHT = 50; // Height of bottom boundary
	public static final int ROW_OF_DOTS = 12; // The amount of rows of dots if there were no boundaries
	public static final int COLUMN_OF_DOTS = 28; // The amount of columns of dots if there were no boundaries
	public static final int WIDTH_OF_DOT = 5; // The width of each dot
	public static final int HEIGHT_OF_DOT = 5; // The height of each dot

	/*
	 * Instance fields
	 */

	public static int dotCounter; // The amount of dots there are at the beginning of the level
	private Image pacRight; // Image of PacMan's right side
	private Image pacLeft; // Image of PacMan's left side
	private Image redGhost; // Image of red ghost
	private Image orangeGhost; // Image of orange ghost
	private Image yellowGhost; // Image of yellow ghost
	private Image greenGhost; // Image of green ghost

	/*
	 * Create the array to store the FilledRects
	 */

	public static FilledRect[] boundary = new FilledRect[19];

	/*
	 * Initialize the array to store the dots
	 */

	public static FilledRect[] dot = new FilledRect[500]; // Change 500 once you know the amount of dots in the array

	/*
	 * Create the images and any other objects necessary to run the game.
	 */

	public void begin()
	{
		/*
		 * Create the canvas for the game
		 */

	    new FilledRect(LEFT_BORDER, TOP_BORDER, RIGHT_BORDER, BOTTOM_BORDER, canvas);
		this.resize(RIGHT_BORDER, BOTTOM_BORDER);

		/**
		 * Create the boundaries that pacman and the ghosts cannot go past as blue FilledRects and store them in the array, obstacles are boundaries not at the borders
		 */

		boundary[0] = new FilledRect(LEFT_BORDER, TOP_BORDER + SCORE_HEIGHT, RIGHT_BORDER + SCORE_HEIGHT, SCORE_HEIGHT, canvas); // Top border boundary
		boundary[0].setColor(Color.BLUE);
		boundary[1] = new FilledRect(LEFT_BORDER, TOP_BORDER + SCORE_HEIGHT, LEFT_BOUNDARY_WIDTH, TOP_BOUNDARY_HEIGHT + DOT_DISTANCE * 5, canvas); // First half of left border boundary
		boundary[1].setColor(Color.BLUE);
		boundary[2] = new FilledRect(LEFT_BORDER, TOP_BOUNDARY_AND_SCORE_HEIGHT + DOT_DISTANCE * 7, LEFT_BOUNDARY_WIDTH, DOT_DISTANCE * 5 + TOP_BOUNDARY_HEIGHT, canvas); // Second half of left border boundary
		boundary[2].setColor(Color.BLUE);
		boundary[3] = new FilledRect(LEFT_BORDER, TOP_BOUNDARY_AND_SCORE_HEIGHT + DOT_DISTANCE * 12, RIGHT_BORDER, BOTTOM_BOUNDARY_HEIGHT, canvas); // Bottom border boundary
		boundary[3].setColor(Color.BLUE);
		boundary[4] = new FilledRect(RIGHT_BORDER - RIGHT_BOUNDARY_WIDTH, TOP_BORDER + SCORE_HEIGHT, RIGHT_BOUNDARY_WIDTH, TOP_BOUNDARY_HEIGHT + DOT_DISTANCE * 5, canvas); // First half of right border boundary
		boundary[4].setColor(Color.BLUE);
		boundary[5] = new FilledRect(RIGHT_BORDER - RIGHT_BOUNDARY_WIDTH, TOP_BOUNDARY_AND_SCORE_HEIGHT + DOT_DISTANCE * 7, RIGHT_BOUNDARY_WIDTH, DOT_DISTANCE * 5 + BOTTOM_BOUNDARY_HEIGHT, canvas); // Second half of right border boundary
	    boundary[5].setColor(Color.BLUE);
		boundary[6] = new FilledRect(LEFT_BOUNDARY_WIDTH + DOT_DISTANCE * 2, TOP_BOUNDARY_AND_SCORE_HEIGHT + DOT_DISTANCE * 2, DOT_DISTANCE * 2, DOT_DISTANCE * 3, canvas); // First obstacle
		boundary[6].setColor(Color.BLUE);
		boundary[7] = new FilledRect(LEFT_BOUNDARY_WIDTH + DOT_DISTANCE * 2, TOP_BOUNDARY_AND_SCORE_HEIGHT + DOT_DISTANCE * 7, DOT_DISTANCE * 2, DOT_DISTANCE * 3, canvas); // Second obstacle
		boundary[7].setColor(Color.BLUE);
		boundary[8] = new FilledRect(LEFT_BOUNDARY_WIDTH + DOT_DISTANCE * 4, TOP_BOUNDARY_AND_SCORE_HEIGHT + DOT_DISTANCE * 2, DOT_DISTANCE * 6, DOT_DISTANCE, canvas); // Third obstacle
	 	boundary[8].setColor(Color.BLUE);
		boundary[9] = new FilledRect(LEFT_BOUNDARY_WIDTH + DOT_DISTANCE * 6, TOP_BOUNDARY_AND_SCORE_HEIGHT + DOT_DISTANCE * 5, DOT_DISTANCE * 3, DOT_DISTANCE * 2, canvas); // Fourth obstacle
	    boundary[9].setColor(Color.BLUE);
		boundary[10] = new FilledRect(LEFT_BOUNDARY_WIDTH + DOT_DISTANCE * 4, TOP_BOUNDARY_AND_SCORE_HEIGHT + DOT_DISTANCE * 9, DOT_DISTANCE * 5, DOT_DISTANCE, canvas); // Fifth obstacle
	    boundary[10].setColor(Color.BLUE);
	    boundary[11] = new FilledRect(LEFT_BOUNDARY_WIDTH + DOT_DISTANCE * 12, TOP_BOUNDARY_AND_SCORE_HEIGHT + DOT_DISTANCE * 2, DOT_DISTANCE, DOT_DISTANCE * 3, canvas); // Sixth obstacle
		boundary[11].setColor(Color.BLUE);
		boundary[12] = new FilledRect(LEFT_BOUNDARY_WIDTH + DOT_DISTANCE * 11, TOP_BOUNDARY_AND_SCORE_HEIGHT + DOT_DISTANCE * 5, DOT_DISTANCE * 3, DOT_DISTANCE * 3, canvas); // Seventh obstacle
		boundary[12].setColor(Color.WHITE);
		boundary[13] = new FilledRect(LEFT_BOUNDARY_WIDTH + DOT_DISTANCE * 11, TOP_BOUNDARY_AND_SCORE_HEIGHT + DOT_DISTANCE * 8, DOT_DISTANCE * 3, DOT_DISTANCE * 2, canvas); // Eighth obstacle
		boundary[13].setColor(Color.BLUE);
		boundary[14] = new FilledRect(LEFT_BOUNDARY_WIDTH + DOT_DISTANCE * 15, TOP_BOUNDARY_AND_SCORE_HEIGHT + DOT_DISTANCE * 2, DOT_DISTANCE * 6, DOT_DISTANCE, canvas); // Ninth obstacle
		boundary[14].setColor(Color.BLUE);
		boundary[15] = new FilledRect(LEFT_BOUNDARY_WIDTH + DOT_DISTANCE * 16, TOP_BOUNDARY_AND_SCORE_HEIGHT + DOT_DISTANCE * 5, DOT_DISTANCE * 3, DOT_DISTANCE * 2, canvas); // Tenth obstacle
		boundary[15].setColor(Color.BLUE);
		boundary[16] = new FilledRect(LEFT_BOUNDARY_WIDTH + DOT_DISTANCE * 16, TOP_BOUNDARY_AND_SCORE_HEIGHT + DOT_DISTANCE * 9, DOT_DISTANCE * 5, DOT_DISTANCE, canvas); // Eleventh obstacle
		boundary[16].setColor(Color.BLUE);
		boundary[17] = new FilledRect(LEFT_BOUNDARY_WIDTH + DOT_DISTANCE * 21, TOP_BOUNDARY_AND_SCORE_HEIGHT + DOT_DISTANCE * 2, DOT_DISTANCE * 2, DOT_DISTANCE * 3, canvas); // Twelfth obstacle
	    boundary[17].setColor(Color.BLUE);
		boundary[18] = new FilledRect(LEFT_BOUNDARY_WIDTH + DOT_DISTANCE * 21, TOP_BOUNDARY_AND_SCORE_HEIGHT + DOT_DISTANCE * 7, DOT_DISTANCE * 2, DOT_DISTANCE * 3, canvas); // Thirteenth obstacle
		boundary[18].setColor(Color.BLUE);

	    /*
		 * Create the dots in the game as white FilledRects, and store them in the array, but make sure the dots are not in the boundaries
		 */

	    int xCoord = LEFT_BOUNDARY_WIDTH - DOT_DISTANCE; // X-coordinate of dot to be placed
		int yCoord = TOP_BOUNDARY_AND_SCORE_HEIGHT; // Y-coordinate of dot to be placed
		dotCounter = 0; // Counter of dots
	    int counter = 0; // Will count how many times a dot is inside a boundary
		for (int r = 0; r < ROW_OF_DOTS; r++)
		{
			for (int c = 0; c < COLUMN_OF_DOTS; c++)
	    	{
				for (int i = 0; i < boundary.length; i++)
				{
					if ((xCoord >= boundary[i].getX ()) && (xCoord <= boundary[i].getX () + boundary[i].getWidth ()) &&
					(yCoord >= boundary[i].getY ()) && (yCoord <= boundary[i].getY () + boundary[i].getHeight ()))
					{
						counter++;
					}
				}
				if (counter == 0)
	        	{
					dot[dotCounter] = new FilledRect(xCoord, yCoord, WIDTH_OF_DOT, HEIGHT_OF_DOT, canvas);
					dot[dotCounter].setColor(Color.WHITE);
					dotCounter++;
				}
				else
				{
					counter = 0;
				}
				xCoord += DOT_DISTANCE;
		 	}
	     	yCoord += DOT_DISTANCE;
	     	xCoord = LEFT_BOUNDARY_WIDTH - DOT_DISTANCE;
		}

		/*
		 * Initialize the images for pacman
		 */

	    pacRight = getImage("pacmanRightImage.png"); // Image of the right of pacman
	    pacLeft = getImage("pacmanLeftImage.png");   // Image of the left of pacman

		/*
		 * Initialize the images of the ghosts
		 */

	    redGhost = getImage("redGhostImage.png"); // Image of the red ghost
	    orangeGhost = getImage("orangeGhostImage.png"); // Image of the orange ghost
	    yellowGhost = getImage("yellowGhostImage.png"); // Image of the yellow ghost
	    greenGhost = getImage("greenGhostImage.png"); // Image of the green ghost

		/*
		 * Create PacMan
		 */

		PacManCharacter pac = new PacManCharacter (pacRight, LEFT_BOUNDARY_WIDTH + DOT_DISTANCE * 13 - PacManCharacter.WIDTH_OF_PACMAN / 2, TOP_BOUNDARY_AND_SCORE_HEIGHT + DOT_DISTANCE * 11 - PacManCharacter.HEIGHT_OF_PACMAN / 2, this, canvas);

		/*
		 * Create the ghosts
		 */

		Ghosts ghosts = new Ghosts(redGhost, orangeGhost, yellowGhost, greenGhost, LEFT_BOUNDARY_WIDTH + DOT_DISTANCE * 10 - Ghosts.WIDTH_OF_GHOSTS / 2, TOP_BOUNDARY_AND_SCORE_HEIGHT + DOT_DISTANCE * 6 - Ghosts.HEIGHT_OF_GHOSTS / 2, canvas);
	}
 }