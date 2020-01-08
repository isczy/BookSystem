package com.xst.project.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //必须设置设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        
        //如果不设置默认会自动寻找web工程根目录下的"/login.jsp"页面
        //设置没有登录跳转的页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        
        //添加拦截器
        /**
         * 常用内置过滤器
         *  anon:无需认证（登录）用户可以直接访问（就相当于游客）
         *  authc:必须要认证才能访问
         *  user：使用了remenberMe的功能的用户可以直接无需登录访问（相当于记住登录状态）
         *  perms:必须获取资源权限才能访问
         *  role：必须获取角色授权才能访问
         *  logout:用户登出，这里不用设置控制器，退出后直接跳转到/
         */
        Map<String,String> filterMap = new LinkedHashMap<String,String>();
        //配置不会被拦截的链接 顺序判断 anon：所有的url都可以匿名访问
        filterMap.put("/static/**","anon");
        filterMap.put("/user/login","anon");
        
        //配置退出过滤器,其中具体的实现shiro已经替我们实现了
        filterMap.put("/logout", "logout");
        
        //authc：所有url需要认证才能访问
        filterMap.put("/admin/**", "authc");
        filterMap.put("/houtai/**", "authc");
    
        
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建 安全管理器 SecurityManager
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm
        securityManager.setRealm(myRealm());
       return securityManager;
   }
    /**
     * 身份认证realm；（这个需要自己写，账号密码校验，权限等）
     * @return
     */

    @Bean
    public MyRealm myRealm(){
        return new MyRealm();
    }
    
    /**
     * shiro生命周期处理器
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    	return new LifecycleBeanPostProcessor();
    }
    
    /**
     * 开启shiro的注解（如@RequiresRoles,@RequiresPermissions）,需借助springAOP扫描使用
     * shiro注解的类，并在必要时进行安全逻辑验证
     * 配置以下两个bean即可实现此功能
     * @return
     */
    
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
    	DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
    	advisorAutoProxyCreator.setProxyTargetClass(true);
    	return advisorAutoProxyCreator;
    }
    
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
    	AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
    	authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
    	return authorizationAttributeSourceAdvisor;
    }
    

    /**
     * 配置 ShiroDialect 用于thymeleaf 和shiro标签配合使用
     * @return
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}