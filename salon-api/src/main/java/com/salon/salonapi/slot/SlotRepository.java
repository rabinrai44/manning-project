package com.salon.salonapi.slot;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salon.salonapi.salonservice.SalonServiceDetail;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {

	List<Slot> findAllBySlotForGreaterThanEqualAndSlotForLessThanEqualAndAvailableServicesContainingAndStatus(
			LocalDateTime startTime, LocalDateTime endTime, SalonServiceDetail serviceDetail, SlotStatus status);

	Optional<Slot> findByIdAndStatus(Long slotId, SlotStatus slotStatus);

}
