package com.crio.starter.controller;

import com.crio.starter.exchange.RequestDto;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.service.MemesService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController // RestController for memes endpoint
public class MemesController {

  @Autowired
  private MemesService memesService;

  @PostMapping("/memes/")
  public ResponseDto addMeme(@RequestBody @Valid RequestDto dto) {
    ResponseDto response = memesService.saveMeme(dto);
    return response;
  }

  @GetMapping("/memes/{id}")
  public ResponseDto getMemeById(@PathVariable String id) {
    return memesService.getMemeById(id);
  }

  @GetMapping("/memes/")
  public List<ResponseDto> getMemes() {
    return memesService.getRecent100Memes();
  }

}
