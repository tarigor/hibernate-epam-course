package by.epam.hibernate.databaseController;

import by.epam.hibernate.entity.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.lang.reflect.InvocationTargetException;

public class DatabaseController {
    private Session session;

    public DatabaseController() {
    }

    public void createHibernateSession(Class clazz) {
        SessionFactory sessionFactory;
        ServiceRegistry registry;
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Employee.class).configure();
            registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(registry);
        } catch (Throwable ex) {
            System.err.println("Failed to create session factory object" + ex);
            throw new ExceptionInInitializerError(ex);
        }
        this.session = sessionFactory.openSession();
    }

    public void recordsAdd(Class clazz) {
        Short msgId = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Object o = clazz.getClass().getConstructor().newInstance();
            o.getClass().getMethod("paramInit").invoke(this);
            msgId = (Short) session.save(o);
            System.out.println("msgId->" + msgId);
            transaction.commit();
        } catch (HibernateException | NoSuchMethodException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
//    Object o = clazz.getClass().getConstructor().newInstance();
//            o.getClass().getMethod("paramInit");