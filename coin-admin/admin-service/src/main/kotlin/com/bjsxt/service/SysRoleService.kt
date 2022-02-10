package com.bjsxt.service

import com.baomidou.mybatisplus.extension.service.IService
import com.bjsxt.domain.SysRole

interface SysRoleService : IService<SysRole?> {
    fun isSuperAdmin(userId: Long?): Boolean
}
