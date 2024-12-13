package com.limitedTimeDeal.LTD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.limitedTimeDeal.LTD.model.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, String> {

}
