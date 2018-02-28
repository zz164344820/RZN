package com.zyhealth.expertlib.net;


import com.zyhealth.expertlib.bean.ResponseBean;

/**
 * Created by pdi_10 on 2016/4/27.
 */
public interface HttpRequestListener {
        void onSuccess(ResponseBean response);

        void onFailure(String err);
}
