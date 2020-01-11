package com.wm.service;

import com.wm.dataobject.ProjectFileDownloadDO;
import com.wm.dataobject.UserDO;

public interface ProjectFileDownloadService {

    void updateFileDownloadTimes(ProjectFileDownloadDO projectFileDownloadDO, UserDO userDO);
}
