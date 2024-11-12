package com.limitedTimeDeal.LTD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.limitedTimeDeal.LTD.model.Buyer;

@Repository
public interface UserRepository extends JpaRepository<Buyer, String> {

}
