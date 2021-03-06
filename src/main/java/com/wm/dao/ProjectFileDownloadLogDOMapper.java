package com.wm.dao;

import com.github.pagehelper.Page;
import com.wm.dataobject.ProjectFileDownloadLogDO;

public interface ProjectFileDownloadLogDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_file_download_log
     *
     * @mbg.generated Sun Jun 09 14:56:07 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_file_download_log
     *
     * @mbg.generated Sun Jun 09 14:56:07 CST 2019
     */
    int insert(ProjectFileDownloadLogDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_file_download_log
     *
     * @mbg.generated Sun Jun 09 14:56:07 CST 2019
     */
    int insertSelective(ProjectFileDownloadLogDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_file_download_log
     *
     * @mbg.generated Sun Jun 09 14:56:07 CST 2019
     */
    ProjectFileDownloadLogDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_file_download_log
     *
     * @mbg.generated Sun Jun 09 14:56:07 CST 2019
     */
    int updateByPrimaryKeySelective(ProjectFileDownloadLogDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_file_download_log
     *
     * @mbg.generated Sun Jun 09 14:56:07 CST 2019
     */
    int updateByPrimaryKey(ProjectFileDownloadLogDO record);

    Page<ProjectFileDownloadLogDO> selectMyDownload(Integer userId);
}