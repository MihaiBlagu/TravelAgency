package repo;

import exception.CustomException;
import model.User;
import model.VacationPack;

import javax.persistence.*;

public class UserRepo {

    private static final EntityManagerFactory entityManagerFactory =
        Persistence.createEntityManagerFactory("travel.agency");
    private final EntityManager entityManager;

    public UserRepo() {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void insertUser(User u) throws CustomException{
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(u);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            throw new CustomException("Username already in use!");
        }

    }

    public User getUserByUsername(String username) throws CustomException{
        entityManager.getTransaction().begin();
        try{
            User u = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
            entityManager.getTransaction().commit();
            return u;
        } catch (NoResultException e){
            entityManager.getTransaction().rollback();
            throw new CustomException("No user with that username found!");
        }
    }

    public void addNewBooking(User user, VacationPack pack) throws CustomException {
        try {
            entityManager.getTransaction().begin();

            user.addBookedPack(pack);
            entityManager.merge(pack);
            entityManager.persist(user);


            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw new CustomException("Couldn't book pack: " + pack.getName());
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw new CustomException("Some error occurred!");
        }
    }
}
