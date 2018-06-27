import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Danushka
 */
public class ZigZag {
    private static int[] subseaquence; //Memoization calculated results in an array

    private static int setlowestHigh(int[][] array, int index){ //get the lowest value which is higher than given index value
        int currentval=Integer.MIN_VALUE;
        int currentindex=-1;
        for (int i=index;i<array.length;i++){
            if(array[i][0]>currentval){
                if(currentindex==-1){
                    currentval=array[i][0];
                    currentindex=i;
                }else if(currentval<array[i][0]){
                    currentval=array[i][0];
                    currentindex=i;
                }
            }
        }
        return currentindex;
    }

    private static int sethighesttLow(int[][] array, int index){ //get the Highest value which is lower than given index value
        int currentval=Integer.MAX_VALUE;
        int currentindex=-1;
        for(int i=index;i<array.length;i++){
            if(array[i][0]<currentval){
                if(currentindex==-1){
                    currentval=array[i][0];
                    currentindex=i;
                }else if(currentval>array[i][0]){
                    currentval=array[i][0];
                    currentindex=i;
                }
            }
        }
        return currentindex;
    }

    private static void checkerror(int[][] values,int index){   //return the mismatch point when a 2D array and a index is given
        int previous=values[index-1][1];
        int breakpoint=-1;
        for (int i=index;i<values.length;i++){ //Recurse from the given index
            if(previous==0){
                if(values[i][1]==0){
                    breakpoint=i;
                    break;
                }else{
                    previous=1;
                    subseaquence[i]=values[i][0];
                }
            }else{
                if(values[i][1]==1){
                    breakpoint=i;
                    break;
                }else{
                    previous=0;
                    subseaquence[i]=values[i][0];
                }
            }
        }
        if(breakpoint!=-1){ //if Breakpoint is met
            int newindex=-1;
            if(values[breakpoint][1]==1){
                newindex=sethighesttLow(values,breakpoint);
                if(newindex!=breakpoint) { //if Breakpoint is not same as the new index
                    subseaquence[breakpoint] = values[newindex][0];
                    values[newindex][1]=0;
                }
            }else{
                newindex=setlowestHigh(values,breakpoint);
                if(newindex!=breakpoint) {
                    subseaquence[breakpoint]=values[newindex][0];
                    values[newindex][1] = 1;
                }
            }
            if(newindex!=values.length-1&&newindex!=breakpoint) {
                checkerror(values, newindex); // Call the recursive function
            }
        }
    }

    private static int[][] checker(int[] values){   //Set Values for the 2D Array
        int[][] arr=new int[values.length][2];
        for(int i=1;i<values.length;i++){
            arr[i][0]=values[i];
            if(values[i]-values[i-1]>0){
                arr[i][1]=1;
            }else{
                arr[i][1]=0;
            }
        }
        return arr;
    }

    private static int[] setinput(String[] arr){  //String array to Int array
        int[] inputs=new int[arr.length];
        for (int j=0;j<arr.length;j++){
            inputs[j]=Integer.parseInt(arr[j]);
        }
        return inputs;
    }

    private static int getsame(int[] values){  //check same valued inputs and get a count of them
        int previous=-1;
        int count=0;
        for(int num:values){
            if(previous==-1&&num!=0){
                previous=num;
            }else{
                if(num!=0) {
                    if (previous == num) {
                        count++;
                        previous = num;
                    } else {
                        previous = num;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String args[]){ //Driver Method
        Scanner inputter=new Scanner(System.in);
        // Number of test Cases
        System.out.println("Enter the Number of Test Case: ");
        int numberofCases=inputter.nextInt();
        for(int i=0;i<numberofCases;i++){
            System.out.println("Number of elements per test case: ");
            int inputSize=inputter.nextInt(); //number of inputs per test case

            inputter.nextLine(); //input value list
            System.out.println("Enter the Element List: ");
            String input1=inputter.nextLine();
            String[] separted=input1.split("\\s+");

            int[] values=setinput(separted); //Get Inputs into Integer Array

            int[][] genArray=checker(values); //2D array Value

            subseaquence=new int[values.length];
            subseaquence[0]=values[0];
            checkerror(genArray,1); //calling recursive Algorithm

            int count=0;
            for(int k:subseaquence){
                if(k!=0) {
                    count++;
                    System.out.print(k + " ");
                }
            }
            int sameinputs=getsame(subseaquence);
            System.out.println("\nLongest Sub-Sequence "+(count-sameinputs));
        }
    }
}
