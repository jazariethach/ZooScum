package src;

import java.lang.String;
import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class GameOverPanel extends JPanel implements ActionListener {
    private int         maxX;
    private int         maxY;
    private JButton     Exit;
    private JButton     Main;
    private JButton     Play;
    private GroupLayout SplashLayout;
    private JEditorPane textPane;
    private String      splashText;
    {
        maxX            = 300;
        maxY            = 300;
        Exit            = new JButton("Exit");
        Main            = new JButton("Main Menu");
        Play            = new JButton("New Game");
        SplashLayout    = new GroupLayout(this);
        textPane        = new JEditorPane();
        splashText      = "GAME OVER!\nPlay Again?";
    }
    
    /**
     * Constructor GameOverPanel - initialize menu with buttons & text
     */
    public GameOverPanel() {
        setTextPane();
        
        Buttons();
        
        SplashLayout.setAutoCreateGaps(true);
        SplashLayout.setAutoCreateContainerGaps(true);
        SplashLayout.setHorizontalGroup(SplashLayout.createSequentialGroup()
                                        .addGroup(SplashLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                  .addComponent(textPane)
                                                  .addComponent(Play)
                                                  .addComponent(Main)
                                                  .addComponent(Exit))
                                        );
        SplashLayout.setVerticalGroup(SplashLayout.createSequentialGroup()
                                      .addComponent(textPane)
                                      .addComponent(Play)
                                      .addComponent(Main)
                                      .addComponent(Exit)
                                      );
        setLayout(SplashLayout);
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.GREEN);
        setVisible(true);
    }
    
    
    /**
     * Method Buttons - initialize menu buttons
     */
    private void Buttons() {
        Exit.setPreferredSize(new Dimension(100, 50));
        Main.setPreferredSize(new Dimension(100, 50));
        Play.setPreferredSize(new Dimension(100, 50));
        
        Exit.addActionListener(this);
        Main.addActionListener(this);
        Play.addActionListener(this);
    }
    
    /**
     * Method getText - get text to display with buttons on panel
     */
    private void setTextPane() {
        textPane.setPreferredSize(new Dimension(200, 100));
        
        textPane.setText(splashText);
    }
    
    
    /**
     * Override actionPerformed - sets actions when menu buttons are pressed
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == Play) {
            setVisible(false);
            GameEnvironment gameEnv = new GameEnvironment();
        }
        if(event.getSource() == Exit) {
            System.exit(0);
        }
        if(event.getSource() == Main) {
            setVisible(false);
            MainEnvironment mainEnv = new MainEnvironment();
        }
    }// actionPerformed
    
}