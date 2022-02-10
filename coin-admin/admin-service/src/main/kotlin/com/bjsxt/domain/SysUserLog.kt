package com.bjsxt.domain

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*


/**
 * 系统日志
 */
@ApiModel(value = "系统日志")
@TableName(value = "sys_user_log")
class SysUserLog {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private var id: Long? = null

    /**
     * 组
     */
    @TableField(value = "`group`")
    @ApiModelProperty(value = "组")
    private var group: String? = null

    /**
     * 用户Id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value = "用户Id")
    private var userId: Long? = null

    /**
     * 日志类型 1查询 2修改 3新增 4删除 5导出 6审核
     */
    @TableField(value = "`type`")
    @ApiModelProperty(value = "日志类型 1查询 2修改 3新增 4删除 5导出 6审核")
    private var type: Short? = null

    /**
     * 方法
     */
    @TableField(value = "`method`")
    @ApiModelProperty(value = "方法")
    private var method: String? = null

    /**
     * 参数
     */
    @TableField(value = "params")
    @ApiModelProperty(value = "参数")
    private var params: String? = null

    /**
     * 时间
     */
    @TableField(value = "`time`")
    @ApiModelProperty(value = "时间")
    private var time: Long? = null

    /**
     * IP地址
     */
    @TableField(value = "ip")
    @ApiModelProperty(value = "IP地址")
    private var ip: String? = null

    /**
     * 描述
     */
    @TableField(value = "description")
    @ApiModelProperty(value = "描述")
    private var description: String? = null

    /**
     * 备注
     */
    @TableField(value = "remark")
    @ApiModelProperty(value = "备注")
    private var remark: String? = null

    /**
     * 创建时间
     */
    @TableField(value = "created")
    @ApiModelProperty(value = "创建时间")
    private var created: Date? = null

    constructor() {}
    constructor(
        id: Long?,
        group: String?,
        userId: Long?,
        type: Short?,
        method: String?,
        params: String?,
        time: Long?,
        ip: String?,
        description: String?,
        remark: String?,
        created: Date?
    ) {
        this.id = id
        this.group = group
        this.userId = userId
        this.type = type
        this.method = method
        this.params = params
        this.time = time
        this.ip = ip
        this.description = description
        this.remark = remark
        this.created = created
    }
}