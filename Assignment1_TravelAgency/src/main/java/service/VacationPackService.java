package service;

import exception.CustomException;
import model.Destination;
import model.User;
import model.VacationPack;
import repo.VacationPackRepo;
import validator.VacationPackValidator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.ArrayList;

public class VacationPackService {
    private final VacationPackRepo packRepo;
    private final VacationPackValidator packValidator;

    public VacationPackService() {
        this.packRepo = new VacationPackRepo();
        this.packValidator = new VacationPackValidator();
    }

    public void insertPack(Destination destination, String name, String price, String startDate, String endDate,
           String maxNbOfPeople, String extras) throws CustomException {

        try {
            VacationPack pack = packValidator.validatePack(destination, name, price, startDate, endDate, maxNbOfPeople, extras);
            packRepo.insertPack(pack);
        } catch (CustomException e) {
            throw e;
        }
    }

    public void deletePack(String name) throws CustomException {
        try {
            packValidator.validatePackName(name);
            packRepo.getPackByName(name);
            packRepo.deletePack(name);
        } catch (CustomException e) {
            throw e;
        }
    }

    public VacationPack getPackByName(String name) throws CustomException {
        try {
            packValidator.validatePackName(name);
            return packRepo.getPackByName(name);
        } catch (CustomException e) {
            throw e;
        }
    }

    public ArrayList<VacationPack> getAvailablePacks(User user) throws CustomException {
        try {
            return packRepo.getAvailablePacks(user);
        } catch (CustomException e) {
            throw e;
        }
    }

    public ArrayList<VacationPack> getListedPacks() throws CustomException {
        try {
            return packRepo.getListedPacks();
        } catch (CustomException e) {
            throw e;
        }
    }

    public ArrayList<VacationPack> getFilteredPacks(boolean priceChecked, String price,
                                                    boolean destinationChecked, String destination, boolean startDateChecked,
                                                    String startDate, boolean endDateChecked, String endDate) {
        try {
            ArrayList<Object> values = packValidator.validateOptions(priceChecked, price,
                    startDateChecked, startDate, endDateChecked, endDate);
            Double p = (Double) values.get(0);
            LocalDate sd = (LocalDate) values.get(1);
            LocalDate ed = (LocalDate) values.get(2);

            return packRepo.getFilteredPacks(priceChecked, p, destinationChecked, destination, startDateChecked, sd, endDateChecked, ed);
        } catch (CustomException e) {
            throw e;
        }
    }

    public JTable getAvailablePacksTable(ArrayList<VacationPack> packs) {
        String[] columns = {"Name", "Price", "Start", "End", "Max People", "Extras", "Book"};

        Object[][] data = new Object[packs.size()][7];
        int i = 0;
        for(VacationPack p : packs) {
            String extras = p.getExtras();
            Object[] row;
            if(extras == null) {
                row = new Object[]{p.getName(), p.getPrice(), p.getStartDate(), p.getEndDate(), p.getMaxNbOfPeople(),
                        "", false};
            } else {
                row = new Object[]{p.getName(), p.getPrice(), p.getStartDate(), p.getEndDate(), p.getMaxNbOfPeople(),
                        extras, false};
            }
            data[i] = row;
            i++;
        }

        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model) {
            public Class getColumnClass(int column) {
                //return Boolean.class
                return getValueAt(0, column).getClass();
            }
        };

        return table;
    }

    public JTable getListedPacksTable(ArrayList<VacationPack> packs) {
        String[] columns = {"Id", "Name", "Destination", "Price", "Start", "End", "Max People", "Extras", "Status"};

        Object[][] data = new Object[packs.size()][columns.length];
        int i = 0;
        for(VacationPack p : packs) {
            String extras = p.getExtras();
            Object[] row;
            if(extras == null) {
                row = new Object[]{p.getId(), p.getName(), p.getDestination().getName(), p.getPrice(), p.getStartDate(), p.getEndDate(), p.getMaxNbOfPeople(),
                        "", p.getStatus()};
            } else {
                row = new Object[]{p.getId(), p.getName(), p.getDestination().getName(), p.getPrice(), p.getStartDate(), p.getEndDate(), p.getMaxNbOfPeople(),
                        extras, p.getStatus()};
            }
            data[i] = row;
            i++;
        }

        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model) {
            public Class getColumnClass(int column) {
                //return Boolean.class
                return getValueAt(0, column).getClass();
            }
        };

        return table;
    }

    public void editPack(String oldPackName, Destination destination, String name, String price, String startDate, String endDate,
         String maxNbOfPeople, String extras) throws CustomException {

        try {
            VacationPack pack = packValidator.validatePack(destination, name, price, startDate, endDate, maxNbOfPeople, extras);
            packRepo.updatePack(oldPackName, pack);
        } catch (CustomException e) {
            throw e;
        }
    }

    public ArrayList<VacationPack> getBookedVacations(User user) throws CustomException {
        try {
            return packRepo.getBookedPacks(user);
        } catch (CustomException e) {
            throw e;
        }
    }
}
