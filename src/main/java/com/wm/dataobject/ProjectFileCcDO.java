package com.wm.dataobject;

import java.util.Date;

public class ProjectFileCcDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_file_cc_info.id
     *
     * @mbg.generated Sun Apr 14 15:11:58 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_file_cc_info.user_id
     *
     * @mbg.generated Sun Apr 14 15:11:58 CST 2019
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_file_cc_info.file_id
     *
     * @mbg.generated Sun Apr 14 15:11:58 CST 2019
     */
    private String fileId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_file_cc_info.create_time
     *
     * @mbg.generated Sun Apr 14 15:11:58 CST 2019
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_file_cc_info.id
     *
     * @return the value of project_file_cc_info.id
     *
     * @mbg.generated Sun Apr 14 15:11:58 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_file_cc_info.id
     *
     * @param id the value for project_file_cc_info.id
     *
     * @mbg.generated Sun Apr 14 15:11:58 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_file_cc_info.user_id
     *
     * @return the value of project_file_cc_info.user_id
     *
     * @mbg.generated Sun Apr 14 15:11:58 CST 2019
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_file_cc_info.user_id
     *
     * @param userId the value for project_file_cc_info.user_id
     *
     * @mbg.generated Sun Apr 14 15:11:58 CST 2019
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_file_cc_info.file_id
     *
     * @return the value of project_file_cc_info.file_id
     *
     * @mbg.generated Sun Apr 14 15:11:58 CST 2019
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_file_cc_info.file_id
     *
     * @param fileId the value for project_file_cc_info.file_id
     *
     * @mbg.generated Sun Apr 14 15:11:58 CST 2019
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_file_cc_info.create_time
     *
     * @return the value of project_file_cc_info.create_time
     *
     * @mbg.generated Sun Apr 14 15:11:58 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_file_cc_info.create_time
     *
     * @param createTime the value for project_file_cc_info.create_time
     *
     * @mbg.generated Sun Apr 14 15:11:58 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}