package ex0.algo;

import ex0.CallForElevator;
import ex0.Elevator;
import java.util.Comparator;
public class ComparatorDown implements Comparator{

    private Elevator elevator;
    ComparatorDown(Elevator elevator){
        this.elevator = elevator;
    }
    @Override
    public int compare(Object o1, Object o2) {
        // reflective
        CallForElevator call1 = (CallForElevator) o1;
        CallForElevator call2 = (CallForElevator) o2;
        if(dist(call1) > dist(call2)){
            return 1;
        }
        else if (dist(call1) < dist(call2)){
            return -1;
        }
        else{
            return 0;
        }
    }
    private double dist(CallForElevator c){
        return Math.abs(elevator.getPos() - c.getSrc());
    }
}
