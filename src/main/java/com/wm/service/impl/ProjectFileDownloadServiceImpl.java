package com.wm.service.impl;

import com.wm.dao.ProjectFileDownloadDOMapper;
import com.wm.dao.ProjectFileDownloadLogDOMapper;
import com.wm.dataobject.ProjectFileDownloadDO;
import com.wm.dataobject.ProjectFileDownloadLogDO;
import com.wm.dataobject.UserDO;
import com.wm.service.ProjectFileDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ProjectFileDownloadServiceImpl implements ProjectFileDownloadService {

    @Autowired
    private ProjectFileDownloadDOMapper projectFileDownloadDOMapper;

    @Autowired
    private ProjectFileDownloadLogDOMapper projectFileDownloadLogDOMapper;


    @Override
    @Transactional
    public void updateFileDownloadTimes(ProjectFileDownloadDO projectFileDownloadDO, UserDO userDO) {
        ProjectFileDownloadDO projectFileDownloadDO1 = projectFileDownloadDOMapper.selectByPrimaryKey(projectFileDownloadDO.getFileId());
        if (projectFileDownloadDO1 == null) {
            projectFileDownloadDOMapper.insertSelective(projectFileDownloadDO);
        } else {
            projectFileDownloadDOMapper.addDownloadOrReadTimes(projectFileDownloadDO);
        }
        ProjectFileDownloadLogDO projectFileDownloadLogDO = new ProjectFileDownloadLogDO();
        projectFileDownloadLogDO.setFileId(projectFileDownloadDO.getFileId());
        projectFileDownloadLogDO.setUserId(userDO.getId());
        projectFileDownloadLogDO.setUserName(userDO.getName());
        projectFileDownloadLogDO.setTime(new Date());
        projectFileDownloadLogDOMapper.insertSelective(projectFileDownloadLogDO);
    }
}
