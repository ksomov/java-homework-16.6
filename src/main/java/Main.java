import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {

    public static void main(String[] args) {
        StandardServiceRegistry registry;
        registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        int i = 0;
        while (true) {
            i++;
            try {
                System.out.printf("%-40s%s%n", session.get(Courses.class, i).getName(),
                                               session.get(Courses.class, i).getStudentsCount().toString());
            } catch (NullPointerException exception) {
                break;
            }
        }
        sessionFactory.close();
        registry.close();
    }
}
