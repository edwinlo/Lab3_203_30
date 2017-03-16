/*******************************************************
 * GameBlock.java             Feb 18th 2017
 *
 * Group # 30
 *
 * Lab Members: Edwin Lo, Madeleine Wang, Yizhou Yang
 *
 * This class extends the ImageView class so that we can set the
 * X and Y coordinates in such a way to make it look like the
 * GameBlock is sliding across the screen. This is done by
 * using a Linear-pixel-Acceleration Motion.
 *
 *******************************************************/
package lab3_203_30.uwaterloo.ca.lab3_203_30;

import android.content.Context;
import android.widget.ImageView;

public class GameBlock extends ImageView{

    //declaring fields

    private final float GB_ACC = 4.0f;
    private final float IMAGE_SCALE = 1.0f;

    //These values need to be changed w.r.t the android device used
    private final int UP_BOUNDARY = 20;         //the upper boundary of the GameBoard
    private final int DOWN_BOUNDARY = 918;      //the lower boundary of the GameBoard 432
    private final int LEFT_BOUNDARY = 20;       //the Left boundary of the GameBoard
    private final int RIGHT_BOUNDARY = 918;     //the Right Boundary of the GameBoard 432

    private int myVelocity;
    private int myCoordX;
    private int myCoordY;
    private int targetCoordX;
    private int targetCoordY;

    private boolean isMovingUp = false;
    private boolean isMovingDown = false;
    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;

    private GameLoopTask.gameDirection myDir = GameLoopTask.gameDirection.NO_MOVEMENT;


    //constructor
    public GameBlock(Context myContext, int coordX, int coordY){
        super(myContext);
        this.setImageResource(R.drawable.gameblock);
        this.setScaleX(IMAGE_SCALE);
        this.setScaleY(IMAGE_SCALE);
        myCoordX = coordX;
        myCoordY = coordY;
        myVelocity = 0;

        this.setX(myCoordX);
        this.setY(myCoordY);

    }


    //setting the BlockDirection to the specified direction
    public void setBlockDirection(GameLoopTask.gameDirection newDir){
        myDir = newDir;
    }


    //method that is in charge of moving the block
    public void move(){

        switch(myDir){
            case UP:
                targetCoordY = UP_BOUNDARY;

                //only runs if it is not already moving in a direction
                if (myCoordY > targetCoordY && (!isMovingDown && !isMovingLeft && !isMovingRight)){

                    isMovingUp = true;

                    //Linear-Pixel-Acceleration motion
                    if((myCoordY - myVelocity) <= targetCoordY){
                        myCoordY = targetCoordY;
                        myVelocity = 0;
                        isMovingUp = false;
                        //myDir = GameLoopTask.gameDirection.NO_MOVEMENT;
                    }   else {
                        myCoordY -= myVelocity;
                        myVelocity += GB_ACC;
                    }
                }   else if(isMovingDown || isMovingRight || isMovingLeft){     //continues the motion that it was
                    if (isMovingDown){                                          //originally in if the block was
                        myDir = GameLoopTask.gameDirection.DOWN;                //already in motion
                    }   else if(isMovingRight){
                        myDir = GameLoopTask.gameDirection.RIGHT;
                    }   else if(isMovingLeft){
                        myDir = GameLoopTask.gameDirection.LEFT;
                    }
                    move();
                }

                break;
            case DOWN:

                targetCoordY = DOWN_BOUNDARY;

                //only runs if it is not already moving in a direction
                if(myCoordY < targetCoordY && (!isMovingUp && !isMovingRight && !isMovingLeft)){

                    isMovingDown = true;

                    //Linear-Pixel-Acceleration-motion
                    if((myCoordY + myVelocity) >= targetCoordY){
                        myCoordY = targetCoordY;
                        myVelocity = 0;
                        isMovingDown = false;
                       // myDir = GameLoopTask.gameDirection.NO_MOVEMENT;

                    }   else{
                        myCoordY += myVelocity;
                        myVelocity += GB_ACC;
                    }
                }   else if(isMovingUp || isMovingRight || isMovingLeft){           //continues in the motion that
                    if (isMovingUp){                                                // it was already moving in
                        myDir = GameLoopTask.gameDirection.UP;
                    }   else if(isMovingRight){
                        myDir = GameLoopTask.gameDirection.RIGHT;
                    }   else if(isMovingLeft){
                        myDir = GameLoopTask.gameDirection.LEFT;
                    }
                    move();
                }
                break;
            case LEFT:

                targetCoordX = LEFT_BOUNDARY;

                //only runs if the block is not already in motion
                if (myCoordX > targetCoordX && (!isMovingRight && !isMovingDown && !isMovingUp)){

                    isMovingLeft = true;

                    //Linear-Pixel-Acceleration Motion
                    if((myCoordX - myVelocity) <= targetCoordX){
                        myCoordX = targetCoordX;
                        myVelocity = 0;
                        isMovingLeft =false;
                        //myDir = GameLoopTask.gameDirection.NO_MOVEMENT;
                    }   else{
                        myCoordX -= myVelocity;
                        myVelocity +=GB_ACC;
                    }
                }   else if(isMovingDown || isMovingRight || isMovingUp){          //continues in the motion that
                    if (isMovingDown){                                              // it is already moving in
                        myDir = GameLoopTask.gameDirection.DOWN;
                    }   else if(isMovingRight){
                        myDir = GameLoopTask.gameDirection.RIGHT;
                    }   else if(isMovingUp){
                        myDir = GameLoopTask.gameDirection.UP;
                    }
                    move();
                }
                break;
            case RIGHT:

                targetCoordX = RIGHT_BOUNDARY;

                //only runs if the block is idle
                if (myCoordX < targetCoordX && (!isMovingUp && !isMovingLeft && !isMovingDown)){

                    isMovingRight = true;

                    //Linear-Pixel-Acceleration Motion
                    if ((myCoordX + myVelocity) >= targetCoordX){
                        myCoordX = targetCoordX;
                        myVelocity = 0;
                        isMovingRight = false;
                        //myDir = GameLoopTask.gameDirection.NO_MOVEMENT;
                    }   else {
                        myCoordX +=myVelocity;
                        myVelocity += GB_ACC;
                    }
                }   else if(isMovingDown || isMovingUp || isMovingLeft){        //continues in the motion that it was
                    if (isMovingDown){                                          //already moving in
                        myDir = GameLoopTask.gameDirection.DOWN;
                    }   else if(isMovingUp){
                        myDir = GameLoopTask.gameDirection.UP;
                    }   else if(isMovingLeft){
                        myDir = GameLoopTask.gameDirection.LEFT;
                    }
                    move();
                }
                break;
            default:
                //continues in the motion that it was moving in
                if(isMovingUp || isMovingDown || isMovingRight || isMovingLeft){
                    if(isMovingUp){
                        myDir = GameLoopTask.gameDirection.UP;
                    }   else if (isMovingDown){
                        myDir = GameLoopTask.gameDirection.DOWN;
                    }   else if(isMovingRight){
                        myDir = GameLoopTask.gameDirection.RIGHT;
                    }   else if(isMovingLeft){
                        myDir = GameLoopTask.gameDirection.LEFT;
                    }

                    move();
            }
                break;
        }

        //setting the new X and Y coordinates to the ImageView
        this.setX(myCoordX);
        this.setY(myCoordY);

        if(myVelocity == 0)
            myDir = GameLoopTask.gameDirection.NO_MOVEMENT;
    }
}
