package repo;

import exception.CustomException;
import model.Destination;
import model.User;
import model.VacationPack;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VacationPackRepo {
    private EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("travel.agency");
    private EntityManager entityManager;

    public VacationPackRepo() {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public ArrayList<VacationPack> getAvailablePacks(User user) throws CustomException {
        entityManager.getTransaction().begin();
        try{
            String query = "SELECT p FROM VacationPack p " +
                    "WHERE p.startDate > curdate() " +
                    "AND (p.status = 2 or p.status = 1)";
            if(/*user.getBookedPacks() == null ||*/ !user.getBookedPacks().isEmpty()){
                query += " AND p NOT IN :bookedPacks";
            }

            Query q = entityManager.createQuery(query, VacationPack.class);

            if(/*user.getBookedPacks() == null ||*/ !user.getBookedPacks().isEmpty()){
                q.setParameter("bookedPacks", user.getBookedPacks());
            }

            List<VacationPack> packs = q.getResultList();
            entityManager.getTransaction().commit();
            return new ArrayList<>(packs);
        } catch (NoResultException e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw new CustomException("No available packs!");
        }
    }

    public ArrayList<VacationPack> getBookedPacks(User user) throws CustomException {
        entityManager.getTransaction().begin();
        try{
            List<VacationPack> packs = entityManager.createQuery(
                        "SELECT p FROM VacationPack p " +
                        "WHERE p.startDate > curdate() AND " +
                        "p IN :bookedPacks",
                        VacationPack.class)
                    .setParameter("bookedPacks", user.getBookedPacks())
                    .getResultList();
            entityManager.getTransaction().commit();
            return new ArrayList<>(packs);
        } catch (NoResultException e){
            entityManager.getTransaction().rollback();
            throw new CustomException("No booked packs!");
        }
    }

    public ArrayList<VacationPack> getFilteredPacks(boolean priceChecked, Double price, boolean destinationChecked,
                                                    String destination, boolean startDateChecked, LocalDate startDate, boolean endDateChecked, LocalDate endDate)
            throws CustomException {
        entityManager.getTransaction().begin();
        Destination d;
        try{
            d = entityManager.createQuery(
                    "SELECT d FROM Destination d " +
                    "WHERE d.name = :destName",
                    Destination.class
                )
                .setParameter("destName", destination)
                .getSingleResult();
            entityManager.getTransaction().commit();
        } catch (NoResultException e){
            entityManager.getTransaction().rollback();
            throw new CustomException("No destinations with that name exist!");
        }

        String query = "Select p from VacationPack p where ";
        if(priceChecked) {
            query += "p.price < :price AND ";
        }
        if(destinationChecked) {
            query += "p.destination = :destination AND ";
        }
        if(startDateChecked) {
            query += "p.startDate > :startDate AND ";
        }
        if(endDateChecked) {
            query += "p.endDate < :endDate AND ";
        }
        query += "p.startDate > curdate() AND (p.status = 2 or p.status = 1) AND p.packId NOT MEMBER OF p.users";

        entityManager.getTransaction().begin();
        try{
            Query q = entityManager.createQuery(query, VacationPack.class);
            if(priceChecked) {
                q.setParameter("price", price);
            }
            if(destinationChecked) {
                q.setParameter("destination", d);
            }
            if(startDateChecked) {
                q.setParameter("startDate", startDate);
            }
            if(endDateChecked) {
                q.setParameter("endDate", endDate);
            }
            List<VacationPack> packs = q.getResultList();
            entityManager.getTransaction().commit();
            return new ArrayList<>(packs);
        } catch (NoResultException e){
            entityManager.getTransaction().rollback();
            throw new CustomException("No destinations with that name exist!");
        }
    }

    public ArrayList<VacationPack> getListedPacks() throws CustomException{
        entityManager.getTransaction().begin();
        try{
            List<VacationPack> packs = entityManager.createQuery(
                    "SELECT p FROM VacationPack p " +
                    "WHERE p.startDate > curdate() ",
                    VacationPack.class)
                    .getResultList();
            entityManager.getTransaction().commit();
            return new ArrayList<>(packs);
        } catch (NoResultException e){
            entityManager.getTransaction().rollback();
            throw new CustomException("No available packs!");
        }
    }

    public void insertPack(VacationPack pack) {
        try {
            entityManager.getTransaction().begin();

            entityManager.persist(pack);

            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw new CustomException("Error inserting pack!");
        }
    }

    public void deletePack(String name) throws CustomException {
        entityManager.getTransaction().begin();
        try{
            entityManager.createQuery("DELETE FROM VacationPack p WHERE p.name = :name")
                    .setParameter("name", name).executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e){
            entityManager.getTransaction().rollback();
            throw new CustomException("No packs matching that name found!");
        }
    }

    public VacationPack getPackByName(String name) throws CustomException {
        entityManager.getTransaction().begin();
        try{
            VacationPack p = entityManager.createQuery("SELECT p FROM VacationPack p WHERE p.name = :name", VacationPack.class)
                    .setParameter("name", name)
                    .getSingleResult();
            entityManager.getTransaction().commit();
            return p;
        } catch (NoResultException e){
            entityManager.getTransaction().rollback();
            throw new CustomException("No pack with that name found!");
        }
    }

    public void updatePack(String oldPackName, VacationPack pack) throws CustomException {
        entityManager.getTransaction().begin();
        try{
            entityManager.createQuery("UPDATE VacationPack p Set p.name = :name, " +
                    "p.destination = :destination, p.price = :price, p.startDate = :sd, " +
                    "p.endDate = :ed, p.maxNbOfPeople = :nbPpl, p.extras = :extras " +
                    "WHERE p.name = :oldName")
                    .setParameter("name", pack.getName())
                    .setParameter("destination", pack.getDestination())
                    .setParameter("price", pack.getPrice())
                    .setParameter("sd", pack.getStartDate())
                    .setParameter("ed", pack.getEndDate())
                    .setParameter("nbPpl", pack.getMaxNbOfPeople())
                    .setParameter("extras", pack.getExtras())
                    .setParameter("oldName", oldPackName)
                    .executeUpdate();

            entityManager.getTransaction().commit();
        } catch (NoResultException e){
            entityManager.getTransaction().rollback();
            throw new CustomException("Pack could not be updated!");
        }
    }


}
