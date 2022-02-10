package com.bjsxt.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.bjsxt.domain.SysPrivilege
import com.bjsxt.mapper.SysPrivilegeMapper
import com.bjsxt.service.SysPrivilegeService
import org.springframework.stereotype.Service

@Service
open class SysPrivilegeServiceImpl : ServiceImpl<SysPrivilegeMapper?, SysPrivilege?>(),
    SysPrivilegeService
