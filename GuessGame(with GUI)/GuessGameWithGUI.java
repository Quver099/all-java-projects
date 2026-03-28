import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.*;

public class GuessGameWithGUI {

    public static void main(String[] args){
        
        JFrame frame = new JFrame("Guessing Game with GUI");
        
        frame.setSize(400,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Random rand = new Random();
        final int[] target = { rand.nextInt(100) + 1 };
        AtomicInteger attempt = new AtomicInteger(5);

        JTextField input = new JTextField(10);
        JButton button = new JButton("Guess");
        JButton restartBtn = new JButton("Restart");
        JLabel label = new JLabel("Enter a number (You have 5  attempts)");

        button.addActionListener(e ->{
             try {
        int guess = Integer.parseInt(input.getText());
        input.setText("");

        if (guess == target[0]) {
            label.setText("Your guess is correct! Number was: " + target[0]);
            button.setEnabled(false);
            return;
        }
        attempt.decrementAndGet();
        String msg;
        if (guess > target[0]) {
            msg = "Too high! Attempts left: " + attempt.get();
        }else {
            msg = "Too low! Attempts left: " + attempt.get();
        }
        if (Math.abs(guess - target[0]) <= 5) {
            msg += " (Very close!)";
        }
        if (attempt.get() == 0) {
            msg = "Game Over! Number was: " + target[0];
            button.setEnabled(false);
        }
        label.setText(msg);
    } catch (NumberFormatException ex) {
        label.setText("Enter a valid number!");
    }
        });
        restartBtn.addActionListener(e -> {
            target[0] = rand.nextInt(100) + 1;
            attempt.set(5);
            label.setText("Game restarted! Guess again.");
            button.setEnabled(true);
});
        frame.setLayout(new java.awt.FlowLayout());
        frame.add(input);
        frame.add(button);
        frame.add(restartBtn);
        frame.add(label);

        frame.setVisible(true);
    }

}
