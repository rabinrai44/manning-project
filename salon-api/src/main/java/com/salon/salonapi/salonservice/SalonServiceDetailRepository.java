package com.salon.salonapi.salonservice;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalonServiceDetailRepository extends JpaRepository<SalonServiceDetail, Long> {

}
