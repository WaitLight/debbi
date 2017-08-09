package org.dl.debbi.user.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.dl.debbi.user.domain.Authority;
import org.dl.debbi.user.repo.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户userrealm
 *
 * @author Dean
 * @version 0.0.1
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private AuthorityRepository authorityRepo;

    @Autowired
    private PasswordService passwordService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        //得到用户名
//        String inputPrincipal = (String) token.getPrincipal();
//        Authority authority = authorityRepo.findByUsername(inputPrincipal);
//        //没有用户
//        if (authority == null) {
//            throw new UnknownAccountException();
//        }
//        String credential = authority.getPassword();
//        ByteSource salt = ByteSource.Util.bytes(credential.substring(credential.length() - 24));
//        //如果身份认证验证成功，返回一个AuthenticationInfo实现
//        SimpleHash md5 = new SimpleHash("md5", credential.toCharArray(), salt, 1);
//        return new SimpleAuthenticationInfo(inputPrincipal, md5, salt, getName());
        String username = (String) token.getPrincipal();
        Authority authority = authorityRepo.findByUsername(username);
        String password = authority.getPassword();
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
