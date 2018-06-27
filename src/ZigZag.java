import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Danushka
 */
public class ZigZag {
    private static int[] subseaquence;

    private static int setlowestHigh(int[][] array, int index){
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

    private static int sethighesttLow(int[][] array, int index){
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
        //System.out.println("index:  "+currentindex+" current val: "+currentval);
        return currentindex;
    }

    //return the mismatch point when a 2D array and a index is given
    private static void checkerror(int[][] values,int index){
        int previous=values[index-1][1];
        int breakpoint=-1;
        for (int i=index;i<values.length;i++){
            if(previous==0){
                if(values[i][1]==0){
                    breakpoint=i;
                    break;
                }else{
                    previous=1;
                    //System.out.println(values[i][0]);
                    subseaquence[i]=values[i][0];
                }
            }else{
                if(values[i][1]==1){
                    breakpoint=i;
                    //System.out.println("Done");
                    break;
                }else{
                    previous=0;
                    //System.out.println(values[i][0]);
                    subseaquence[i]=values[i][0];
                }
            }
        }
        if(breakpoint!=-1){
            //System.out.println("BP= "+breakpoint);
            int newindex=-1;
            if(values[breakpoint][1]==1){
                newindex=sethighesttLow(values,breakpoint);
                //System.out.println(values[newindex][0]+" "+values[breakpoint][0]);
                //System.out.println("new Index= "+newindex);
                if(newindex!=breakpoint) {
                    subseaquence[breakpoint] = values[newindex][0];
                    values[newindex][1]=0;
                }

            }else{
                newindex=setlowestHigh(values,breakpoint);
                //System.out.println("new Index= "+newindex);
                if(newindex!=breakpoint) {
                    //System.out.println(values[newindex][0]+" "+values[breakpoint][1]);
                    subseaquence[breakpoint]=values[newindex][0];
                    values[newindex][1] = 1;
                }

            }
            if(newindex!=values.length-1&&newindex!=breakpoint) {
                checkerror(values, newindex);
            }
        }
    }

    //Set Values for the 2D Array
    private static int[][] checker(int[] values){
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

    //String array to Int array
    private static int[] setinput(String[] arr){
        int[] inputs=new int[arr.length];
        for (int j=0;j<arr.length;j++){
            inputs[j]=Integer.parseInt(arr[j]);
        }
        return inputs;
    }

    //check same valued inputs and get a count of them
    private static int getsame(int[] values){
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

    public static void main(String args[]){

        Scanner inputter=new Scanner(System.in);
        // Number of test Cases
        int numberofCases=inputter.nextInt();

        for(int i=0;i<numberofCases;i++){
            //number of inputs per test case
            int inputSize=inputter.nextInt();

            //input value list
            inputter.nextLine();
            String input1=inputter.nextLine();
            String[] separted=input1.split("\\s+");

            //Get Inputs into Integer Array
            int[] values=setinput(separted);

            //2D array Value
            int[][] genArray=checker(values);

            //for(int[] k:genArray) System.out.println(Arrays.toString(k));

            subseaquence=new int[values.length];
            subseaquence[0]=values[0];
            checkerror(genArray,1);

            int count=0;
            for(int k:subseaquence){
                if(k!=0) {
                    count++;
                    System.out.print(k + " ");
                }
            }

            int sameinputs=getsame(subseaquence);
            //System.out.println(sameinputs);
            System.out.println("\nLongest Sub-Sequence "+(count-sameinputs));
        }
    }
}
