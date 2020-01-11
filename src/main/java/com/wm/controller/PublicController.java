package com.wm.controller;

import com.wm.dataobject.WebsiteManagementDO;
import com.wm.response.CommonReturnType;
import com.wm.service.WebsiteManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/public")
public class PublicController extends BaseController {

    @Autowired
    private WebsiteManagementService websiteManagementService;

    @RequestMapping("/get")
    public CommonReturnType get(@RequestParam("modualType")String modualType) {
        List<WebsiteManagementDO> websiteManagementDOPage = websiteManagementService.selectByModualType(modualType);
        return CommonReturnType.create(websiteManagementDOPage);
    }


}
