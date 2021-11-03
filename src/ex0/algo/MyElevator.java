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


}
