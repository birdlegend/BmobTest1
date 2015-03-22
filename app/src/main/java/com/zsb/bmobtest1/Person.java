package com.zsb.bmobtest1;

import cn.bmob.v3.BmobObject;

/**
 * Created by zsb on 2015/3/21.
 */
public class Person extends BmobObject{
    private String name;
    private String address;
//你 好贱
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
    
        return name;
    }

    public String getAddress() {
        return address;
    }
}
