package com.ourway.sys.dao;

import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysJob;

import java.util.List;

public interface SysJobDao extends BaseService<OurwaySysJob> {

    List<OurwaySysJob> listStartJobs();

    List<OurwaySysJob> listQuatzJobs();

    List<OurwaySysJob> listJobs(String orderBy);

}
