package com.crio.starter.service;

import com.crio.starter.data.MemesEntity;
import com.crio.starter.exchange.RequestDto;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.repository.MemesRepository;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Primary
public class MemesServiceImpl implements MemesService {

  private AtomicLong counter = new AtomicLong();

  @Autowired
  private final MemesRepository memesRepository;

  public ResponseDto getMemeById(String id) {
    ResponseDto dto = null;
    Optional<MemesEntity> meme = memesRepository.findById(id);

    if (meme.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    } else {
      MemesEntity entity = meme.get();
      dto = new ResponseDto(entity.getId(), entity.getName(), entity.getUrl(), entity.getCaption());
    }
    return dto;
  }

  public List<ResponseDto> getRecent100Memes() {
    List<MemesEntity> memesList = memesRepository.findFirst100ByOrderByCreatedAtDesc();

    List<ResponseDto> dtoList = new ArrayList<ResponseDto>();
    if (memesList.isEmpty()) {
      return dtoList;
    } else {
      for (MemesEntity entity : memesList) {
        ResponseDto dto =
            new ResponseDto(entity.getId(), entity.getName(), entity.getUrl(), entity.getCaption());
        dtoList.add(dto);
      }
    }
    return dtoList;
  }

  public ResponseDto saveMeme(RequestDto dto) {
    long id = counter.incrementAndGet();

    MemesEntity entity = new MemesEntity();
    String name = dto.getName();
    String url = dto.getUrl();
    String caption = dto.getCaption();
    if (name == null || url == null || caption == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    Optional<MemesEntity> existingMeme =
        memesRepository.findByNameAndUrlAndCaption(name, url, caption);
    if (existingMeme.isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }
    String identifer = String.valueOf(id);
    entity.setCaption(caption);
    entity.setId(identifer);
    entity.setName(name);
    entity.setUrl(url);
    entity.setCreatedAt(Instant.now());

    MemesEntity respEntity = memesRepository.save(entity);
    ResponseDto respDto = null;
    if (respEntity.getId() != null) {
      respDto = new ResponseDto(respEntity.getId());
    }
    return respDto;
  }
}
