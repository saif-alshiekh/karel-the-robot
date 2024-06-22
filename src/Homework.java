import stanford.karel.SuperKarel;


public class Homework extends SuperKarel {


    public int x_axis = 1;
    public int y_axis = 1;
    public int width = 1;
    public int height = 1;
    public int moves = 0;
    public int beepersSpent = 0;
    public int midPointy =(int) Math.ceil(height/2);
    public int midPointx =(int) Math.ceil(width/2);

    @Override
    public void move() {
        super.move();
        if (facingEast()) {x_axis++;
        } else if (facingNorth()) {y_axis++;
        } else if (facingWest()) {x_axis--;
        } else {y_axis--;}
        moves++;
        System.out.printf("moves: %d\n", moves);
    }
    @Override
    public void putBeeper(){
        if (noBeepersPresent()){super.putBeeper();beepersSpent++;}
    }

    public void goToOrigin(){
        while (notFacingSouth()){turnLeft();}
        while (frontIsClear()){move();}turnRight();
        while (frontIsClear()){move();}
    }
    public void calculateScale() {
        while (frontIsClear()) {move();width++;}turnLeft();
        while (frontIsClear()) {move();height++;}
        midPointy =(int) Math.ceil(height/2);
        midPointx =(int) Math.ceil(width/2);
    }
    public void ticTac(int point){
        if (point ==0){//  ticL tacR
            while (frontIsClear()){
                putBeeper();
                if (frontIsClear()){move();}else {break;}turnLeft();
                if (frontIsClear()){move();}else {break;}
                putBeeper();turnRight();
                if (frontIsClear()){move();}else {break;}turnRight();
                if (frontIsClear()){move();}else {break;}
                putBeeper();turnLeft();
            }
        } else if (point==1) {// ticR ticX
            while (frontIsClear()){
                putBeeper();turnLeft();move();turnRight();move();
                putBeeper();turnRight();move();turnLeft();move();
                putBeeper();turnLeft();move();turnRight();move();
                putBeeper();turnRight();move();
            }
        } else {
            while (x_axis!= point&&frontIsClear()){
                putBeeper();
                if (x_axis!= point&&frontIsClear()){move();}else {break;}turnRight();
                if (x_axis!= point&&frontIsClear()){move();}else {break;}putBeeper();turnLeft();
                if (x_axis!= point&&frontIsClear()){move();}else {break;}turnLeft();
                if (x_axis!= point&&frontIsClear()){move();}else {break;}putBeeper();turnRight();
            }
        }
    }
    public void case56(int x){
        if (x ==0){
            turnLeft();putBeeperLine();turnLeft();move();
            while (frontIsClear()){ticTac(1);}
        }else{
            turnAround();putBeeperLine();turnRight();move();
            while (frontIsClear()){ticTac(0);}
        }
        goToOrigin();
    }


}
