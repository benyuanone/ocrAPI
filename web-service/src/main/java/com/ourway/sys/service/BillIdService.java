package com.ourway.sys.service;

public interface BillIdService {


    void updateListId2Redis(String key,String pre, String ymd, int times);

    void saveNewIds(String key, String pre,String ymd, int times,int len);


}
