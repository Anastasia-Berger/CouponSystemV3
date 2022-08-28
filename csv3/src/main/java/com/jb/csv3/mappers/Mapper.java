package com.jb.csv3.mappers;

import java.util.List;

public interface Mapper <DAO,DTO>{

    DAO toDAO(DTO dto);
    DTO toDTO(DAO dao);

    List<DAO> toDaoList(List<DTO> dtos);
    List<DTO> toDtoList(List<DAO> daos);
}
