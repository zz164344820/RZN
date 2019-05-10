package com.rzn.module_main.ui.sellagriculturalgoods.commoditylist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2019/5/10.
 */

public class ComodityBean {
    List<CommodityListBean> rows;
    String total;

    public List<CommodityListBean> getRows() {
        if (rows == null) {
            return new ArrayList<>();
        }
        return rows;
    }

    public void setRows(List<CommodityListBean> rows) {
        this.rows = rows;
    }

    public String getTotal() {
        return total == null ? "" : total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
