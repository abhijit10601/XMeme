package com.crio.starter.service;

import com.crio.starter.exchange.RequestDto;
import com.crio.starter.exchange.ResponseDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface MemesService {

  public ResponseDto getMemeById(String id);

  public List<ResponseDto> getRecent100Memes();

  public ResponseDto saveMeme(RequestDto dto);
}
