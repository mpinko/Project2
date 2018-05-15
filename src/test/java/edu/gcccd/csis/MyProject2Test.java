package edu.gcccd.csis;

import org.junit.Test;
import sun.misc.resources.Messages_ja;

import java.math.BigInteger;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class MyProject2Test {

    @Test
    public void testReverse(){
        NodeList<Integer> toTest = Project2.generateNumber(10);
        Iterator<Integer> testing = toTest.iterator();
        NodeList<Integer> copy = MyProject2.reverse(testing);
        Project2.print(copy);
    }

    @Test
    public void testAddition(){

    }

    @Test
    public void testListAddition(){

    }

    @Test
    public void testSave(){

    }

    @Test
    public void testLoad(){

    }
}
