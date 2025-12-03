package Exercises;
/*
Using the String, StringBuilder, and StringBuffer, create an application to present 
the performance difference between the three. How you handle the test and present 
it should be all done within the Java Console, but you are free to test it how you like.
*/

public class myStringTesting {
    public static void main(String[] args){
        //Objective: Create a string concatonating numbers 0-99 

        String myStr = "";

        //String
        myString(myStr);

        //StringBuilder
        myStringBuilder(myStr);

        //StringBuffer
        myStringBuffer(myStr);
        
    }

    public static void myString(String str){
        System.out.println("----------Using String----------");

        long startTime = System.nanoTime();
        for (int i = 0; i < 100; i++){
            str += String.valueOf(i);
        }
        long endTime = System.nanoTime();
        long time = endTime - startTime;

        System.out.println(str);
        System.out.println("Time for String: " + time);
        System.out.println();
    }

    public static void myStringBuilder(String str){
        System.out.println("-------Using StringBuilder-------");
        
        long startTime = System.nanoTime();
        StringBuilder sb = new StringBuilder(str);

        for (int i = 0; i < 100; i++){
            sb.append(i);
        }
        long endTime = System.nanoTime();
        long time = endTime - startTime;

        System.out.println(sb);
        System.out.println("Time for StringBuilder: " + time);
        System.out.println();
    }

    public static void myStringBuffer(String str){
        System.out.println("-------Using StringBuffer-------");

        long startTime = System.nanoTime();
        StringBuffer sbf = new StringBuffer(str);

        for (int i = 0; i < 100; i++){
            sbf.append(i);
        }
        long endTime = System.nanoTime();
        long time = endTime - startTime;

        System.out.println(sbf);
        System.out.println("Time for StringBuffer: " + time);
        System.out.println();
    }
}
