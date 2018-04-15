package pl.com.frankiewicz.handel;

import javax.swing.*;
import java.awt.*;

public class ProfessionalWindow extends GenericWindow{
    JLabel idLabel, nameLabel, companyNameLabel, companyTypeLabel, emailLabel, phoneLabel, voivodeshipLabel, countyLabel, cityLabel;
    JTextField idTextField, nameTextEdit, companyNameTextField, companyTypeTextField, emailTextField, phoneTextField;
    JComboBox voivodeshipComboBox, countyComboBox, cityComboBox;
    JButton addButton, deleteButton, editButton, clearButton, findButton;
    JTable dataTable;
    JPanel mainPanel;

    ProfessionalWindow(){
        super();
        this.setLocationRelativeTo(null);

        mainPanel = new JPanel();

        idLabel = new JLabel("Id");
        nameLabel = new JLabel("Imie i nazwisko");
        companyNameLabel = new JLabel("Nazwa firmy");
        companyTypeLabel = new JLabel("Typ firmy");
        emailLabel = new JLabel("Email");
        phoneLabel = new JLabel("Telefon");
        voivodeshipLabel = new JLabel("Województwo");
        countyLabel = new JLabel("Powiat");
        cityLabel = new JLabel("Miejscowosc");
        idTextField = new JTextField(10);
        nameTextEdit = new JTextField(10);
        companyNameTextField = new JTextField(10);
        companyTypeTextField = new JTextField(10);
        emailTextField = new JTextField(10);
        phoneTextField = new JTextField(10);
        voivodeshipComboBox = new JComboBox();
        countyComboBox = new JComboBox();
        cityComboBox = new JComboBox();
        addButton = new JButton("Dodaj");
        deleteButton = new JButton("Usuń");
        editButton = new JButton("Edytuj");
        clearButton = new JButton("Wyczyść");
        findButton = new JButton("Znajdź");
        dataTable = new JTable(15,9);

        Box labelBox = Box.createVerticalBox();
        labelBox.add(idLabel);
        labelBox.add(Box.createRigidArea(new Dimension(0,10)));
        labelBox.add(nameLabel);
        labelBox.add(Box.createRigidArea(new Dimension(0,10)));
        labelBox.add(companyNameLabel);
        labelBox.add(Box.createRigidArea(new Dimension(0,10)));
        labelBox.add(companyTypeLabel);
        labelBox.add(Box.createRigidArea(new Dimension(0,10)));
        labelBox.add(emailLabel);
        labelBox.add(Box.createRigidArea(new Dimension(0,10)));
        labelBox.add(phoneLabel);
        labelBox.add(Box.createRigidArea(new Dimension(0,15)));
        labelBox.add(voivodeshipLabel);
        labelBox.add(Box.createRigidArea(new Dimension(0,15)));
        labelBox.add(countyLabel);
        labelBox.add(Box.createRigidArea(new Dimension(0,15)));
        labelBox.add(cityLabel);

        Box textFieldBox = Box.createVerticalBox();
        textFieldBox.add(idTextField);
        textFieldBox.add(Box.createRigidArea(new Dimension(0,5)));
        textFieldBox.add(nameTextEdit);
        textFieldBox.add(Box.createRigidArea(new Dimension(0,5)));
        textFieldBox.add(companyNameTextField);
        textFieldBox.add(Box.createRigidArea(new Dimension(0,5)));
        textFieldBox.add(companyTypeTextField);
        textFieldBox.add(Box.createRigidArea(new Dimension(0,5)));
        textFieldBox.add(emailTextField);
        textFieldBox.add(Box.createRigidArea(new Dimension(0,5)));
        textFieldBox.add(phoneTextField);
        textFieldBox.add(Box.createRigidArea(new Dimension(0,20)));
        textFieldBox.add(voivodeshipComboBox);
        textFieldBox.add(Box.createRigidArea(new Dimension(0,5)));
        textFieldBox.add(countyComboBox);
        textFieldBox.add(Box.createRigidArea(new Dimension(0,5)));
        textFieldBox.add(cityComboBox);

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(addButton);
        buttonBox.add(Box.createRigidArea(new Dimension(10,5)));
        buttonBox.add(deleteButton);
        buttonBox.add(Box.createRigidArea(new Dimension(10,5)));
        buttonBox.add(editButton);
        buttonBox.add(Box.createRigidArea(new Dimension(10,5)));
        buttonBox.add(clearButton);
        buttonBox.add(Box.createRigidArea(new Dimension(510,70)));
        buttonBox.add(findButton);

//        Box dataBox = Box.createVerticalBox();
//        dataBox.add(dataTable);
//        dataBox.add(dataBox.add(Box.createRigidArea(new Dimension(20,5))));


        addComp(mainPanel,labelBox,0,0,1,1,GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(mainPanel,textFieldBox,1,0,1,1,GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(mainPanel, dataTable, 2,0,1,1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(mainPanel, buttonBox,0,3, 1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);

        this.add(mainPanel);
        this.setSize(950,370);
    }
}
