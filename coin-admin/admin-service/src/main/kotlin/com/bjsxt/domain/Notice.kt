package com.bjsxt.domain

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*

@ApiModel(value = "系统资讯公告信息")
@TableName(value = "notice")
class Notice {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private var id: Long? = null

    /**
     * 标题
     */
    @TableField(value = "title")
    @ApiModelProperty(value = "标题")
    private var title: String? = null

    /**
     * 简介
     */
    @TableField(value = "description")
    @ApiModelProperty(value = "简介")
    private var description: String? = null

    /**
     * 作者
     */
    @TableField(value = "author")
    @ApiModelProperty(value = "作者")
    private var author: String? = null

    /**
     * 文章状态
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value = "文章状态")
    private var status: Int? = null

    /**
     * 文章排序，越大越靠前
     */
    @TableField(value = "sort")
    @ApiModelProperty(value = "文章排序，越大越靠前")
    private var sort: Int? = null

    /**
     * 内容
     */
    @TableField(value = "content")
    @ApiModelProperty(value = "内容")
    private var content: String? = null

    /**
     * 最后修改时间
     */
    @TableField(value = "last_update_time")
    @ApiModelProperty(value = "最后修改时间")
    private var lastUpdateTime: Date? = null

    /**
     * 创建日期
     */
    @TableField(value = "created")
    @ApiModelProperty(value = "创建日期")
    private var created: Date? = null

    constructor() {}
    constructor(
        id: Long?,
        title: String?,
        description: String?,
        author: String?,
        status: Int?,
        sort: Int?,
        content: String?,
        lastUpdateTime: Date?,
        created: Date?
    ) {
        this.id = id
        this.title = title
        this.description = description
        this.author = author
        this.status = status
        this.sort = sort
        this.content = content
        this.lastUpdateTime = lastUpdateTime
        this.created = created
    }
}