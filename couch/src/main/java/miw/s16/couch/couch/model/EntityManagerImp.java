//package miw.s16.couch.couch.model;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;

/**in progress
 * @author: @Adamantia


public class EntityManagerImp {

    public static void main(String[] args) {

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );

        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );

        User user = new User();

        entitymanager.persist(user);
        entitymanager.getTransaction( ).commit( );

        entitymanager.close( );
        emfactory.close( );
    }
}
**/