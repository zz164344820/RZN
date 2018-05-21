api使用说明
本库仅对3级联动做支持，4级联动的扩展未做相应改良，若要对4级联动，可按现有逻辑改动小部分即可

1.传入json文件名，json的地址层级需要是一个级别，若数据源已经存在则不会再次生成内部已经做了相应判断
  AddressUtils.CreateDBData(Context context, String assetsFileName)

2.创建地址选择器，并将选择器放入已有的容器布局中即可
 AddressSelector selector = new AddressSelector(this);
 如：frameLayout.addView(selector.getView());

3.添加选中监听，实现OnAddressSelectedListener接口
  implements OnAddressSelectedListener

4.默认回显，分别传入省，市，县对象（对象需要自己构造）
  selector.defChecked(p,city,county);





