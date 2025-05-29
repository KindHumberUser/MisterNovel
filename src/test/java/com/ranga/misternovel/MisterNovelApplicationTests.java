package com.ranga.misternovel;

import com.ranga.misternovel.Controllers.HomeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MisterNovelApplicationTests {

    @Autowired
    private HomeController homeController;

    @Test
    void contextLoads() {
        assertThat(homeController).isNotNull();
    }

}
