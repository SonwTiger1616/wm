package com.wm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wm.dao.WebsiteFilePathDOMapper;
import com.wm.dao.WebsiteManagementDOMapper;
import com.wm.dataobject.WebsiteFilePathDOKey;
import com.wm.dataobject.WebsiteManagementDO;
import com.wm.error.BusinessException;
import com.wm.error.EmBusinessError;
import com.wm.service.WebsiteManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebsiteManagementServiceImpl implements WebsiteManagementService {

    @Autowired
    private WebsiteManagementDOMapper websiteManagementDOMapper;

    @Autowired
    private WebsiteFilePathDOMapper websiteFilePathDOMapper;

    @Override
    public WebsiteManagementDO get(Integer id) throws BusinessException {
        if (id == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        WebsiteManagementDO websiteManagementDO = websiteManagementDOMapper.selectByPrimaryKey(id);
        websiteManagementDO.setFilesPath(websiteFilePathDOMapper.selectById(id));
        return websiteManagementDO;
    }

    @Override
    public Page<WebsiteManagementDO> selectByPage(Integer pageNum, Integer pageSize, String modualType) {
        PageHelper.startPage(pageNum, pageSize);
        WebsiteManagementDO websiteManagementDO = new WebsiteManagementDO();
        websiteManagementDO.setModualType(modualType);
        return websiteManagementDOMapper.selectAll(websiteManagementDO);
    }


    @Override
    @Transactional
    public void update(WebsiteManagementDO websiteManagementDO) {
        websiteManagementDOMapper.updateByPrimaryKeySelective(websiteManagementDO);
        //websiteFilePathDOMapper.deleteByWebsiteId(websiteManagementDO.getId());
        if (websiteManagementDO.getFilesPath() != null && websiteManagementDO.getFilesPath().size() > 0){
            saveFilesPath(websiteManagementDO);
        }
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        int result = websiteManagementDOMapper.deleteByPrimaryKey(id);
        websiteFilePathDOMapper.deleteByWebsiteId(id);
        return result > 0;
    }

    @Override
    public List<WebsiteManagementDO> selectByModualType(String modualType) {
        List<WebsiteManagementDO> websiteManagementDObList = websiteManagementDOMapper.selectByModualType(modualType);
        for (WebsiteManagementDO websiteManagementDO : websiteManagementDObList) {
            websiteManagementDO.setFilesPath(websiteFilePathDOMapper.selectById(websiteManagementDO.getId()));
        }

        return websiteManagementDObList;
    }

    @Override
    @Transactional
    public void insert(WebsiteManagementDO websiteManagementDO) {
        websiteManagementDOMapper.insertSelective(websiteManagementDO);
        saveFilesPath(websiteManagementDO);
    }

    @Override
    public boolean deleteFilePath(Integer id, String filePath) {
        WebsiteFilePathDOKey websiteFilePathDOKey = new WebsiteFilePathDOKey();
        websiteFilePathDOKey.setWebsiteId(id);
        websiteFilePathDOKey.setFilePath(filePath);
        int deleted = websiteFilePathDOMapper.deleteByPrimaryKey(websiteFilePathDOKey);
        return deleted > 0;
    }

    private void saveFilesPath(WebsiteManagementDO websiteManagementDO) {
        List<WebsiteFilePathDOKey> websiteFilePathDOKeys = new ArrayList<>();
        List<String> filesPath =  websiteManagementDO.getFilesPath();
        for (String filePath : filesPath) {
            WebsiteFilePathDOKey websiteFilePathDOKey = new WebsiteFilePathDOKey();
            websiteFilePathDOKey.setFilePath(filePath);
            websiteFilePathDOKey.setWebsiteId(websiteManagementDO.getId());
            websiteFilePathDOKeys.add(websiteFilePathDOKey);
        }
        websiteFilePathDOMapper.insertByBatch(websiteFilePathDOKeys);
    }
}
