package pl.com.frankiewicz.handel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseAPersonWindow extends GenericWindow {


    JRadioButton traderRadioButton, professionalRadioButton;
    ButtonGroup buttonGroup;
    JButton nextButton;

    public ChooseAPersonWindow() {
        super();
        this.setSize(250, 200);
        this.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        professionalRadioButton = new JRadioButton("Fachowiec");
        professionalRadioButton.requestFocus();
        traderRadioButton = new JRadioButton("Handlowiec");

        buttonGroup = new ButtonGroup();
        buttonGroup.add(professionalRadioButton);
        buttonGroup.add(traderRadioButton);

        nextButton = new JButton("Dalej");
        ListenForButton listenForButton = new ListenForButton();
        nextButton.addActionListener(listenForButton);

        addComp(mainPanel, professionalRadioButton, 0, 0, 1, 1,
                GridBagConstraints.WEST, GridBagConstraints.NONE);

        addComp(mainPanel, traderRadioButton, 1, 0, 1, 1,
                GridBagConstraints.EAST, GridBagConstraints.NONE);

        addComp(mainPanel, nextButton, 1, 1, 1, 1,
                GridBagConstraints.EAST, GridBagConstraints.NONE);

        Border border = BorderFactory.createTitledBorder("Wybierz fachowca lub handlowca");
        mainPanel.setBorder(border);


        this.add(mainPanel);

    }

    private class ListenForButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == nextButton) {
                if (professionalRadioButton.isSelected()) {
                    ChooseAPersonWindow.this.dispose();
                    ProfessionalWindow professionalWindow = new ProfessionalWindow();
                    professionalWindow.setVisible(true);
                } else if (traderRadioButton.isSelected()) {
                    ChooseAPersonWindow.this.dispose();
                    TraderWindow traderWindow = new TraderWindow();
                    traderWindow.setVisible(true);
                }
            }
        }
    }


}
