package com.douzone.df.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.douzone.df.model.EvalItem;


@Repository
public interface EvalItemRepository extends JpaRepository<EvalItem, Long>{
	
}
