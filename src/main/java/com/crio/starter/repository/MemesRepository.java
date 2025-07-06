package com.crio.starter.repository;

import com.crio.starter.data.MemesEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemesRepository extends MongoRepository<MemesEntity, String> {
  Optional<MemesEntity> findById(String id);

  MemesEntity save(MemesEntity meme);

  List<MemesEntity> findFirst100ByOrderByCreatedAtDesc();

  Optional<MemesEntity> findByNameAndUrlAndCaption(String name, String url, String caption);
}
