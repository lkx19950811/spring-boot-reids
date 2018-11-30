package com.lkx.springbootRedis.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author : lee Cather
 * @date : 2018-11-23 16:14
 * desc :
 */
@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T,Long>,JpaSpecificationExecutor<T> {
}
