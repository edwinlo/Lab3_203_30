/*******************************************************
 * GameLoopTask.java             Feb 18th 2017
 *
 * Group # 30
 *
 * Lab Members: Edwin Lo, Madeleine Wang, Yizhou Yang
 *
 * This class contains the code for the GameLoopTask object. It implements a TimerTask
 * that has a period of 30ms. The task it runs is move() function from the GameBlock.java
 * class.
 *
 *******************************************************/
package lab3_203_30.uwaterloo.ca.lab3_203_30;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.RelativeLayout;

import java.util.TimerTask;

public class GameLoopTask extends TimerTask {

    public enum gameDirection{UP, DOWN, LEFT, RIGHT, NO_MOVEMENT};

    public gameDirection currentGameDirection = gameDirection.NO_MOVEMENT;

    public GameBlock newBlock;

    private Activity myActivity;
    private Context myContext;
    private RelativeLayout myRL;

    public void run(){
        this.myActivity.runOnUiThread(
                new Runnable(){
                    public void run() {
                        newBlock.move();
                    }
                }
        );
    }

    //constructor for GameLoopTask object
    public GameLoopTask (Activity myActivity, RelativeLayout myRL, Context myContext){
        this.myActivity = myActivity;
        this.myRL = myRL;
        this.myContext = myContext;
        createBlock();
    }

    //method for setting the direction in the specified direction
    public void setDirection(gameDirection newDirection){
        Log.d("Direction is:", "" + newDirection);
        currentGameDirection = newDirection;
        newBlock.setBlockDirection(currentGameDirection);
    }

    //instantiates the GameBlock object at the bottom right corner of the game board
    private void createBlock(){

        newBlock = new GameBlock(myContext, 918, 918); //Or any (x,y) of your choice
        myRL.addView(newBlock);

    }

}
