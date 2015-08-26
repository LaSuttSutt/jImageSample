package ch.nag.ImageSample.logic;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Stateless
public class DataAccessManager  {

    //region #Declarations

    @PersistenceContext(unitName = "IMAGE_SAMPLE")
    private EntityManager entityManager;

    //endregion

    //region #Public Methods

    // Stores the given object
    @TransactionAttribute
    public <T> void persist(T objectToPersist) {

        entityManager.persist(objectToPersist);
    }

    // Updates the given object
    @TransactionAttribute
    public <T> void update(T objectToUpdate) {

        entityManager.merge(objectToUpdate);
    }

    // Deletes the given object
    @TransactionAttribute
    public <T> void remove(T objectToRemove) {

        entityManager.remove(objectToRemove);
    }

    // Deletes the object with the given id of the given type
    @TransactionAttribute
    public <T> void deleteEntityById(Class<T> typeClass, UUID id) {

        T entity = entityManager.getReference(typeClass, id.toString());
        entityManager.remove(entity);
    }

    // Deletes all stored objects of the given type
    @TransactionAttribute
    public <T> void deleteAllEntities(Class<T> typeClass) {

        Query query = entityManager.createQuery("DELETE FROM " + typeClass.getSimpleName());
        query.executeUpdate();
    }

    // Deletes all stored objects of the given type that matches the given query
    @TransactionAttribute
    public void deleteEntites(String givenQuery, Map<String, String> queryParameters) {

        Query query = entityManager.createQuery(givenQuery);
        queryParameters.forEach(query::setParameter);

        query.executeUpdate();
    }

    // Returns a object of the given type with the given id
    public <T> T getEntityById(Class<T> typeClass, UUID id) {

        return entityManager.find(typeClass, id.toString());
    }

    // Returns a object of the given type with the given id
    public <T> T getEntityById(Class<T> typeClass, int id) {

        return entityManager.find(typeClass, id);
    }

    // Returns all stored objects of the given type
    public <T> List<T> getAllEntities(Class<T> typeClass) {

        TypedQuery<T> query = entityManager.createQuery
                ("SELECT p FROM " + typeClass.getSimpleName() + " p", typeClass);
        return query.getResultList();
    }

    // Returns all stored objects of the given type that matches the given query
    public <T> List<T> getEntities(Class<T> typeClass, String givenQuery, Map<String, String> queryParameters) {

        TypedQuery<T> query = entityManager.createQuery(givenQuery, typeClass);
        queryParameters.forEach(query::setParameter);

        return query.getResultList();
    }

    //endregion
}
