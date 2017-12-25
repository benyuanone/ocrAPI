package com.ourway.sys.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.sys.dao.SysLangMultDao;
import com.ourway.sys.dao.SysLanguageDao;
import com.ourway.sys.model.*;
import com.ourway.sys.service.LanguageSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 LanguageSerImpl.java : <p>
 * <p>说明：多语言</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 14:48
 * </pre>
 */
@Service("languageSer")
public class LanguageSerImpl implements LanguageSer {
    @Autowired
    SysLanguageDao sysLanguageDao;
    @Autowired
    SysLangMultDao sysLangMultDao;

    @Override
    public Boolean doCheckUniqueLabel(OurwaySysLanguage language) {
        return sysLanguageDao.doCheckUniqueLabelKey(language);
    }

    @Override
    public void saveOrUpdateLanguage(OurwaySysLanguage language) {
        sysLanguageDao.saveOrUpdate(language);
        if (null != language.getDataList() && language.getDataList().size() > 0)
            for (OurwaySysLanguageMult languageMult : language.getDataList()) {
                if (null == languageMult.getUpdateFlag())
                    languageMult.setUpdateFlag(0);
                switch (languageMult.getUpdateFlag()) {
                    case 0:
                        break;
                    case 1:
                        languageMult.setLanguageRefOwid(language.getOwid());
                        if (TextUtils.isEmpty(languageMult.getOwid()))
                            sysLangMultDao.save(languageMult);
                        else
                            sysLangMultDao.update(languageMult);
                        break;
                    case 2:
                        sysLangMultDao.removeByIds(OurwaySysLanguageMult.class, languageMult.getOwid());
                        break;
                }
            }
    }

    @Override
    public PageInfo<OurwaySysLanguage> listLanguageByPage(List<FilterModel> models, int pageNo, int pageSize) {
        for (FilterModel filterModel:models) {
            filterModel.setType(1);
        }
        return sysLanguageDao.listLanguageForPage(models, pageNo, pageSize);
    }

    @Override
    public List<Map<String, Object>> removeItems(List<String> owids) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();
        for (String owid : owids) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            params.put("owid", owid);
            OurwaySysLanguage language = sysLanguageDao.getOneByParams( params, "");
            if (null != language) {
                params = new HashMap<String, Object>(1);
                params.put("languageRefOwid", owid);
                sysLangMultDao.removeByParams(params);
                params = new HashMap<String, Object>(1);
                params.put("owid", owid);
                sysLanguageDao.removeEntity(language);
                objs.add(params);
            }
        }
        return objs;
    }

    @Override
    public OurwaySysLanguage detailLanguage(String owid) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("owid", owid);
        OurwaySysLanguage language = sysLanguageDao.getOneByParams(params, "");
        params = new HashMap<String, Object>(1);
        params.put("languageRefOwid", owid);
        List<OurwaySysLanguageMult> dataList = sysLangMultDao.listAllByParam( params, "indexNo");
        language.setDataList(dataList);
        return language;
    }

    @Override
    public boolean updateNewLanguageForObject(String labelId, String chnName) {
        Map<String,Object> params = new HashMap<String,Object>(1);
        params.put("labelKey",labelId);
        OurwaySysLanguage language = sysLanguageDao.getOneByParams(params, "");
        if(null!=language)
            return false;
        OurwaySysLanguage newLanguage = new OurwaySysLanguage();
        newLanguage.setLabelKey(labelId);
        newLanguage.setLabelName(chnName);
        sysLanguageDao.save(newLanguage);
        OurwaySysLanguageMult languageMult = new OurwaySysLanguageMult();
        languageMult.setLanguageRefOwid(newLanguage.getOwid());
        languageMult.setLanguage("chn");
        languageMult.setLabelContent(chnName);
        languageMult.setLabelFloat((byte)0);
        sysLangMultDao.save(languageMult);
        return true;
    }
}
