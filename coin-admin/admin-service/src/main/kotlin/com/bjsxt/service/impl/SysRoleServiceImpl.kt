package com.bjsxt.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.bjsxt.domain.SysRole
import com.bjsxt.mapper.SysRoleMapper
import com.bjsxt.service.SysRoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils

@Service
open class SysRoleServiceImpl : ServiceImpl<SysRoleMapper?, SysRole?>(), SysRoleService {
    @Autowired
    lateinit var sysRoleMapper: SysRoleMapper

    /**
     * 判断一个用户是否为超级的管理员
     *
     * @param userId
     * @return
     */
    override fun isSuperAdmin(userId: Long?): Boolean {
        // 当用户的角色code 为：ROLE_ADMIN 时，该用户为超级的管理员
        // 用户的id->用户的角色->该角色的Code是否为ROLE_ADMIN
        val roleCode = sysRoleMapper!!.getUserRoleCode(userId)
        return !StringUtils.isEmpty(roleCode) && roleCode == "ROLE_ADMIN"
    }
}