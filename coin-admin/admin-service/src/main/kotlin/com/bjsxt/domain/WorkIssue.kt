package com.bjsxt.domain

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*


/**
 * 工单记录
 */
@ApiModel(value = "工单记录")
@TableName(value = "work_issue")
class WorkIssue {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private var id: Long? = null

    /**
     * 用户id(提问用户id)
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value = "用户id(提问用户id)")
    private var userId: Long? = null

    /**
     * 回复人id
     */
    @TableField(value = "answer_user_id")
    @ApiModelProperty(value = "回复人id")
    private var answerUserId: Long? = null

    /**
     * 回复人名称
     */
    @TableField(value = "answer_name")
    @ApiModelProperty(value = "回复人名称")
    private var answerName: String? = null

    /**
     * 工单内容
     */
    @TableField(value = "question")
    @ApiModelProperty(value = "工单内容")
    private var question: String? = null

    /**
     * 回答内容
     */
    @TableField(value = "answer")
    @ApiModelProperty(value = "回答内容")
    private var answer: String? = null

    /**
     * 状态：1-待回答；2-已回答；
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value = "状态：1-待回答；2-已回答；")
    private var status: Boolean? = null

    /**
     * 修改时间
     */
    @TableField(value = "last_update_time")
    @ApiModelProperty(value = "修改时间")
    private var lastUpdateTime: Date? = null

    /**
     * 创建时间
     */
    @TableField(value = "created")
    @ApiModelProperty(value = "创建时间")
    private var created: Date? = null

    constructor() {}
    constructor(
        id: Long?,
        userId: Long?,
        answerUserId: Long?,
        answerName: String?,
        question: String?,
        answer: String?,
        status: Boolean?,
        lastUpdateTime: Date?,
        created: Date?
    ) {
        this.id = id
        this.userId = userId
        this.answerUserId = answerUserId
        this.answerName = answerName
        this.question = question
        this.answer = answer
        this.status = status
        this.lastUpdateTime = lastUpdateTime
        this.created = created
    }
}