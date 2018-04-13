package pl.com.frankiewicz.handel;

import javax.swing.*;
import java.awt.*;

public class ChooseAPersonWindow extends GenericWindow{


    JRadioButton traderRadioButton, professionalRadioButton;
    ButtonGroup buttonGroup;
    JButton nextButton;

    public ChooseAPersonWindow(){
        this.setSize(250,200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        professionalRadioButton = new JRadioButton("Fachowiec");
        professionalRadioButton.requestFocus();
        traderRadioButton = new JRadioButton("Handlowiec");

        buttonGroup = new ButtonGroup();
        buttonGroup.add(professionalRadioButton);
        buttonGroup.add(traderRadioButton);

        nextButton = new JButton("Dalej");

        addComp(mainPanel, professionalRadioButton,0,0,1,1,
                GridBagConstraints.WEST, GridBagConstraints.NONE);

        addComp(mainPanel, traderRadioButton,1,0,1,1,
                GridBagConstraints.EAST, GridBagConstraints.NONE);

        addComp(mainPanel, nextButton,1,1,1,1,
                GridBagConstraints.EAST, GridBagConstraints.NONE);


        this.add(mainPanel);

    }


}
