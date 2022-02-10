package com.bjsxt.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.bjsxt.domain.SysRole
import org.springframework.stereotype.Component

@Component
interface SysRoleMapper : BaseMapper<SysRole?> {
    fun getUserRoleCode(userId: Long?): String?
}