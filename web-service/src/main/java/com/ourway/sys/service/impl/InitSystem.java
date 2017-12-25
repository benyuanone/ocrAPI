package com.ourway.sys.service.impl;

import com.ourway.baiduapi.constants.BaiDuApiInfo;
import com.ourway.base.CommonConstants;
import com.ourway.base.service.BaseInitService;
import com.ourway.base.utils.QuartzManager;
import com.ourway.base.utils.TextUtils;
import com.ourway.sys.dao.SysJobDao;
import com.ourway.sys.model.OurwaySysJob;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * 整个系统启动时候进行处理
 * Created by Administrator on 2017/9/23.
 */
@Component
public class InitSystem {
    public static final String PLATFORM_CONFIG_FILE_NAME = "/app/config.properties";
    @Autowired
    SysJobDao sysJobDao;

    @PostConstruct
    public void init() {
        System.out.println("系统启动中............................................");
        try {
            initAPIConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        启动系统启动时需要执行的任务
        List<OurwaySysJob> startJob = sysJobDao.listStartJobs();
        if (null != startJob && startJob.size() > 0) {
            for (OurwaySysJob job : startJob) {
                if (!TextUtils.isEmpty(job.getJobClass())) {
                    try {
                        Class c = Class.forName(job.getJobClass());
                        Object o = c.newInstance();
                        if (o instanceof BaseInitService) {
                            BaseInitService service = (BaseInitService) o;
                            service.init();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("执行启动任务结束............................................");
        }
        List<OurwaySysJob> quatrzJobs = sysJobDao.listQuatzJobs();
        if (null != quatrzJobs && quatrzJobs.size() > 0) {
            try {
                SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
                Scheduler sche = gSchedulerFactory.getScheduler();
                for (OurwaySysJob quatrzJob : quatrzJobs) {
                    if (TextUtils.isEmpty(quatrzJob.getJobClass()) || TextUtils.isEmpty(quatrzJob.getJobCron()))
                        continue;
                    try {
                        Class c = Class.forName(quatrzJob.getJobClass());
                        QuartzManager.addJob(sche, quatrzJob.getJobName(), c, quatrzJob.getJobCron());
                    } catch (Exception e) {
                        continue;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("执行定时任务结束............................................");
        }
    }

    private void initAPIConfig() throws Exception {
        URL url = InitSystem.class.getClassLoader().getResource(PLATFORM_CONFIG_FILE_NAME);
        if (url == null) {
            return;
        }
        InputStream is = null;
        InputStreamReader isr = null;
        try {
            is = InitSystem.class.getClassLoader().getResourceAsStream(PLATFORM_CONFIG_FILE_NAME);
            isr = new InputStreamReader(is);
            Properties pros = new Properties();
            pros.load(isr);
            setConfig(pros);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //配置系统变量
    private void setConfig(Properties pros) {
        if (!TextUtils.isEmpty(pros.get("SYSTEM_FILE_PATH"))) {
            CommonConstants.SYSTEM_FILE_PATH = pros.get("SYSTEM_FILE_PATH").toString();
        }

        if (!TextUtils.isEmpty(pros.get("mailHost"))) {
            CommonConstants.mailHost = pros.get("mailHost").toString();
        }
        if (!TextUtils.isEmpty(pros.get("mailPort"))) {
            CommonConstants.mailPort = pros.get("mailPort").toString();
        }
        if (!TextUtils.isEmpty(pros.get("mailTimeOut"))) {
            CommonConstants.mailTimeOut = pros.get("mailTimeOut").toString();
        }
        if (!TextUtils.isEmpty(pros.get("mailSmtpStarttlsEnable"))) {
            CommonConstants.mailSmtpStarttlsEnable = pros.get("mailSmtpStarttlsEnable").toString();
        }
        if (!TextUtils.isEmpty(pros.get("mailDebug"))) {
            CommonConstants.mailDebug = pros.get("mailDebug").toString();
        }
        if (!TextUtils.isEmpty(pros.get("mailUsername"))) {
            CommonConstants.mailUsername = pros.get("mailUsername").toString();
        }
        if (!TextUtils.isEmpty(pros.get("mailPassword"))) {
            CommonConstants.mailPassword = pros.get("mailPassword").toString();
        }
        if (!TextUtils.isEmpty(pros.get("mailSmtpAuth"))) {
            CommonConstants.mailSmtpAuth = pros.get("mailSmtpAuth").toString();
        }

        if (!TextUtils.isEmpty(pros.get("mailFrom"))) {
            CommonConstants.mailFrom = pros.get("mailFrom").toString();
        }
        //百度api基本信息
        if (!TextUtils.isEmpty(pros.get("baidu.appid"))) {
            BaiDuApiInfo.APP_ID = pros.get("baidu.appid").toString();
        }
        if (!TextUtils.isEmpty(pros.get("baidu.apikey"))) {
            BaiDuApiInfo.API_KEY = pros.get("baidu.apikey").toString();
        }
        if (!TextUtils.isEmpty(pros.get("baidu.secretkey"))) {
            BaiDuApiInfo.SECRET_KEY = pros.get("baidu.secretkey").toString();
        }

    }
}
