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
        Call call0 = new Call(2,0,0);
        calls.add(call0);

    }

    public Call getByIndex(int index){
        if(index < 0 || index > calls.size()) return null;

        return calls.get(index);
    }
    // returns the first call which has flag of 0 or 1
    public Call getFirstCall(){
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
        int size=0;
        while(this.calls.size() > size){
            if(this.calls.get(size) == null)
                break;
            size+=1;
        }
        return size;
    }
    public void add(Call call){

        this.calls.add(call);
    }
   public void remove(int index){
        if(getByIndex(index).CheckFlag()) {
            calls.remove(index);
        }
   }
   // hasNext is implemented by getSize, since Size counts how many there are and by index we can assume we have more
    public Call NextCall(Call current){
    int indexCurrent = calls.indexOf(current);
    int callSize = getSize();
    if(indexCurrent+1 <getSize() || indexCurrent < calls.size()){
        return null;
    }
    Call nextCall = calls.get(indexCurrent+1);
    while(indexCurrent +1 < getSize()){
        if(nextCall.getFlag() == 2){
            indexCurrent+=1;
            nextCall = calls.get(indexCurrent);
        }
        else{
            break;
        }
    }
    return nextCall;
    }
    public int IndexOf(Call call){
        return calls.indexOf(call);
    }
    // adds a specific call in index
    public void add(int index, Call call){
        calls.add(index,call);
    }
}
