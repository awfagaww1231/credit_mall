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
//        String fileName = "zh.properties";
//        ClassPathResource classPathResource = new ClassPathResource("lang/"+fileName);
//
//
//        String path = System.getProperty("user.dir");
//        String dirPath = path + File.separator +"lang";
//        File dir = new File(dirPath);
//        dir.mkdirs();
//
//        String filePath = dirPath + File.separator +fileName;
//        File file = new File(filePath);
//        if (!file.exists()){
//            file.createNewFile();
//            System.out.println("噶啊嘎嘎");
//        }
        //加载语言包缓存
        langMgrService.reloadLangMgrCache();
    }
}
