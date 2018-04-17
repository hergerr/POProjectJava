package pl.com.frankiewicz.handel;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TraderWindow extends GenericWindow {
    JLabel idLabel, nameLabel, companyNameLabel, companyTypeLabel, emailLabel, phoneLabel, voivodeshipLabel, countyLabel, cityLabel;
    JTextField idTextField, nameTextEdit, workHoursTextField, emailTextField, phoneTextField;
    JComboBox voivodeshipComboBox, countyComboBox, cityComboBox;
    JButton addButton, deleteButton, editButton, clearButton, findButton;
    JTable dataTable;
    JPanel mainPanel;
    Connection connection = null;
    int voivodeshipId, countyId;

    TraderWindow() {
        super();
        connection = DatabaseConnection.dbConnector();

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
        idTextField = new JTextField(15);
        nameTextEdit = new JTextField(15);
        workHoursTextField = new JTextField(15);
        emailTextField = new JTextField(15);
        phoneTextField = new JTextField(15);
        voivodeshipComboBox = new JComboBox();
        countyComboBox = new JComboBox();
        cityComboBox = new JComboBox();
        addButton = new JButton("Dodaj");
        deleteButton = new JButton("Usuń");
        editButton = new JButton("Edytuj");
        clearButton = new JButton("Wyczyść");
        findButton = new JButton("Znajdź");
        dataTable = new JTable(15, 8);
        dataTable.setPreferredSize(new Dimension(900, 245));
        idTextField.setEditable(false);


        Box labelBox = Box.createVerticalBox();
        labelBox.add(idLabel);
        labelBox.add(Box.createRigidArea(new Dimension(0, 10)));
        labelBox.add(nameLabel);
        labelBox.add(Box.createRigidArea(new Dimension(0, 10)));
        labelBox.add(companyNameLabel);
        labelBox.add(Box.createRigidArea(new Dimension(0, 10)));
        labelBox.add(companyTypeLabel);
        labelBox.add(Box.createRigidArea(new Dimension(0, 10)));
        labelBox.add(emailLabel);
        labelBox.add(Box.createRigidArea(new Dimension(0, 10)));
        labelBox.add(phoneLabel);
        labelBox.add(Box.createRigidArea(new Dimension(0, 15)));
        labelBox.add(voivodeshipLabel);
        labelBox.add(Box.createRigidArea(new Dimension(0, 15)));
        labelBox.add(countyLabel);
        labelBox.add(Box.createRigidArea(new Dimension(0, 15)));
        labelBox.add(cityLabel);

        Box textFieldBox = Box.createVerticalBox();
        textFieldBox.add(idTextField);
        textFieldBox.add(Box.createRigidArea(new Dimension(0, 5)));
        textFieldBox.add(nameTextEdit);
        textFieldBox.add(Box.createRigidArea(new Dimension(0, 5)));
        textFieldBox.add(workHoursTextField);
        textFieldBox.add(Box.createRigidArea(new Dimension(0, 5)));
        textFieldBox.add(emailTextField);
        textFieldBox.add(Box.createRigidArea(new Dimension(0, 5)));
        textFieldBox.add(phoneTextField);
        textFieldBox.add(Box.createRigidArea(new Dimension(0, 20)));
        textFieldBox.add(voivodeshipComboBox);
        textFieldBox.add(Box.createRigidArea(new Dimension(0, 5)));
        textFieldBox.add(countyComboBox);
        textFieldBox.add(Box.createRigidArea(new Dimension(0, 5)));
        textFieldBox.add(cityComboBox);

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(addButton);
        buttonBox.add(Box.createRigidArea(new Dimension(10, 5)));
        buttonBox.add(deleteButton);
        buttonBox.add(Box.createRigidArea(new Dimension(10, 5)));
        buttonBox.add(editButton);
        buttonBox.add(Box.createRigidArea(new Dimension(10, 5)));
        buttonBox.add(clearButton);
        buttonBox.add(Box.createRigidArea(new Dimension(780, 70)));
        buttonBox.add(findButton);

        ListenForFindButton listenForFindButton = new ListenForFindButton();
        findButton.addActionListener(listenForFindButton);

        ListenForAddButton listenForAddButton = new ListenForAddButton();
        addButton.addActionListener(listenForAddButton);

        ListenForEditButton listenForEditButton = new ListenForEditButton();
        editButton.addActionListener(listenForEditButton);

        ListenForDeleteButton listenForDeleteButton = new ListenForDeleteButton();
        deleteButton.addActionListener(listenForDeleteButton);

        ListenForClearButton listenForClearButton = new ListenForClearButton();
        clearButton.addActionListener(listenForClearButton);


        dataTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    super.mouseClicked(e);
                    int row = dataTable.getSelectedRow();
                    String id = (dataTable.getModel().getValueAt(row, 0)).toString();
                    String query = "select * from Traders where id = '" + id + "'";

                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        idTextField.setText(resultSet.getString("id"));
                        nameTextEdit.setText(resultSet.getString("name"));
                        workHoursTextField.setText(resultSet.getString("work_hours"));
                        phoneTextField.setText(resultSet.getString("phone_number"));
                        emailTextField.setText(resultSet.getString("email"));
                        voivodeshipComboBox.setSelectedItem(resultSet.getString("voivodeship"));
                        countyComboBox.setSelectedItem(resultSet.getString("county"));
                        cityComboBox.setSelectedItem(resultSet.getString("city"));
                    }


                    preparedStatement.close();
                    resultSet.close();

                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });

        fillVoivodeshipComboBox();

        voivodeshipComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    countyComboBox.removeAllItems();
                    voivodeshipId = voivodeshipComboBox.getSelectedIndex();
                    voivodeshipId += 1;
                    voivodeshipId *= 2;
                    String query = "select powiat from Powiaty where wojewodztwoId = " + voivodeshipId;
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        countyComboBox.addItem(resultSet.getString("powiat"));
                    }
                    countyComboBox.setSelectedIndex(-1);

                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });

        countyComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cityComboBox.removeAllItems();
                    countyId = countyComboBox.getSelectedIndex();
                    countyId += 1;
                    String query = "select miasto from Miasta where wojewodztwoId = " + voivodeshipId + " and powiatId = " + countyId;
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        cityComboBox.addItem(resultSet.getString("miasto"));
                    }
                    cityComboBox.setSelectedIndex(-1);

                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });

        addComp(mainPanel, labelBox, 0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(mainPanel, textFieldBox, 1, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(mainPanel, dataTable, 2, 0, 3, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(mainPanel, buttonBox, 0, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);


        this.add(mainPanel);
        this.setSize(1230, 370);
    }

    public void fillVoivodeshipComboBox() {
        try {
            String query = "select * from Wojewodztwa";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                voivodeshipComboBox.addItem(resultSet.getString("wojewodztwo"));
            }
            voivodeshipComboBox.setSelectedIndex(-1);

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private class ListenForFindButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == findButton) {
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;
                String query;
                try {
                    if (voivodeshipComboBox.getSelectedIndex() == -1) {
                        query = "select * from Traders";
                    } else if (voivodeshipComboBox.getSelectedIndex() != -1 && countyComboBox.getSelectedIndex() == -1) {
                        String voivodeship = voivodeshipComboBox.getSelectedItem().toString();
                        query = "select * from Traders where voivodeship = '" + voivodeship + "' ";
                    } else {
                        String voivodeship = voivodeshipComboBox.getSelectedItem().toString();
                        String county = countyComboBox.getSelectedItem().toString();
                        query = "select * from Traders where voivodeship = '" + voivodeship + "' and county = '" + county + "'";
                    }

                    preparedStatement = connection.prepareStatement(query);
                    resultSet = preparedStatement.executeQuery();
                    dataTable.setModel(DbUtils.resultSetToTableModel(resultSet));
                    TableColumnModel tableColumnModel = dataTable.getColumnModel();
                    tableColumnModel.getColumn(0).setPreferredWidth(20);
                    tableColumnModel.getColumn(1).setPreferredWidth(100);
                    tableColumnModel.getColumn(2).setPreferredWidth(100);
                    tableColumnModel.getColumn(3).setPreferredWidth(100);
                    tableColumnModel.getColumn(4).setPreferredWidth(70);
                    tableColumnModel.getColumn(5).setPreferredWidth(150);
                    tableColumnModel.getColumn(6).setPreferredWidth(100);
                    tableColumnModel.getColumn(7).setPreferredWidth(100);
                    resultSet.close();
                    preparedStatement.close();


                } catch (SQLException exception) {
                    exception.printStackTrace();
                }

            }
        }
    }

    private class ListenForAddButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addButton) {
                if (phoneTextField.getText().length() == 9 || phoneTextField.getText().length() == 11) {
                    try {
                        String query = "insert into Traders (name, work_hours, phone_number,email, " +
                                "voivodeship, county, city)" +
                                "values (?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, nameTextEdit.getText());
                        preparedStatement.setString(2, workHoursTextField.getText());
                        preparedStatement.setString(4, phoneTextField.getText());
                        preparedStatement.setString(5, emailTextField.getText());
                        preparedStatement.setString(6, voivodeshipComboBox.getSelectedItem().toString());
                        preparedStatement.setString(7, countyComboBox.getSelectedItem().toString());
                        preparedStatement.setString(8, cityComboBox.getSelectedItem().toString());
                        preparedStatement.execute();

                        JOptionPane.showMessageDialog(null, "Zapisano");
                        preparedStatement.close();
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }
                } else JOptionPane.showMessageDialog(null, "Zły numer telefonu");

            }
        }
    }

    private class ListenForEditButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == editButton) {
                if (phoneTextField.getText().length() == 9 || phoneTextField.getText().length() == 11) {
                    try {
                        String query = "update Traders set name = '" + nameTextEdit.getText() + "'," +
                                " work_hours = '" + workHoursTextField.getText() + "', " +
                                " phone_number = '" + phoneTextField.getText() + "', email = '" + emailTextField.getText() + "', " +
                                " voivodeship = '" + voivodeshipComboBox.getSelectedItem().toString() + "', " +
                                " county = '" + countyComboBox.getSelectedItem().toString() + "'," +
                                " city = '" + cityComboBox.getSelectedItem().toString() + "'" +
                                " where id = '" + idTextField.getText() + "'";

                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.execute();

                        JOptionPane.showMessageDialog(null, "Zmieniono");
                        preparedStatement.close();
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }
                } else JOptionPane.showMessageDialog(null, "Zły numer telefonu");
            }
        }
    }

    private class ListenForDeleteButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == deleteButton) {
                try {
                    String query = "delete from Traders where id = '" + idTextField.getText() + "'";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.execute();
                    JOptionPane.showMessageDialog(null, "Usunięto");
                    preparedStatement.close();


                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    private class ListenForClearButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearButton) {
                idTextField.setText("");
                nameTextEdit.setText("");
                workHoursTextField.setText("");
                phoneTextField.setText("");
                emailTextField.setText("");
                voivodeshipComboBox.setSelectedIndex(-1);
                countyComboBox.setSelectedIndex(-1);
                cityComboBox.setSelectedIndex(-1);
            }
        }
    }

}
