package src;

import java.lang.String;
import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class PausePanel extends JPanel implements ActionListener {
    private int         maxX;
    private int         maxY;
    private JButton     Exit;
    private JButton     Main;
    private JButton     Resume;
    private GroupLayout SplashLayout;
    private JEditorPane textPane;
    private String      splashText;
    {
        maxX            = 200;
        maxY            = 200;
        Exit            = new JButton("Exit");
        Main            = new JButton("Main Menu");
        Resume          = new JButton("Resume Game");
        SplashLayout    = new GroupLayout(this);
        textPane        = new JEditorPane();
        splashText      = "Game paused ...";
    }
    
    /**
     * Constructor PausePanel - initialize menu with buttons & text
     */
    public PausePanel() {
        setTextPane();
        
        Buttons();
        
        SplashLayout.setAutoCreateGaps(true);
        SplashLayout.setAutoCreateContainerGaps(true);
        SplashLayout.setHorizontalGroup(SplashLayout.createSequentialGroup()
                                        .addGroup(SplashLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                  .addComponent(textPane)
                                                  .addComponent(Resume)
                                                  .addComponent(Main)
                                                  .addComponent(Exit))
                                        );
        SplashLayout.setVerticalGroup(SplashLayout.createSequentialGroup()
                                      .addComponent(textPane)
                                      .addComponent(Resume)
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
        Resume.setPreferredSize(new Dimension(100, 50));
        
        Exit.addActionListener(this);
        Main.addActionListener(this);
        Resume.addActionListener(this);
    }
    
    /**
     * Method getText - get text to display with buttons on panel
     */
    private void setTextPane() {
        textPane.setPreferredSize(new Dimension(150, 100));
        
        textPane.setText(splashText);
    }
    
    
    /**
     * Override actionPerformed - sets actions when menu buttons are pressed
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == Resume) {
            setVisible(false);
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