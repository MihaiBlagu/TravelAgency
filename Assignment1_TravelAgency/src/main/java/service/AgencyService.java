package service;

import exception.CustomException;
import model.Destination;
import model.VacationPack;

import java.util.ArrayList;

public class AgencyService {

    private final DestinationService destinationService;
    private final VacationPackService packService;

    public AgencyService() {
        this.destinationService = new DestinationService();
        this.packService = new VacationPackService();
    }

    public void insertDestination(String name) throws CustomException {
        try {
            destinationService.insertDestination(name);
        } catch (CustomException e) {
            throw e;
        }
    }

    public void insertVacationPack(String destinationName, String name, String price, String startDate, String endDate,
            String maxNbOfPeople, String extras) throws CustomException {

        try {
            Destination d = destinationService.getDestinationByName(destinationName);
            packService.insertPack(d, name, price, startDate, endDate, maxNbOfPeople, extras);
        } catch (CustomException e) {
            throw e;
        }
    }

    public void deleteDestination(String name) throws CustomException {
        try {
            destinationService.deleteDestination(name);
        } catch (CustomException e) {
            throw e;
        }
    }

    public void editVacationPack(String oldPackName, String destinationName, String name, String price, String startDate, String endDate,
             String maxNbOfPeople, String extras) throws CustomException {

        try {
            Destination d = destinationService.getDestinationByName(destinationName);
            packService.editPack(oldPackName, d, name, price, startDate, endDate, maxNbOfPeople, extras);
        } catch (CustomException e) {
            throw e;
        }
    }

    public ArrayList<Destination> getDestinations() throws CustomException {
        try {
            return destinationService.getDestinations();
        } catch (CustomException e) {
            throw e;
        }
    }

    public VacationPack getPackByName(String name) throws CustomException {
        try {
            return packService.getPackByName(name);
        } catch (CustomException e) {
            throw e;
        }
    }
}
