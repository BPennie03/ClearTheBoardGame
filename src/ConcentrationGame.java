import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ConcentrationGame {

    public ConcentrationGame() {
        JFrame frame = new JFrame();
        JPanel root = new JPanel();


        root.setLayout(new GridLayout(3, 4));
        root.setBorder(new EmptyBorder(10, 10, 10, 10));

        frame.add(root);
        frame.setSize(750, 650);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        ArrayList<JButton> buttons = new ArrayList<>();

        for (int i=0; i<12; i++) {
            buttons.add(new JButton());
        }

        for (int i=0; i<buttons.size(); i++) {
            root.add(buttons.get(i));
        }

        for (int i=0; i<buttons.size(); i++) {
            buttons.get(i).setIcon(new ImageIcon("backOfCard.png"));
        }

        ArrayList<BufferedImage> images = new ArrayList<>();


        for (int i=0; i< buttons.size(); i++) {
            buttons.get(i).addActionListener( e ->  {
                System.out.println("test");
            });
        }

    }

    public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        new ConcentrationGame();


        FileInputStream input = new FileInputStream("songysongsong.wav");

        BufferedInputStream buffInput = new BufferedInputStream(input);

        AudioInputStream audio = AudioSystem.getAudioInputStream(buffInput);
        Clip clip = AudioSystem.getClip();
        clip.open(audio);
        clip.loop(-1);
        clip.start();

        audio.close();
    }
}
