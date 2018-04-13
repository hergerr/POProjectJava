package pl.com.frankiewicz.handel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginWindow extends JFrame{

    JLabel loginLabel;
    JTextField loginTextField;
    JLabel passwordLabel;
    JPasswordField passwordTextField;
    JButton loginButton;

    public static void main(String[] args) {
        new LoginWindow();
    }

    public LoginWindow(){
        this.setSize(350,200);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Login");
        JPanel loginPanel = new JPanel(new GridBagLayout());


        loginLabel = new JLabel("Login");
        addComp(loginPanel, loginLabel,0,0,1,1,
                GridBagConstraints.EAST, GridBagConstraints.NONE);

        loginTextField = new JTextField(15);
        loginTextField.requestFocus();
        addComp(loginPanel, loginTextField,1,0,1,1,
                GridBagConstraints.WEST, GridBagConstraints.NONE);

        passwordLabel = new JLabel("Hasło");
        addComp(loginPanel, passwordLabel,0,1,1,1,
                GridBagConstraints.EAST, GridBagConstraints.NONE);


        passwordTextField = new JPasswordField(15);
        addComp(loginPanel, passwordTextField,1,1,1,1,
                GridBagConstraints.WEST, GridBagConstraints.NONE);

        loginButton = new JButton("Zaloguj");
        addComp(loginPanel, loginButton,1,2,1,1,
                GridBagConstraints.EAST, GridBagConstraints.NONE);
        ListenForButton listenForButton = new ListenForButton();
        loginButton.addActionListener(listenForButton);


        Border border = BorderFactory.createTitledBorder("Login");
        loginPanel.setBorder(border);

        this.add(loginPanel);
        this.setVisible(true);
    }

    private void addComp(JPanel thePanel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight, int place, int stretch){

        GridBagConstraints gridConstraints = new GridBagConstraints();

        gridConstraints.gridx = xPos;
        gridConstraints.gridy = yPos;
        gridConstraints.gridwidth = compWidth;
        gridConstraints.gridheight = compHeight;
        gridConstraints.weightx = 1;
        gridConstraints.weighty = 1;
        gridConstraints.insets = new Insets(5,5,5,5);
        gridConstraints.anchor = place;
        gridConstraints.fill = stretch;

        thePanel.add(comp, gridConstraints);

    }

    private class ListenForButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == loginButton){
                try {
                    Connection connection = DatabaseConnection.dbConnector();
                    String query = "select * from Users where login = ? and password = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1,loginTextField.getText());
                    preparedStatement.setString(2, passwordTextField.getText());
                    ResultSet resultSet = preparedStatement.executeQuery();

                    int count = 0;
                    while (resultSet.next())++count;

                    if(count == 1){
                        LoginWindow.this.dispose();
                        ChooseAPersonWindow chooseAPersonWindow = new ChooseAPersonWindow();
                        chooseAPersonWindow.setVisible(true);
                    } else JOptionPane.showMessageDialog(null, "Nie zalogowano");

                    resultSet.close();
                    preparedStatement.close();
                } catch (SQLException error) {JOptionPane.showMessageDialog(null, error);}


            }
        }
    }


}


