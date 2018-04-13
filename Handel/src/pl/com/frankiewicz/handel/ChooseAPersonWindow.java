package pl.com.frankiewicz.handel;

import javax.swing.*;
import java.awt.*;

public class ChooseAPersonWindow extends LoginWindow{

    public ChooseAPersonWindow(){
        this.setSize(350,200);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Login");
        this.setVisible(true);
    }
}
