package com.wl.service;

import com.wl.DAO.ExpressDAO;
import com.wl.entity.Express;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author jianghc
 * @create 2017-05-20 20:05
 **/
@Service("expressService")
@Transactional
public class ExpressServiceImpl implements ExpressService{
    @Autowired
    private ExpressDAO expressDAO;
    public List<Express> findAll() {
        return expressDAO.findAll();
    }

    public Express getByBarCode(String barCode) {
        return expressDAO.getByBarCode(barCode);
    }

    public int save(Express u) {
        byte[] b = new byte[]{1,2,3};
        u.setHex(b);
        return expressDAO.save(u);
    }

    public int saveWeight(String barCode, Integer d){
        return expressDAO.saveWeight(barCode,d);
    }
}
