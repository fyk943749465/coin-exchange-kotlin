package com.bjsxt.domain

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*

@ApiModel(value = "系统菜单")
@TableName(value = "sys_menu")
class SysMenu {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private var id: Long? = null

    /**
     * 上级菜单ID
     */
    @TableField(value = "parent_id")
    @ApiModelProperty(value = "上级菜单ID")
    private var parentId: Long? = null

    /**
     * 上级菜单唯一KEY值
     */
    @TableField(value = "parent_key")
    @ApiModelProperty(value = "上级菜单唯一KEY值")
    private var parentKey: String? = null

    /**
     * 类型 1-分类 2-节点
     */
    @TableField(value = "`type`")
    @ApiModelProperty(value = "类型 1-分类 2-节点")
    private var type: Byte? = null

    /**
     * 名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value = "名称")
    private var name: String? = null

    /**
     * 描述
     */
    @TableField(value = "`desc`")
    @ApiModelProperty(value = "描述")
    private var desc: String? = null

    /**
     * 目标地址
     */
    @TableField(value = "target_url")
    @ApiModelProperty(value = "目标地址")
    private var targetUrl: String? = null

    /**
     * 排序索引
     */
    @TableField(value = "sort")
    @ApiModelProperty(value = "排序索引")
    private var sort: Int? = null

    /**
     * 状态 0-无效； 1-有效；
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value = "状态 0-无效； 1-有效；")
    private var status: Byte? = null

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
        parentId: Long?,
        parentKey: String?,
        type: Byte?,
        name: String?,
        desc: String?,
        targetUrl: String?,
        sort: Int?,
        status: Byte?,
        createBy: Long?,
        modifyBy: Long?,
        created: Date?,
        lastUpdateTime: Date?
    ) {
        this.id = id
        this.parentId = parentId
        this.parentKey = parentKey
        this.type = type
        this.name = name
        this.desc = desc
        this.targetUrl = targetUrl
        this.sort = sort
        this.status = status
        this.createBy = createBy
        this.modifyBy = modifyBy
        this.created = created
        this.lastUpdateTime = lastUpdateTime
    }
}