package repo;

import exception.CustomException;
import model.Destination;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class DestinationRepo {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("travel.agency");
    private final EntityManager entityManager;

    public DestinationRepo() {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void insertDestination(Destination d) throws CustomException {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(d);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            throw new CustomException("A Destination with that name already exists!");
        }
    }

    public ArrayList<Destination> getDestinations() throws CustomException {
        entityManager.getTransaction().begin();
        try{
            List<Destination> destinations = entityManager.createQuery("SELECT d FROM Destination d", Destination.class)
                    .getResultList();
            entityManager.getTransaction().commit();
            return (ArrayList)destinations;
        } catch (NoResultException e){
            entityManager.getTransaction().rollback();
            throw new CustomException("No destinations found!");
        }
    }

    public void deleteDestination(String name) throws CustomException {
        try{
            Destination d = getDestinationByName(name);

            entityManager.getTransaction().begin();
            try{
                //entityManager.createQuery("DELETE FROM Destination d WHERE d.name = :name")
                //        .setParameter("name", name).executeUpdate();
                entityManager.remove(d);
                entityManager.getTransaction().commit();
            } catch (Exception e){
                entityManager.getTransaction().rollback();
                e.printStackTrace();
                throw new CustomException("Error in deleting destination!");
            }
        } catch (CustomException e) {
            throw e;
        }


    }

    public Destination getDestinationByName(String name) throws CustomException {
        entityManager.getTransaction().begin();
        try{
            Destination d = entityManager.createQuery("SELECT d FROM Destination d WHERE d.name = :name", Destination.class)
                    .setParameter("name", name)
                    .getSingleResult();
            entityManager.getTransaction().commit();
            return d;
        } catch (NoResultException e){
            entityManager.getTransaction().rollback();
            throw new CustomException("No destination with that name found!");
        }
    }
}
