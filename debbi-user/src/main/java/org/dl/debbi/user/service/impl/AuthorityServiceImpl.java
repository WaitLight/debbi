package org.dl.debbi.user.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.subject.Subject;
import org.dl.debbi.user.domain.Authority;
import org.dl.debbi.user.repo.AuthorityRepository;
import org.dl.debbi.user.service.IAuthorityService;
import org.dl.debbi.user.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements IAuthorityService {

    @Autowired
    private AuthorityRepository authorityRepo;

    @Autowired
    private PasswordService passwordService;

    @Override
    public Authority save(Authority authority) {
        String encryptPassword = passwordService.encryptPassword(authority.getPassword());
        authority.setPassword(encryptPassword);
        return authorityRepo.save(authority);
    }

    @Override
    public Boolean login(LoginVo loginVo) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginVo.getUsername(), loginVo.getPassword());
        subject.login(token);
        return subject.isAuthenticated();
    }
}
