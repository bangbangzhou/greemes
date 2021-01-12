package com.cn.greemes.modules.mes.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.greemes.common.result.CommonPage;
import com.cn.greemes.common.result.CommonResult;
import com.cn.greemes.component.MesDynamicSecurityMetadataSource;
import com.cn.greemes.modules.mes.model.MesResource;
import com.cn.greemes.modules.mes.service.MesResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 后台资源表 前端控制器
 * </p>
 *
 * @author zbb
 * @since 2021-01-11
 */
@RestController
@Api(tags = "MesResourceController", description = "后台资源管理")
@RequestMapping("/mesResource")
public class MesResourceController {

    @Autowired
    private MesResourceService resourceService;
    /**
     * 由于我们的后台资源规则被缓存在了一个Map对象之中，
     * 所以当后台资源发生变化时，我们需要清空缓存的数据，
     * 然后下次查询时就会被重新加载进来。
     * 这里我们需要修改UmsResourceController类，
     * 注入DynamicSecurityMetadataSource，
     * 当修改后台资源时，需要调用clearDataSource方法来清
     * 空缓存的数据。
     */
    @Autowired
    private MesDynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    @ApiOperation("添加后台资源")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody MesResource umsResource) {
        boolean success = resourceService.create(umsResource);
        dynamicSecurityMetadataSource.clearDataSource();
        if (success) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改后台资源")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id,
                               @RequestBody MesResource umsResource) {
        boolean success = resourceService.update(id, umsResource);
        dynamicSecurityMetadataSource.clearDataSource();
        if (success) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据ID获取资源详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<MesResource> getItem(@PathVariable Long id) {
        MesResource umsResource = resourceService.getById(id);
        return CommonResult.success(umsResource);
    }

    @ApiOperation("根据ID删除后台资源")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        boolean success = resourceService.delete(id);
        dynamicSecurityMetadataSource.clearDataSource();
        if (success) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("分页模糊查询后台资源")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<MesResource>> list(@RequestParam(required = false) Long categoryId,
                                                      @RequestParam(required = false) String nameKeyword,
                                                      @RequestParam(required = false) String urlKeyword,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<MesResource> resourceList = resourceService.list(categoryId,nameKeyword, urlKeyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(resourceList));
    }

    @ApiOperation("查询所有后台资源")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<MesResource>> listAll() {
        List<MesResource> resourceList = resourceService.list();
        return CommonResult.success(resourceList);
    }
}

