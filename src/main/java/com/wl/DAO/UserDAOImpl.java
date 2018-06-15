package com.wl.DAO;

import com.wl.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public int save(User u) {
        return (Integer) sessionFactory.getCurrentSession().save(u);
    }

    public List<User> findAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        return criteria.list();
    }

    public User getByNameAndPwd(String user, String pwd) {
        Query query = sessionFactory.getCurrentSession().createQuery(" from User  where username=:username and password=:password");
        query.setParameter("username", user);
        query.setParameter("password", pwd);
        return (User) query.uniqueResult();
       /* List<User> users = query.list();
       return (users==null||users.size()<=0)?null:users.get(0);*/
    }

}
