package factory;

import dao.UserDAO;
import dao.UserHibernateDAO;
import dao.UserJdbcDAO;
import java.util.Properties;

public class UserDaoFactory {
    public static UserDAO getDAO(Properties properties) {
        UserDAO result = null;
        if (properties.getProperty("daotype").equals("JDBC")) {
            result = UserJdbcDAO.getInstance();
        } else if (properties.getProperty("daotype").equals("Hibernate")) {
            result = UserHibernateDAO.getInstance();
        }
        return result;
    }
}
