package com.crio.starter.exchange;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class RequestDto {
  @NotEmpty
  private String name;

  @NotEmpty
  private String url;

  @NotEmpty
  private String caption;
}
