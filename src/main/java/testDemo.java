import cn.combaomidou.testHibernate.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.File;

/**
 * Created by zzx on 2017/7/21.
 */
public class testDemo {
    public static void main(String[] args) {
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
    }
}
