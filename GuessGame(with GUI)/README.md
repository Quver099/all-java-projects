# Java Guessing Game (GUI Edition) 🎯

A simple, interactive number guessing game built with **Java Swing**. I made this to move away from console-based apps and handle real-time user interaction.

### Why I built this:
* **GUI Practice:** Getting comfortable with `JFrame`, `JButton`, and `JLabel`.
* **The "Final" Problem:** Handled Java's restriction on using non-final variables inside Lambda expressions by using `AtomicInteger` and `int[]` arrays. 
* **Logic:** Added a "Very Close" hint system if you're within 5 numbers of the target.

### Features:
* **Limited Attempts:** You get 5 tries before the game locks you out.
* **Restart Button:** Resets the target number and your attempt counter without restarting the app.
* **Error Handling:** Won't crash if you accidentally type letters instead of numbers (uses `try-catch`).

### Tech Used:
* **Language:** Java
* **Library:** Swing (GUI)
* **Logic Tools:** `AtomicInteger` (for thread-safe counting), `Random` (for target generation).

### How to run:
1. Compile: `javac GuessGameWithGUI.java`
2. Run: `java GuessGameWithGUI`

---