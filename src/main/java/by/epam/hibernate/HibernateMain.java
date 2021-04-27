package by.epam.hibernate;

import by.epam.hibernate.entity.Employee;
import by.epam.hibernate.entity.Phone;
import by.epam.hibernate.utility.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.HashSet;
import java.util.Set;

public class HibernateMain {

//    private Session session = null;
//
//    private Session createHibernateSession() {
//        SessionFactory sessionFactory;
//        ServiceRegistry registry;
//        try {
//            Configuration configuration = new Configuration();
//            configuration.addAnnotatedClass(MessageEntity.class).configure();
//            registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//            sessionFactory = configuration.buildSessionFactory(registry);
//        } catch (Throwable ex) {
//            System.err.println("Failed to create session factory object" + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//        return sessionFactory.openSession();
//    }
//
//    private void recordsAdd() {
//        Short msgId = null;
//        Transaction transaction = null;
//        try {
//            transaction = session.beginTransaction();
//            MessageEntity message = new MessageEntity(readFromConsole());
//            msgId = (Short) session.save(message);
//            System.out.println("msgId->" + msgId);
//            transaction.commit();
//        } catch (HibernateException e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        }
//    }
//
//    private String readFromConsole() {
//        System.out.println("Input a message");
//        return new Scanner(System.in).nextLine();
//    }
//
//    private void recordsRead() {
//        List messages = session.createSQLQuery("select message from Message").list();
//        messages.forEach(m -> System.out.println("Message: " + m));
//    }
//
//    public HibernateMain() {
//        session = createHibernateSession();
//        System.out.println("session->" + session);
//        if (session != null) {
//            recordsAdd();
//            recordsRead();
//            if (session.isOpen()) {
//                session.close();
//            }
//            System.out.println("session state->" + session);
//        }
//    }

    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();

        Employee employee = new Employee();
        employee.setFirstName("Igor");
        employee.setLastName("Taren");
        employee.setSalary(4500);

        Phone workPhone = new Phone();
        workPhone.setPhoneNumber("111-222-333");
        Phone homePhone = new Phone();
        homePhone.setPhoneNumber("222-333-444");

        Set<Phone> phoneSet = new HashSet<>();
        phoneSet.add(workPhone);
        phoneSet.add(homePhone);
        employee.setPhonesById(phoneSet);

        session.save(employee);

        session.getTransaction().commit();
        session.close();
    }
}
