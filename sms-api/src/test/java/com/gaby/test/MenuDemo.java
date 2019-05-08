package com.gaby.test;

import com.gaby.sms.system.MenuInfo;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MenuDemo {
    @Test
    public void f1() {
        List<MenuInfo> menuInfos = new ArrayList<>();
        MenuInfo menuInfo = new MenuInfo();
        MenuInfo menuInfo1 = new MenuInfo();
        MenuInfo menuInfo2 = new MenuInfo();
        MenuInfo menuInfo3 = new MenuInfo();
        MenuInfo menuInfo4 = new MenuInfo();
        menuInfo.setId(1L);
        menuInfo.setName("系统管理");
        menuInfo.setParentId(-1L);

        menuInfo1.setId(2L);
        menuInfo1.setName("用户管理");
        menuInfo1.setParentId(1L);

        menuInfo2.setId(3L);
        menuInfo2.setName("检测管理");
        menuInfo2.setParentId(2L);

        menuInfo3.setId(4L);
        menuInfo3.setName("系统管理1");
        menuInfo3.setParentId(1L);

        menuInfo4.setId(5L);
        menuInfo4.setName("系统管理1的子级");
        menuInfo4.setParentId(4L);


        menuInfos.add(menuInfo);
        menuInfos.add(menuInfo1);
        menuInfos.add(menuInfo2);
        menuInfos.add(menuInfo3);
        menuInfos.add(menuInfo4);



//        List<MenuInfo> copy = new ArrayList<>(menuInfos);
//        List<MenuInfo> parentInfo=menuInfos.stream().filter(x -> x.getParentId().intValue() == -1).collect(Collectors.toList());
//
//        copy.removeAll(parentInfo);
//        copy.stream().forEach(x->{
//            System.out.println(x.getId()+":"+x.getParentId());
//        });
        //创造一个副本
        List<MenuInfo> copy = new ArrayList<>(menuInfos);
        //筛选出顶层父级菜单  parentId为-1
        List<MenuInfo> parentInfos=menuInfos.stream().filter(x->x.getParentId().intValue()==-1).collect(Collectors.toList());
        //遍历顶层菜单
//        if (CollectionUtils.isNotEmpty(parentInfos)) {
//            copy.removeAll(parentInfos);
//            if (copy.size() != 0) {
//                parentInfos.forEach(parentInfo->{
//                    List<MenuInfo> subMenus = menuInfos.stream().filter(x -> x.getParentId().intValue() == parentInfo.getId().intValue()).collect(Collectors.toList());
//                    if (CollectionUtils.isNotEmpty(subMenus)) {
//                        parentInfo.setSubMenus(ModelDtoConvertUtil.convertDtoList2ModelList(subMenus, SubMenu.class));
//                        copy.removeAll(subMenus);
//                        if (copy.size() != 0) {
//                            parentInfo.getSubMenus().forEach(subMenu->{
//                                List<MenuInfo> collect = menuInfos.stream().filter(x -> x.getParentId().intValue() == subMenu.getId().intValue()).collect(Collectors.toList());
//                                if (CollectionUtils.isNotEmpty(collect)) {
//                                    subMenu.setSubMenus(ModelDtoConvertUtil.convertDtoList2ModelList(collect, SubMenu.class));
//                                }
//                            });
//                        }
//                    }
//                });
//            }
//
//        }
        tree(parentInfos, copy);
        System.out.println(parentInfos);


    }

    public void tree(List<MenuInfo> parentInfos, List<MenuInfo> copy) {
        if (CollectionUtils.isNotEmpty(parentInfos)) {
            copy.removeAll(parentInfos);
            if (copy.size() == 0) {
                return;
            }
            parentInfos.forEach(parentInfo->{
                List<MenuInfo> subMenus = copy.stream().filter(x -> x.getParentId().intValue() == parentInfo.getId().intValue()).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(subMenus)) {
                    parentInfo.setSubMenus(subMenus);
                    tree(parentInfo.getSubMenus(),copy);
                }
            });
        }
    }
}
