package pt.ulusofona.lp2.deisiGreatGame;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestesUnitarios {
    @Test
    public void testeToString() {
        ArrayList<String> a = new ArrayList<>();
        ArrayList<String> b = new ArrayList<>();
        a.add("Java");
        a.add("C++");
        a.add("C");
        Programmer programmer = new Programmer("Ze", 1, ProgrammerColor.BLUE, a, 1);
        //Programmer programmer1 = new Programmer("alex", 2, ProgrammerColor.PURPLE, b, 5);
        assertEquals(" TESTE ", programmer.toString() );

    }
}
