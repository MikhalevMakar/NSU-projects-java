package org.ru.nsu.mikhalev.task3;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;;
import java.beans.EventHandler;
import java.awt.event.ActionListener;



class MyCommponent extends JComponent  {

    @Override
    protected void paintComponent(Graphics g) {
        Graphics g2 = (Graphics2D) g;
        Font font = new Font("Bitstream Character", Font.BOLD, 20);
        g2.setFont(font);

        Image image = new ImageIcon("../Task3/src/main/resources/gameMenu.png").getImage ();
        g2.drawImage (image, 0, 0, null);
    }
}
public class Main {
    static JFrame getFrame() {
        JFrame  jFrame = new JFrame() {};
        jFrame.setVisible (true);
        Toolkit toolkit = Toolkit.getDefaultToolkit ();
        Dimension dimension = toolkit.getScreenSize ();
        jFrame.setBounds (dimension.width / 2-1000,
                dimension.height / 2 - 800,
                1000,
                500);
        jFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        jFrame.setLocation (400, 250);
        jFrame.setTitle ("Tetris");
        return jFrame;
    }
    static class myAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel jPanel = new JPanel ();
            jPanel.setBackground(Color.blue);
        }
    }

    public static void main(String[] args) {
        JFrame jFrame = getFrame ();
        JPanel jPanel = new JPanel();
        jFrame.add(jPanel);
        AbstractAction myAction = new myAction ();

        JButton jButton = new JButton (myAction);
        jButton.setText ("submit");
        jPanel.add(jButton);

        jButton.addActionListener (EventHandler.create
                (ActionListener.class,
                                jFrame,
                         "title",
                "source.text"));

        KeyStroke keyStroke = KeyStroke.getKeyStroke ("shift B");
        InputMap inputMap = jPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(keyStroke, "changeColor");
        ActionMap actionMap = jPanel.getActionMap ();
        actionMap.put("changeColor", myAction);
        jFrame.add (new MyCommponent ());

    }
}