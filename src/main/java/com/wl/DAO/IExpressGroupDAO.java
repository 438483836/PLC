
package com.wl.DAO;

import com.wl.entity.Express;
import com.wl.entity.ExpressGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/5/20.
 */
public interface IExpressGroupDAO {

    public ExpressGroup getOneBarCode();

    public void update(ExpressGroup group);


}
