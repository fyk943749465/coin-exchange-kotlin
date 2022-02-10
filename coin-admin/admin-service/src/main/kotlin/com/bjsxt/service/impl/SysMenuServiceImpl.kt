package com.bjsxt.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.bjsxt.domain.SysMenu
import com.bjsxt.mapper.SysMenuMapper
import com.bjsxt.service.SysMenuService
import com.bjsxt.service.SysRoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class SysMenuServiceImpl : ServiceImpl<SysMenuMapper?, SysMenu?>(), SysMenuService {
    @Autowired
    lateinit var sysRoleService: SysRoleService

    @Autowired
    lateinit var sysMenuMapper: SysMenuMapper

    /**
     * 通过用户的id 查询用户的菜单数据
     *
     * @param userId
     * @return
     */
    override fun getMenusByUserId(userId: Long?): List<SysMenu?>? {
        // 1 如果该用户是超级管理员->>拥有所有的菜单
        return if (sysRoleService?.isSuperAdmin(userId) == true) {
            list() // 查询所有
        } else sysMenuMapper!!.selectMenusByUserId(userId)
        // 2 如果该用户不是超级管理员->>查询角色->查询菜单
    }
}