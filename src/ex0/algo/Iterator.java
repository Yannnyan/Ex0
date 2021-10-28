package ex0.algo;
import ex0.Building;

public class Iterator implements java.util.Iterator {
    Building building;
    int index;
    Iterator(Building building){
        this.building = building;
        index=0;
    }
    @Override
    public boolean hasNext() {
        if(index < building.numberOfElevetors()){
            return true;
        }
        return false;
    }

    @Override
    public Object next() {
        return building.getElevetor(index++);
    }
}
