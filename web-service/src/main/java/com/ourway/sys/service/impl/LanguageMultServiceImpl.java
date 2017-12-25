package com.ourway.sys.service.impl;

import com.ourway.sys.dao.SysLangMultDao;
import com.ourway.sys.dao.SysLanguageDao;
import com.ourway.sys.model.OurwaySysLanguage;
import com.ourway.sys.model.OurwaySysLanguageMult;
import com.ourway.sys.service.LanguageMultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**<p>接口 LanguageMultService.java : <p>
 *<p>说明：多语言具体名称</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 14:36
</pre>
 */
@Service("languageMultService")
public class LanguageMultServiceImpl implements LanguageMultService {
    @Autowired
    SysLangMultDao sysLangMultDao;
    @Autowired
    SysLanguageDao sysLanguageDao;
    @Override
    public OurwaySysLanguageMult queryOneByKey(String language, String key) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("labelKey",key);
        OurwaySysLanguage lan = sysLanguageDao.getOneByHql("from OurwaySysLanguage where labelKey=:labelKey ",params);
        if(null==lan)
           return null;
        params = new HashMap<String,Object>();
        params.put("language",language);
        params.put("languageRefOwid",lan.getOwid());
        OurwaySysLanguageMult lm = sysLangMultDao.getOneByHql("from OurwaySysLanguageMult where language=:language and languageRefOwid=:languageRefOwid ",params);
        return lm;
    }
}
