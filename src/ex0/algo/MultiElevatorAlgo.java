package ex0.algo;

import ex0.Building;
import ex0.CallForElevator;

public class MultiElevatorAlgo implements ElevatorAlgo{

    private MyBuilding myBuilding; // contains the MyElevator allocate uses


    public MultiElevatorAlgo(Building building){
        this.myBuilding = new MyBuilding(building);
    }

    @Override
    public Building getBuilding() {
        return this.myBuilding.getBuilding();
    }

    @Override
    public String algoName() {
        return "MultiElevatorAlgo";
    }

    @Override
    public int allocateAnElevator(CallForElevator c) {
        Call call = new Call(c);
        return myBuilding.allocate(call);
    }

    @Override
    // takes all the calls up and then takes all the calls down
    public void cmdElevator(int elev) {
        MyElevator myElevator = myBuilding.getElev(elev);
        Call call = myElevator.CurrentCall();
        if(call == null){
            return;
        }
        else{
            myElevator.UpdateAndGetFlag();
        }
        // go to the current call source or destination.
        if(call.getFlag() == 0){
            myElevator.Goto(call.getSrc());
        }
        else if(call.getFlag() == 1){
            myElevator.Goto(call.getDest());
        }
        myElevator.MiddleStop();
    }
}
