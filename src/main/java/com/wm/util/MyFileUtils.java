package com.wm.util;

import com.wm.error.BusinessException;
import com.wm.error.EmBusinessError;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@Component
public class MyFileUtils {

    @Value("${my.project.dir}")
    private String projectDir;

    @Value("${my.img.dir}")
    private String imgDir;

    private String saveFile(MultipartFile file, String dir) throws BusinessException {
            String fileName = file.getOriginalFilename();
            fileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));

            String path =  DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd")
                    + "/" + fileName;

            File dest = new File(dir + "/" +path);
            if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest); //保存文件
                return path;
            } catch (IOException e) {
                e.printStackTrace();
                throw new BusinessException(EmBusinessError.OPTION_FAIL);
            }

    }

    public String saveWebSiteFile(MultipartFile file) throws BusinessException {
        return saveFile(file, imgDir);
    }

    public String saveProjectFile(MultipartFile file) throws BusinessException {
        return saveFile(file, projectDir);
    }

    public void downloadFile(String filePath, OutputStream outputStream) throws IOException {
        //设置文件路径
        File file = new File(projectDir + File.separator + filePath);
        FileUtils.copyFile(file, outputStream);
    }

    public void deleteWebsiteFile(String filePath) {
        File file = new File(imgDir + File.separator + filePath);
        file.delete();
    }
}
