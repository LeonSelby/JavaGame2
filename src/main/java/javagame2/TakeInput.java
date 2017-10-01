package javagame2;

import lombok.Data;

import java.util.InputMismatchException;
import java.util.Scanner;
import static javagame2.Direction.*;

@Data
public class TakeInput {
    static public Scanner sc = new Scanner(System.in);

    //Input
    static private String takeNextLine() {
        return sc.nextLine();
    }

    static private int takeNextInt() {
        boolean badInput = true;
        int tmp = 0;
        while (badInput) {
            try {
                tmp = Integer.parseInt(sc.nextLine());
                badInput = false;
            } catch (InputMismatchException | NumberFormatException var3) {
                needIntPlease();
            }
        }
        return tmp;
    }

    static private int takeNextIntInRange(int min, int max) {
        boolean badInput = true;
        int tmp = 0;

        while (badInput) {
            try {
                tmp = Integer.parseInt(sc.nextLine());

                if (tmp > max || tmp < min) {
                    needIntInRange(min, max);
                    tmp = Integer.parseInt(sc.nextLine());
                } else
                    badInput = false;
            } catch (InputMismatchException var3) {
                needIntInRange(min, max);
            } catch (NumberFormatException var4) {
                needIntInRange(min, max);
            }
        }
        return tmp;
    }

    static public int requestInput(String question) {
        System.out.println(question);
        int ans = takeNextInt();
        return ans;
    }

    static public int requestInputInRange(String question, int min, int max) {
        System.out.println(question);
        int ans = takeNextIntInRange(min, max);
        return ans;
    }

    static public int requestInputInRange(String question, int max) {
        System.out.println(question);
        int ans = takeNextIntInRange(0, max);
        return ans;
    }

    static private String requestWASD(){
        boolean badInput = true;
        String tmp = "";
        while (badInput) {
            try {
                tmp = takeNextLine().toLowerCase();
            } catch (InputMismatchException var3) {
                needIntPlease();
            } catch (NumberFormatException var4) {
                needIntPlease();
            }
            if((!tmp.equals("w")) && !tmp.equals("s") && !tmp.equals("d") && !tmp.equals("a")){
                needWASD();
            }else{
                badInput= false;
            }
        }
        return tmp;
    }

    static public Direction requestMovementDirection(String question){
        Direction answer = null;
        System.out.println(question);
        String ans = requestWASD();
        if(ans.equalsIgnoreCase("w")){
            answer = Dir_NORTH;
        }else if( ans.equalsIgnoreCase("s")){
            answer = Dir_SOUTH;
        }else if( ans.equalsIgnoreCase("d")){
            answer = Dir_EAST;
        }else if( ans.equalsIgnoreCase("a")){
            answer = Dir_WEST;
        }
        return answer;
    }

    static public boolean requestYesOrNo(String question) {
        System.out.println(question);
        boolean isYes = false;
        boolean answerNotGiven = true;
        while (answerNotGiven) {
            switch (TakeInput.takeNextLine().toLowerCase()) {
                case "yes":
                case "y":
                    isYes = true;
                    answerNotGiven = false;
                    break;
                case "no":
                case "n":
                    isYes = false;
                    answerNotGiven = false;
                    break;
                default:
                    TakeInput.needYesOrNo();
                    System.out.println(question);
                    break;
            }
        }
        return isYes;
    }
    //Errors
    static private void needIntPlease() {
        System.out.println("We're sorry! Please input a number!");
    }

    static public void intTooHigh(int cap) {
        System.out.println("We're sorry that number is too high! Please give a number below "+cap+".");
    }

    static private void needIntInRange(int min, int max) {
        System.out.printf("We're sorry! Please input a number between " + min + " and " + max + ".");
    }

    static private void needYesOrNo() {
        System.out.println("We're sorry! Please enter yes or no, y or n is accepted also.");
    }
    static private void needWASD() {
        System.out.println("We're sorry! Please enter W, A, S, or D!");}

}
