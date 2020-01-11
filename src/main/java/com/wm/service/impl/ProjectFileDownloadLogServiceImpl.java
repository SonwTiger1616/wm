package com.wm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wm.dao.ProjectFileDownloadLogDOMapper;
import com.wm.dataobject.ProjectFileDownloadLogDO;
import com.wm.service.ProjectFileDownloadLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectFileDownloadLogServiceImpl implements ProjectFileDownloadLogService {

    @Autowired
    private ProjectFileDownloadLogDOMapper projectFileDownloadLogDOMapper;

    @Override
    public int save(ProjectFileDownloadLogDO projectFileDownloadLogDO) {
        return projectFileDownloadLogDOMapper.insertSelective(projectFileDownloadLogDO);
    }

    @Override
    public Page<ProjectFileDownloadLogDO> selectMyDownloadByPage(Integer pageNum, Integer pageSize,
            Integer userId) {
        PageHelper.startPage(pageNum,pageSize);
        return projectFileDownloadLogDOMapper.selectMyDownload(userId);
    }
}
