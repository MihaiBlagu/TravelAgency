package service;

import exception.CustomException;
import model.User;
import model.VacationPack;
import repo.UserRepo;
import validator.UserValidator;

import javax.swing.*;
import java.util.ArrayList;

public class UserService {

    private final UserRepo userRepo;
    private final UserValidator userValidator;
    private final VacationPackService packService;

    public UserService() {
        this.userRepo = new UserRepo();
        this.userValidator = new UserValidator();
        this.packService = new VacationPackService();
    }

    public void insertUser(String username, String password) throws CustomException {
        try{
            User u = userValidator.validateUser(username, password);
            userRepo.insertUser(u);
        } catch (CustomException e) {
            throw e;
        }
    }

    public User getUserByUsername(String username) {
        if(userValidator.validateUsername(username)){
            return userRepo.getUserByUsername(username);
        }

        return null;
    }

    public JTable getAvailablePacksTable(User user) throws CustomException{
        ArrayList<VacationPack> packs = packService.getAvailablePacks(user);

        if(!packs.isEmpty()) {
            return packService.getAvailablePacksTable(packs);
        } else {
            throw new CustomException("No available packs!");
        }
    }

    public JTable getFilteredPacksTable(boolean priceChecked, String price,
                                        boolean destinationChecked, String destination, boolean startDateChecked,
                                        String startDate, boolean endDateChecked, String endDate) {
        ArrayList<VacationPack> packs = packService.getFilteredPacks(priceChecked, price,
            destinationChecked, destination, startDateChecked,
            startDate, endDateChecked, endDate);

        if(!packs.isEmpty()) {
            return packService.getAvailablePacksTable(packs);
        } else {
            throw new CustomException("No available packs that match your filters!");
        }
    }

    public void addNewBooking(User user, String packName) throws CustomException {
        try {
            VacationPack pack = packService.getPackByName(packName);

            pack.addNewUser(user);

            userRepo.addNewBooking(user, pack);
        } catch (CustomException e) {
            throw e;
        }
    }

    public JTable getBookedVacations(User user) throws CustomException {
        ArrayList<VacationPack> packs = packService.getBookedVacations(user);

        if (!packs.isEmpty()) {
            return packService.getAvailablePacksTable(packs);
        } else {
            throw new CustomException("You have no booked packs!");
        }
    }
}
