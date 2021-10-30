package ex0.algo;
import java.lang.Math;
import ex0.Building;
import ex0.CallForElevator;
import ex0.Elevator;

import java.util.Iterator;
import java.util.PriorityQueue;
//import java.util.logging.Level;

public class MultiElevAlgo implements ElevatorAlgo {
    public static final int UP = 1, LEVEL = 0, DOWN = -1, ERROR = -2;
   private PriorityQueue<CallForElevator>[] upQueues;
   private PriorityQueue<CallForElevator>[] downQueues;
    private int[] Direction;
   private Building _building;

   public MultiElevAlgo(Building _building){    // Banai
       this._building = _building;
       int ElevNum = _building.numberOfElevetors();
       upQueues = new PriorityQueue[ElevNum];
       downQueues = new PriorityQueue[ElevNum];
       Direction = new int[ElevNum];    // sets to 0 automatically

       for(int i=0; i< ElevNum; i++){ // setting the comparators for the queues
           ComparatorUp CU = new ComparatorUp(_building.getElevetor(i));
           ComparatorDown CD = new ComparatorDown(_building.getElevetor(i));
           upQueues[i] = new PriorityQueue<CallForElevator>(CU);
           downQueues[i] = new PriorityQueue<CallForElevator>(CD);
       }

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
       int ans =-1, elevNum=_building.numberOfElevetors(),State;

        double t, T;
        if(elevNum <= 0) return ans; // condition 0 no elevetors
        // get elevator, get another elevator, compare times and take the lower one.
        int IndexValid=0;
        while(_building.getElevetor(IndexValid).getState() == ERROR && IndexValid < elevNum) IndexValid++;
        // checks for a working elevator
        if(IndexValid >= elevNum) return -1;
        if(c.getSrc() < c.getDest()){ // UP

                T = TravelTime(upQueues[IndexValid], IndexValid, c);

            ans =0;
            for(int i=IndexValid+1; i< elevNum; i++){
                State = _building.getElevetor(i).getState();
                t = TravelTime(upQueues[i],i,c );  // returnes the traveltime between floors i and c
                 if(t < T && State != ERROR){ ans =i; T=t;}
            }

            upQueues[ans].add(c);// queued the call in the queue
        }
        else{   // DOWN

            T = TravelTime(downQueues[IndexValid],IndexValid,c);

            ans =0;
            for (int i=IndexValid+1; i<elevNum; i++){
                t=TravelTime(downQueues[i],i,c);
                if(t < T){ans =i; T=t;}
            }
            downQueues[ans].add(c);
        }
       return ans;
    }
    private double TravelTime(PriorityQueue<CallForElevator> queue, int Elev, CallForElevator c){
       // case not handeled: if the elevator is above the call and goes up
        double ans=0;
       Iterator<CallForElevator> iterator =queue.iterator();
       if(!iterator.hasNext()){     // if the queue is empty
           ans = dt(Elev,c);
        return ans;
    }
       CallForElevator CurrentCall =  iterator.next();
       CallForElevator LastCall = CurrentCall;
       // with the assumption that the elevator is higher than all the calls given
       if(c.getSrc() > CurrentCall.getSrc() && c.getSrc() > c.getDest()){ // DOWN
            return dt(Elev,c);
       }
       // with the assumption that the levator is lower than all the calls given
        if(c.getSrc() < CurrentCall.getSrc() && c.getSrc() < c.getDest()){ // UP
            return dt(Elev,c);
        }
        while(iterator.hasNext()) {
            ans = ans + dt(Elev,LastCall, CurrentCall);
            LastCall = CurrentCall;
            CurrentCall =  iterator.next();
            // with the assumption that the elevator is higher than all the calls given
            if(c.getSrc() > CurrentCall.getSrc() && c.getSrc() > c.getDest()){ // DOWN
                return dt(Elev,LastCall,c);
            }
            // with the assumption that the levator is lower than all the calls given
            if(c.getSrc() < CurrentCall.getSrc() && c.getSrc() < c.getDest()){ // UP
                return dt(Elev,LastCall,c);
            }
        }
        ans = ans + dt(Elev,CurrentCall,c);

       return ans;
    }
    // calculates the time for the elevator to travel between its current position and a call
    private double dt (int Elev, CallForElevator c){
        int ElevPos = _building.getElevetor(Elev).getPos();
        double ElevSpeed,CloseTime,OpenTime,StartTime,StopTime;
        ElevSpeed = _building.getElevetor(Elev).getSpeed();
        CloseTime = _building.getElevetor(Elev).getTimeForClose();
        OpenTime = _building.getElevetor(Elev).getTimeForClose();
        StartTime = _building.getElevetor(Elev).getStartTime();
        StopTime = _building.getElevetor(Elev).getStopTime();

        return ((Math.abs(ElevPos - c.getSrc())) / ElevSpeed + StartTime + StopTime + OpenTime + CloseTime);

    }
    // calculates the time takes for the elevator to travel between two adjacent calls
    private double dt(int Elev, CallForElevator LastCall, CallForElevator CurrentCall){
        double ElevSpeed,CloseTime,OpenTime,StartTime,StopTime;
        ElevSpeed = _building.getElevetor(Elev).getSpeed();
        CloseTime = _building.getElevetor(Elev).getTimeForClose();
        OpenTime = _building.getElevetor(Elev).getTimeForClose();
        StartTime = _building.getElevetor(Elev).getStartTime();
        StopTime = _building.getElevetor(Elev).getStopTime();
        if(LastCall == CurrentCall) return 0;
        return Math.abs(CurrentCall.getSrc() - LastCall.getSrc()) / ElevSpeed + StartTime + StopTime + OpenTime + CloseTime;
    }
    @Override
    public void cmdElevator(int elev) {
       Elevator elevator = _building.getElevetor(elev);
       int State = elevator.getState();
        if(State == ERROR && _building.numberOfElevetors() >= 1){
            if(-1==RepairError(elev)) System.out.println("NO ELEVATORS ARE VALID");
            // would love to throw an exception
            return;
        }

       int dir = Direction[elev];
       int sizeUp=upQueues[elev].size(),sizeDown=downQueues[elev].size();
        if(State == LEVEL && sizeUp ==0 && sizeDown==0) {
            Direction[elev] = 0; dir=0;
        }
       if(dir ==0){ // elevator has no direction can go up or down
           if(sizeUp == 0 && sizeDown >0){  // take the elevator to up or down if one queue has no waiters
               dir = DOWN; Direction[elev] = DOWN;
           }
           else if(sizeUp >0 && sizeDown ==0){
               dir = UP; Direction[elev] = UP;
           }
           else if (sizeUp >0 && sizeDown >0){  // take the elevator to where there are more waiters up or down
                if(sizeUp < sizeDown) {
                    dir = DOWN;
                    Direction[elev] = DOWN;
                }
                else {
                    dir = UP;
                    Direction[elev] = UP;
                }
                }
       }
       if(dir == DOWN && State == LEVEL && downQueues[elev].size() != 0){

            CallForElevator c = downQueues[elev].poll();
            elevator.goTo(c.getSrc());

        }
       else if(dir == UP && State == LEVEL && upQueues[elev].size() != 0){
           CallForElevator c = upQueues[elev].poll();
           elevator.goTo(c.getSrc());
       }

    }
    private int RepairError(int elev){ // function provided to move all the calls from a bugged elevator
       if(_building.numberOfElevetors() == 1)
           return -1;
       while(upQueues[elev].size() >0){
            int flag = allocateAnElevator(upQueues[elev].poll());
            if(flag == -1) // no available elevators
                return -1;

        }
        while(downQueues[elev].size() >0){
            int flag = allocateAnElevator(downQueues[elev].poll()); // finds an elevator to take care of the call
            if(flag == -1)  // no available elevators
                return -1;
        }
       return 0;
    }

}
