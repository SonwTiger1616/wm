package com.wm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wm.controller.viewobject.ProjectFileVO;
import com.wm.dao.ProjectFileCcDOMapper;
import com.wm.dao.ProjectFileDOMapper;
import com.wm.dao.UserDOMapper;
import com.wm.dataobject.ProjectFileCcDO;
import com.wm.dataobject.ProjectFileDO;
import com.wm.dataobject.UserDO;
import com.wm.service.ProjectFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("projectFileService")
public class ProjectFileServiceImpl implements ProjectFileService {
    @Autowired
    private ProjectFileDOMapper projectFileDOMapper;

    @Autowired
    private ProjectFileCcDOMapper projectFileCcDOMapper;

    @Autowired
    private UserDOMapper userDOMapper;

    @Override
    @Transactional
    public boolean addProjectFile(ProjectFileDO projectFileDO, String[] ccuserid) {
        String id = UUID.randomUUID().toString();
        projectFileDO.setId(id);

        if (ccuserid != null && ccuserid.length > 0) {
            List<UserDO> userDOS =  userDOMapper.selectByUserIds(ccuserid);
            StringBuilder userNames = new StringBuilder();
            for (UserDO userDO : userDOS) {
                userNames.append(',');
                userNames.append(userDO.getName());
            }
            projectFileDO.setCcUser(userNames.substring(1));
        }

        int inserted = projectFileDOMapper.insertSelective(projectFileDO);
        if (inserted > 0) {
            if (ccuserid == null) return true;

            for (String userid : ccuserid) {
                ProjectFileCcDO projectFileCcDO = new ProjectFileCcDO();
                projectFileCcDO.setFileId(id);
                projectFileCcDO.setUserId(Integer.parseInt(userid));
                projectFileCcDOMapper.insertSelective(projectFileCcDO);
            }
        }else {
            return false;
        }
        return true;
    }

    @Override
    public Page<ProjectFileDO> selectAllNoCc(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        ProjectFileDO projectFileDO = new ProjectFileDO();
        projectFileDO.setIfCc((byte)0);
        return projectFileDOMapper.selectBySearch(projectFileDO);
    }

    @Override
    public Page<ProjectFileDO> selectMyAll(Integer pageNum, Integer pageSize, String userId) {
        PageHelper.startPage(pageNum, pageSize);
        ProjectFileDO projectFileDO = new ProjectFileDO();
        projectFileDO.setUploadUser(userId);
        return projectFileDOMapper.selectBySearch(projectFileDO);
    }

    @Override
    public ProjectFileDO get(String id) {
        return projectFileDOMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<ProjectFileDO> selectAllCc(Integer userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return projectFileDOMapper.selectAllCc(userId);
    }
}
