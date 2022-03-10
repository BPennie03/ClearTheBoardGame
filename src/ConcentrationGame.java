import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.JarURLConnection;
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

        JLabel title = new JLabel("Welcome to Clear the Board!");
        JLabel instructions = new JLabel();

        title.setHorizontalAlignment(title.CENTER);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

        // <html> tags were added to allow the JLabel to have text wrapping
        instructions.setText("<html> Clear the board is simple, all you have to do is clear the board! Click" +
                "on a button to remove it, and remove all the buttons as fast as you can! The faster the time, " +
                "the better the score. Good Luck! </html>");
        instructions.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));

        JButton closeInstructions = new JButton("Close Instructions");
        JButton help = new JButton("Instructions");
        JButton muteMusic = new JButton("Mute Music");

        JSlider slider = new JSlider();

        BorderLayout layout = new BorderLayout();

        // button on instruction screen to close the instruction page
        closeInstructions.setSize(100, 100);
        closeInstructions.addActionListener(e -> {
            instructionFrame.setVisible(false);
        });

        // Button at bottom of game screen to allow instructions to be re-opened
        help.addActionListener(e -> {
            instructionFrame.setVisible(true);
        });

        //slider.addChangeListener(this);
        slider.setPaintTicks(true);
        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(10);
        slider.setPaintLabels(true);

        grid.setLayout(new GridLayout(10, 10));
        grid.setBorder(new EmptyBorder(10, 10, 10, 10));

        frame.add(grid, BorderLayout.CENTER);
        frame.add(gameInfo, BorderLayout.SOUTH);
        frame.setSize(750, 650);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        instructionFrame.add(instructionPanel);
        instructionFrame.setVisible(true);
        instructionFrame.setResizable(false);
        instructionFrame.setSize(450, 450);
        instructionFrame.setLocationRelativeTo(null);

        instructionPanel.setLayout(layout);
        instructionPanel.add(title, BorderLayout.NORTH);
        instructionPanel.add(closeInstructions, BorderLayout.SOUTH);
        instructionPanel.add(instructions, BorderLayout.CENTER);
        instructionPanel.add(slider, BorderLayout.SOUTH);

        gameInfo.add(help);

// *** START OF GAME ***

        // Creates a new arrayList of buttons
        ArrayList<JButton> buttons = new ArrayList<>();

        // Method to play music in background
        playMusic();

        int randNum = (int)(Math.random() * 5) + 1;

        // Loops x amount of times to populate the game board
        for (int i=0; i<100; i++) {
            buttons.add(new JButton());
        }

        // Goes through each button in the arrayList and adds it to the grid panel and adds the card image
        // to the button
        for (JButton button : buttons) {
            grid.add(button);
            button.setIcon(new ImageIcon("cheatham.png"));

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



    public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
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
