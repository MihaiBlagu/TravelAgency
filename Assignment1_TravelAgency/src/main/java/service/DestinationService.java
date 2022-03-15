package service;

import exception.CustomException;
import model.Destination;
import repo.DestinationRepo;
import validator.DestinationValidator;

import java.util.ArrayList;

public class DestinationService {

    private final DestinationRepo destinationRepo;
    private final DestinationValidator destinationValidator;

    public DestinationService() {
        this.destinationRepo = new DestinationRepo();
        this.destinationValidator = new DestinationValidator();
    }

    public void insertDestination(String name) throws CustomException {
        try {
            Destination d = destinationValidator.validateNewDestination(name);
            destinationRepo.insertDestination(d);
        } catch (CustomException e) {
            throw e;
        }
    }

    public ArrayList<Destination> getDestinations() throws CustomException {
        try {
            return destinationRepo.getDestinations();
        } catch (CustomException e) {
            throw e;
        }
    }

    public Destination getDestinationByName(String name) throws CustomException {
        try {
            return destinationRepo.getDestinationByName(name);
        } catch (CustomException e) {
            throw e;
        }
    }

    public void deleteDestination(String name) throws CustomException {
        try {
            destinationRepo.deleteDestination(name);
        } catch (CustomException e) {
            throw e;
        }
    }
}
