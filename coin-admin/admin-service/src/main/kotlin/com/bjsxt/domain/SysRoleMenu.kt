package com.bjsxt.domain

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*

/**
 * 角色菜单
 */
@ApiModel(value = "角色菜单")
@TableName(value = "sys_role_menu")
class SysRoleMenu {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private var id: Long? = null

    @TableField(value = "role_id")
    @ApiModelProperty(value = "")
    private var roleId: Long? = null

    @TableField(value = "menu_id")
    @ApiModelProperty(value = "")
    private var menuId: Long? = null

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    @ApiModelProperty(value = "创建人")
    private var createBy: Long? = null

    /**
     * 修改人
     */
    @TableField(value = "modify_by")
    @ApiModelProperty(value = "修改人")
    private var modifyBy: Long? = null

    /**
     * 创建时间
     */
    @TableField(value = "created")
    @ApiModelProperty(value = "创建时间")
    private var created: Date? = null

    /**
     * 修改时间
     */
    @TableField(value = "last_update_time")
    @ApiModelProperty(value = "修改时间")
    private var lastUpdateTime: Date? = null

    constructor() {}
    constructor(
        id: Long?,
        roleId: Long?,
        menuId: Long?,
        createBy: Long?,
        modifyBy: Long?,
        created: Date?,
        lastUpdateTime: Date?
    ) {
        this.id = id
        this.roleId = roleId
        this.menuId = menuId
        this.createBy = createBy
        this.modifyBy = modifyBy
        this.created = created
        this.lastUpdateTime = lastUpdateTime
    }
}