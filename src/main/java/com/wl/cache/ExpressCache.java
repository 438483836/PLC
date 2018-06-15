package com.wl.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.wl.entity.Express;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author jianghc
 * @create 2017-05-20 15:41
 **/
public class ExpressCache {

    private static LoadingCache<String, Express> store = CacheBuilder.newBuilder().build(new CacheLoader<String, Express>() {
        @Override
        public Express load(String code) throws Exception {
            return null;
        }
    });

    public static void loadData2Cache(List<Express> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (Express express : list) {
            store.put(express.getBarCode(), express);
        }
    }

    public static Express getFromCache(String barCode) throws Exception {
        if (StringUtils.isEmpty(barCode)) {
            throw new Exception("barCode is require");
        }
        return store.get(barCode);
    }


}
