import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ConcentrationGame {

    private int numButtons;

    public ConcentrationGame() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        // Main frame where game is played
        JFrame frame = new JFrame();
        // Frame that comes up in the beginning of the game to show the player instructions
        JFrame instructionFrame = new JFrame();

        JPanel grid = new JPanel();
        JPanel gameInfo = new JPanel();
        JPanel instructionPanel = new JPanel();
        JPanel options = new JPanel();
        JPanel buttonPanel = new JPanel();

        JLabel title = new JLabel("Welcome to Clear the Board!");
        JLabel instructions = createLabel("<html> Clear the board is simple, all you have to do is clear the board! Click" +
                "on a button to remove it, and remove all the buttons as fast as you can! The faster the time, " +
                "the better the score. Good Luck! </html>");
        JLabel choicesLabel = createLabel("<html> Please choose from one of the options below to begin: </html>");

        title.setHorizontalAlignment(title.CENTER);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

        BorderLayout layout = new BorderLayout();

        JButton fourByFour = createButtons("4x4");
        JButton fiveByFive = createButtons("5x5");
        JButton sixBySix = createButtons("6x6");
        JButton tenByTen = createButtons("10x10");

        grid.setBorder(new EmptyBorder(10, 10, 10, 10));

        frame.add(grid, BorderLayout.CENTER);
        frame.add(gameInfo, BorderLayout.SOUTH);
        frame.setSize(750, 650);
        frame.setVisible(false);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        instructionFrame.add(instructionPanel);
        instructionFrame.setVisible(true);
        instructionFrame.setResizable(false);
        instructionFrame.setSize(450, 550);
        instructionFrame.setLocationRelativeTo(null);

        instructionPanel.setLayout(layout);
        instructionPanel.add(buttonPanel, BorderLayout.SOUTH);
        instructionPanel.add(title, BorderLayout.NORTH);
        instructionPanel.add(instructions, BorderLayout.CENTER);

        buttonPanel.add(options, BorderLayout.SOUTH);
        buttonPanel.add(choicesLabel, BorderLayout.NORTH);

        options.setLayout(new GridLayout(1, 4));
        options.setVisible(true);
        options.add(choicesLabel, BorderLayout.NORTH);
        options.add(fourByFour);
        options.add(fiveByFive);
        options.add(sixBySix);
        options.add(tenByTen);

        addActionListener(fourByFour, grid, frame, instructionFrame, 16);
        addActionListener(fiveByFive, grid, frame, instructionFrame, 25);
        addActionListener(sixBySix, grid, frame, instructionFrame, 36);
        addActionListener(tenByTen, grid, frame, instructionFrame, 100);

        // Method to play music in background
        // playMusic();

    }
    public JButton createButtons(String label) {
        JButton button = new JButton(label);
        button.setSize(100, 50);
        button.setMaximumSize(new Dimension(100, 50));

        return button;
    }

    public JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));

        return label;
    }

    public void addActionListener(JButton button, JPanel grid, JFrame frame, JFrame instructionFrame, int num) {
        button.addActionListener(e -> {
            numButtons = num;
            createGridLayout(grid, frame);
            instructionFrame.setVisible(false);
            frame.setVisible(true);
            createGameButtons(grid);
        });
    }

    public void createGridLayout(JPanel grid, JFrame frame) {
        int length = (int) Math.sqrt(numButtons);

        grid.setLayout(new GridLayout(length, length));

    }

    public void createGameButtons(JPanel grid) {
        // Creates a new arrayList of buttons
        ArrayList<JButton> buttons = new ArrayList<>();

        for (int i=0; i<numButtons; i++) {
            buttons.add(new JButton());
        }

        for (JButton button : buttons) {
            grid.add(button);
            button.setIcon(new ImageIcon("reese.png"));

            // adds an action listener to each button in the arrayList
            button.addActionListener(e -> {
                button.setVisible(false);
                buttons.remove(button);

                if (buttons.size() == 0) {
                    System.exit(0);
                }
            });
        }
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        // Creates a new instance of the ConcentrationGame
        new ConcentrationGame();

    }

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
}
