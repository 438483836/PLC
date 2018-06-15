package com.wl.DAO;

import com.wl.entity.Express;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author jianghc
 * @create 2017-05-20 13:54
 **/
@Repository
public class ExpressDAOImpl implements ExpressDAO {

    @Autowired
    private SessionFactory sessionFactory;


    public int save(Express u) {
        return (Integer) sessionFactory.getCurrentSession().save(u);
    }

    public List<Express> findAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Express.class);
        return criteria.list();
    }

    public Express getByBarCode(String barCode) {
        Query query = sessionFactory.getCurrentSession().createQuery(" from Express  where barCode=:barCode");
        query.setParameter("barCode", barCode);
        return (Express) query.uniqueResult();
    }

    public void Update(Express u) {
        sessionFactory.getCurrentSession().update(u);
    }


    public int saveWeight(String barCode, Integer d) {
        Query query = sessionFactory.getCurrentSession().createQuery(" update Express set weight =:weight where barCode=:barCode");
        query.setParameter("barCode", barCode);
        query.setParameter("weight", d);
        return query.executeUpdate();
    }

}
