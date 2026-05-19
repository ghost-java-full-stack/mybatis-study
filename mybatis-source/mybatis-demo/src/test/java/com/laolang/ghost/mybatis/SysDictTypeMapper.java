package com.laolang.ghost.mybatis;

import org.apache.ibatis.annotations.Param;

public interface SysDictTypeMapper {
    SysDictType selectById(@Param("id") Long id);
}
