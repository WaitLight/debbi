package org.dl.debbi.common.id.repositroy;

import org.dl.debbi.common.id.domain.IdOrigin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Rollback(value = false)
public class IdOriginRepositoryTest {

    @Autowired
    private IdOriginRepository repo;

    @Test
    public void save() {
        long now = System.currentTimeMillis();
        IdOrigin idOrigin = new IdOrigin()
                .setProcess(1)
                .setBusiness(1)
                .setOriginTimeMillis(now);

        repo.save(idOrigin);

        assertThat(repo.findByProcess(1)).isPresent().get()
                .extracting(IdOrigin::getOriginTimeMillis).isEqualTo(now);
    }

}