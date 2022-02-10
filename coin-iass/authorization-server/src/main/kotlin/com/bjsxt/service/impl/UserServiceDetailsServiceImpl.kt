package com.bjsxt.service.impl

import com.bjsxt.constant.LoginConstant.ADMIN_ROLE_CODE
import com.bjsxt.constant.LoginConstant.ADMIN_TYPE
import com.bjsxt.constant.LoginConstant.MEMBER_TYPE
import com.bjsxt.constant.LoginConstant.QUERY_ADMIN_SQL
import com.bjsxt.constant.LoginConstant.QUERY_ADMIN_USER_WITH_ID
import com.bjsxt.constant.LoginConstant.QUERY_ALL_PERMISSIONS
import com.bjsxt.constant.LoginConstant.QUERY_MEMBER_SQL
import com.bjsxt.constant.LoginConstant.QUERY_MEMBER_USER_WITH_ID
import com.bjsxt.constant.LoginConstant.QUERY_PERMISSION_SQL
import com.bjsxt.constant.LoginConstant.QUERY_ROLE_CODE_SQL
import com.bjsxt.constant.LoginConstant.REFRESH_TOKEN
import org.apache.commons.lang.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.IncorrectResultSizeDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.*
import java.util.stream.Collectors

@Service
class UserServiceDetailsServiceImpl : UserDetailsService {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        var username = username
        val requestAttributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
        val loginType = requestAttributes.request.getParameter("login_type")
        if (StringUtils.isEmpty(loginType)) {
            throw AuthenticationServiceException("please add login_type paramter")
        }
        var userDetails: UserDetails? = null
        val grantType = requestAttributes.request.getParameter("grant_type")
        try {
            if (REFRESH_TOKEN == grantType.uppercase(Locale.getDefault())) {
                username = adjustUsername(username, loginType)
            }
            userDetails = when (loginType) {
                ADMIN_TYPE -> loadAdminUserByUsername(username)
                MEMBER_TYPE -> loadMemberUserByUsername(username)
                else -> throw AuthenticationServiceException("$loginType is no supported")
            }
        } catch (e: IncorrectResultSizeDataAccessException) {
            throw UsernameNotFoundException("member: $username is not exists")
        }
        return userDetails
    }

    private fun adjustUsername(username: String, loginType: String): String {
        if (ADMIN_TYPE == loginType) {
            return jdbcTemplate!!.queryForObject(
                QUERY_ADMIN_USER_WITH_ID,
                String::class.java, username
            )
        }
        return if (MEMBER_TYPE == loginType) {
            jdbcTemplate!!.queryForObject(
                QUERY_MEMBER_USER_WITH_ID,
                String::class.java, username
            )
        } else username
    }

    private fun loadMemberUserByUsername(username: String): UserDetails {
        return jdbcTemplate!!.queryForObject<UserDetails>(QUERY_MEMBER_SQL,
            { resultSet, i ->
                if (resultSet.wasNull()) {
                    throw UsernameNotFoundException("member $username is not exists")
                }
                val id = resultSet.getLong("id")
                val password = resultSet.getString("password")
                val status = resultSet.getInt("status")
                User(
                    id.toString(),
                    password,
                    status == 1,
                    true,
                    true,
                    true,
                    Arrays.asList(SimpleGrantedAuthority("ROLE_USER"))
                )
            }, username, username
        )
    }

    private fun loadAdminUserByUsername(username: String): UserDetails {
        return jdbcTemplate!!.queryForObject(QUERY_ADMIN_SQL,
            { resultSet, i ->
                if (resultSet.wasNull()) {
                    throw UsernameNotFoundException("user: $username is not exists")
                }
                val id = resultSet.getLong("id")
                val password = resultSet.getString("password")
                val status = resultSet.getInt("status")
                User(
                    id.toString(),
                    password,
                    status == 1,
                    true,
                    true,
                    true,
                    getUserPermissions(id)
                )
            }, username
        )
    }

    private fun getUserPermissions(id: Long): Collection<GrantedAuthority?> {
        val code = jdbcTemplate!!.queryForObject(
            QUERY_ROLE_CODE_SQL,
            String::class.java, id
        )
        var permissions: List<String?>? = null
        permissions = if (ADMIN_ROLE_CODE == code) {
            jdbcTemplate.queryForList(QUERY_ALL_PERMISSIONS, String::class.java)
        } else {
            jdbcTemplate.queryForList(QUERY_PERMISSION_SQL, String::class.java, id)
        }
        return if (permissions == null || permissions.isEmpty()) {
            listOf<GrantedAuthority>()
        } else permissions
            .stream()
            .distinct()
            .map { perm: String? ->
                SimpleGrantedAuthority(
                    perm
                )
            }
            .collect(Collectors.toSet())
    }
}