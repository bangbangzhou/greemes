package com.cn.greemes.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
/**
 * 后台菜单节点封装
 *
 */
@Getter
@Setter
public class MyMesMenuNode {
    @ApiModelProperty(value = "子级菜单")
    private List<MyMesMenuNode> children;
}
