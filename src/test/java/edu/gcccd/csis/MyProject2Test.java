package edu.gcccd.csis;

import org.junit.Test;

import java.io.*;
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

    private static NodeList<Integer> genNodeList(final String s){
        final NodeList<Integer> toReturn = new NodeList<>();
        for(final char c : s.toCharArray()){
            toReturn.append(Character.getNumericValue(c));
        }
        return toReturn;
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

        //corner case, empty lists
        NodeList<Integer> cc1 = new NodeList<>();
        NodeList<Integer> cc2 = new NodeList<>();
        NodeList<Integer> cc3 = new MyProject2().addition(cc1, cc2);

        //corner case, adding empty
        NodeList<Integer> cc4 = new NodeList<>();
        NodeList<Integer> cc5 = genNodeList("1234");
        NodeList<Integer> cc6 = new MyProject2().addition(cc4, cc5);
        BigInteger CC5 = genBigInteger(cc5);
        BigInteger CC6 = genBigInteger(cc6);
        assertEquals(CC5, CC6);
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
        MyProject2 p = new MyProject2();
        NodeList<Integer> n1 = Project2.generateNumber(30);
        NodeList<Integer> n2 = new NodeList<>();
        p.save(n1, "result.bin");
        try{
            InputStream stream = new FileInputStream("result.bin");
            while(stream.available() > 0){
                n2.append(stream.read());
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        BigInteger N1 = genBigInteger(n1);
        BigInteger N2 = genBigInteger(n2);
        assertEquals(N1, N2);
    }

    @Test
    public void testLoad(){
        NodeList<Integer> n1 = Project2.generateNumber(30);
        try{
            FileOutputStream stream = new FileOutputStream("result.bin");
            final Iterator<Integer> i = n1.iterator();
            while(i.hasNext()){
                stream.write(i.next());
            }
            stream.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        NodeList<Integer> n2 = new MyProject2().load("result.bin");
        BigInteger N1 = genBigInteger(n1);
        BigInteger N2 = genBigInteger(n2);
        assertEquals(N1, N2);
    }
}