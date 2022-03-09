import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ConcentrationGame {

    public ConcentrationGame() {
        // Main frame where game is played
        JFrame frame = new JFrame();
        // Frame that comes up in the beginning of the game to show the player instructions
        JFrame instructionFrame = new JFrame();

        JPanel root = new JPanel();
        JPanel instructionPanel = new JPanel();

        JLabel title = new JLabel("Welcome to Concentration!");
        JLabel instructions = new JLabel();

        title.setHorizontalAlignment(title.CENTER);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

        // <html> tags were added to allow the JLabel to have text wrapping
        instructions.setText("<html> To play concentration, you have to click on 2 of the cards\n that are laid out on the screen," +
                " and try to get a match. Each match gets you 1 point. You will have 1 minute to find all the matches." +
                " After the time is up, however many matches you get before the time is up will be your score." +
                " Good Luck! </html>");
        instructions.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));

        JButton closeInstructions = new JButton("Close Instructions");

        BorderLayout layout = new BorderLayout();

        closeInstructions.setSize(100, 100);
        closeInstructions.addActionListener(e -> {
            instructionFrame.dispose();
        });

        root.setLayout(new GridLayout(3, 4));
        root.setBorder(new EmptyBorder(10, 10, 10, 10));

        frame.add(root);
        frame.setSize(750, 650);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        instructionFrame.add(instructionPanel);
        instructionFrame.setVisible(true);
        instructionFrame.setResizable(false);
        instructionFrame.setSize(350, 350);
        instructionFrame.setLocationRelativeTo(null);

        instructionPanel.setLayout(layout);
        instructionPanel.add(title, BorderLayout.NORTH);
        instructionPanel.add(closeInstructions, BorderLayout.SOUTH);
        instructionPanel.add(instructions, BorderLayout.CENTER);


        // Creates a new arrayList of buttons(cards)
        ArrayList<JButton> buttons = new ArrayList<>();

        // Loops 12 times and creates 12 different cards(buttons) and adds them to an arrayList of buttons
        for (int i=0; i<12; i++) {
            buttons.add(new JButton());
        }

        // Goes through each button in the arrayList and adds it to the root panel and adds the card image
        // to the button
        for (JButton button : buttons) {
            root.add(button);
            button.setIcon(new ImageIcon("backOfCard.png"));

            // adds an action listener to each button in the arrayList
            button.addActionListener(e -> {
                System.out.println("test");
            });
        }

        ArrayList<BufferedImage> images = new ArrayList<>();

    }

    public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        // Creates a new instance of the ConcentrationGame
        new ConcentrationGame();


        /*
        FileInputStream input = new FileInputStream("backgroundMusic.wav");

        BufferedInputStream buffInput = new BufferedInputStream(input);

        AudioInputStream audio = AudioSystem.getAudioInputStream(buffInput);
        Clip clip = AudioSystem.getClip();
        clip.open(audio);
        clip.loop(-1);
        clip.start();

        audio.close();
        */

    }
}
