package by.epam.hibernate.dao;

import by.epam.hibernate.entity.Employee;
import by.epam.hibernate.entity.Phone;
import by.epam.hibernate.utility.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class EmployeeDao {
    public void addEmployee() {
        String answer = "yes";
        while (answer.startsWith("y") || answer.startsWith("Y")) {
            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Scanner scanner = new Scanner(System.in);
            Employee employee = new Employee();
            employee.setParameters(scanner);

            scanner = new Scanner(System.in);
            Phone workPhone = new Phone();
            workPhone.setParameters(scanner, "work");
            Phone homePhone = new Phone();
            homePhone.setParameters(scanner, "home");

            Set<Phone> phoneSet = new HashSet<>();
            phoneSet.add(workPhone);
            phoneSet.add(homePhone);
            employee.setPhonesById(phoneSet);

            try {
                session.save(employee);
                session.getTransaction().commit();
            } catch (Throwable ex) {
                System.out.println("Error -> " + ex);
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                }
            } finally {
                session.close();
            }

            System.out.println("Do you want add one more employee? y/n");
            answer = scanner.nextLine();
        }
    }

    public void updateEmployee(Integer employeeID, int salary) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();

        Employee employee = session.get(Employee.class, employeeID);
        employee.setSalary(salary);
        try {
            session.update(employee);
            session.getTransaction().commit();
        } catch (Throwable ex) {
            System.out.println("Error -> " + ex);
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
    }

    public void deleteEmployee(Integer employeeID) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();

        Employee employee = session.get(Employee.class, employeeID);
        try {
            session.delete(employee);
            session.getTransaction().commit();
        } catch (Throwable ex) {
            System.out.println("Error -> " + ex);
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
    }

    public void listOfEmployee() {
//        for (Iterator it = allpatients.iterator(); it.hasNext(); ) {
//            Object[] myResult = (Object[]) it.next();
//            String firstName = (String) myResult[0];
//            String lastName = (String) myResult[1];
//            System.out.println( "Found " + firstName + " " + lastName );
//        }

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();

        try {
//            List result = session.createQuery("select e.id,e.firstName,e.lastName, e.salary FROM Employee as e where e.lastName like 'T%' and salary>100").list();
//            List result = session.createQuery("select e.id,e.firstName,e.lastName, e.salary FROM Employee as e where  salary > :salary").list();
//            Query query = session.createQuery("select e.id,e.firstName,e.lastName, e.salary FROM Employee as e where  salary > :salary");
//            Query query = session.createQuery("select max(e.salary) from Employee as e ");
//            query.setParameter("salary", 6000);
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//            CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
            CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);
            criteriaQuery
//                    .select(root)
//                    .where(criteriaBuilder.gt(root.get("salary"), 6000))
//                    .where(criteriaBuilder.like(root.get("lastName"), "%ar%"))
//                    .orderBy(criteriaBuilder.asc(root.get("firstName")));
                    .select(criteriaBuilder.min(root.get("salary")));
            Query<Double> query = session.createQuery(criteriaQuery);
            List r = query.getResultList();
//            Query<Employee> query = session.createQuery(criteriaQuery);
//            List<Employee> reuslts = query.getResultList();
//            List r = query.list();
//            System.out.println("max salary is->"+((int) query.uniqueResult()));
//            System.out.println("-----------------------");
//            for (Object r : result) {
//                for (Object o : ((Object[]) r)) {
//                    System.out.print(o + " ");
//                }
//                System.out.println("");
//            }
            r.forEach(System.out::println);
            System.out.println("-----------------------");
            session.getTransaction().commit();
        } catch (Throwable ex) {
            System.out.println("Error -> " + ex);
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
    }
}
