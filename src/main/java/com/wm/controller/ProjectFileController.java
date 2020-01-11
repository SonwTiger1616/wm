package com.wm.controller;

import com.github.pagehelper.Page;
import com.wm.controller.viewobject.ProjectFileDownloadLogVO;
import com.wm.controller.viewobject.ProjectFileVO;
import com.wm.dataobject.ProjectFileDO;
import com.wm.dataobject.ProjectFileDownloadDO;
import com.wm.dataobject.ProjectFileDownloadLogDO;
import com.wm.dataobject.UserDO;
import com.wm.error.BusinessException;
import com.wm.error.EmBusinessError;
import com.wm.response.CommonReturnType;
import com.wm.response.PageCommonReturnType;
import com.wm.service.ProjectFileDownloadLogService;
import com.wm.service.ProjectFileDownloadService;
import com.wm.service.ProjectFileService;
import com.wm.util.LoginUtil;
import com.wm.util.MyFileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/project/file")
public class ProjectFileController extends BaseController {

    @Autowired
    private ProjectFileService projectFileService;

    @Autowired
    private ProjectFileDownloadService projectFileDownloadService;

    @Autowired
    private MyFileUtils myFileUtils;

    @Autowired
    private ProjectFileDownloadLogService projectFileDownloadLogService;

    @RequestMapping("/upload")
    public CommonReturnType upload(HttpServletRequest request,
                                   @RequestParam("filename") MultipartFile file,
                                   @RequestParam("projectname") String projectname,
                                   @RequestParam("projectdescription") String projectdescription,
                                   @RequestParam(value="ccuserid",required = false) String ccuserid) throws BusinessException {

        if (file.isEmpty()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        String path = myFileUtils.saveProjectFile(file);
        UserDO userDO = LoginUtil.getUser(request);
        // 保存上传信息
        ProjectFileDO projectFileDO = new ProjectFileDO();
        projectFileDO.setFileName(file.getOriginalFilename());
        projectFileDO.setFileSize(file.getSize());
        if (StringUtils.isNotBlank(ccuserid)) {
            projectFileDO.setIfCc((byte)1);
        }
        projectFileDO.setProjectName(projectname);
        projectFileDO.setProjectDescription(projectdescription);
        projectFileDO.setUploadUser(userDO.getName());
        projectFileDO.setFilePath(path);
        projectFileService.addProjectFile(projectFileDO, ccuserid.split(","));
        return CommonReturnType.create("操作成功");
    }

    @RequestMapping("/list")
    public PageCommonReturnType selectAll(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize,
                                          Integer draw) {
        Page<ProjectFileDO> page = projectFileService.selectAllNoCc(pageNum, pageSize);
        return PageCommonReturnType.create(convertFromPageProjectFileDO(page), draw);
    }

    @RequestMapping("/my/list")
    public PageCommonReturnType selectMyAll(HttpServletRequest request,
                                          @RequestParam("pageNum") Integer pageNum,
                                          @RequestParam("pageSize") Integer pageSize,
                                          Integer draw) {
        UserDO userDO = LoginUtil.getUser(request);
        Page<ProjectFileDO> page = projectFileService.selectMyAll(pageNum, pageSize, userDO.getName());
        return PageCommonReturnType.create(convertFromPageProjectFileDO(page), draw);
    }

    @RequestMapping("/my/download/list")
    public PageCommonReturnType selectMyDownloadList(HttpServletRequest request,
                                            @RequestParam("pageNum") Integer pageNum,
                                            @RequestParam("pageSize") Integer pageSize,
                                            Integer draw) {
        UserDO userDO = LoginUtil.getUser(request);
        Page<ProjectFileDownloadLogDO> page = projectFileDownloadLogService.selectMyDownloadByPage(pageNum, pageSize, userDO.getId());
        return PageCommonReturnType.create(convertFromPageProjectFileDownloadLogDO(page), draw);
    }

    @RequestMapping("/cc/list")
    public PageCommonReturnType selectCcAll(HttpServletRequest request,
                                          @RequestParam("pageNum") Integer pageNum,
                                          @RequestParam("pageSize") Integer pageSize,
                                            Integer draw) {
        UserDO userDO = LoginUtil.getUser(request);
        Page<ProjectFileDO> page = projectFileService.selectAllCc(userDO.getId(), pageNum, pageSize);
        return PageCommonReturnType.create(convertFromPageProjectFileDO(page), draw);
    }

    @GetMapping("/download")
    public CommonReturnType downloadFile(HttpServletRequest request, HttpServletResponse response, @RequestParam("fileid") String fileid) throws BusinessException, IOException {
        if (StringUtils.isEmpty(fileid)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        ProjectFileDO projectFileDO = projectFileService.get(fileid);
        if (projectFileDO == null) {
            throw new BusinessException(EmBusinessError.PROJECT_FILE_NOT_EXIST);
        }
        // 保存下载统计信息
        ProjectFileDownloadDO projectFileDownloadDO = new ProjectFileDownloadDO();
        projectFileDownloadDO.setFileId(fileid);
        projectFileDownloadDO.setDownloadCount(1);
        UserDO userDO = LoginUtil.getUser(request);
        projectFileDownloadService.updateFileDownloadTimes(projectFileDownloadDO, userDO);

        String fileName = projectFileDO.getFileName();// 文件名
        if (fileName != null) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
            myFileUtils.downloadFile(projectFileDO.getFilePath(),response.getOutputStream());
        }
        return null;
    }
    private Page<ProjectFileDownloadLogVO> convertFromPageProjectFileDownloadLogDO(Page<ProjectFileDownloadLogDO> page) {
        Page<ProjectFileDownloadLogVO> result = new Page<>();
        BeanUtils.copyProperties(page, result);
        result.clear();
        for (ProjectFileDownloadLogDO projectFileDownloadLogDO : page) {
            result.add(convertFromProjectFileDownloadLogDO(projectFileDownloadLogDO));
        }
        return result;
    }

    private ProjectFileDownloadLogVO convertFromProjectFileDownloadLogDO(ProjectFileDownloadLogDO projectFileDownloadLogDO) {
        ProjectFileDownloadLogVO projectFileDownloadLogVO = new ProjectFileDownloadLogVO();
        BeanUtils.copyProperties(projectFileDownloadLogDO, projectFileDownloadLogVO);
        projectFileDownloadLogVO.setDownloadTime(DateFormatUtils.format(projectFileDownloadLogDO.getTime(), "yyyy-MM-dd HH:mm:ss"));

        ProjectFileDO projectFileDO = projectFileDownloadLogDO.getProjectFileDO();
        if (projectFileDO != null) {
            BeanUtils.copyProperties(projectFileDO, projectFileDownloadLogVO);
            ProjectFileDownloadDO projectFileDownloadDO = projectFileDO.getProjectFileDownloadDO();
            if (projectFileDownloadDO != null) {
                projectFileDownloadLogVO.setDownloadCount(projectFileDownloadDO.getDownloadCount());
            }
        }
        return projectFileDownloadLogVO;
    }

    private Page<ProjectFileVO> convertFromPageProjectFileDO(Page<ProjectFileDO> page) {
        Page<ProjectFileVO> result = new Page<>();
        BeanUtils.copyProperties(page, result);
        result.clear();
        for (ProjectFileDO projectFileDO : page) {
            result.add(convertFromProjectFileDO(projectFileDO));
        }
        return result;
    }
    private ProjectFileVO convertFromProjectFileDO(ProjectFileDO projectFileDO) {
        ProjectFileVO projectFileVO = new ProjectFileVO();
        BeanUtils.copyProperties(projectFileDO, projectFileVO);
        ProjectFileDownloadDO projectFileDownloadDO = projectFileDO.getProjectFileDownloadDO();
        if (projectFileDownloadDO == null) {
            projectFileVO.setDownloadCount(0);
            projectFileVO.setReadCount(0);
        }else{
            projectFileVO.setDownloadCount(projectFileDownloadDO.getDownloadCount());
            projectFileVO.setReadCount(projectFileDownloadDO.getReadCount());
        }
        projectFileVO.setUploadTime(DateFormatUtils.format(projectFileDO.getUploadTime(), "yyyy-MM-dd HH:mm:ss"));
        return projectFileVO;
    }
}
