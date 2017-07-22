package cn.com.baomidou.testHibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

/**
 * Created by zzx on 2017/7/21.
 */
public class HibernateUtils {
    private static SessionFactory sessionFactory = null;
    private static Configuration configuration = null;

    static {
        configuration = new Configuration();
        configuration.configure(new File("src/main/resources/Hibernate/hibernate.cfg.xml"));
        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFaction() {
        return sessionFactory;
    }

}
