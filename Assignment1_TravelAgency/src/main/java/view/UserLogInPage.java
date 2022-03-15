package view;

import javax.swing.*;

public class UserLogInPage extends JFrame {

    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JButton logInButton;
    private JButton registerButton;
    private JButton exitButton;

    public void initialize() {
        this.setSize(700, 750);
        this.setTitle("Log In as a User");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        initializeForm(panel);

        this.setContentPane(panel);
        this.setVisible(true);
    }

    private void initializeForm(JPanel panel) {
        usernameLabel = new JLabel("Username: ");
        passwordLabel = new JLabel("Password: ");
        usernameTextField = new JTextField();
        passwordTextField = new JPasswordField();
        logInButton = new JButton("Log In");
        registerButton = new JButton("Register");
        exitButton = new JButton("Exit");

        usernameLabel.setBounds(50, 50, 100, 20);
        passwordLabel.setBounds(50, 80, 100, 20);
        usernameTextField.setBounds(160, 50, 100, 20);
        passwordTextField.setBounds(160, 80, 100, 20);
        logInButton.setBounds(50, 120, 95, 20);
        registerButton.setBounds(155, 120, 95, 20);
        exitButton.setBounds(50, 150, 200, 20);

        panel.add(usernameLabel);
        panel.add(passwordLabel);
        panel.add(usernameTextField);
        panel.add(passwordTextField);
        panel.add(logInButton);
        panel.add(registerButton);
        panel.add(exitButton);
    }

    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    public JPasswordField getPasswordTextField() {
        return passwordTextField;
    }

    public JButton getLogInButton() {
        return logInButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public void displayErrorMessage(Exception e) {
        if(e != null) {
            String message = e.getMessage();
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void displayInformationMessage(String message) {
        if(message != null) {
            JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
