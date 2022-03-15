package validator;

import exception.CustomException;
import model.User;

public class UserValidator {

    public UserValidator() {
    }

    public User validateUser(String username, String password) throws CustomException {
        if(username == null){
            throw new CustomException("Username null!");
        }
        if(username.equals("")){
            throw new CustomException("Username can not be empty!");
        }
        if(username.length() < 5) {
            throw new CustomException("Username must be at least 5 characters long!");
        }
        if(password == null){
            throw new CustomException("Password null!");
        }
        if(password.equals("")) {
            throw new CustomException("Password can not be empty!");
        }
        if(password.length() < 5) {
            throw new CustomException("Password must be at least 5 characters long");
        }

        return new User(username, password);
    }

    public boolean validateUsername(String username){
        if(username == null){
            System.out.println("Null username");
            return false;
        }
        if(username.equals("")){
            System.out.println("Invalid username!");
            return false;
        }

        return true;
    }
}
