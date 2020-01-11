package com.wm.dao;

import com.github.pagehelper.Page;
import com.wm.dataobject.WebsiteManagementDO;

import java.util.List;

public interface WebsiteManagementDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table website_management
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table website_management
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    int insert(WebsiteManagementDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table website_management
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    int insertSelective(WebsiteManagementDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table website_management
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    WebsiteManagementDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table website_management
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    int updateByPrimaryKeySelective(WebsiteManagementDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table website_management
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    int updateByPrimaryKey(WebsiteManagementDO record);

    List<WebsiteManagementDO> selectByModualType(String modualType);

    Page<WebsiteManagementDO> selectAll(WebsiteManagementDO websiteManagementDO);
}