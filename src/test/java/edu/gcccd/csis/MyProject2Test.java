package edu.gcccd.csis;

import org.junit.Test;
import sun.misc.resources.Messages_ja;

import java.math.BigInteger;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MyProject2Test {

    private static BigInteger genBigInteger(final NodeList<Integer> nodeList) {
        final StringBuilder sb = new StringBuilder();
        for (final int i : nodeList) {
            sb.append(i);
        }
        return new BigInteger(sb.toString());
    }

    @Test
    public void testReverse(){
        NodeList<Integer> toTestNode = Project2.generateNumber(30);
        BigInteger toTestInt = genBigInteger(toTestNode);

        Iterator<Integer> toTestIterator = toTestNode.iterator();
        NodeList<Integer> reversedToTest = MyProject2.reverse(toTestIterator);
        BigInteger reversedToTestInt = genBigInteger(reversedToTest);
        assertNotEquals(toTestInt, reversedToTestInt);

        Iterator<Integer> reversedIterator = reversedToTest.iterator();
        NodeList<Integer> x2Reversed = MyProject2.reverse(reversedIterator);
        BigInteger x2ReversedInt = genBigInteger(x2Reversed);
        assertEquals(toTestInt, x2ReversedInt);
    }

    @Test
    public void testAddition(){
        NodeList<Integer> testNode1 = Project2.generateNumber(30);
        NodeList<Integer> testNode2 = Project2.generateNumber(30);

        BigInteger testInt1 = genBigInteger(testNode1);
        BigInteger testInt2 = genBigInteger(testNode2);

        NodeList<Integer> sumNode = new MyProject2().addition(testNode1, testNode2);
        BigInteger sumInt = testInt1.add(testInt2);

        BigInteger sumToInt = genBigInteger(sumNode);
        assertEquals(sumToInt, sumInt);
    }

    @Test
    public void testListAddition(){
        NodeList<NodeList<Integer>> listOfLists = new NodeList<>();
        NodeList<Integer> n1 = Project2.generateNumber(30);
        NodeList<Integer> n2 = Project2.generateNumber(30);
        NodeList<Integer> n3 = Project2.generateNumber(30);

        listOfLists.append(n1);
        listOfLists.append(n2);
        listOfLists.append(n3);

        Iterator<NodeList<Integer>> listIterator = listOfLists.iterator();
        NodeList<Integer> listSum = new MyProject2().addition(listIterator);
        BigInteger nodeSum = genBigInteger(listSum);

        BigInteger i1 = genBigInteger(n1);
        BigInteger i2 = genBigInteger(n2);
        BigInteger i3 = genBigInteger(n3);
        BigInteger sum1 = i1.add(i2);
        BigInteger finalSum = sum1.add(i3);

        assertEquals(finalSum, nodeSum);
    }

    @Test
    public void testSave(){

    }

    @Test
    public void testLoad(){

    }
}