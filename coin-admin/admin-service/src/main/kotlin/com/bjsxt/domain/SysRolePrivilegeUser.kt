package com.bjsxt.domain

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * 用户权限配置
 */
@ApiModel(value = "用户权限配置")
@TableName(value = "sys_role_privilege_user")
class SysRolePrivilegeUser {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private var id: Long? = null

    /**
     * 角色Id
     */
    @TableField(value = "role_id")
    @ApiModelProperty(value = "角色Id")
    private var roleId: Long? = null

    /**
     * 用户Id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value = "用户Id")
    private var userId: Long? = null

    /**
     * 权限Id
     */
    @TableField(value = "privilege_id")
    @ApiModelProperty(value = "权限Id")
    private var privilegeId: Long? = null

    constructor() {}
    constructor(id: Long?, roleId: Long?, userId: Long?, privilegeId: Long?) {
        this.id = id
        this.roleId = roleId
        this.userId = userId
        this.privilegeId = privilegeId
    }
}