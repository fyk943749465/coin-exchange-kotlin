package com.bjsxt.domain

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*

@ApiModel(value = "平台配置信息")
@TableName(value = "config")
class Config {
    fun constructor() {}

    fun constructor(id: Long?, type: String?, code: String?, name: String?, desc: String?, value: String?, created: Date?) {
        this.id = id
        this.type = type
        this.code = code
        this.name = name
        this.desc = desc
        this.value = value
        this.created = created
    }
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private var id: Long? = null

    /**
     * 配置规则类型
     */
    @TableField(value = "`type`")
    @ApiModelProperty(value = "配置规则类型")
    private var type: String? = null

    /**
     * 配置规则代码
     */
    @TableField(value = "code")
    @ApiModelProperty(value = "配置规则代码")
    private var code: String? = null

    /**
     * 配置规则名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value = "配置规则名称")
    private var name: String? = null

    /**
     * 配置规则描述
     */
    @TableField(value = "`desc`")
    @ApiModelProperty(value = "配置规则描述")
    private var desc: String? = null

    /**
     * 配置值
     */
    @TableField(value = "`value`")
    @ApiModelProperty(value = "配置值")
    private var value: String? = null

    /**
     * 创建时间
     */
    @TableField(value = "created")
    @ApiModelProperty(value = "创建时间")
    private var created: Date? = null
}