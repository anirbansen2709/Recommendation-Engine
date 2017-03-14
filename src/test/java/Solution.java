import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = 200000000;
        int c = 20;
        int temp,sum=0;
        int[][] crate = new int[c][2];

       crate[0][0]= 15450824;
       crate[1][0]= 839717;
       crate[2][0]= 260084;
       crate[3][0]= 1140850;
       crate[4][0]= 28744;
       crate[5][0]= 675318;
       crate[6][0]= 25161;
       crate[7][0]= 5487;
       crate[8][0]= 6537698;
       crate[9][0]= 100000000;
       crate[10][0]= 7646970;
       crate[11][0]= 16489;
       crate[12][0]= 24627;
       crate[13][0]= 1009409;
       crate[14][0]= 22455;
       crate[15][0]= 25488456;
       crate[16][0]= 484528;
       crate[17][0]= 32663641;
       crate[18][0]= 750968;
       crate[19][0]= 5152;

        crate[0][1]= 5;
        crate[1][1]= 10;
        crate[2][1]= 8;
        crate[3][1]= 8;
        crate[4][1]= 6;
        crate[5][1]= 3;
        crate[6][1]= 2;
        crate[7][1]= 3;
        crate[8][1]= 9;
        crate[9][1]= 5;
        crate[10][1]= 9;
        crate[11][1]= 6;
        crate[12][1]= 3;
        crate[13][1]= 5;
        crate[14][1]= 1;
        crate[15][1]= 4;
        crate[16][1]= 9;
        crate[17][1]= 3;
        crate[18][1]= 4;
        crate[19][1]= 6;




        for(int crate_i=0; crate_i < c; crate_i++){
            System.out.println(crate[crate_i][0]+" "+crate[crate_i][1]);
        }
        System.out.println(" ");
        for(int crate_i=0; crate_i < c-1; crate_i++){
            for(int crate_j=0; crate_j <c-crate_i-1; crate_j++){
                if(crate[crate_j][1]<crate[crate_j+1][1]){
                    temp=crate[crate_j][0];
                    crate[crate_j][0]=crate[crate_j+1][0];
                    crate[crate_j+1][0]=temp;
                    temp=crate[crate_j][1];
                    crate[crate_j][1]=crate[crate_j+1][1];
                    crate[crate_j+1][1]=temp;
                }
            }
        }
        for(int crate_i=0; crate_i < c; crate_i++){
            System.out.println(crate[crate_i][0]+" "+crate[crate_i][1]);
        }

        int crate_no=0;
        int x=0;
        int value=crate[0][0];
        while(crate_no<n)
        {

            sum+=crate[x][1];
            crate_no++;
            value--;
            if(value==0&&x<c-1)
            {
                x++;
                value=crate[x][0];
            }

        }
        System.out.println(sum);

    }
}
