package me.whiteship.springtestdemo;

import me.whiteship.springtestdemo.sample.SampleService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SpringtestdemoApplicationTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    SampleService mocksSampleService;

    @Test
    void contextLoads() throws Exception{

        when(mocksSampleService.getName()).thenReturn("seungjun");

        webTestClient.get().uri("/hello").exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("hello seungjun");

        String forObject = testRestTemplate.getForObject("/hello", String.class);
        assertThat(forObject).isEqualTo("hello seungjun");
    }

}
