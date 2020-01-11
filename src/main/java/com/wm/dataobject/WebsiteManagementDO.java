package com.wm.dataobject;

import java.util.Date;
import java.util.List;

public class WebsiteManagementDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column website_management.id
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column website_management.file_path
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    private List<String> filesPath;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column website_management.upload_user
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    private String uploadUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column website_management.upload_time
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    private Date uploadTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column website_management.modual_type
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    private String modualType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column website_management.title
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column website_management.product_number
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    private String productNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column website_management.detailed_description
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    private String detailedDescription;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column website_management.id
     *
     * @return the value of website_management.id
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column website_management.id
     *
     * @param id the value for website_management.id
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column website_management.file_path
     *
     * @return the value of website_management.file_path
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    public List<String> getFilesPath() {
        return filesPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column website_management.file_path
     *
     * @param filesPath the value for website_management.file_path
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    public void setFilesPath(List<String> filesPath) {
        this.filesPath = filesPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column website_management.upload_user
     *
     * @return the value of website_management.upload_user
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    public String getUploadUser() {
        return uploadUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column website_management.upload_user
     *
     * @param uploadUser the value for website_management.upload_user
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    public void setUploadUser(String uploadUser) {
        this.uploadUser = uploadUser == null ? null : uploadUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column website_management.upload_time
     *
     * @return the value of website_management.upload_time
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    public Date getUploadTime() {
        return uploadTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column website_management.upload_time
     *
     * @param uploadTime the value for website_management.upload_time
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column website_management.modual_type
     *
     * @return the value of website_management.modual_type
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    public String getModualType() {
        return modualType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column website_management.modual_type
     *
     * @param modualType the value for website_management.modual_type
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    public void setModualType(String modualType) {
        this.modualType = modualType == null ? null : modualType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column website_management.title
     *
     * @return the value of website_management.title
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column website_management.title
     *
     * @param title the value for website_management.title
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column website_management.product_number
     *
     * @return the value of website_management.product_number
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    public String getProductNumber() {
        return productNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column website_management.product_number
     *
     * @param productNumber the value for website_management.product_number
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber == null ? null : productNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column website_management.detailed_description
     *
     * @return the value of website_management.detailed_description
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    public String getDetailedDescription() {
        return detailedDescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column website_management.detailed_description
     *
     * @param detailedDescription the value for website_management.detailed_description
     *
     * @mbg.generated Sun Apr 21 16:17:07 CST 2019
     */
    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription == null ? null : detailedDescription.trim();
    }
}