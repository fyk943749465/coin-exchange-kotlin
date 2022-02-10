package com.bjsxt.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.bjsxt.domain.SysMenu
import org.springframework.stereotype.Component

@Component
interface SysMenuMapper : BaseMapper<SysMenu?> {
    /**
     * 通过用户的id 查询用户的菜单数据
     * @param userId
     * @return
     */
    fun selectMenusByUserId(userId: Long?): List<SysMenu?>?
}