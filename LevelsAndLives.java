/*
 * Import statements
 */

import objectdraw.ActiveObject;
import objectdraw.FilledRect;

/*
 * Class declaration
 */

public class LevelsAndLives extends ActiveObject
{
	/**
	 * Reset the dots
	 */

	public void resetDots ()
	{
		for (int n = 0; n < PacMan.dotCounter; n++)
		{
			PacMan.dot[n].show();
		}
	}

	 /**
	  * Make PacMan eat dots
	  * @param dot the dot to be eaten
	  */

	 public void eatDot (FilledRect dot)
	 {
	    dot.hide();
	 }

	/**
     * Run the other aspects of the game
	 */

	public void run ()
	{
		int level = 0; // The level that PacMan is currently on
	    int dotsEatenCount = 0; // Counts how many dots are eaten in a certain level
		while (level < 5)
	    {
			for (int i = 0; i < PacMan.dotCounter; i++)
			{
			   if (PacMan.dot[i].getX () > PacManCharacter.pacMan.getX () && PacMan.dot[i].getX () < PacManCharacter.pacMan.getX () + PacManCharacter.pacMan.getWidth ()
			   && PacMan.dot[i].getY () > PacManCharacter.pacMan.getY () && PacMan.dot[i].getY () < PacManCharacter.pacMan.getY () + PacManCharacter.pacMan.getHeight () && !PacMan.dot[i].isHidden())
			   {
					eatDot(PacMan.dot[i]);
					dotsEatenCount++;
			   }
			}
			if (dotsEatenCount == PacMan.dotCounter)
			{
				level++;
				dotsEatenCount = 0;
				resetDots();
				PacManCharacter.pacMan.moveTo (PacMan.LEFT_BOUNDARY_WIDTH + PacMan.DOT_DISTANCE * 13 - PacManCharacter.WIDTH_OF_PACMAN / 2, PacMan.TOP_BOUNDARY_AND_SCORE_HEIGHT + PacMan.DOT_DISTANCE * 11 - PacManCharacter.HEIGHT_OF_PACMAN / 2);
				for (int j = 0; j < Ghosts.ghost.length; j++)
				{
					Ghosts.ghost[j].moveTo(PacMan.LEFT_BOUNDARY_WIDTH + PacMan.DOT_DISTANCE * 10 - Ghosts.WIDTH_OF_GHOSTS / 2, PacMan.TOP_BOUNDARY_AND_SCORE_HEIGHT + PacMan.DOT_DISTANCE * 6 - Ghosts.HEIGHT_OF_GHOSTS / 2);
				}
			}

		 }
	}
}