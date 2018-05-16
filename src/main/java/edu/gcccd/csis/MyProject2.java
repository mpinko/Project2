package edu.gcccd.csis;

import java.io.*;
import java.util.Iterator;

public class MyProject2 implements Project2 {

    private static NodeList<Integer> toReverse;
    private final int BASE = 10;

    public static NodeList<Integer> reverse(Iterator<Integer> input){
        toReverse = new NodeList<>();
        while(input.hasNext()){
            int temp = input.next();
            reverse(input);
            toReverse.append(temp);
        }
        return toReverse;
    }


    //Overview of how many times next() is called in addition(NodeList<Integer> nodeList1, NodeList<Integer> nodeList2)
    //We originally generate 2 nodeLists, which contain large numbers.  After we reverse
    //the numbers, we make the lists the same length, so we must consider the longer of the
    //2 original NodeLists while doing big O analysis.
    //While calculating the sum of 2 nodes, iterators' next method is called twice, or 2n.
    //It is called for every node in the value, so L times.
    //so, for just the basic addition class, the method runs in O(2n)
    //We can simplify this by dropping the coefficient, and say this method runs in
    //O(n)
    //next() is also called when removing zeroes from the sum, which will be called L-l times,
    //with L being the length of the larger NodeList, and l being the length of the smaller NodeList
    //meaning our total O(n) can be written as
    //O(n) = 2n + (L-l)
    //simplified O(n) = n
    @Override
    public NodeList<Integer> addition(NodeList<Integer> nodeList1, NodeList<Integer> nodeList2) {
        NodeList<Integer> toReturn = new NodeList<>();
        NodeList<Integer> reversed1 = reverse(nodeList1.iterator());
        NodeList<Integer> reversed2 = reverse(nodeList2.iterator());
        while(reversed1.getLength() != reversed2.getLength()){
            if(reversed1.getLength() > reversed2.getLength()){
                reversed2.append(0);
            }
            else if(reversed2.getLength() > reversed1.getLength()){
                reversed1.append(0);
            }
        }
        //nodelists should be backwards and the same length, use quotient remainder theorem and modular artimetic w base 10
        final Iterator<Integer> i1 = reversed1.iterator();
        final Iterator<Integer> i2 = reversed2.iterator();
        int toCarry = 0;
        int samePlace;
        while(i1.hasNext()){
            samePlace = 0;
            int sum = toCarry + i1.next() + i2.next();
            if(sum >= BASE) { //in base 10, change for different bases
                toCarry = sum / BASE;
                samePlace = sum % BASE;
            }
            else{
                toCarry = 0;
                samePlace = sum;
            }
            toReturn.append(samePlace);
        }
        //leftovers
        if(toCarry != 0)
            toReturn.append(toCarry);
        toReturn = reverse(toReturn.iterator());
        if(toReturn.getLength() > 1 && toReturn.iterator().next() == 0)
            toReturn.remove(toReturn.iterator().next());
        return toReturn;
    }

    //Big O analysis for addition(Iterator<NodeList<Integer>> iterator)
    //this method calls the other addition method, which means it will be some modification
    //of our other O(n), which is O(n) = n;
    //this method called the other addition method for every NodeList in the Iterator,
    //meaning it will be called n times, with n = number of NodeList in the Iterator.
    //This means that our final O(n) value will be
    //O(n) = n^2, which means it runs in quadratic time.
    @Override
    public NodeList<Integer> addition(Iterator<NodeList<Integer>> iterator) {
        NodeList<Integer> toReturn = new NodeList<>();
        while(iterator.hasNext())
        {
            NodeList<Integer> currentNode = iterator.next();
            toReturn = addition(toReturn, currentNode);
        }
        return toReturn;
    }

    @Override
    public void save(NodeList<Integer> nodeList, String fileName) {
        try{
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileName));
            final Iterator<Integer> i = nodeList.iterator();
            while(i.hasNext()){
                stream.write(i.next());
            }
            stream.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public NodeList<Integer> load(String fileName) {
        NodeList<Integer> toReturn = new NodeList<>();
        try{
            BufferedInputStream stream = new BufferedInputStream(new FileInputStream(fileName));
            while(stream.available() > 0){
                toReturn.append(stream.read());
            }
            stream.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return toReturn;
    }



    public static void main(final String[] args) {
        final int L = 30;

        NodeList<Integer> n1 = Project2.generateNumber(L); // (head 1st) e.g. 3457
        final NodeList<Integer> n2 = Project2.generateNumber(L); // (head 1st) e.g. 682

        final Project2 p = new MyProject2();

        Project2.print(p.addition(n1, n2)); //  n1+n2, e.g. 4139

        final NodeList<NodeList<Integer>> listOfLists = new NodeList<>();
        for (int i = 0; i < L; i++) {
            listOfLists.append(Project2.generateNumber(L));
        }
        p.save(p.addition(listOfLists.iterator()), "result.bin");
        Project2.print(p.load("result.bin"));
    }
}