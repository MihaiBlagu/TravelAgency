package controller;

import exception.CustomException;
import model.Destination;
import model.VacationPack;
import service.AgencyService;
import service.DestinationService;
import service.VacationPackService;
import view.AgencyPage;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

public class AgencyController {

    private final AgencyService agencyService;
    private final VacationPackService packService;
    private final DestinationService destinationService;

    public AgencyController() {
        this.agencyService = new AgencyService();
        this.packService = new VacationPackService();
        this.destinationService = new DestinationService();
    }

    public void run() {
        AgencyPage agencyPage = new AgencyPage();
        ArrayList<Destination> destinations = agencyService.getDestinations();
        agencyPage.initialize(destinations);
        initializeListeners(agencyPage);
    }

    private void initializeListeners(AgencyPage agencyPage) {
        agencyPage.getAddDestButton().addActionListener(a -> {
            try {
                agencyService.insertDestination(agencyPage.getAddDestTF().getText());
                agencyPage.refreshCombos(agencyService.getDestinations());
                agencyPage.displayInformationMessage("Destination successfully added!");
            } catch (CustomException e) {
                agencyPage.displayErrorMessage(e);
            }
        });

        agencyPage.getDeleteDestButton().addActionListener(a -> {
            try {
                agencyService.deleteDestination(agencyPage.getDeleteDestCombo().getSelectedItem().toString());
                agencyPage.refreshCombos(agencyService.getDestinations());
                agencyPage.displayInformationMessage("Destination successfully deleted!");
            } catch (CustomException e) {
                agencyPage.displayErrorMessage(e);
            }
        });

        agencyPage.getAddPackButton().addActionListener(a -> {
            try {

                agencyService.insertVacationPack(agencyPage.getDestCombo().getSelectedItem().toString(), agencyPage.getPackNameTF().getText(), agencyPage.getPriceTF().getText(),
                        agencyPage.getStartDateTF().getText(), agencyPage.getEndDateTF().getText(),
                        agencyPage.getNbPeopleTF().getText(), agencyPage.getExtrasTF().getText());

                agencyPage.displayInformationMessage("Package successfully added!");
            } catch (CustomException e) {
                agencyPage.displayErrorMessage(e);
            }
        });

        // in order to edit a pack, the agency operator will first have to
        // - insert the pack name
        // - press edit button
        // - change the fields they want to change
        // - press the commit changes button
        AtomicReference<VacationPack> p = new AtomicReference<>();
        agencyPage.getEditPackButton().addActionListener( a -> {
            JButton btn = agencyPage.getEditPackButton();
            String text = btn.getText();
            if (text.equals("Edit Package")) {
                // get package with given name and display contents in TFs
                try {
                    p.set(agencyService.getPackByName(agencyPage.getPackNameTF().getText()));

                    agencyPage.getDestCombo().setSelectedItem(p.get().getDestination().getName());
                    agencyPage.getPriceTF().setText(p.get().getPrice().toString());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date d = format.parse(p.get().getStartDate().toString());
                        format.applyPattern("dd-MM-yyyy");
                        String date = format.format(d);
                        agencyPage.getStartDateTF().setText(date);
                    } catch (ParseException e) {
                        agencyPage.displayErrorMessage(e);
                    }
                    try {
                        format.applyPattern("yyyy-MM-dd");
                        Date d = format.parse(p.get().getEndDate().toString());
                        format.applyPattern("dd-MM-yyyy");
                        String date = format.format(d);
                        agencyPage.getEndDateTF().setText(date);
                    } catch (ParseException e) {
                        agencyPage.displayErrorMessage(e);
                    }
                    agencyPage.getNbPeopleTF().setText(p.get().getMaxNbOfPeople().toString());
                    agencyPage.getExtrasTF().setText(p.get().getExtras());

                    btn.setText("Commit Package Changes");
                } catch (CustomException e) {
                    agencyPage.displayErrorMessage(e);
                }
            } else {
                // execute update query
                try {
                    agencyService.editVacationPack(p.get().getName(), agencyPage.getDestCombo().getSelectedItem().toString(),
                            agencyPage.getPackNameTF().getText(), agencyPage.getPriceTF().getText(),
                            agencyPage.getStartDateTF().getText(), agencyPage.getEndDateTF().getText(),
                            agencyPage.getNbPeopleTF().getText(), agencyPage.getExtrasTF().getText());

                    btn.setText("Edit Package");

                    agencyPage.displayInformationMessage("Package successfully updated!");
                } catch (CustomException e) {
                    agencyPage.displayErrorMessage(e);
                }
            }
        });

        agencyPage.getDeletePackButton().addActionListener(a -> {
            try {
                packService.deletePack(agencyPage.getPackNameTF().getText());
                agencyPage.displayInformationMessage("Package successfully deleted!");
            } catch (CustomException e) {
                agencyPage.displayErrorMessage(e);
            }
        });

        agencyPage.getViewPacksButton().addActionListener(a -> {
            try {
                ArrayList<VacationPack> packs = packService.getListedPacks();
                JTable t = packService.getListedPacksTable(packs);

                agencyPage.addTableToPane(t);
            } catch (CustomException e) {
                agencyPage.displayErrorMessage(e);
            }
        });

        agencyPage.getExitButton().addActionListener(a -> {
            agencyPage.dispose();
        });
    }


}
