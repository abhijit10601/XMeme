package com.crio.starter.data;

import java.time.Instant;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "memes")
@NoArgsConstructor
public class MemesEntity {

  @Id
  private String id;

  private String name;

  private String url;

  private String caption;

  private Instant createdAt;
}
