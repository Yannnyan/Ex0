package ex0.algo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ex0.simulator.Call_A;
import ex0.simulator.Simulator_A;
import static org.junit.jupiter.api.Assertions.*;
import ex0.Building;

class MyBuildingTest {
    Building b1,b2,b3,b4;
    MultiElevatorAlgo MEA;

    @Test
    void minimalTime() {
        Simulator_A.initData(9,null); // (-10,100)
        b1 = Simulator_A.getBuilding();
        /*
        Simulator_A.initData(2,null);
        b2 = Simulator_A.getBuilding();
        Simulator_A.initData(3,null);
        b3 = Simulator_A.getBuilding();
        Simulator_A.initData(4,null);
        b4 = Simulator_A.getBuilding();
        */
        /*
        mb2 = new MyBuilding(b2);
        mb3 = new MyBuilding(b3);
        mb4 = new MyBuilding(b4);
        */
        MEA = new MultiElevatorAlgo(b1);
        MEA.getBuilding().getElevetor(0).goTo(99);
        MEA.getBuilding().getElevetor(1).goTo(98);
        MEA.getBuilding().getElevetor(2).goTo(97);
        MEA.getBuilding().getElevetor(3).goTo(96);
        MEA.getBuilding().getElevetor(4).goTo(95);
        MEA.getBuilding().getElevetor(5).goTo(94);
        MEA.getBuilding().getElevetor(6).goTo(93);
        MEA.getBuilding().getElevetor(7).goTo(92);
        MEA.getBuilding().getElevetor(8).goTo(91);
        MEA.getBuilding().getElevetor(9).goTo(0);

        Call_A call_a = new Call_A(2,10,12);
        Call_A call_b = new Call_A(3,20,10);
        Call_A call_c = new Call_A(4,30,10);
        Call_A call_d = new Call_A(5,20,50);
        Call_A call_e = new Call_A(6,90,-5);
        Call_A call_f = new Call_A(7,0,10);
        Call_A call_g = new Call_A(8,10,15);
        Call_A call_h = new Call_A(9,25,30);
        Call_A call_j = new Call_A(10,35,45);
        int a,b,c,d,e,f,g,h,j;
        a=MEA.allocateAnElevator(call_a);
        b=MEA.allocateAnElevator(call_b);
        c=MEA.allocateAnElevator(call_c);
        d=MEA.allocateAnElevator(call_d);
        e=MEA.allocateAnElevator(call_e);
        f=MEA.allocateAnElevator(call_f);
        g=MEA.allocateAnElevator(call_g);
        h=MEA.allocateAnElevator(call_h);
        j=MEA.allocateAnElevator(call_j);

        //assertEquals(a,);
        //assertEquals(b,);
        //assertEquals(c,);
        //assertEquals(d,);
        //assertEquals(e,);
        //assertEquals(f,);
        //assertEquals(g,);
        //assertEquals(h,);

    }
}