身份认证流程
1.获取用户名和密码，封装用户名和密码，将认证操作委托为身份认证管理器 -- UsernamePasswordAuthenticationFilter
2.调用根据用户名查询用户的方法loadUserByUsername -- DaoAuthenticationProvider
3.在配置的内存中查询用户，并将查询到用户信息封装在UserDetails（User）对象 -- InMemoryUserDetailsManager（内存用户）是UserDetailsService接口的实现类
4.对查询到的用户做检查（前，中，后）。前置检查（用户名是否过期，用户是否禁用，用户是否锁定），中间检查（核对密码），后置检查（密码是否过期） -- AbstractUserDetailsAuthenticationProvider
5.清除密码 -- ProviderManager

总结：
1.如果从指定数据库查询用户，需要重写loadUserByUsername方法，实现UserDetailsService接口
2.默认获取用户名和密码是通过名值的方式，如果需要获取json字符串，重写UsernamePasswordAuthenticationFilter过滤器