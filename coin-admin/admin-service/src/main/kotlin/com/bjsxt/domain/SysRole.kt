package com.bjsxt.domain

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*

/**
 * 角色
 */
@ApiModel(value = "角色")
@TableName(value = "sys_role")
class SysRole {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private var id: Long? = null

    /**
     * 名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value = "名称")
    private var name: String? = null

    /**
     * 代码
     */
    @TableField(value = "code")
    @ApiModelProperty(value = "代码")
    private var code: String? = null

    /**
     * 描述
     */
    @TableField(value = "description")
    @ApiModelProperty(value = "描述")
    private var description: String? = null

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
     * 状态0:禁用 1:启用
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value = "状态0:禁用 1:启用")
    private var status: Byte? = null

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
        name: String?,
        code: String?,
        description: String?,
        createBy: Long?,
        modifyBy: Long?,
        status: Byte?,
        created: Date?,
        lastUpdateTime: Date?
    ) {
        this.id = id
        this.name = name
        this.code = code
        this.description = description
        this.createBy = createBy
        this.modifyBy = modifyBy
        this.status = status
        this.created = created
        this.lastUpdateTime = lastUpdateTime
    }
}