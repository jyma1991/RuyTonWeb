package com.ryt.web.util;

import java.util.ArrayList;
import java.util.List;

import com.ryt.web.entity.auth.AuthSystemResource;




public class TreeUtil {

    public static List<AuthSystemResource> buildTreeData(List<AuthSystemResource> list) {

        List<AuthSystemResource> menu = new ArrayList<AuthSystemResource>();

        resolveMenuTree(list, 0, menu);

        return menu;
    }

    public static int resolveMenuTree(List<AuthSystemResource> menus, int parentMenuId, List<AuthSystemResource> nodes) {

        int count = 0;
        for (AuthSystemResource menu : menus) {
            if (menu.getParentId() == parentMenuId) {
                AuthSystemResource node = new AuthSystemResource();

                nodes.add(node);
                node.setId(menu.getId());
                node.setSysResName(menu.getText());
                node.setUrl(menu.getUrl());
                node.setParentId(menu.getParentId());
                node.setChildren(new ArrayList<AuthSystemResource>());
                node.setaSystemFunctions(menu.getaSystemFunctions());
                //新增
                node.setUserName(menu.getUserName());
                node.setSortId(menu.getSortId());
                node.setDataTypeResource(menu.getDataTypeResource());
                node.setEditedTime(menu.getEditedTime());
                node.setIsDeleted(menu.getIsDeleted());
                node.setOperaterId(menu.getOperaterId());
                node.setUuid(menu.getUuid());
                node.setUserId(menu.getUserId());
                resolveMenuTree(menus, menu.getId(), node.getChildren());
                count++;
            }
        }
        return count;
    }
}
