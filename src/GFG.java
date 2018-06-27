// Java program to find longest
// Zig-Zag subsequence in an array
import java.io.*;
 
class GFG {
 
// Function to return longest 
// Zig-Zag subsequence length
static int zzis(int arr[], int n)
{
    /*Z[i][0] = Length of the longest 
        Zig-Zag subsequence ending at
        index i and last element is 
        greater than its previous element
    Z[i][1] = Length of the longest 
        Zig-Zag subsequence ending at
        index i and last element is 
        smaller than its previous 
        element */
    int Z[][] = new int[n][2];
	String seq[][] = new String[n][2];
	seq[0][0] = seq[0][1] = arr[0] + " ";
 
    /* Initialize all values from 1 */
    for (int i = 0; i < n; i++) {
        Z[i][0] = Z[i][1] = 1;
	}
	
    int res = 1; // Initialize result
 
    /* Compute values in bottom up manner */
    for (int i = 1; i < n; i++) {
        // Consider all elements as 
        // previous of arr[i]
        for (int j = 0; j < i; j++) {
            // If arr[i] is greater, then 
            // check with Z[j][1]
            if (arr[j] < arr[i] && Z[i][0] < Z[j][1] + 1) {
                Z[i][0] = Z[j][1] + 1;
				seq[i][0] = seq[j][1]  + arr[i] + " ";
			}
            // If arr[i] is smaller, then
            // check with Z[j][0]
            if( arr[j] > arr[i] && Z[i][1] < Z[j][0] + 1) {
                Z[i][1] = Z[j][0] + 1;
				seq[i][1] = seq[j][0] + arr[i] + " ";
			}
        }
        /* Pick maximum of both values at
        index i */
        if (res < Math.max(Z[i][0], Z[i][1])) {
            res = Math.max(Z[i][0], Z[i][1]);
		}
    }
		for (int i=0; i<arr.length; i++) {
			if (res == Z[i][0])
				System.out.println(seq[i][0]);
			else if (res == Z[i][1])
				System.out.println(seq[i][1]);
		}
 
    return res;
}
 
/* Driver program */
public static void main(String[] args)
{
    int arr[] = {374, 40, 854, 203, 203, 156, 362, 279, 812, 955, 600, 947, 978, 46, 100, 953, 670,
862, 568, 188, 67, 669, 810, 704, 52, 861, 49, 640, 370, 908, 477, 245, 413, 109, 659,
401, 483, 308, 609, 120, 249, 22, 176, 279, 23, 22, 617, 462, 459, 244}
;
    int n = arr.length;
    System.out.println("Length of Longest "+
                    "Zig-Zag subsequence is " +
                    zzis(arr, n));
}
}