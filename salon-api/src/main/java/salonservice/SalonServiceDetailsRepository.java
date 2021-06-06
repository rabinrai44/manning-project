package salonservice;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalonServiceDetailsRepository extends CrudRepository<SalonServiceDetail, Long> {

}
