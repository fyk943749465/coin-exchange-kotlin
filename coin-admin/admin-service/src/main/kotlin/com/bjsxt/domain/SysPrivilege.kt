package com.bjsxt.domain

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*
/**
 * 权限配置
 */
@ApiModel(value = "权限配置")
@TableName(value = "sys_privilege")
class SysPrivilege {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    var id: Long? = null

    /**
     * 所属菜单Id
     */
    @TableField(value = "menu_id")
    @ApiModelProperty(value = "所属菜单Id")
    var menuId: Long? = null

    /**
     * 功能点名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value = "功能点名称")
    var name: String? = null

    /**
     * 功能描述
     */
    @TableField(value = "description")
    @ApiModelProperty(value = "功能描述")
    var description: String? = null

    @TableField(value = "url")
    @ApiModelProperty(value = "")
    var url: String? = null

    @TableField(value = "`method`")
    @ApiModelProperty(value = "")
    var method: String? = null

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    @ApiModelProperty(value = "创建人")
    var createBy: Long? = null

    /**
     * 修改人
     */
    @TableField(value = "modify_by")
    @ApiModelProperty(value = "修改人")
    var modifyBy: Long? = null

    /**
     * 创建时间
     */
    @TableField(value = "created")
    @ApiModelProperty(value = "创建时间")
    var created: Date? = null

    /**
     * 修改时间
     */
    @TableField(value = "last_update_time")
    @ApiModelProperty(value = "修改时间")
    var lastUpdateTime: Date? = null

    constructor() {}
    constructor(
        id: Long?,
        menuId: Long?,
        name: String?,
        description: String?,
        url: String?,
        method: String?,
        createBy: Long?,
        modifyBy: Long?,
        created: Date?,
        lastUpdateTime: Date?
    ) {
        this.id = id
        this.menuId = menuId
        this.name = name
        this.description = description
        this.url = url
        this.method = method
        this.createBy = createBy
        this.modifyBy = modifyBy
        this.created = created
        this.lastUpdateTime = lastUpdateTime
    }
}