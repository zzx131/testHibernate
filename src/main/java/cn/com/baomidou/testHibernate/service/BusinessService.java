package cn.com.baomidou.testHibernate.service;

import cn.com.baomidou.testHibernate.entity.Customer;
import cn.com.baomidou.testHibernate.entity.Order;
import cn.com.baomidou.testHibernate.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by zzx on 2017/7/23.
 */
public class BusinessService {
    private Session session;
    private static SessionFactory sessionFactory = HibernateUtils.getSessionFaction();

    /**
     * 保存用户信息
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

    /**
     * 保存订单
     *
     * @param order
     */
    public void saveOrder(Order order) {
        session = HibernateUtils.getSesionObject();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(order);
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

    /**
     * 通过用户id，查询用户信息
     *
     * @param customer_id
     * @return
     */
    public Customer findCustomer(long customer_id) {
        session = HibernateUtils.getSesionObject();
        Customer customer = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            customer = session.get(Customer.class, customer_id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return customer;
    }

    /**
     * 查询全部用户信息
     *
     * @return
     */
    public List<Customer> findAllCustomer() {
        session = HibernateUtils.getSesionObject();
        List<Customer> customerList = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Customer as c order by  c.name asc ");
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
        FileOutputStream fileOutputStream = null;
        try {
            byte[] buffer = customer.getImage();
            fileOutputStream = new FileOutputStream(new File("D:/phone_copy.png"));
            if (buffer != null) {
                fileOutputStream.write(buffer);
            }
            out.println("------以下是" + customer.getName() + "的个人信息");
            out.println("Id:" + customer.getId());
            out.println("口令：" + customer.getPassword());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void loadAndUpdateCustomer(Long customer_id, String address) {
        session = HibernateUtils.getSesionObject();
        try {
            Transaction tx = session.beginTransaction();
            Customer customer = session.get(Customer.class, customer_id);
            customer.setAddress(address);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * 通过用户查询订单信息
     *
     * @param customer
     * @return
     */
    public List<Order> findOrdersByCustomer(Customer customer) {
        session = HibernateUtils.getSesionObject();
        List<Order> orders = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            orders = session.createQuery("from  Order as o where o.customer.id=" + customer.getId()).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return orders;
    }

    /**
     * 级联保存用户和订单
     * 在保存订单的时候,关联保存一个已经存在的用户
     */
    public void saveCustomerAndOrderWithCascade() {
        session = HibernateUtils.getSesionObject();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Order order = new Order();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            order.setOrderNumber(simpleDateFormat.format(new java.util.Date()));
            order.setPrice(55.78);
            session.save(order);
            Customer customer = session.get(Customer.class, 1L);
            order.setCustomer(customer);
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

    public void testSave() {
        try {
            /*Customer customer = new Customer();
            customer.setName("李 小花");
            customer.setEmail("lixiaohua@yahoo.com");
            customer.setPassword("1234");
            customer.setAddress("xianan");
            customer.setSex('M');
            customer.setPhone("13191028185");
            customer.setDescription("I am not very honest");
            customer.setMarried(true);*/

            /*InputStream in = this.getClass().getResourceAsStream("10.png");
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
            customer.setImage(buffer);*/

            /*customer.setRegisteredTime(new java.util.Date());*/

            /*customer.setBrithday(Date.valueOf("1980-05-06"));*/

            /*Order order1 = new Order("order001", new Double(100), customer);
            Order order2 = new Order("order002", new Double(200), customer);
            customer.getOrders().add(order1);
            customer.getOrders().add(order2);*/

            /*this.saveCustomer(customer);*/

            Order order = new Order();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            order.setOrderNumber(simpleDateFormat.format(new java.util.Date()));
            order.setPrice(25.04);
            Customer customer = this.findCustomer(2L);
            order.setCustomer(customer);

            this.saveOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试查询
     */
    public void testFind(long customer_id) {
        Customer customer = this.findCustomer(customer_id);
        System.out.println(customer);
    }

    public static void main(String[] args) throws Exception {
        BusinessService businessService = new BusinessService();
        /*businessService.test();*/

        List<Customer> customerList = businessService.findAllCustomer();
        System.out.println();
        for (Customer customer : customerList) {
            businessService.printCustomer(new PrintWriter(System.out, true), customer);
        }

        /*businessService.loadAndUpdateCustomer(1L, "beijing");*/
        /*Customer customer = businessService.findCustomer(7L);
        System.out.println(customer.getFirstname());*/

        /*businessService.testSave();*/
        /*businessService.testFind(2L);*/

        /*businessService.saveCustomerAndOrderWithCascade();*/

        /*Customer customer = new Customer();
        customer.setId(14L);
        List<Order> orders = businessService.findOrdersByCustomer(customer);
        System.out.println();*/
        BusinessService.sessionFactory.close();
    }
}
