package com.ourway.baiduapi.dto;

import net.sf.ezmorph.bean.MorphDynaBean;

import java.util.Map;

/**
 * Created by D.chen.g on 2017/12/25.
 */
public class IdcardDTO {
   private String log_id;
   private String words_result_num;
   private String image_status;
   private String risk_type;

    public Map<String, MorphDynaBean> getWords_result() {
        return words_result;
    }

    public void setWords_result(Map<String, MorphDynaBean> words_result) {
        this.words_result = words_result;
    }

    private Map<String,MorphDynaBean> words_result;

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public String getWords_result_num() {
        return words_result_num;
    }

    public void setWords_result_num(String words_result_num) {
        this.words_result_num = words_result_num;
    }

    public String getImage_status() {
        return image_status;
    }

    public void setImage_status(String image_status) {
        this.image_status = image_status;
    }

    public String getRisk_type() {
        return risk_type;
    }

    public void setRisk_type(String risk_type) {
        this.risk_type = risk_type;
    }

}
