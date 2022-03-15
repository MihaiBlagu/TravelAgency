package validator;

import exception.CustomException;
import model.Destination;
import model.VacationPack;
import utils.Status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

public class VacationPackValidator {

    public ArrayList<Object> validateOptions(boolean priceChecked, String price,
            boolean startDateChecked, String startDate, boolean endDateChecked, String endDate)
            throws CustomException {

        ArrayList<Object> values = new ArrayList<>();

        if(priceChecked) {
            try {
                Double p = Double.parseDouble(price);
                if(p < 0.0) {
                    throw new CustomException("Price can't be negative!");
                }
                values.add(p);
            } catch (NumberFormatException e) {
                throw new CustomException("Invalid price!");
            }
        } else {
            values.add(null);
        }

        String dateRegex = "([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9])|((1)[0-2]))(\\-)\\d{4}$";
        if(startDateChecked) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            dateFormat.setLenient(false);
            if(!startDate.matches(dateRegex)){
                throw new CustomException("Invalid start date! Try this format: dd-mm-yyyy!");
            }
            try {
                LocalDate d = dateFormat.parse(startDate.trim()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                values.add(d);
            } catch (ParseException pe) {
                throw new CustomException("Invalid start date! Try this format: 'dd-mm-yyyy'");
            }
        } else {
            values.add(null);
        }
        if(endDateChecked) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            dateFormat.setLenient(false);
            if(!endDate.matches(dateRegex)){
                throw new CustomException("Invalid end date! Try this format: dd-mm-yyyy!");
            }
            try {
                LocalDate d = dateFormat.parse(endDate.trim()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                values.add(d);
            } catch (ParseException pe) {
                throw new CustomException("Invalid end date! Try this format: 'dd-mm-yyyy'");
            }
        } else {
            values.add(null);
        }

        return values;
    }

    public VacationPack validatePack(Destination destination, String name, String price, String startDate, String endDate,
             String maxNbOfPeople, String extras) throws CustomException{
        if(destination == null) {
            throw new CustomException("Destination is null!");
        }

        if(name == null) {
            throw new CustomException("Vacation Pack name is null!");
        }
        if(name.equals("")) {
            throw new CustomException("Vacation pack name can't be empty!");
        }

        if(price == null) {
            throw new CustomException("Price is null!");
        }
        if(price.equals("")){
            throw new CustomException("Price can't be empty!");
        }
        Double p;
        try {
            p = Double.parseDouble(price);
        } catch (NumberFormatException e) {
            throw new CustomException("invalid price!");
        }
        String dateRegex = "([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9])|((1)[0-2]))(\\-)\\d{4}$";
        if(startDate == null) {
            throw new CustomException("Start date is null!");
        }
        if(startDate.equals("")){
            throw new CustomException("Start date can't be empty!");
        }
        if(!startDate.matches(dateRegex)){
            throw new CustomException("Invalid start date! Try this format: dd-mm-yyyy!");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        LocalDate sd;
        try {
            sd = dateFormat.parse(startDate.trim()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e) {
            throw new CustomException("Invalid start date! Try this format: dd-mm-yyyy!");
        }

        if(endDate == null) {
            throw new CustomException("End date is null!");
        }
        if(endDate.equals("")){
            throw new CustomException("End date can't be empty!");
        }
        if(!endDate.matches(dateRegex)){
            throw new CustomException("Invalid end date! Try this format: dd-mm-yyyy!");
        }
        LocalDate ed;
        try {
            ed = dateFormat.parse(endDate.trim()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e) {
            throw new CustomException("Invalid end date! Try this format: dd-mm-yyyy!");
        }

        if(maxNbOfPeople == null) {
            throw new CustomException("Number of people is null!");
        }
        if(maxNbOfPeople.equals("")) {
            throw new CustomException("Number of people can't pe empty");
        }
        Integer mxPpl;
        try {
            mxPpl = Integer.parseInt(maxNbOfPeople);
        } catch (NumberFormatException e) {
            throw new CustomException("Invalid number of people!");
        }

        if(extras == null) {
            throw new CustomException("Extras can't be null!");
        }

        return new VacationPack(name, destination, p, sd, ed, mxPpl, extras, Status.NOT_BOOKED);

    }

    public void validatePackName(String name) throws CustomException {
        if(name == null) {
            throw new CustomException("Name is null!");
        }
        if(name.equals("")){
            throw new CustomException("Pack name can't be empty!");
        }
    }

}
