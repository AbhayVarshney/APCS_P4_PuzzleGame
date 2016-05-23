import Controller.PGController;

/**
 * APCS_P4_PuzzleGame
 * Name: Abhay Varshney, Joshua Cheung, Sasan Erfan  Period 4   3/23/16
 * Time Spent: ~10hrs
 * Reflection: Overall this lab was very fun because it was entirely a group project. We got to work
 *             as a team and communicate the issues that we had as a team. I enjoyed using Github
 *             because that is what is going to be used in the real world. Using terminal and
 *             dealing with merge conflicts was fun as well. This game is mostly like the Sokoban game
 *             online. We just converted it to pure java, and we had to think of the logic
 *             by ourselves. We didn't really run into huge problems in this lab. It was just time-consuming
 *             because we had a lot of components and logic to add.
 */


/**
 * APCS_P4_PuzzleGame Extension
 * Name: Abhay Varshney
 * Time Spent: ~2 hours
 * Reflection: In this lab, I created an extension to my original project. The original project did not
 *             have a move history options. So I added right and left arrow buttons (with the left button
 *             only working), which allows the user to undo a move that they may have made. Also
 *             I want to create a multi player options, and so I added that to the menu bar. And if
 *             the user clicks on the multi player option, the screen enlarges. The user will be able
 *             to use a-w-s-d to move around the map. I included a multiplayer level map in which the
 *             players must communicate to properly solve the map!
 */

public class PGMain {
    public static void main(String[] args) { new PGController(); }
}