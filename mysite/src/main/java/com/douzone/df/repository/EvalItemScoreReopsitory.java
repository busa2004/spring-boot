package com.douzone.df.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.douzone.df.model.EvalItemScore;

@Repository
public interface EvalItemScoreReopsitory extends JpaRepository<EvalItemScore, Long> {
	
}
