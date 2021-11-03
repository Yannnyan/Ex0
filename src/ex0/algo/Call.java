package ex0.algo;

import ex0.CallForElevator;

public class Call {
    private int[] call; // a call contains a flag [0] a source [1] and a destination [2]
    // flag 0 = nothing done, flag 1 = elevator reached source, flag 2 = elevator reached destination.
    // flag 2 usually means delete this call from the list of calls.
    public Call(CallForElevator c){
        int src = c.getSrc();
        int dest = c.getDest();
        this.call = new int[3];
        call[0] = 0;
        call[1] = src;
        call[2] = dest;
    }
    public Call(int flag, int src, int dest){
        this.call = new int[3];
        call[0] = flag;
        call[1] = src;
        call[2] = dest;
    }
    public int getFlag(){
        return call[0];
    }
    public int getSrc(){
        return call[1];
    }
    public int getDest(){
        return call[2];
    }
    // this method checks that the call should or should not be deleted
    public boolean CheckFlag(){
        if(getFlag() == 2){
            return true;
        }
        else{
            return false;
        }
    }
    // this method changed the current call flag to 1
    public void ReachedSource(){
        this.call[0] = 1;
    }
    //this method changes the current call flag to 2
    public void ReachedDest(){
        this.call[0] = 2;
    }
}
