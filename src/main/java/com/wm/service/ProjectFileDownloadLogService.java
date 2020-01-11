package com.wm.service;

import com.github.pagehelper.Page;
import com.wm.dataobject.ProjectFileDownloadLogDO;

public interface ProjectFileDownloadLogService {

    int save(ProjectFileDownloadLogDO projectFileDownloadLogDO);

    Page<ProjectFileDownloadLogDO> selectMyDownloadByPage(Integer pageNum, Integer pageSize,Integer userId);
}
