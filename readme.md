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

