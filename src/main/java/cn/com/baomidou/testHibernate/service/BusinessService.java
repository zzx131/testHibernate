package cn.com.baomidou.testHibernate.service;

import cn.com.baomidou.testHibernate.entity.Customer;
import cn.com.baomidou.testHibernate.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by zzx on 2017/7/23.
 */
public class BusinessService {
    private Session session;
    private static SessionFactory sessionFactory=HibernateUtils.getSessionFaction();

    /**
     * 保存
     *
     * @param customer
     */
    public void saveCustomer(Customer customer) {
        session = HibernateUtils.getSesionObject();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(customer);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Customer> findAllCustomer() {
        session = HibernateUtils.getSesionObject();
        List<Customer> customerList = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Customer");
            customerList = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return customerList;
    }

    public void printCustomer(PrintWriter out, Customer customer) {
        try {
            byte[] buffer = customer.getImage();
            FileOutputStream fileOutputStream = new FileOutputStream(new File("D:/phone_copy.png"));
            fileOutputStream.write(buffer);
            fileOutputStream.close();

            out.println("------以下是" + customer.getName() + "的个人信息");
            out.println("Id:" + customer.getId());
            out.println("口令：" + customer.getPassword());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

    public void test() {
        try {
            Customer customer = new Customer();
            customer.setName("jack");
            customer.setEmail("rom@yahoo.com");
            customer.setPassword("1234");
            customer.setAddress("xian");
            customer.setSex('M');
            customer.setPhone("18435185644");
            customer.setDescription("I am very honest");
            customer.setMarried(true);
            InputStream in = this.getClass().getResourceAsStream("10.png");
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
            customer.setImage(buffer);
            customer.setRegisteredTime(new java.util.Date());

            customer.setBrithday(Date.valueOf("1980-05-06"));

            this.saveCustomer(customer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        BusinessService businessService = new BusinessService();
         businessService.test();
        /*List<Customer> customerList = businessService.findAllCustomer();
        for (Customer customer : customerList) {
            businessService.printCustomer(new PrintWriter(System.out,true),customer);
        }*/
        BusinessService.sessionFactory.close();
    }
}
