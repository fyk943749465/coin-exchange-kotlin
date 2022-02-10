package com.bjsxt.domain

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*

/**
 * 网站配置信息
 */
@ApiModel(value = "网站配置信息")
@TableName(value = "web_config")
class WebConfig {
    /**
     * Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "Id")
    private var id: Long? = null

    /**
     * 分组, LINK_BANNER ,WEB_BANNER
     */
    @TableField(value = "`type`")
    @ApiModelProperty(value = "分组, LINK_BANNER ,WEB_BANNER")
    private var type: String? = null

    /**
     * 名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value = "名称")
    private var name: String? = null

    /**
     * 值
     */
    @TableField(value = "`value`")
    @ApiModelProperty(value = "值")
    private var value: String? = null

    /**
     * 权重
     */
    @TableField(value = "sort")
    @ApiModelProperty(value = "权重")
    private var sort: Short? = null

    /**
     * 创建时间
     */
    @TableField(value = "created")
    @ApiModelProperty(value = "创建时间")
    private var created: Date? = null

    /**
     * 超链接地址
     */
    @TableField(value = "url")
    @ApiModelProperty(value = "超链接地址")
    private var url: String? = null

    /**
     * 是否使用 0 否 1是
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value = "是否使用 0 否 1是")
    private var status: Boolean? = null

    constructor() {}
    constructor(
        id: Long?,
        type: String?,
        name: String?,
        value: String?,
        sort: Short?,
        created: Date?,
        url: String?,
        status: Boolean?
    ) {
        this.id = id
        this.type = type
        this.name = name
        this.value = value
        this.sort = sort
        this.created = created
        this.url = url
        this.status = status
    }
}