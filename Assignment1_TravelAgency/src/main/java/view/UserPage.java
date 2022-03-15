package view;

import javax.swing.*;

public class UserPage extends JFrame {

    private JCheckBox priceCB;
    private JLabel priceLabel;
    private JTextField priceTF;
    private JCheckBox destinationCB;
    private JLabel destinationLabel;
    private JTextField destinationTF;
    private JCheckBox startDateCB;
    private JLabel startDateLabel;
    private JTextField startDateTF;
    private JCheckBox endDateCB;
    private JLabel endDateLabel;
    private JTextField endDateTF;

    private JButton filterButton;
    private JButton viewAllButton;
    private JButton bookButton;
    private JButton viewBooked;

    private JScrollPane pane;

    private JButton exitButton;

    public void initialize() {
        this.setSize(700, 750);
        this.setTitle("User Page");
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
        priceCB = new JCheckBox();
        priceLabel = new JLabel("Price: ");
        priceTF = new JTextField();
        destinationCB = new JCheckBox();
        destinationLabel = new JLabel("Destination: ");
        destinationTF = new JTextField();
        startDateCB = new JCheckBox();
        startDateLabel = new JLabel("Start date(dd-mm-yyyy): ");
        startDateTF = new JTextField();
        endDateCB = new JCheckBox();
        endDateLabel = new JLabel("End date(dd-mm-yyyy): ");
        endDateTF = new JTextField();
        filterButton = new JButton("Filter!");
        viewAllButton = new JButton("View All Packages!");
        bookButton = new JButton("Book checked vacations!");
        viewBooked = new JButton("View booked vacations!");
        pane = new JScrollPane();
        exitButton = new JButton("Exit");

        priceCB.setBounds(15, 15, 20, 20);
        priceLabel.setBounds(35, 15, 140, 20);
        priceTF.setBounds(175, 15, 200, 20);
        destinationCB.setBounds(15, 40, 20, 20);
        destinationLabel.setBounds(35, 40, 140, 20);
        destinationTF.setBounds(175, 40, 200, 20);
        startDateCB.setBounds(15, 65, 20, 20);
        startDateLabel.setBounds(35, 65, 140, 20);
        startDateTF.setBounds(175, 65, 200, 20);
        endDateCB.setBounds(15, 90, 20, 20);
        endDateLabel.setBounds(35, 90, 140, 20);
        endDateTF.setBounds(175, 90, 200, 20);
        filterButton.setBounds(420, 15, 200, 20);
        viewAllButton.setBounds(420, 40, 200, 20);
        bookButton.setBounds(420, 65, 200, 20);
        viewBooked.setBounds(420, 90, 200, 20);
        pane.setBounds(15, 130, 600, 400);
        exitButton.setBounds(15, 685, 100, 20);

        panel.add(priceCB);
        panel.add(priceLabel);
        panel.add(priceTF);
        panel.add(destinationCB);
        panel.add(destinationLabel);
        panel.add(destinationTF);
        panel.add(startDateCB);
        panel.add(startDateLabel);
        panel.add(startDateTF);
        panel.add(endDateCB);
        panel.add(endDateLabel);
        panel.add(endDateTF);
        panel.add(filterButton);
        panel.add(viewAllButton);
        panel.add(bookButton);
        panel.add(viewBooked);
        panel.add(pane);
        panel.add(exitButton);
    }


    public JCheckBox getPriceCB() {
        return priceCB;
    }

    public JTextField getPriceTF() {
        return priceTF;
    }

    public JCheckBox getDestinationCB() {
        return destinationCB;
    }

    public JTextField getDestinationTF() {
        return destinationTF;
    }

    public JCheckBox getStartDateCB() {
        return startDateCB;
    }

    public JTextField getStartDateTF() {
        return startDateTF;
    }

    public JCheckBox getEndDateCB() {
        return endDateCB;
    }

    public JTextField getEndDateTF() {
        return endDateTF;
    }

    public JButton getFilterButton() {
        return filterButton;
    }

    public JButton getViewAllButton() {
        return viewAllButton;
    }

    public JButton getBookButton() {
        return bookButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getViewBooked() {
        return viewBooked;
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

    public void addTableToPane(JTable t) {
        pane.setViewportView(t);
    }

    public JTable getTable() {
        return (JTable) pane.getViewport().getView();
    }
}
