package com.bjsxt.controller

import com.baomidou.mybatisplus.core.metadata.OrderItem
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.bjsxt.domain.SysPrivilege
import com.bjsxt.model.R
import com.bjsxt.service.SysPrivilegeService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import springfox.documentation.annotations.ApiIgnore
/**
 * 权限管理的控制器
 * 1 查询
 * 2 修改
 * 3 新增
 * 4 删除
 * 权限管理的权限：
 * 查询：sys_privilege_query
 * 修改：sys_privilege_update
 * 新增：sys_privilege_create
 * 删除：sys_privilege_delete
 */
@RestController
@RequestMapping("/privileges")
@Api(tags = ["权限的管理"])
open class SysPrivilegeController {
    @Autowired
    open lateinit var sysPrivilegeService: SysPrivilegeService

    /**
     * 权限数据的分页查询
     * @param page
     * @return
     */
    @GetMapping
    @ApiOperation(value = "分页查询权限数据")
    @ApiImplicitParams(
        ApiImplicitParam(name = "current", value = "当前页"),
        ApiImplicitParam(name = "size", value = "每页显示的大小")
    )
    @PreAuthorize("hasAuthority('sys_privilege_query')")
    fun findByPage(@ApiIgnore page: Page<SysPrivilege?>): R<Page<SysPrivilege?>> {
        page.addOrder(OrderItem.desc("last_update_time"))
        val sysPrivilegePage = sysPrivilegeService!!.page(page)
        return R.ok(sysPrivilegePage)
    }

    @PostMapping
    @ApiOperation(value = "新增一个权限")
    @PreAuthorize("hasAuthority('sys_privilege_create')")
    @ApiImplicitParams(ApiImplicitParam(name = "sysPrivilege", value = "sysPrivilege 的json数据"))
    fun add(@RequestBody @Validated sysPrivilege: SysPrivilege): R<Any?> {
        val save = sysPrivilegeService!!.save(sysPrivilege)
        return if (save) {
            R.ok("新增成功")
        } else R.fail("新增失败")
    }

    @PatchMapping
    @ApiOperation(value = "修改一个权限")
    @PreAuthorize("hasAuthority('sys_privilege_update')")
    @ApiImplicitParams(ApiImplicitParam(name = "sysPrivilege", value = "sysPrivilege 的json数据"))
    fun update(@RequestBody @Validated sysPrivilege: SysPrivilege): R<Any?> {
        val save = sysPrivilegeService!!.updateById(sysPrivilege)
        return if (save) {
            R.ok("修改成功")
        } else R.fail("修改失败")
    }
}

