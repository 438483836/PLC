package com.wl.DAO;

import com.wl.entity.Express;

import java.util.List;

/**
 * Created by Administrator on 2017/5/20.
 */
public interface ExpressDAO {
    public int save(Express u);
    public List<Express> findAll();
    public Express getByBarCode(String barCode);
    public void Update(Express u);
    public int saveWeight(String barCode,Integer d);
}
