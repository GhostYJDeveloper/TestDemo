package com.example.demo.model.mapper;

import com.example.demo.model.uploadFile.UploadFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Mapper
@Service
public interface UploadFileMapper {
    int deleteByPrimaryKey(Long urId);

    int deleteByRelated(Map<String,Object> map);

    int insert(UploadFile record);

    int insertSelective(UploadFile record);

    UploadFile selectByPrimaryKey(Long urId);

    List<UploadFile> selectByDomainname(String domainName);

    List<UploadFile> selectByRelated(Map<String,Object> map);

    int updateByPrimaryKeySelective(UploadFile record);

    int updateByPrimaryKey(UploadFile record);
}