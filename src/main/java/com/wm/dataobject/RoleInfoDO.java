package com.wm.dataobject;

public class RoleInfoDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_info.id
     *
     * @mbg.generated Sat Apr 13 23:13:44 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_info.name
     *
     * @mbg.generated Sat Apr 13 23:13:44 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_info.if_admin
     *
     * @mbg.generated Sat Apr 13 23:13:44 CST 2019
     */
    private Byte ifAdmin;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_info.id
     *
     * @return the value of role_info.id
     *
     * @mbg.generated Sat Apr 13 23:13:44 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_info.id
     *
     * @param id the value for role_info.id
     *
     * @mbg.generated Sat Apr 13 23:13:44 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_info.name
     *
     * @return the value of role_info.name
     *
     * @mbg.generated Sat Apr 13 23:13:44 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_info.name
     *
     * @param name the value for role_info.name
     *
     * @mbg.generated Sat Apr 13 23:13:44 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_info.if_admin
     *
     * @return the value of role_info.if_admin
     *
     * @mbg.generated Sat Apr 13 23:13:44 CST 2019
     */
    public Byte getIfAdmin() {
        return ifAdmin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_info.if_admin
     *
     * @param ifAdmin the value for role_info.if_admin
     *
     * @mbg.generated Sat Apr 13 23:13:44 CST 2019
     */
    public void setIfAdmin(Byte ifAdmin) {
        this.ifAdmin = ifAdmin;
    }
}