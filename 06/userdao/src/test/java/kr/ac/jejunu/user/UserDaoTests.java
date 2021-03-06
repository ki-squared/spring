package kr.ac.jejunu.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.nullValue;


import java.sql.SQLException;

public class UserDaoTests {
    private static UserDao userDao;

    @BeforeAll
    public static void setup() {
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext("kr.ac.jejunu.user");
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Integer id = 1;
        String name = "Jade";
        String password = "1234";

        User user = userDao.get(id);

        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        String name = "Minju";
        String password = "pw1234";

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        userDao.insert(user);

        assertThat(user.getId(), greaterThan(0));

        User insertedUser = userDao.get(user.getId());
        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));

    }

    @Test
    public void update() throws SQLException, ClassNotFoundException {
        String name = "Minju";
        String password = "pw1234";
        String updatedName = "Red";
        String updatedPassword = "redpw";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);

        user.setName(updatedName);
        user.setPassword(updatedPassword);

        userDao.update(user);
        User updatedUser = userDao.get(user.getId());
        assertThat(updatedUser.name, is(updatedName));
        assertThat(updatedUser.password, is(updatedPassword));
    }

    @Test
    public void delete() throws SQLException {
        String name = "?????????";
        String password  ="1111";
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        userDao.insert(user);

        userDao.delete(user.getId());
        User deletedUser = userDao.get(user.getId());
        assertThat(deletedUser, nullValue());
    }
}
