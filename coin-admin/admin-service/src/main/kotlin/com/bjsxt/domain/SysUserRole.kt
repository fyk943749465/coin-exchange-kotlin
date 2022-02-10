package com.bjsxt.domain

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*

/**
 * 用户角色配置
 */
@ApiModel(value = "用户角色配置")
@TableName(value = "sys_user_role")
class SysUserRole {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private var id: Long? = null

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    @ApiModelProperty(value = "角色ID")
    private var roleId: Long? = null

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value = "用户ID")
    private var userId: Long? = null

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
        userId: Long?,
        createBy: Long?,
        modifyBy: Long?,
        created: Date?,
        lastUpdateTime: Date?
    ) {
        this.id = id
        this.roleId = roleId
        this.userId = userId
        this.createBy = createBy
        this.modifyBy = modifyBy
        this.created = created
        this.lastUpdateTime = lastUpdateTime
    }
}