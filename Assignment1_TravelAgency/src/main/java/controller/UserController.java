package controller;

import exception.CustomException;
import model.User;
import service.UserService;
import view.UserLogInPage;
import view.UserPage;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicReference;

public class UserController {

    private final UserService userService;
    private final AtomicReference<User> user;

    public UserController() {
        userService = new UserService();
        user = new AtomicReference<>();
    }

    public void run(){
        UserLogInPage userLogInPage = new UserLogInPage();
        userLogInPage.initialize();
        initializeLogInListeners(userLogInPage);
    }

    private void initializeLogInListeners(UserLogInPage logInPage) {
        logInPage.getLogInButton().addActionListener(a -> {
            String username = logInPage.getUsernameTextField().getText();
            String password = logInPage.getPasswordTextField().getText();

            try {
                user.set(userService.getUserByUsername(username));
                if(user.get().getPassword().equals(password)){
                    UserPage userPage = new UserPage();
                    userPage.initialize();
                    initializeUserPageListeners(userPage);
                    logInPage.setVisible(false);
                } else {
                    logInPage.displayErrorMessage(new CustomException("Invalid Password! Try again!"));
                }
            } catch (CustomException e) {
                logInPage.displayErrorMessage(e);
            }
        });

        logInPage.getRegisterButton().addActionListener(a -> {
            String username = logInPage.getUsernameTextField().getText();
            String password = logInPage.getPasswordTextField().getText();

            try {
                userService.insertUser(username, password);
                logInPage.displayInformationMessage("Account successfully created! You can now log in!");
            } catch (CustomException e) {
                logInPage.displayErrorMessage(e);
            }
        });

        logInPage.getExitButton().addActionListener(a -> {
            logInPage.dispose();
        });
    }

    //change to private
    private void initializeUserPageListeners(UserPage userPage) {
        userPage.getViewAllButton().addActionListener(a -> {
            try{
                JTable t = userService.getAvailablePacksTable(user.get());
                userPage.addTableToPane(t);
            } catch (CustomException e) {
                userPage.displayErrorMessage(e);
            }
        });

        userPage.getFilterButton().addActionListener(a -> {
            try {
                JTable t = userService.getFilteredPacksTable(userPage.getPriceCB().isSelected(),
                        userPage.getPriceTF().getText(), userPage.getDestinationCB().isSelected(),
                        userPage.getDestinationTF().getText(), userPage.getStartDateCB().isSelected(),
                        userPage.getStartDateTF().getText(), userPage.getEndDateCB().isSelected(),
                        userPage.getEndDateTF().getText());
                userPage.addTableToPane(t);
            } catch (CustomException e) {
                userPage.displayErrorMessage(e);
            }
        });

        userPage.getBookButton().addActionListener(a -> {
            JTable t = userPage.getTable();
            for(int i = 0; i < t.getRowCount(); i++) {
                if((boolean) t.getValueAt(i, 6)){
                    try {
                        userService.addNewBooking(user.get(), (String)t.getValueAt(i, 0));

                        userPage.displayInformationMessage("Booking successfully registered!");
                    } catch (CustomException e) {
                        userPage.displayErrorMessage(e);
                    }
                }
            }
        });

        userPage.getViewBooked().addActionListener(a -> {
            try {
                JTable t = userService.getBookedVacations(user.get());
                userPage.addTableToPane(t);
            } catch (CustomException e) {
                userPage.displayErrorMessage(e);
            }
        });

        userPage.getExitButton().addActionListener(a -> {
            userPage.dispose();
        });
    }

//    public void insertUser(String username, String password) throws CustomException {
//        try {
//
//        } catch (CustomException e) {
//            throw e;
//        }
//    }
//
//    public User getUserByUsername(String username) {
//        return userService.getUserByUsername(username);
//    }
}
