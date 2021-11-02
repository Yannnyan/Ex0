package ex0.algo;
import ex0.CallForElevator;

import java.util.Iterator;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Calls {

    ArrayList<Call> calls; // Contains all the calls to elevators in the building.
    // notice that calls[0] refers to the calls for elevator 0
    // this class controls all the calls to elevators and connects the calls to the elvators.


    public Calls(){
        calls = new ArrayList<Call>();
        Call call0 = new Call(0,0,0);
        calls.add(call0);
    }
    public Call getByIndex(int index){
        if(index <0) return null;

        return calls.get(index);
    }
    public Call getFirstCall(){ // returns the first call which has flag of 0 or 1
        for(int i=0; i< calls.size(); i++){
            Call call = calls.get(i);
            if(call.getFlag() == 0 || call.getFlag() == 1){
                return call;
            }
            else{   // if the call has a flag of 2 its already handled no need to keep it
                calls.remove(i);
            }
        }
        return null;
    }
    public int getSize(){
        Iterator<Call> iterator = calls.iterator();
        int size =0;
        while(iterator.hasNext()){
            size+=1;
        }
        return size;
    }
    public void add(Call call){

        this.calls.add(call);
    }
   public void remove(int index){
        calls.remove(index);
   }

    // this method returns Source elevator hasn't reached its source or Destination if it has reached its source
    // warning, need to check flag is not 2 since then the call should be deleted!
    public int getFloor(int index){
        Call call = calls.get(index);
        if(call.getFlag() == 0){
            return call.getSrc();
        }
        else {
            return call.getDest();
        }

    }

}
