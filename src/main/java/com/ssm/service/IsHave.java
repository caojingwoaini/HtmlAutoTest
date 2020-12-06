package com.ssm.service;

import java.util.ArrayList;

//该类实现判断是否存在的相关方法
public interface IsHave {



    //判断中电联万马密钥信息与第三方密钥信息是否一致,并返回加密密钥和加密向量
    public ArrayList<String> SecretAndThird_secret(String Secret, String third_secret);

}
