import cn.com.baomidou.testHibernate.entity.UserEntity;
import cn.com.baomidou.testHibernate.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * Created by zzx on 2017/7/21.
 */
public class testDemo {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction tx;

    @Before
    public void start() {
        sessionFactory = HibernateUtils.getSessionFaction();
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
    }

    @Test
    public void addUser() {
        //添加功能
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("小花花");
        userEntity.setPassword("205");
        userEntity.setAdress("山西朔州市");
        session.save(userEntity);
    }

    /**
     * 通过id查询实体类
     */
    @Test
    public void getUser() {
        UserEntity userEntity = session.get(UserEntity.class, "2");
        System.out.println(userEntity);
    }

    @Test
    public void updateUser() {
        UserEntity userEntity = session.get(UserEntity.class, "2");
        userEntity.setUsername("小明明");
        session.update(userEntity);
    }

    @Test
    public void deleteUser() {
        UserEntity userEntity = session.get(UserEntity.class, "2");
        session.delete(userEntity);
    }

    @Test
    public void saveOrUpdate() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("王小强");
        userEntity.setAdress("hh");
        userEntity.setPassword("123");
        session.saveOrUpdate(userEntity);
    }


    @After
    public void finsh() {
        tx.commit();
        session.close();
        sessionFactory.close();
    }

    /*public static void main(String[] args) {
        File file = new File("src/main/resources/Hibernate/hibernate.cfg.xml");
        Configuration cfg = new Configuration();
        cfg.configure(file);
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //添加功能
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("小王");
        userEntity.setPassword("205");
        userEntity.setAdress("山西朔州市");
        session.save(userEntity);
        tx.commit();
        session.close();
        sessionFactory.close();
    }*/
}
