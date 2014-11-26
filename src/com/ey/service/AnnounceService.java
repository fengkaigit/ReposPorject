package com.ey.service;

import java.util.List;
import java.util.Map;

import com.ey.dao.entity.SysAnnouncement;

public interface AnnounceService {
      void saveObject(Object o) throws RuntimeException;
      void updateObject(Object o) throws RuntimeException;
  	  void deleteAnnounceByIds(String[] ids) throws RuntimeException;
      SysAnnouncement getSysAnnouncement(Long id) throws RuntimeException;
      List<SysAnnouncement> getAnnouncesByQueryParam(Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException;
}
