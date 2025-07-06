package com.crio.starter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.service.MemesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;


@AutoConfigureMockMvc
@SpringBootTest
class MemesControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private MemesService memesService;

  @Test
  void getMemeById() throws Exception {
    //given
    Mockito.doReturn(new ResponseDto("1"))
        .when(memesService).getMemeById("1");

    // when
    URI uri = UriComponentsBuilder
        .fromPath("/memes/{id}")
        .build("1");

    MockHttpServletResponse response = mvc.perform(
        get(uri.toString()).accept(APPLICATION_JSON_VALUE)
    ).andReturn().getResponse();

    //then
    String responseStr = response.getContentAsString();
    ObjectMapper mapper = new ObjectMapper();
    ResponseDto responseDto = mapper.readValue(responseStr, ResponseDto.class);
    ResponseDto ref = new ResponseDto("1");

    assertEquals(responseDto, ref);
    Mockito.verify(memesService, Mockito.times(1)).getMemeById("1");
  }
}