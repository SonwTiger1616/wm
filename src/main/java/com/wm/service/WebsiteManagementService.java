package com.wm.service;

import com.github.pagehelper.Page;
import com.wm.dataobject.WebsiteManagementDO;
import com.wm.error.BusinessException;

import java.util.List;

public interface WebsiteManagementService {
    WebsiteManagementDO get(Integer id) throws BusinessException;

    Page<WebsiteManagementDO> selectByPage(Integer pageNum, Integer pageSize, String modualType);

    void update(WebsiteManagementDO websiteManagementDO);

    boolean delete(Integer id);

    List<WebsiteManagementDO> selectByModualType(String modualType);

    void insert(WebsiteManagementDO websiteManagementDO);

    boolean deleteFilePath(Integer id, String filePath);
}
