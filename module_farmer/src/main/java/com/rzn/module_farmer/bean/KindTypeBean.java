package com.rzn.module_farmer.bean;

/**
 * Created by 17662 on 2018/5/11.
 */

public class KindTypeBean {
    private String isOrder;//":"1", //该类型作物是否(完善接单信息)接单  0: 未接单   1:接单
    private String kindId;//":"40289fab626a9fa101626aa12b0b0002", //作业种类Id
    private String kindName;//":"玉米", //作业种类名称
    private String kindTypeId;//":"111", //作业类型 ID
    private String kindTypeName;//":"小麦打捆", //作业类型名称
    private String types;//":"玉米-小麦打捆", //作业种类 和 作业类型 组合字段（方便展示）
    private String unitPrice;//":"40.30" //单价
}
