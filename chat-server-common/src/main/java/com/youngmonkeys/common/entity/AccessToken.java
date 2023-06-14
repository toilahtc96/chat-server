package com.youngmonkeys.common.entity;

import com.tvd12.ezyfox.annotation.EzyId;
import com.tvd12.ezyfox.database.annotation.EzyCollection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EzyCollection
public class AccessToken {
  @EzyId
  private String accessToken;
//  private LocalDateTime updatedTime;
//  private LocalDateTime createdTime;
  private String username;
//  private String deleted;
//  private LocalDateTime expireAt;
//  private LocalDateTime expireIn;
//  private LocalDateTime firstIssueAt;
}
