package view;

import model.Destination;

import javax.swing.*;
import java.util.ArrayList;

public class AgencyPage extends JFrame{

    private JPanel mainPanel;

    private JLabel addDestLabel;
    private JTextField addDestTF;
    private JButton addDestButton;
    private JLabel deleteDestLabel;
    private JComboBox<String> deleteDestCombo;
    private JButton deleteDestButton;

    private JLabel packLabel;

    private JLabel destLabel;
    private JComboBox<String> destCombo;
    private JLabel packNameLabel;
    private JTextField packNameTF;
    private JLabel priceLabel;
    private JTextField priceTF;
    private JLabel startDateLabel;
    private JTextField startDateTF;
    private JLabel endDateLabel;
    private JTextField endDateTF;
    private JLabel nbPeopleLabel;
    private JTextField nbPeopleTF;
    private JLabel extrasLabel;
    private JTextField extrasTF;

    private JButton addPackButton;
    private JButton editPackButton;
    private JButton deletePackButton;
    private JButton viewPacksButton;

    private JScrollPane pane;

    private JButton exitButton;

    public void initialize(ArrayList<Destination> destinations) {
        this.setSize(700, 750);
        this.setTitle("Log In as a User");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        initializeForm(destinations);

        this.setContentPane(mainPanel);
        this.setVisible(true);
    }

    private void initializeForm(ArrayList<Destination> destinations) {
        refreshCombos(destinations);
        addDestLabel = new JLabel("Add Destination: ");
        addDestTF = new JTextField();
        addDestButton = new JButton("Add");
        deleteDestLabel = new JLabel("Delete Destination: ");
        deleteDestButton = new JButton("Delete");
        packLabel = new JLabel("Vacation Packs");
        destLabel = new JLabel("Destination: ");
        packNameLabel = new JLabel("Package Name: ");
        packNameTF = new JTextField();
        priceLabel = new JLabel("Price: ");
        priceTF = new JTextField();
        startDateLabel = new JLabel("Start Date(dd-mm-yyyy): ");
        startDateTF = new JTextField();
        endDateLabel = new JLabel("End Date(dd-mm-yyyy): ");
        endDateTF = new JTextField();
        nbPeopleLabel = new JLabel("Max number of people: ");
        nbPeopleTF = new JTextField();
        extrasLabel = new JLabel("Extra Details: ");
        extrasTF = new JTextField();
        addPackButton = new JButton("Add package");
        editPackButton = new JButton("Edit Package");
        deletePackButton = new JButton("Delete Package");
        viewPacksButton = new JButton("View Listed Packages");
        pane = new JScrollPane();
        exitButton = new JButton("Exit");

        addDestLabel.setBounds(15, 15, 200, 20);
        addDestTF.setBounds(215, 15, 200, 20);
        addDestButton.setBounds(420, 15, 200, 20);
        deleteDestLabel.setBounds(15, 40, 200, 20);
        deleteDestCombo.setBounds(215, 40, 200, 20);
        deleteDestButton.setBounds(420, 40, 200, 20);
        packLabel.setBounds(15, 80, 100, 20);
        destLabel.setBounds(15, 110, 140, 20);
        destCombo.setBounds(160, 110, 140, 20);
        packNameLabel.setBounds(15, 135, 140, 20);
        packNameTF.setBounds(160, 135, 140, 20);
        priceLabel.setBounds(15, 160, 140, 20);
        priceTF.setBounds(160, 160, 140, 20);
        startDateLabel.setBounds(15, 185, 140, 20);
        startDateTF.setBounds(160, 185, 140, 20);
        endDateLabel.setBounds(15, 210, 140, 20);
        endDateTF.setBounds(160, 210, 140, 20);
        nbPeopleLabel.setBounds(15, 235, 140, 20);
        nbPeopleTF.setBounds(160, 235, 140, 20);
        extrasLabel.setBounds(15, 260, 140, 20);
        extrasTF.setBounds(160, 260, 140, 20);
        addPackButton.setBounds(350, 160, 200, 20);
        editPackButton.setBounds(350, 185, 200, 20);
        deletePackButton.setBounds(350, 210, 200, 20);
        viewPacksButton.setBounds(350, 235, 200, 20);
        pane.setBounds(15, 295, 650, 300);
        exitButton.setBounds(15, 685, 100, 20);

        mainPanel.add(addDestLabel);
        mainPanel.add(addDestTF);
        mainPanel.add(deleteDestCombo);
        mainPanel.add(addDestButton);
        mainPanel.add(deleteDestLabel);
        mainPanel.add(deleteDestButton);
        mainPanel.add(packLabel);
        mainPanel.add(destLabel);
        mainPanel.add(destCombo);
        mainPanel.add(packNameLabel);
        mainPanel.add(packNameTF);
        mainPanel.add(priceLabel);
        mainPanel.add(priceTF);
        mainPanel.add(startDateLabel);
        mainPanel.add(startDateTF);
        mainPanel.add(endDateLabel);
        mainPanel.add(endDateTF);
        mainPanel.add(nbPeopleLabel);
        mainPanel.add(nbPeopleTF);
        mainPanel.add(extrasLabel);
        mainPanel.add(extrasTF);
        mainPanel.add(addPackButton);
        mainPanel.add(editPackButton);
        mainPanel.add(deletePackButton);
        mainPanel.add(viewPacksButton);
        mainPanel.add(pane);
        mainPanel.add(exitButton);

        //destCombo.setBounds(15, 15, 200, 20);

//        destCombo.addActionListener(a -> {
//            System.out.println(destCombo.getSelectedItem().toString());
//        });

        //panel.add(destCombo);

    }

    public JTextField getAddDestTF() {
        return addDestTF;
    }

    public JButton getAddDestButton() {
        return addDestButton;
    }

    public JComboBox<String> getDeleteDestCombo() {
        return deleteDestCombo;
    }

    public JButton getDeleteDestButton() {
        return deleteDestButton;
    }

    public JComboBox<String> getDestCombo() {
        return destCombo;
    }

    public JTextField getPriceTF() {
        return priceTF;
    }

    public JTextField getStartDateTF() {
        return startDateTF;
    }

    public JTextField getEndDateTF() {
        return endDateTF;
    }

    public JTextField getNbPeopleTF() {
        return nbPeopleTF;
    }

    public JTextField getExtrasTF() {
        return extrasTF;
    }

    public JButton getAddPackButton() {
        return addPackButton;
    }

    public JButton getEditPackButton() {
        return editPackButton;
    }

    public JButton getDeletePackButton() {
        return deletePackButton;
    }

    public JButton getViewPacksButton() {
        return viewPacksButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JTextField getPackNameTF() {
        return packNameTF;
    }

    public void refreshCombos(ArrayList<Destination> destinations) {
        if(destCombo != null) {
            mainPanel.remove(destCombo);
        }
        if(deleteDestCombo != null) {
            mainPanel.remove(deleteDestCombo);
        }

        destCombo = new JComboBox<>();
        deleteDestCombo = new JComboBox<>();

        for(Destination d : destinations) {
            destCombo.addItem(d.getName());
            deleteDestCombo.addItem(d.getName());
        }

        deleteDestCombo.setBounds(215, 40, 200, 20);
        destCombo.setBounds(160, 110, 140, 20);

        mainPanel.add(destCombo);
        mainPanel.add(deleteDestCombo);
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
//        pane = new JScrollPane();
//        pane.setBounds(15, 295, 650, 300);
//        mainPanel.add(pane);
        pane.setViewportView(t);
    }

    public JTable getTable() {
        return (JTable) pane.getViewport().getView();
    }
}
