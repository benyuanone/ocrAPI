package com.ourway.init;

import com.ourway.baiduapi.action.BaiDuApi;
import com.ourway.base.service.BaseInitService;

/**
 * Created by D.chen.g on 2017/12/26.
 */
public class BaiduApiInit implements BaseInitService {
    @Override
    public void init() {
        BaiDuApi.getToKen();
    }
}
