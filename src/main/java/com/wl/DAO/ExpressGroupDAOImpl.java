package com.wl.DAO;

import com.wl.DAO.IExpressGroupDAO;
import com.wl.entity.ExpressGroup;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author jianghc
 * @create 2017-05-20 15:15
 **/
@Repository
public class ExpressGroupDAOImpl implements IExpressGroupDAO {
    @Autowired
    private SessionFactory sessionFactory;
    public ExpressGroup getOneBarCode() {
        Query query = sessionFactory.getCurrentSession().createQuery(" from ExpressGroup where state =0 ");
        query.setMaxResults(1);
        return (ExpressGroup)query.uniqueResult();
    }

    public void update(ExpressGroup group) {
        sessionFactory.getCurrentSession().update(group);
    }
}
