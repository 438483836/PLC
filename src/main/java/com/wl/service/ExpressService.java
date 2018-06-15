package com.wl.service;

import com.wl.entity.Express;

import java.util.List;

/**
 * Created by Administrator on 2017/5/20.
 */
public interface ExpressService {
    public List<Express> findAll();
    public Express getByBarCode(String barCode);
    public int save(Express u);
    public int saveWeight(String barCode, Integer d);
}
