package exo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class SemanticEvents extends JFrame {

    private JButton[] buttons = new JButton[3];

    public final static int LEFT_BUTTON = 0;
    public final static int CENTRAL_BUTTON = 1;
    public final static int RIGHT_BUTTON = 2;

    private Box content;
    private JLabel northLabel;
    private JLabel southLabel;

    private JButton quitButton = new JButton("Quit");

    public SemanticEvents() {
        super("Semantic and Low Level events");

        this.getContentPane().setBackground(Color.MAGENTA);

        this.buildFrameContent();
    }

    private void buildFrameContent(){
        content = this.createButtonsBox();
        northLabel = new JLabel("I am the north label");
        southLabel = new JLabel("I am the south label");

        this.add(content);
        this.add(northLabel, BorderLayout.NORTH);
        this.add(southLabel, BorderLayout.SOUTH);
        this.add(buildEastBox(), BorderLayout.EAST);
    }

    private Box buildEastBox(){
        Box b = Box.createVerticalBox();
        b.add(Box.createVerticalGlue());
        b.add(quitButton);

        this.quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return b;
    }

    private Box createButtonsBox(){
        Box content = Box.createHorizontalBox();

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("Do you know who has focus ?");
            content.add(Box.createHorizontalGlue());
            content.add(buttons[i]);
        }
        content.add(Box.createHorizontalGlue());
        content.setBorder(new LineBorder(Color.BLUE, 4));
        return content;
    }

    public static void createAndShowGUI() {
        SemanticEvents se = new SemanticEvents();
        se.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        se.pack();
        se.setLocationRelativeTo(null);
        se.setVisible(true);
    }

    public static void main(String[] args) {
        // Formule "magique" pour l'instant
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

