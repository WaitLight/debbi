package org.dl.debbi.user.repo;

import org.dl.debbi.user.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户repo
 *
 * @author Dean
 * @version 0.0.1
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    /**
     * 通过用户名查找
     *
     * @param username 用户名
     * @return 权限信息
     */
    Authority findByUsername(String username);
}
