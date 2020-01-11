package com.wm.service;

import com.github.pagehelper.Page;
import com.wm.controller.viewobject.ProjectFileVO;
import com.wm.dataobject.ProjectFileDO;

public interface ProjectFileService {
    boolean addProjectFile(ProjectFileDO projectFileDO, String[] ccuserid);

    Page<ProjectFileDO> selectAllNoCc(Integer pageNum, Integer pageSize);

    Page<ProjectFileDO> selectMyAll(Integer pageNum, Integer pageSize, String userId);

    ProjectFileDO get(String id);

    Page<ProjectFileDO> selectAllCc(Integer userId, Integer pageNum, Integer pageSize);
}
