package com.rzn.module_main.ui.sellagriculturalgoods.commoditylist;

/**
 * Created by zz on 2019/5/10.
 */

public class CommodityListBean {

    String createSysUserName; //商品创建人姓名
    String createTime; //创建时间
    String dealer;//经销商
    String goodsBreedType;  //商品的品种 : 1:水果 2:蔬菜  3:粮油   4:其他
    String goodsName;//商品名称
    String goodsId; //商品id
    String isOnLine; //是否在线：  0 ： 下架  1：上架
    String updateTime; //更新时间


    public String getCreateSysUserName() {
        return createSysUserName == null ? "" : createSysUserName;
    }

    public void setCreateSysUserName(String createSysUserName) {
        this.createSysUserName = createSysUserName;
    }

    public String getCreateTime() {
        return createTime == null ? "" : createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDealer() {
        return dealer == null ? "" : dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    public String getGoodsBreedType() {
        return goodsBreedType == null ? "" : goodsBreedType;
    }

    public void setGoodsBreedType(String goodsBreedType) {
        this.goodsBreedType = goodsBreedType;
    }

    public String getGoodsName() {
        return goodsName == null ? "" : goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsId() {
        return goodsId == null ? "" : goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getIsOnLine() {
        return isOnLine == null ? "" : isOnLine;
    }

    public void setIsOnLine(String isOnLine) {
        this.isOnLine = isOnLine;
    }

    public String getUpdateTime() {
        return updateTime == null ? "" : updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
