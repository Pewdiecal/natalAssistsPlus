package com.caltech.natalassistsplus;

public class Information {
    private int infoBgPic;
    private int infoSmallPic;
    private String infoTitile;
    private String infoCategory;
    private String infoDesc;
    private String infoShortDesc;

    public Information(int infoBgPic, int infoSmallPic, String infoTitile, String infoCategory, String infoDesc, String infoShortDesc){
        this.infoBgPic = infoBgPic;
        this.infoSmallPic = infoSmallPic;
        this.infoTitile = infoTitile;
        this.infoCategory = infoCategory;
        this.infoDesc = infoDesc;
        this.infoShortDesc = infoShortDesc;
    }

    public int getInfoBgPic(){
        return infoBgPic;
    }
    public int getInfoSmallPic(){
        return infoSmallPic;
    }
    public String getInfoTitile(){
        return infoTitile;
    }
    public String getInfoCategory(){
        return infoCategory;
    }
    public String getInfoDesc(){
        return infoDesc;
    }
    public String getInfoShortDesc(){
        return infoShortDesc;
    }
}
