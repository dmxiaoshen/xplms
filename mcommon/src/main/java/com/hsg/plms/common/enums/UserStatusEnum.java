
package com.hsg.plms.common.enums;

import java.util.HashMap;
import java.util.Map;

import com.hsg.plms.base.enums.SuperEnum;

public enum UserStatusEnum implements SuperEnum{
    Work("正常"),
    Stop("停用")
    ;
    private UserStatusEnum(String name){
        this.name = name;
    }
    private String name;
    
    public String getName(){
        return name;
    }
    
    @Override
    public Map<String, Object> getMap() {
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("name", getName());
        result.put("value", this.name());
        return result;
    }

}
