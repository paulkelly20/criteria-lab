package db;

import models.Child;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public class DBChild {

    private static Transaction transaction;
    private static Session session;

    public static void save(Child child) {

        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(child);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<Child> listAll(){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Child> children = null;
        try{
            Criteria cr = session.createCriteria(Child.class);
            children = cr.list();

        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        } return children;

    }

    public static Child find(String name){
        session = HibernateUtil.getSessionFactory().openSession();
        Child result = null;
        try{
            Criteria cr = session.createCriteria(Child.class);
            cr.add(Restrictions.eq("name", name));
            result = (Child)cr.uniqueResult();
        } catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        } return result;
    }

    public static List<Child> listByAge(){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Child> children = null;
        try {   Criteria cr = session.createCriteria(Child.class);
            cr.addOrder(Order.asc("age"));
            children = cr.list();

        } catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        } return children;
    }

    public static List<Child> listByRange(String range){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Child> children = null;
        try{
            Criteria cr = session.createCriteria(Child.class);
            cr.add(Restrictions.eq("range", range));
            children = cr.list();

        } catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        } return children;
    }

    public static void Update(Child child){
        session = HibernateUtil.getSessionFactory().openSession();
        try{
            transaction = session.beginTransaction();
            session.update(child);
            transaction.commit();
        } catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public static List<Child> listByAgeUnderTen(){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Child> children = null;
        try{
            Criteria cr = session.createCriteria(Child.class);
            cr.add(Restrictions.between("age", 0, 9));
            children = cr.list();

        } catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        } return children;
    }


    public static Child findOldest(){
        session = HibernateUtil.getSessionFactory().openSession();
        Child result = null;
        try{
            Criteria cr = session.createCriteria(Child.class);
            cr.addOrder(Order.desc("Age"));
            result = (Child)cr.uniqueResult();
        } catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        } return result;
    }



}

