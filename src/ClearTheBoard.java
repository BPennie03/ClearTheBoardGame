import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ClearTheBoard {

    // These are promoted to fields of the class to allow them to be modified within a lambda expression
    private int numButtons;
    private JLabel timeLabel = new JLabel("Time: ");
    private JLabel endLabel = new JLabel();

    public ClearTheBoard() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        // Main gameFrame where game is played
        JFrame gameFrame = new JFrame();

        // Frame that comes up in the beginning of the game to show the player instructions
        JFrame instructionFrame = new JFrame();

        // Initializes JPanels that are used throughout the game
        JPanel grid = new JPanel();
        JPanel instructionPanel = new JPanel();
        JPanel options = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel infoPanel = new JPanel();

        // Creates JLabels for the Instruction Frame
        JLabel title = new JLabel("Welcome to Clear the Board!");
        JLabel instructions = createLabel("<html> Clear the board is simple, all you have to do is clear the board! Click" +
                "on a button to remove it, and remove all the buttons as fast as you can! The faster the time, " +
                "the better the score. Please choose from one of the options below to begin. Good Luck! </html>");

        // Sets font of the time and end Labels
        timeLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        endLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));

        // sets the title's horizontal alignment and font
        title.setHorizontalAlignment(title.CENTER);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

        // Creates a new borderLayout that is used for the InstructionPanel
        BorderLayout layout = new BorderLayout();

        // Calls createButtons method to create 4 buttons with the desired text
        JButton fourByFour = createButtons("4x4");
        JButton fiveByFive = createButtons("5x5");
        JButton sixBySix = createButtons("6x6");
        JButton tenByTen = createButtons("10x10");

        // Sets the grid panels border to have an empty border surrounding it
        grid.setBorder(new EmptyBorder(10, 10, 10, 10));

        // gameFrame's JFrame settings
        setDefaultFrameBehavior(gameFrame, 750, 850);
        gameFrame.add(grid, BorderLayout.CENTER);
        gameFrame.setVisible(false);
        gameFrame.setDefaultCloseOperation(gameFrame.EXIT_ON_CLOSE);
        gameFrame.add(infoPanel, BorderLayout.NORTH);

        // Adds timeLabel and endLabel to the infoPanel at the bottom of Instruction Frame
        infoPanel.add(timeLabel);
        infoPanel.add(endLabel, BorderLayout.SOUTH);

        // InstructionFrame's JFrame settings
        setDefaultFrameBehavior(instructionFrame, 550, 550);
        instructionFrame.add(instructionPanel);
        instructionFrame.setVisible(true);

        // InstructionPanel's JPanel settings
        instructionPanel.setLayout(layout);
        instructionPanel.add(buttonPanel, BorderLayout.SOUTH);
        instructionPanel.add(title, BorderLayout.NORTH);
        instructionPanel.add(instructions, BorderLayout.CENTER);

        // Adds the option's panel to the buttonPanel with a borderLayout on the South border
        buttonPanel.add(options, BorderLayout.SOUTH);

        // Option's JPanel settings
        options.setLayout(new GridLayout(1, 4));
        options.setVisible(true);
        options.add(fourByFour);
        options.add(fiveByFive);
        options.add(sixBySix);
        options.add(tenByTen);

        // Calls addActionListener Method on each button in the instruction gameFrame
        addActionListener(fourByFour, grid, gameFrame, instructionFrame, 16);
        addActionListener(fiveByFive, grid, gameFrame, instructionFrame, 25);
        addActionListener(sixBySix, grid, gameFrame, instructionFrame, 36);
        addActionListener(tenByTen, grid, gameFrame, instructionFrame, 100);

        // Method to play music in background
        playMusic();

    }

    // Method to set a frame's default behavior that is similar between all frames (size, setResizable, and setLocation)
    public JFrame setDefaultFrameBehavior(JFrame frame, int width, int height) {
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        return frame;
    }

    // Method to create buttons using a label
    public JButton createButtons(String label) {

        return new JButton(label);
    }

    // Method to create Labels from pre-defined text
    public JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));

        return label;
    }

    // Method to create actionListeners for the buttons on the Instruction Frame
    public void addActionListener(JButton button, JPanel grid, JFrame frame, JFrame instructionFrame, int num) {
        // when the buttons are pressed, it sets the numButtons to whatever was pressed, and calls the createGridLayout
        // method with that number of buttons. It then sets the instruction frame to invisible, and sets the game frame
        // to visible, and finally creates the buttons to put into the new grid Layout
        button.addActionListener(e -> {
            numButtons = num;
            createGridLayout(grid);
            instructionFrame.setVisible(false);
            frame.setVisible(true);
            createGameButtons(grid);
        });
    }

    // Method to create the grid layout for the game
    public void createGridLayout(JPanel grid) {
        // sets the lenght of each side of the grid equal to the square root of the total number of buttons
        int length = (int) Math.sqrt(numButtons);

        // sets a grid layout to the grid panel with dimensions from length
        grid.setLayout(new GridLayout(length, length));
    }

    // Method to create the buttons in the grid for the Game Frame
    public void createGameButtons(JPanel grid) {
        // Creates a new arrayList of buttons
        ArrayList<JButton> buttons = new ArrayList<>();

        // Loops through each button
        for (int i=0; i<numButtons; i++) {
            // Random number 1-8
            int randNum = (int)(Math.random() * 8) + 1;
            buttons.add(new JButton());

            // Randomizes the color of each button based on a random number
            switch (randNum) {
                case 1 -> buttons.get(i).setBackground(Color.RED);
                case 2 -> buttons.get(i).setBackground(Color.ORANGE);
                case 3 -> buttons.get(i).setBackground(Color.YELLOW);
                case 4 -> buttons.get(i).setBackground(Color.GREEN);
                case 5 -> buttons.get(i).setBackground(Color.BLUE);
                case 6 -> buttons.get(i).setBackground(Color.BLACK);
                case 7 -> buttons.get(i).setBackground(Color.GRAY);
                case 8 -> buttons.get(i).setBackground(Color.MAGENTA);
            }
        }

        // Loops over every button and adds it to the grid, and adds an actionListener to each one
        for (JButton button : buttons) {
            grid.add(button);
            long time = System.currentTimeMillis();

            // adds an action listener to each button in the arrayList
            button.addActionListener(e -> {
                // when the actionListener is clicked, it sets the button's visibility to false, and removes it from the arrayList
                button.setVisible(false);
                buttons.remove(button);

                // if the arrayList of buttons is empty, it ends the game and displays the time it took
                if (buttons.size() == 0) {
                    // displays the time it took to clear the board with regex to show 2 decimal point values
                    timeLabel.setText(String.format("Cleared the" +
                            " board in %.2f seconds", ((System.currentTimeMillis() - time)/1000.0)));
                }
            });
        }
    }

    // Method to play music in background of game was obtained through help from another student
    public void playMusic() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        FileInputStream input = new FileInputStream("backgroundMusic.wav");

        BufferedInputStream buffInput = new BufferedInputStream(input);

        AudioInputStream audio = AudioSystem.getAudioInputStream(buffInput);
        Clip clip = AudioSystem.getClip();
        clip.open(audio);
        clip.loop(-1);
        clip.start();


        audio.close();
    }

    // Main
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        // Creates a new instance of the ClearTheBoard game
        new ClearTheBoard();

    }
}
