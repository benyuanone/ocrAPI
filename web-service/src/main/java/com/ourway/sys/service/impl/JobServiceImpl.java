package com.ourway.sys.service.impl;


import com.ourway.base.utils.TextUtils;
import com.ourway.sys.dao.SysJobDao;
import com.ourway.sys.model.OurwaySysDic;
import com.ourway.sys.model.OurwaySysDicValue;
import com.ourway.sys.model.OurwaySysJob;
import com.ourway.sys.service.JobService;
import groovyjarjarasm.asm.util.Textifier;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 ApiService.java : <p>
 * <p>说明：接口类</p>
 * <pre>
 * @author cc
 * @date 14:35 2017/4/12
 * </pre>
 */
@Service("jobService")
public class JobServiceImpl implements JobService {

    @Autowired
    SysJobDao sysJobDao;

    @Override
    public void saveOrUpdateAll(List<OurwaySysJob> values) {
        if (null != values && values.size() > 0)
            for (OurwaySysJob dicValue : values) {
                if (null == dicValue.getUpdateFlag())
                    continue;
                switch (dicValue.getUpdateFlag()) {
                    case 1://新增或者修改
                        if (TextUtils.isEmpty(dicValue.getOwid())) {
                            //新增
                            if (!TextUtils.isEmpty(dicValue.getJobName()))
                                sysJobDao.save(dicValue);
                        } else {
                            //修改

                            sysJobDao.update(dicValue);
                        }
                        break;
                    case 2:
                        Map<String, Object> params = new HashMap<String, Object>(1);

                        params.put("owid", dicValue.getOwid());

                        sysJobDao.removeByParams(params);
                        break;

                }
            }
    }

    @Override
    public List<OurwaySysJob> listJobs(String orderBy) {

        return sysJobDao.listJobs(orderBy);
    }
}

