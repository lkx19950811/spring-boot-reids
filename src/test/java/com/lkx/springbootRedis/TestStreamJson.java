package com.lkx.springbootRedis;

import com.lkx.springbootRedis.pojo.Quote;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * desc :
 *
 * @author : lee Cather
 * @date : 2018-12-10 15:32
 */
@RunWith(SpringRunner.class)
//  We create a `@SpringBootTest`, starting an actual server on a `RANDOM_PORT`
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestStreamJson {
    @Autowired
    private WebTestClient webTestClient;
    @Test
    public void fetchQuotesAsStream() {
        List<Quote> result = webTestClient
                // We then create a GET request to test an endpoint
                .get().uri("/quotes")
                // this time, accepting "application/stream+json"
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange()
                // and use the dedicated DSL to test assertions against the response
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_STREAM_JSON)
                .returnResult(Quote.class)
                .getResponseBody()
                .take(30)
                .collectList()
                .block();
        assertThat(result).allSatisfy(quote -> assertThat(quote.getPrice()).isPositive());
        System.out.println(result);
    }
}
