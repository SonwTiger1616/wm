package com.wm.controller;

import com.github.pagehelper.Page;
import com.wm.controller.viewobject.WebsiteManagementVO;
import com.wm.dataobject.UserDO;
import com.wm.dataobject.WebsiteManagementDO;
import com.wm.error.BusinessException;
import com.wm.error.EmBusinessError;
import com.wm.response.CommonReturnType;
import com.wm.response.PageCommonReturnType;
import com.wm.service.WebsiteManagementService;
import com.wm.util.LoginUtil;
import com.wm.util.MyFileUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/website")
public class WebsiteManagementController extends BaseController {


    @Autowired
    private WebsiteManagementService websiteManagementService;

    @Autowired
    private MyFileUtils myFileUtils;


    @RequestMapping("/add")
    public CommonReturnType add(@RequestParam("modualType") String modualType,
                                   String title,
                                   String productNumber,
                                   String detailedDescription,
                                   @RequestParam("file") MultipartFile[] files,
                                   HttpServletRequest request) throws BusinessException {
        if (files == null || files.length == 0 || StringUtils.isBlank(modualType)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        List<String> filesPath = saveFiles(files);
        WebsiteManagementDO websiteManagementDO = new WebsiteManagementDO();
        UserDO userDO = LoginUtil.getUser(request);
        websiteManagementDO.setModualType(modualType);
        websiteManagementDO.setTitle(title);
        websiteManagementDO.setProductNumber(productNumber);
        websiteManagementDO.setDetailedDescription(detailedDescription);
        websiteManagementDO.setFilesPath(filesPath);
        websiteManagementDO.setUploadUser(userDO.getName());
        websiteManagementDO.setUploadTime(new Date());
        websiteManagementService.insert(websiteManagementDO);
        return CommonReturnType.create("操作成功");
    }

    @RequestMapping("/update")
    public CommonReturnType update(@RequestParam("id") Integer id,
                                   @RequestParam("modualType") String modualType,
                                   String title,
                                   String productNumber,
                                   String detailedDescription,
                                   @RequestParam("file") MultipartFile[] files,
                                   HttpServletRequest request) throws BusinessException {
        WebsiteManagementDO websiteManagementDO = websiteManagementService.get(id);
        if (websiteManagementDO == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        List<String> filesPath = saveFiles(files);
        UserDO userDO = LoginUtil.getUser(request);
        websiteManagementDO.setModualType(modualType);
        websiteManagementDO.setTitle(title);
        websiteManagementDO.setProductNumber(productNumber);
        websiteManagementDO.setDetailedDescription(detailedDescription);
        websiteManagementDO.setFilesPath(filesPath);
        websiteManagementDO.setUploadUser(userDO.getName());
        websiteManagementDO.setUploadTime(new Date());
        websiteManagementService.update(websiteManagementDO);
        return CommonReturnType.create("操作成功");
    }

    private List<String> saveFiles(MultipartFile[] files) throws BusinessException {
        List<String> filesPath = new ArrayList<>();
        for(MultipartFile file:files) {
            filesPath.add(myFileUtils.saveWebSiteFile(file));
        }
        return filesPath;
    }

    @RequestMapping("/delete")
    public CommonReturnType delete(@RequestParam("id") Integer id) throws BusinessException {
        boolean b = websiteManagementService.delete(id);
        if (!b) {
           throw new BusinessException(EmBusinessError.OPTION_FAIL);
        }
        return CommonReturnType.create("操作成功");
    }

    @RequestMapping("/selectByPage")
    public PageCommonReturnType select(@RequestParam("pageNum")Integer pageNum,
                                       @RequestParam("pageSize")Integer pageSize,
                                       String modualType,
                                       Integer draw) {
        Page<WebsiteManagementDO> websiteManagementDOPage = websiteManagementService.selectByPage(pageNum, pageSize,modualType);
        Page<WebsiteManagementVO> websiteManagementVOPage = new Page<>();
        BeanUtils.copyProperties(websiteManagementDOPage, websiteManagementVOPage);
        websiteManagementVOPage.clear();
        for (WebsiteManagementDO websiteManagementDO : websiteManagementDOPage) {
            WebsiteManagementVO websiteManagementVO = convertFromWebsiteManagementDO(websiteManagementDO);
            websiteManagementVOPage.add(websiteManagementVO);
        }
        return PageCommonReturnType.create(websiteManagementVOPage, draw);
    }



    @RequestMapping("/get")
    public CommonReturnType get(@RequestParam("id") Integer id) throws BusinessException {
        WebsiteManagementDO websiteManagementDO = websiteManagementService.get(id);
        return CommonReturnType.create(convertFromWebsiteManagementDO(websiteManagementDO));
    }

    @RequestMapping("/picture/delete")
    public CommonReturnType deletePicture(@RequestParam("id") Integer id,
                                          @RequestParam("filePath") String filePath) throws BusinessException {
        boolean flag = websiteManagementService.deleteFilePath(id, filePath);
        if (!flag) {
            throw new BusinessException(EmBusinessError.OPTION_FAIL);
        }
        myFileUtils.deleteWebsiteFile(filePath);
        return CommonReturnType.create("操作成功");
    }

    private WebsiteManagementVO convertFromWebsiteManagementDO(WebsiteManagementDO websiteManagementDO) {
        WebsiteManagementVO websiteManagementVO = new WebsiteManagementVO();
        BeanUtils.copyProperties(websiteManagementDO,websiteManagementVO);
        String modualType = websiteManagementDO.getModualType();
        String modualName = "";
        //模块类型：1 首页banner，2 产品中心，3 成功案例，4 解决方案
        switch (modualType){
            case "1":
                modualName = "首页banner";
                break;
            case "2":
                modualName = "产品中心";
                break;
            case "3":
                modualName = "成功案例";
                break;
            case "4":
                modualName = "解决方案";
                break;
            default:
                break;
        }
        websiteManagementVO.setModualName(modualName);
        websiteManagementVO.setUploadTime(DateFormatUtils.format(websiteManagementDO.getUploadTime(), "yyyy-MM-dd HH:mm:ss"));
        return websiteManagementVO;
    }
}
