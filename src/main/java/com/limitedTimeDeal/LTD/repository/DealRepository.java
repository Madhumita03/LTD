package com.limitedTimeDeal.LTD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.limitedTimeDeal.LTD.model.Deal;

@Repository
public interface DealRepository extends JpaRepository<Deal, String> {

}
