package com.ruoyi.system.service.impl;

import com.ruoyi.system.mapper.SwitchMapper;
import com.ruoyi.system.service.ISwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SwitchServiceImpl implements ISwitchService {

    @Autowired
    private SwitchMapper switchMapper;

    @Override
    public int registerValidateCode() {
        return switchMapper.registerValidateCode();
    }

    @Override
    public int updateRegisterValidateCode(int status) {
        int flag = 0;
        if (status == 0){
            status = 1;
            flag = 1;
        }
        if (flag == 0){
            if (status == 1){
                status = 0;
            }
        }
        return switchMapper.updateRegisterValidateCode(status);
    }
}
