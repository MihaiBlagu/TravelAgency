package view;

import exception.CustomException;

import javax.swing.*;

public class MainPage extends JFrame {

    private JButton clientButton;
    private JButton agencyButton;

    public void initialize() {
        this.setSize(700, 750);
        this.setTitle("Travel Agency!");
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
        clientButton = new JButton("I am a Client!");
        agencyButton = new JButton("I am an Agency Representative!");

        clientButton.setBounds(50, 50,250, 50);
        agencyButton.setBounds(50, 250, 250, 50);

        panel.add(clientButton);
        panel.add(agencyButton);
    }

    public JButton getClientButton() {
        return clientButton;
    }

    public JButton getAgencyButton() {
        return agencyButton;
    }

    public void displayErrorMessage(CustomException e) {
        if(e != null) {
            String message = e.getMessage();
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
