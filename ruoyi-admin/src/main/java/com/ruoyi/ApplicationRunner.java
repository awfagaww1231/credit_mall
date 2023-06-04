package com.ruoyi;

import com.ruoyi.system.service.ILangMgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//系统启动执行类

@Component
public class ApplicationRunner implements CommandLineRunner {

    @Autowired
    private ILangMgrService langMgrService;

    @Override
    public void run(String... args) throws Exception {
        //加载语言包缓存
        langMgrService.reloadLangMgrCache();
    }
}
