import java.util.Random;
import java.util.Scanner;

public class GuessGame {
    public void playMode(Scanner scanner, int attempt,int maxNumber ){
        Random rand = new Random();
        int target = rand.nextInt(maxNumber) + 1;
        
        while (attempt > 0) { 
            System.out.println("Enter your guess: ");
            int yourGuess = scanner.nextInt();
            if(yourGuess >  target){
                attempt--;
                System.out.println("Too high you only have " + attempt + " attempts left");
            }
            else if(yourGuess < target){
                attempt--;
                System.out.println("Too low you only have " + attempt + " attempts left");
            }
            
            else{
                System.out.println("You won that's correct your score is: " + attempt * 10);
                break;
            }
            if(Math.abs(yourGuess - target) <= 5){
                    System.out.println("Very close!");
            }
        }
        if(attempt == 0){
            System.out.println("You lost! The number was: " + target);
        }

    }
    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);
        GuessGame  game = new GuessGame();
        
        while(true){
            System.out.println("1. Easy\n2. Medium\n3.Hard");
            System.out.println("Choose The Diffucluty of Game");
            int Choice = scanner.nextInt();
            if(Choice == 1){
                System.out.println("Range of number is around 0  to 50");
                game.playMode(scanner,10,50);
            }
            else if(Choice == 2){
                System.out.println("Range of number is around 0 to 100");
                game.playMode(scanner,7,100);
            }
            else if(Choice == 3){
                System.out.println("Range of number is around 0 to 200");
                game.playMode(scanner,5,200);
            }
            else{
                System.out.println("Entered wrong number Enter again");
                continue;
            }
            System.out.println("Wanna continue y/n");
            scanner.nextLine();
            String play = scanner.nextLine();
            if(play.equalsIgnoreCase("n")){
                break;
            } 

        }
        scanner.close();
    
    }
}


