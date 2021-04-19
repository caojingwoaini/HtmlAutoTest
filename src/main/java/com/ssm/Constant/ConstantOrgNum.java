package com.ssm.Constant;


public enum  ConstantOrgNum {
    Org_Code_0(0,"9095","MA01H3BQ1"),
    Org_Code_1(1,"9106","MA005DBW1"),
    Org_Code_2(2,"9107","MA31H3805"),
    Org_Code_3(3,"9131","605150501"),
    Org_Code_4(4,"9121","MA6BLF0A5");

    //下标值
    private int indexNum;

    //公司标识
    private String orgNum;

    //组织机构代码
    private String partnerKey;

    ConstantOrgNum(int indexNum,String orgNum,String partnerKey){
        this.indexNum=indexNum;
        this.orgNum=orgNum;
        this.partnerKey=partnerKey;
    }

    public static boolean checkConstantOrgNum(String OrgNo){
        for (ConstantOrgNum constantOrgNum:ConstantOrgNum.values()){
            if (constantOrgNum.orgNum.equals(OrgNo)){
                return true;
            }
        }
        return false;
    }

}
