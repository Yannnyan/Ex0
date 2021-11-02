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

    public Call CurrentCall(int elev){ // this returns the first call the elevator needs to handle
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


}
