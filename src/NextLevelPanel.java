package src;

import java.lang.String;
import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


class NextLevelPanel extends JPanel implements ActionListener {
    private int         maxX;
    private int         maxY;
    private JButton     Exit;
    private JButton     Main;
    private JButton     Next;
    private JButton     Play;
    private GroupLayout SplashLayout;
    private JEditorPane textPane;
    private String      splashText;
    {
        maxX            = 400;
        maxY            = 400;
        Exit            = new JButton("Exit");
        Main            = new JButton("Main Menu");
        Next            = new JButton("Next Level");
        Play            = new JButton("Start Over");
        SplashLayout    = new GroupLayout(this);
        textPane        = new JEditorPane();
    }
    
    /**
     * Constructor NextLevelPanel - initialize menu with buttons
     */
    public NextLevelPanel() {
        setTextPane();
        Buttons();
        
        SplashLayout.setAutoCreateGaps(true);
        SplashLayout.setAutoCreateContainerGaps(true);
        SplashLayout.setHorizontalGroup(SplashLayout.createSequentialGroup()
                                        .addGroup(SplashLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                  .addComponent(textPane)
                                                  .addGroup(SplashLayout.createSequentialGroup()
                                                            .addComponent(Main)
                                                            .addComponent(Next)
                                                            .addComponent(Play)
                                                            .addComponent(Exit)))
                                        );
        SplashLayout.setVerticalGroup(SplashLayout.createSequentialGroup()
                                      .addComponent(textPane)
                                      .addGroup(SplashLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                                .addComponent(Main)
                                                .addComponent(Next)
                                                .addComponent(Play)
                                                .addComponent(Exit))
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
        Next.setPreferredSize(new Dimension(100, 50));
        Play.setPreferredSize(new Dimension(100, 50));
        
        Exit.addActionListener(this);
        Main.addActionListener(this);
        Next.addActionListener(this);
        Play.addActionListener(this);
    }
    
    /**
     * Method setTextPane - set text on pane (can add more than just text if desired)
     */
    private void setTextPane() {
        textPane.setPreferredSize(new Dimension(400, 300));
        
        textPane.setText(splashText);
    }
    
    
    /**
     * Override actionPerformed - sets actions when menu buttons are pressed
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == Next) {
            setVisible(false);
        }
        if(event.getSource() == Exit) {
            System.exit(0);
        }
        if(event.getSource() == Main) {
            setVisible(false);
            MainEnvironment mainEnv = new MainEnvironment();
        }
        if(event.getSource() == Play) {
            setVisible(false);
            GameEnvironment gameEnv = new GameEnvironment();
        }
    }// actionPerformed
    
}