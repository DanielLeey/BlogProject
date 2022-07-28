# BlogProject
博客Springboot+SpringSecurity+redis
# security

## 登录

controller

service  构造Authentication认证实现类UsernamePasswordAuthenticationToken  调用

ProviderManager authenticate()

~~~~java
public Authentication authenticate(Authentication authentication) throws AuthenticationException {
...
}
~~~~



DaoAuthenticationProvider additionalAuthenticationChecks()对比传入的presentedPassword和UserDetailsService返回的UserDetails对象的password对比

~~~~java
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		if (authentication.getCredentials() == null) {
			this.logger.debug("Failed to authenticate since no credentials provided");
			throw new BadCredentialsException(this.messages
					.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
		}
		String presentedPassword = authentication.getCredentials().toString();
		if (!this.passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
			this.logger.debug("Failed to authenticate since password does not match stored value");
			throw new BadCredentialsException(this.messages
					.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
		}
	}
~~~~



## 认证





## 授权





## 改进

### 1.问题一

问题描述：

登录时，先通过UserDetailsService实现类查询数据库用户信息封装为UserDetails对象返回，如果多次密码错误会导致重复查询数据库。

解决方案：

将UserDetailsService实现类查询数据库用户信息，存入redis中，重复登录时从redis先查询。

将登录service中，通过认证后获取的UserDetails存入redis，这一步骤删除



### 2.问题二

问题描述：

如果用户密码修改，redis中保存的还是原始密码，未更新，传入的正确密码匹配不上

解决方案：

修改密码之后删除redis中的UserDetails对象

同理角色修改，资源类型修改都需要删除redis中缓存

~~~~java
/**
 * 后台用户缓存操作类
 */
public interface UserCacheService {
    /**
     * 删除后台用户缓存
     */
    void delUser(Long userId);

    /**
     * 删除后台用户资源列表缓存
     */
    void delResourceList(Long userId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     */
    void delResourceListByRole(Long roleId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     */
    void delResourceListByRoleIds(List<Long> roleIds);

    /**
     * 当资源信息改变时，删除资源项目后台用户缓存
     */
    void delResourceListByResource(Long resourceId);
}
~~~~



### 3.问题三

问题描述：

如果redis宕机，会导致登录时从redis获取UserDetails对象直接报错，无法登录

解决方案：

通过AOP处理缓存操作异常

要保证缓存业务类中的方法执行不影响正常的业务逻辑，就需要在所有方法中添加`try catch`逻辑。使用AOP，我们可以在一个地方写上`try catch`逻辑，然后应用到所有方法上去。