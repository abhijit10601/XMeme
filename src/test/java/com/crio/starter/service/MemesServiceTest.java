package com.crio.starter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.crio.starter.data.MemesEntity;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.repository.MemesRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class MemesServiceTest {

  @Mock
  private MemesRepository memesRepository;

  @InjectMocks
  private MemesServiceImpl memesService;

  @Test
  void test_getMemeById() {
    Optional<MemesEntity> memesEntity = Optional.of(getMeme("1", "Name_1", "Url_1", "Caption_1"));
    Mockito.doReturn(memesEntity)
        .when(memesRepository).findById("1");
    ResponseDto responseDto = memesService.getMemeById("1");

    ResponseDto expected = new ResponseDto("1", "Name_1", "Url_1", "Caption_1");
    assertEquals(expected, responseDto);
  }

  private MemesEntity getMeme(String id, String name, String url, String caption) {
    MemesEntity memesEntity = new MemesEntity();
    memesEntity.setId(id);
    memesEntity.setName(name);
    memesEntity.setUrl(url);
    memesEntity.setCaption(caption);
    return memesEntity;
  }
}