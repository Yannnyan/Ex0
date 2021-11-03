package ex0.algo;
import ex0.Elevator;

public class MyElevator {
    private Elevator elevator;
    private Calls calls;


    public MyElevator(Elevator elevator){
        this.elevator = elevator;
        this.calls = new Calls();
    }

    public Calls getCalls(){
        return this.calls;
    }

    public Call CurrentCall(){ // this returns the first call the elevator needs to handle
        Call currentCall = this.calls.getFirstCall();
        return currentCall;
    }
    // gets the time for the elevator to travel from its Position to a given floor
    public double dt(int floor){
        double actualTravel = Math.abs(elevator.getPos() - floor)/elevator.getSpeed();
        double totalTime =
                elevator.getTimeForClose() + elevator.getStartTime() +
                        actualTravel +
                         elevator.getStopTime() + elevator.getTimeForOpen();
        return totalTime;
    }
    // this method calculates the time takes for elevator to travel between floors
    public double dt(int startFloor, int stopFloor){
        double actualTravel = Math.abs(stopFloor - startFloor)/ elevator.getSpeed();
        double totalTime =
                elevator.getTimeForClose() + elevator.getStartTime() +
                        actualTravel +
                        elevator.getStopTime() + elevator.getTimeForClose();
        return totalTime;
    }
    public void Goto(int floor){
        this.elevator.goTo(floor);
    }
    public void Stop(int floor){
        this.elevator.stop(floor);
    }
    // updates the current call of the elevator and returns its flag
    public int UpdateAndGetFlag(){
        Call currentCall = CurrentCall();
        if(currentCall.getFlag() == 0){
            if(currentCall.getSrc() == this.elevator.getPos()){
                currentCall.ReachedSource();
            }
        }
        else if(currentCall.getFlag() == 1){
            if(currentCall.getDest() == this.elevator.getPos()){
                currentCall.ReachedDest();
            }
        }

        return currentCall.getFlag();
    }
    // this method check if there is a call 'next' which is located in the middle of elevator and current destination
    // then stops there.
    public void MiddleStop(){
        //////// creating variables
        Call current = CurrentCall();
        if(current == null) return;
        Call next = calls.NextCall(current);
        if(next == null) return;
        int elevPos = elevator.getPos(),currentFloor=0,nextFloor=0;
        /////////// //////
        ////// initializing variables ///////
        if(current.getFlag() ==0)currentFloor=current.getSrc();
        else if(current.getFlag() ==1) currentFloor=current.getDest();
        if(next.getFlag() == 0) nextFloor = next.getSrc();
        else if(next.getFlag() == 1) nextFloor = next.getDest();
        ////////////// ///////

        while(next != null){
            if(elevator.getState() == Elevator.DOWN){
                if(elevPos>nextFloor && nextFloor < currentFloor) {
                    elevator.stop(nextFloor);
                    swap(next); // now the elevator is handling call 'next'
                }
            }
            else if(elevator.getState() == Elevator.UP){
                if(elevPos < nextFloor && nextFloor < currentFloor){
                    elevator.stop(nextFloor);
                    swap(next);
                }
            }
        }
    }
    // swaps the next call with current
    private void swap(Call next){
        Call temp = CurrentCall();
        int indexOfCurrent = calls.IndexOf(CurrentCall());
        int indexOfNext = calls.IndexOf(next);
        this.getCalls().add(indexOfCurrent, next);
        this.getCalls().add(indexOfNext,temp);

    }
}
