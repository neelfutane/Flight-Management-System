package com.rbu.service;

import com.rbu.entity.Flight;
import java.util.List;

public interface FlightService {
	  Flight save(Flight flight);

	    Flight findByCode(int code);

	    List<Flight> findByCarrier(String carrier);

	    List<Flight> findByRoute(String source, String destination);

	    List<Flight> findByPriceRange(int min, int max);

	    List<Flight> list();

	    void delete(int code);

}
