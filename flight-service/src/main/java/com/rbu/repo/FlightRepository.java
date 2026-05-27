package com.rbu.repo;

import com.rbu.entity.Flight;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight , Integer> {
	
	 Flight findByCode(int code);

	    List<Flight> findByCarrier(String carrier);

	    List<Flight> findBySourceAndDestination(String source, String destination);

	    List<Flight> findByCostBetween(int min, int max);
}
