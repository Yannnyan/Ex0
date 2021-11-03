package ex0.algo;
import ex0.Building;
import ex0.Elevator;

import java.sql.Time;

public class MyBuilding{
    private Building myBuilding;
    private MyElevator[] myElevators;

    public MyBuilding(Building myBuilding){
        this.myBuilding = myBuilding;
        myElevators = new MyElevator[myBuilding.numberOfElevetors()];
        for(int i=0; i< myBuilding.numberOfElevetors(); i++){
            MyElevator myElevator = new MyElevator(myBuilding.getElevetor(i));
            myElevators[i] = myElevator;
        }

    }
    public Building getBuilding(){
        return this.myBuilding;
    }
    public int getNumberOfElevators(){
        return this.myBuilding.numberOfElevetors();
    }


    public int allocate(Call call){
       int minElev = MinimalTime();
       myElevators[minElev].getCalls().add(call);
       return minElev;
    }
    public MyElevator getElev(int elev){
        return myElevators[elev];
    }
    // this function iterates with a single call through all the elevators
    // and checks which elevator has minimal time and returns that elevator index
    public int MinimalTime(){
        MyElevator myElevator = myElevators[0];
        int ans =0, CallSize = myElevator.getCalls().getSize();double timeLast,timeLast2;   // comparing two elevators completion times
        if(CallSize == 0){
            return ans;
        }
        int LastCallIndex = CallSize-1;
        Call LastCall = myElevator.getCalls().getByIndex(LastCallIndex);
        if(LastCall != null)
            timeLast = TimeToHandle(myElevator,LastCall);
        else return 0;
        // get elevator, calculate how much time to get to the source and destination of the last call
        // then compare times with the last elevator, and return the minimal.
        for(int i=1; i<myElevators.length; i++){
            myElevator = myElevators[i];
            CallSize = myElevator.getCalls().getSize();
            if(CallSize == 0){
                return i;
            }
            else {
                LastCallIndex = CallSize - 1;
            }
            LastCall = myElevator.getCalls().getByIndex(LastCallIndex);
            if(LastCall != null) {
                timeLast2 = TimeToHandle(myElevator, LastCall);
                if (timeLast >= timeLast2) {
                    ans = i;
                    timeLast = timeLast2;
                }
            }
            else{
                return LastCallIndex;
            }
        }
        return ans;
    }
    // time takes the elevator to reach and handle the last call
    private double TimeToHandle(MyElevator myElevator, Call call){
        return myElevator.dt(call.getSrc()) + myElevator.dt(call.getSrc(),call.getDest());
    }

}
