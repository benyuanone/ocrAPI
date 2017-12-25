package com.ourway.sys.authorization;

import com.ourway.base.utils.TextUtils;
import com.ourway.sys.model.OurwaySysEmploys;
import com.ourway.sys.service.EmploysService;
import com.ourway.sys.service.MenusService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>方法 ShiroDbRealm : <p>
 * <p>说明:自定义shiro的验证框架</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/12 2:20
 * </pre>
 */
public class ShiroDbRealm extends AuthorizingRealm {
    @Autowired
    EmploysService employsSer;
    @Autowired
    MenusService menuService;

    /**
     * <p>方法:doGetAuthorizationInfo 获取当前用户，为当前登录的Subject授予角色和权限  </p>
     * <ul>
     * <li> @param principalCollection 权限接口</li>
     * <li>@return org.apache.shiro.authz.AuthorizationInfo  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/12 2:21  </li>
     * </ul>
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        OurwaySysEmploys employs = (OurwaySysEmploys) principalCollection.getPrimaryPrincipal();

//        String currentUsername = (String) super.getAvailablePrincipal(principalCollection);
//      List<String> roleList = new ArrayList<String>();
//      List<String> permissionList = new ArrayList<String>();
//      //从数据库中获取当前登录用户的详细信息
//      User user = userService.getByUsername(currentUsername);
//      if(null != user){
//          //实体类User中包含有用户角色的实体类信息
//          if(null!=user.getRoles() && user.getRoles().size()>0){
//              //获取当前登录用户的角色
//              for(Role role : user.getRoles()){
//                  roleList.add(role.getName());
//                  //实体类Role中包含有角色权限的实体类信息
//                  if(null!=role.getPermissions() && role.getPermissions().size()>0){
//                      //获取权限
//                      for(Permission pmss : role.getPermissions()){
//                          if(!StringUtils.isEmpty(pmss.getPermission())){
//                              permissionList.add(pmss.getPermission());
//                          }
//                      }
//                  }
//              }
//          }
//      }else{
//          throw new AuthorizationException();
//      }
//      //为当前用户设置角色和权限
//      SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
//      simpleAuthorInfo.addRoles(roleList);
//      simpleAuthorInfo.addStringPermissions(permissionList);
//        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
//        //实际中可能会像上面注释的那样从数据库取得
//        if(null!=currentUsername && "jadyer".equals(currentUsername)){
//            //添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
//            simpleAuthorInfo.addRole("admin");
//            //添加权限
//            simpleAuthorInfo.addStringPermission("admin:manage");
//            System.out.println("已为用户[jadyer]赋予了[admin]角色和[admin:manage]权限");
//            return simpleAuthorInfo;
//        }else if(null!=currentUsername && "玄玉".equals(currentUsername)){
//            System.out.println("当前用户[玄玉]无授权");
//            return simpleAuthorInfo;
//        }
        //若该方法什么都不做直接返回null的话,就会导致任何用户访问/admin/listUser.jsp时都会自动跳转到unauthorizedUrl指定的地址
        //详见applicationContext.xml中的<bean id="shiroFilter">的配置
        Set<String> permsSet = new HashSet<String>();
        permsSet.add("admin");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * <p>方法:doGetAuthenticationInfo 验证用户是否合法，从数据库或者缓存中判断 </p>
     * <ul>
     * <li> @param authenticationToken </li>
     * <li>@return org.apache.shiro.authc.AuthenticationInfo  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/12 2:22  </li>
     * </ul>
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取基于用户名和密码的令牌
        //实际上这个authcToken是从mvc 的LoginController里面currentUser.login(token)传过来的
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("empId", username);
        OurwaySysEmploys employs = employsSer.queryOneByParams(params);
        String position = employsSer.listPositionDepart(employs);
        employs.setDeptPositions(position);
        if (null == employs) {
            throw new UnknownAccountException();
        }
        if (null == employs.getEmpStatue() || employs.getEmpStatue() != 1) {
            throw new LockedAccountException();
        }
        if (!password.equalsIgnoreCase(employs.getEmpPsw())) {
            throw new IncorrectCredentialsException();
        }
        SimpleAuthenticationInfo authcInfo = new SimpleAuthenticationInfo(employs, employs.getEmpPsw(), employs.getEmpName());
        //获取该用户所有的权限功能，放入到redis中，以便对其权限进行读写操作
        //menuService.listEmployMenus(employs)
//            setSession("currentUser", employs);
        return authcInfo;
    }

    /**
     * <p>方法:setSession 额外信息存储到session中去 </p>
     * <ul>
     * <li> @param key 关键字</li>
     * <li> @param value 值</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/12 2:26  </li>
     * </ul>
     */
    private void setSession(String key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        System.out.println("getSession" + currentUser.getPrincipal());
        if (null != currentUser) {
            Session session = currentUser.getSession(true);
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }
}
