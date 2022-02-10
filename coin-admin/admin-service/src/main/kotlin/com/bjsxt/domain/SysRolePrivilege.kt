package com.bjsxt.domain

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * 角色权限配置
 */
@ApiModel(value = "角色权限配置")
@TableName(value = "sys_role_privilege")
class SysRolePrivilege {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private var id: Long? = null

    @TableField(value = "role_id")
    @ApiModelProperty(value = "")
    private var roleId: Long? = null

    @TableField(value = "privilege_id")
    @ApiModelProperty(value = "")
    private var privilegeId: Long? = null

    constructor() {}
    constructor(id: Long?, roleId: Long?, privilegeId: Long?) {
        this.id = id
        this.roleId = roleId
        this.privilegeId = privilegeId
    }
}