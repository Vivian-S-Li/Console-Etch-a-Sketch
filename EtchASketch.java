import java.util.*;
import java.io.*;

public class EtchASketch {

  public static final int BOARD_LENGTH = 20;

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int[][] board = new int[BOARD_LENGTH][BOARD_LENGTH];

    int currXY = 0;
    int userInput;
    char userChar;

    welcome();
    userChar = getUserChar(input);

    while (true) {
      userInput = getDirection(input);

      if (userInput == 100) {
        userChar = getUserChar(input);
      }

      currXY = updateXY(userInput, currXY, board);

      board = updateBoardArray(board, currXY);
      printBoard(board, userChar);
    }

  }

  public static void welcome() {
    System.out.println("\nVer. 1.0\n" +
                       "Welcome to Command Line EtchASketch!\n" +
                       "Enter \"A\", \"D\", \"W\", \"S\" for left, right, up," +
                       " and down respectively.\n" +
                       "Do not enter more than one direction at a time.\n" +
                       "Enter \"RESET\" to clear the board.\n" +
                       "Enter \"CHARACTER\" to change your character.\n" +
                       "Enter \"QUIT\" to quit.\n");
  }

  public static int getDirection(Scanner input) {
    String userInput;
    int dirVal;

    System.out.print("Input: ");
    userInput = input.nextLine().toUpperCase();

    while (!userInput.equals("A") &&
           !userInput.equals("D") &&
           !userInput.equals("W") &&
           !userInput.equals("S") &&
           !userInput.equals("RESET") &&
           !userInput.equals("QUIT") &&
           !userInput.equals("CHARACTER")) {
             System.out.print("Invalid input, please re-enter: ");
             userInput = input.nextLine().toUpperCase();
           }

    // if (userInput.equals("A")) {
    //   return(1);  //Left
    // } else if (userInput.equals("D")) {
    //   return(2);  //Right
    // } else if (userInput.equals("W")) {
    //   return(3);  //Up
    // } else if (userInput.equals("S")) {
    //   return(4);  //Down
    // } else if (userInput.equals("RESET")) {
    //   return(0);  //RESET
    // } else if (userInput.equals("QUIT")) {      //QUIT
    //   System.exit(0);
    //   return(-1);
    // } else {
    //   return(100);
    // }


    switch (userInput) {
      case "A":
        return 1;

      case "D":
        return 2;

      case "W":
        return 3;

      case "S":
        return 4;
    
      case "RESET":
        return 0;

      case "QUIT":
        System.exit(0);
        return(-1);

      default:
        return(100);
    }
  }

  public static int updateXY(int inputVal, int currXY, int[][] board) {
    if (inputVal == 1) {        //Left
      if (currXY / 100 == 0) {
        System.out.println("\n\nYou are already at the left-most column " +
                           "of the board. Please enter another direction.");
      }
      else {
        currXY = currXY - 100;
      }
    }
    else if (inputVal == 2) {   //Right
      if (currXY / 100 == BOARD_LENGTH - 1) {
        System.out.println("\n\nYou are already at the right-most column " +
                           "of the board. Please enter another direction.");
      }
      else {
        currXY = currXY + 100;
      }
    }
    else if (inputVal == 3) {   //Up
      if (currXY % 100 == 0) {
        System.out.println("\n\nYou are already at the top-most row " +
                           "of the board. Please enter another direction.");
      }
      else {
        currXY = currXY - 1;
      }
    }
    else if (inputVal == 4) {   //Down
      if (currXY % 100 == BOARD_LENGTH - 1) {
        System.out.println("\n\nYou are already at the bottom-most row " +
                           "of the board. Please enter another direction.");
      }
      else {
        currXY = currXY + 1;
      }
    }
    else if (inputVal == 0) {
      for (int i = 0; i < BOARD_LENGTH; i++) {
        for (int j = 0; j < BOARD_LENGTH; j++) {
          Arrays.fill(board[i], 0);
        }
      }
    }
    return(currXY);
  }

  public static int[][] updateBoardArray(int[][] board, int currXY) {
    int currX;
    int currY;

    currX = currXY / 100;
    currY = currXY % 100;
    System.out.println("\n"//\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n
                      + "(DEBUG) CurrX: " + currX + " CurrY: " + currY);           

    board[currY][currX] = 1;    //board[rows][columns]
                                //rows are Y and columns are X
    return(board);
  }

  public static char getUserChar(Scanner input) {
    char userChar;

    System.out.print("What character would you like to sketch with? ");

    userChar = input.nextLine().charAt(0);
    while (Character.isWhitespace(userChar)) {
      System.out.print("You must provide a value, re-enter: ");
      userChar = input.nextLine().charAt(0);
    }
    return(userChar);
  }


  public static void printBoard(int[][] board, char userChar) {
    System.out.print(" /");
    for (int i = 0; i < BOARD_LENGTH * 2; i++) {
      System.out.print("=");
    }
    System.out.print("\\\n");

    for (int i = 0; i < board.length; i++) {
      System.out.print("||");
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == 0) {
          System.out.print("  ");
        }
        else {
          System.out.print(userChar + " ");
        }
      }
      System.out.print("||");
      System.out.println();
    }

    System.out.print(" \\=");
    for (int i = 0; i < BOARD_LENGTH * 2 - 2; i++) {
      System.out.print("=");
    }
    System.out.print("=/\n");
  }

}
