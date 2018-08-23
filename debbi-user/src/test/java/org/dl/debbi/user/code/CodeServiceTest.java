package org.dl.debbi.user.code;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class CodeServiceTest {

    @Autowired
    private CodeService codeService;

    @Test
    public void get() {
        String principal = "xx";
        codeService.verify(principal, codeService.get(principal));
    }
}