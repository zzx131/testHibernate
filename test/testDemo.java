import cn.com.baomidou.testHibernate.entity.UserEntity;
import cn.com.baomidou.testHibernate.util.HibernateUtils;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zzx on 2017/7/21.
 */
public class testDemo {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction tx;

    /*@Before
    public void start() {
        sessionFactory = HibernateUtils.getSessionFaction();
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
    }*/

    @Test
    public void testTransaction() {
        try {
            session = HibernateUtils.getSesionObject();
            tx = session.beginTransaction();

            UserEntity userEntity = session.get(UserEntity.class, "3");
            System.out.println(userEntity);
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            tx.commit();
            /*session.close();*/
        }
    }

    /**
     * query对象的测试
     */
    @Test
    public void testQuery() {
        try {
            session = HibernateUtils.getSesionObject();
            tx = session.beginTransaction();

            Query query = session.createQuery("from UserEntity");
            List<UserEntity> userEntities = query.list();

            for (UserEntity userEntity : userEntities) {
                System.out.println(userEntity);
            }
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            tx.commit();
        }
    }

    /**
     * 测试Criteria对象
     */
    @Test
    public void testCriteria() {
        try {
            session = HibernateUtils.getSesionObject();
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(UserEntity.class);
            List<UserEntity> userEntities = criteria.list();

            for (UserEntity userEntity : userEntities) {
                System.out.println(userEntity);
            }
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            tx.commit();
        }
    }

    @Test
    public void testSqlQuery() {
        try {
            session = HibernateUtils.getSesionObject();
            tx = session.beginTransaction();

            SQLQuery sqlQuery = session.createSQLQuery("select * from tb_user");
            sqlQuery.addEntity(UserEntity.class);
            List<UserEntity> userEntities=sqlQuery.list();
            for (UserEntity userEntity:userEntities){
                System.out.println(userEntity);
            }
            /*List<Object[]> userEntities = sqlQuery.list();
            for (Object[] objects : userEntities) {
                System.out.println(Arrays.toString(objects));
            }*/
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            tx.commit();
        }
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

    @Test
    public void testCasch() {
        UserEntity userEntity = session.get(UserEntity.class, "3");
        System.out.println(userEntity);

        UserEntity userEntity1 = session.get(UserEntity.class, "3");
        System.out.println(userEntity1);
    }

    /**
     * 测试hibernate的缓存特性
     * 持久态的状态，更新的时候，不需要update
     */
    @Test
    public void testCaschTx() {
        UserEntity userEntity = session.get(UserEntity.class, "3");
        userEntity.setUsername("韩梅梅");
        System.out.println();
    }


    /*@After
    public void finsh() {
        tx.commit();
        session.close();
        sessionFactory.close();
    }
*/
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
