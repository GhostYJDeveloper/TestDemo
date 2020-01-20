package com.example.demo.model.mapper;

import com.example.demo.model.uploadFile.UploadFileResponse;

public interface UploadFileResponseMapper {
    int deleteByPrimaryKey(Long urId);

    int insert(UploadFileResponse record);

    int insertSelective(UploadFileResponse record);

    UploadFileResponse selectByPrimaryKey(Long urId);

    int updateByPrimaryKeySelective(UploadFileResponse record);

    int updateByPrimaryKey(UploadFileResponse record);
}