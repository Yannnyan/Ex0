package ex0.algo;
import java.lang.Math;
import ex0.Building;
import ex0.CallForElevator;
import ex0.Elevator;

import java.util.PriorityQueue;

public class MultiElevAlgo implements ElevatorAlgo {
    private PriorityQueue[] queues;
    public static final int UP=1, DOWN=-1;
    private int _direction;
    private Building _building;
    public MultiElevAlgo(Building b) {
        _building = b;
        queues = new PriorityQueue[_building.numberOfElevetors()];
        Iterator iterator = new Iterator(_building);
        for (int index=0; iterator.hasNext(); index++){
            queues[index] = new PriorityQueue<CallForElevator>();
        }
        //_direction = UP;
    }
    @Override
    public Building getBuilding() {
        return _building;
    }

    @Override
    public String algoName() {
        return "MultiElevAlgo";
    }

    @Override
    public int allocateAnElevator(CallForElevator c){
        /*
        finds the minimal time of all the elevators to travel to our call and returns the elevator with that minimal time
        explanation of code: iterates through all the elevators compares 2 elevators each time keeps the minimal.
        */
        Iterator iterator = new Iterator(_building);
        if(_building.numberOfElevetors() <= 0) return -1;
        Elevator curElev = (Elevator)iterator.next();
        double T = TravelTime(curElev,c),t;
        int retElevator= curElev.getID();
        do{
           curElev = (Elevator)iterator.next();
           t = TravelTime(curElev,c);
           retElevator = t < T ? curElev.getID() : retElevator;
       }
        while(iterator.hasNext());
        return retElevator;
    }
    private double TravelTime(Elevator elevator, CallForElevator call){
        // v = elevator velocity, d_i = distance of this elevator from the i'th car call
        // t_o = time to open the door, t_c = time to close the door, n number of car calls
        double v = elevator.getSpeed(), t_o = elevator.getTimeForOpen(), t_c = elevator.getTimeForClose(),t=0;
        int n =queues[elevator.getID()].size(); // needs to be updated when elevator car calls are organized in a data structure
        for(int i=0; i<n; i++){
           double d_i = elevator.getPos() - call.getSrc();
           t = t + d_i/v +(t_o+t_c);
        }
    return t;
    }


    @Override
    public void cmdElevator(int elev) {

    }

}
