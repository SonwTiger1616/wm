package com.wm;

import com.github.pagehelper.Page;
import com.wm.dao.ProjectFileDOMapper;
import com.wm.dao.UserDOMapper;
import com.wm.dataobject.ProjectFileDO;
import com.wm.dataobject.ProjectFileDownloadDO;
import com.wm.dataobject.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {App.class})
public class AppTest {

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private ProjectFileDOMapper projectFileDOMapper;

    //@Test
    public void userDoSelectByUserIds() {
        String[] ccuserid = {"1","5"};
        List<UserDO> userDOList = userDOMapper.selectByUserIds(ccuserid);
        assertNotNull(userDOList);
        assertTrue(userDOList.size() > 0);
    }

    //@Test
    public void projectFileDOSelectBySearch() {
        Page<ProjectFileDO> projectFileDO = projectFileDOMapper.selectBySearch(null);
        ProjectFileDownloadDO projectFileDownloadDO = projectFileDO.get(0).getProjectFileDownloadDO();
        assertNotNull(projectFileDownloadDO);
    }
}
