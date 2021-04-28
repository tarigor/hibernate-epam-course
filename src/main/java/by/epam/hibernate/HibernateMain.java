package by.epam.hibernate;

import by.epam.hibernate.dao.EmployeeDao;
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

public class HibernateMain {
    public static void main(String[] args) {
        EmployeeDao employeeDao = new EmployeeDao();
//        hm.addEmployee();
//        hm.updateEmployee(47,9999);
//        hm.listOfEmployee();
//        hm.deleteEmployee(46);
        employeeDao.listOfEmployee();
    }
}
