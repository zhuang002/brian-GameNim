/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamenim;

import java.util.Random;
import java.util.Scanner;


/**
 *
 * @author zhuan
 */
public class GameNim {

    /**
     * @param args the command line arguments
     */
    private static Scanner sc=new Scanner(System.in); // declear scanner in class scope to be shared by all methods;
    private static Random rand=new Random(); // declear the random object to be reused by all methods.
    public static void main(String[] args) {
        // TODO code application logic here
        
        int n=rand.nextInt(16)+15;
        startNimGame(n);
    }

    /**
     * Start the NIM game with n stones.
     * @param n The number of stones to start with.
     */
    private static void startNimGame(int n) {
        while (n>0) { // when the number of left stones are not 0, we continue to play the game.
            n=playOneRound(n);
        }
    }

    /**
     * Play one round. The user first, then the computer.
     * @param n the number of left stones for this round.
     * @return the number of left stones after this round.
     */
    private static int playOneRound(int n) {
        n=userRound(n);
        if (n==0) {
            // the user takes the last stone.
            System.out.println("The computer beats the player.");
        }
        else {
            n=computerRound(n);
            if (n==0) {
                // the computer takes the last stone
                System.out.println("The player beats the computer.");
            }
        }
        return n;
    }

    /**
     * The user round
     * @param n the number of left stones before the user takes.
     * @return the number of left stones after the user takes.
     */
    private static int userRound(int n) {
        if (n==0) return 0; // validate the input parameter.
        int a=0; // set the initial a to be invalid.
        boolean isInputValid=false;
        while (!isInputValid) {
            try {
                System.out.printf("There are %d stones. How many would you like?",n);
                a=sc.nextInt();
                if (n>=3) // if left stones are more than 3, the user can take 1~3
                    isInputValid = (a>=1 && a<=3);
                else  // if left stones are less than 3, the user can take 1~n
                    isInputValid = (a>=1 && a<=n);
            }
            catch (Exception e) {
                // capture the error input that is not an integer.
                sc.next(); // skip the wrong input and allow user to input again.
                isInputValid=false;
            }
            if (!isInputValid) {
                System.out.printf("You input is not valid. Please input integer from 1 to %d.\r\n", (n>=3)?3:n);
            }
        }
        return n-a;
    }

    private static int computerRound(int n) {
        int a;
        if (n>=3) // if there are more than 3 stones, the computer can take 1,2 or 3 stones.
            a = rand.nextInt(3)+1;
        else // if there are less than 3 stones, the computer can take at most n stones.
            a= rand.nextInt(n)+1;
        System.out.printf("There are %d stones. The computer takes %d stones\r\n",n,a);
        return n-a;
    }
    
}
