package com.bjsxt.service

import com.baomidou.mybatisplus.extension.service.IService
import com.bjsxt.domain.SysMenu

interface SysMenuService : IService<SysMenu?> {
    fun getMenusByUserId(userId: Long?): List<SysMenu?>?
}