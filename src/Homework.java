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

    public void CrossSplitMix(){
        if (height%2==1){
            turnAround();while (y_axis!=midPointy+1){move();}turnRight();
            while (frontIsClear() && x_axis!=midPointx) {
                putBeeper();move();
            }
            turnRight();move();
            turnRight();putBeeper();move();putBeeper();turnAround();move();turnRight();
            ticTac(1000000);
            if (midPointy%2 ==1){turnRight();move();turnRight();
            }else {turnAround();
            }
            while (frontIsClear() && y_axis!=midPointy){move();}
            turnRight();putBeeper();move();putBeeper();turnAround();move();turnRight();
            move();
            ticTac(1000000);
            turnAround();
            while (frontIsClear() && y_axis!=midPointy+1){move();}
        }else {
            turnLeft();while (x_axis!=midPointx+1){move();}turnLeft();
            while (frontIsClear() && y_axis!=midPointy) {
                putBeeper();move();
            }
            turnRight();move();
            turnRight();putBeeper();move();putBeeper();turnAround();move();turnRight();
            ticTac(1000000);
            if (midPointx%2 ==1){turnRight();move();turnRight();
            }else {turnAround();}
            while (frontIsClear() && x_axis!=midPointx+2){move();}
            turnRight();putBeeper();move();putBeeper();turnAround();move();turnRight();move();

            ticTac(1000000);
            turnAround();
            while (frontIsClear() && x_axis!=midPointx+1){move();}
        }
        putBeeper();turnLeft();move();putBeeperLine();
        goToOrigin();
    }
    public void CrossSplitEvenwh(){
        int startPoint = midPointx+2;
        turnAround();while (y_axis!=midPointy){move();}turnRight();
        ticTac(startPoint);
        if (midPointx%2==1){move();turnLeft();
        } else {turnRight();}
        putBeeper();move();
        int stoppedY = y_axis-1;
        while (x_axis!=midPointx){putBeeper();move();putBeeper();}
        turnRight();move();putBeeper();
        ticTac(10000);
        if (midPointy%2 ==0){
            turnRight();move();turnRight();
        }else {
            turnAround();
        }
        while (frontIsClear() && y_axis!=stoppedY){move();}
        putBeeper();move();
        ticTac(1000000);
        turnAround();
        while (frontIsClear() && y_axis!=stoppedY){move();}putBeeper();
        turnLeft();move();
        ticTac(1000000);
        goToOrigin();
    }
    public void CrossSplitOddwh() {
        turnAround();
        while (y_axis!=midPointy+1 ){move();}turnRight();
        while (frontIsClear() && x_axis!=midPointx+1) {
            putBeeper();move();
        }
        int stoppedAt = y_axis;
        turnRight();
        while (frontIsClear()){move();}
        turnAround();putBeeperLine();turnAround();
        while (frontIsClear() && y_axis!= stoppedAt){move();}turnLeft();
        putBeeperLine();
        goToOrigin();
    }
    public void putBeeperLine() {
        while (frontIsClear()) {
            if (noBeepersPresent()){putBeeper();}move();
        }
        if (noBeepersPresent()){putBeeper();}
    }

    public void run() {
        setBeepersInBag(1000);calculateScale();
        if (width<=2 && height<=2){
            System.out.println("CAN'T BE DIVIDED!");
        }
        else if ((width==2 && (height<=6)) || (height==2 && (width<=6))) {
            if (width>height){
                if (width==6){
                    turnAround();putBeeperLine();turnRight();move();turnRight();move();
                    case56(1);
                } else if (width==5) {case56(1);
                } else {
                    turnLeft();ticTac(0);goToOrigin();}
            }else {
                if (height==6){
                    turnLeft();putBeeperLine();turnLeft();move();turnLeft();move();turnLeft();
                    case56(0);
                } else if (height==5) {case56(0);}
                else{turnLeft();move();turnLeft();
                    ticTac(0); goToOrigin();
                }
            }
        }
        else if ((width==2 && (height<=8)) || (height==2 && (width<=8))) {
            if(width>height){
                if (width==8){turnLeft();move();turnLeft();}else {turnAround();}
                putBeeperLine();
                turnRight();move();move();
                turnRight();putBeeperLine();
                turnLeft();move();move();
                turnLeft();putBeeperLine();
                turnRight();move();move();
                turnRight();
            }else {
                if (height==8){turnAround();move();turnRight();}
                else {turnLeft();}
                putBeeperLine();
                turnLeft();move();move();
                turnLeft();putBeeperLine();
                turnRight();move();move();
                turnRight();putBeeperLine();
                turnLeft();move();move();
                turnLeft();
            }
            putBeeperLine();goToOrigin();
        }
        else if ((width==2 && (height<=10)) || (height==2 && (width<=10))) {
            if (width>height){
                if (width==10){turnAround();putBeeperLine();turnRight();move();turnRight();move();}
            }else {
                if (height==10){turnLeft();putBeeperLine();turnLeft();move();turnRight();}
            }
            turnLeft();move();putBeeper();move();turnLeft();move();putBeeper();
            turnRight();move();move();turnRight();putBeeperLine();turnLeft();move();move();
            putBeeper();turnLeft();move();turnRight();move();putBeeper();goToOrigin();
        }
        else if (width==height ){
            putBeeper();turnLeft();
            while (frontIsClear()){
                move();turnLeft();
                move();turnRight();
                if (noBeepersPresent()){
                    putBeeper();
                }
            }
            turnRight();
            while (frontIsClear()){move();}
            putBeeper();turnRight();
            while (frontIsClear()){
                move();turnRight();
                move();turnLeft();
                if (noBeepersPresent()){
                    putBeeper();
                }
            }
            turnAround();while (frontIsClear()){move();}
        }
        else if ((width%2==0 && height%2==0) || (width%2==1 && height%2==1)) {
            if (width%2==0 && height%2==0){
                CrossSplitEvenwh();
            }else {
                CrossSplitOddwh();
            }
        }
        else if ((width%2==1 && height%2==0) || (width%2==0 && height%2==1)) {
            CrossSplitMix();
        }
        System.out.printf("Beepers spent: %d",beepersSpent);
    }



}
