package validator;

import exception.CustomException;
import model.Destination;

public class DestinationValidator {

    public DestinationValidator() {
    }

    public Destination validateNewDestination(String name) {
        if(name == null) {
            throw new CustomException("Destination name null!");
        }
        if(name.equals("")) {
            throw new CustomException("Destination name can't be empty!");
        }
        return new Destination(name);
    }
}
