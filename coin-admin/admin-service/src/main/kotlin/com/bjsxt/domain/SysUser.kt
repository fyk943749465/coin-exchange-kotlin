package com.bjsxt.domain

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*

/**
 * 平台用户
 */
@ApiModel(value = "平台用户")
@TableName(value = "sys_user")
class SysUser {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private var id: Long? = null

    /**
     * 账号
     */
    @TableField(value = "username")
    @ApiModelProperty(value = "账号")
    private var username: String? = null

    /**
     * 密码
     */
    @TableField(value = "`password`")
    @ApiModelProperty(value = "密码")
    private var password: String? = null

    /**
     * 姓名
     */
    @TableField(value = "fullname")
    @ApiModelProperty(value = "姓名")
    private var fullname: String? = null

    /**
     * 手机号
     */
    @TableField(value = "mobile")
    @ApiModelProperty(value = "手机号")
    private var mobile: String? = null

    /**
     * 邮箱
     */
    @TableField(value = "email")
    @ApiModelProperty(value = "邮箱")
    private var email: String? = null

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
        username: String?,
        password: String?,
        fullname: String?,
        mobile: String?,
        email: String?,
        status: Byte?,
        createBy: Long?,
        modifyBy: Long?,
        created: Date?,
        lastUpdateTime: Date?
    ) {
        this.id = id
        this.username = username
        this.password = password
        this.fullname = fullname
        this.mobile = mobile
        this.email = email
        this.status = status
        this.createBy = createBy
        this.modifyBy = modifyBy
        this.created = created
        this.lastUpdateTime = lastUpdateTime
    }
}