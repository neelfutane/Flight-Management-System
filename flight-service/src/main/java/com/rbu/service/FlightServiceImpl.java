package com.rbu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbu.entity.Flight;
import com.rbu.exception.InvalidFlightException;
import com.rbu.repo.FlightRepository;

import java.util.List;


@Service
public class FlightServiceImpl implements FlightService {
	
	  @Autowired
	    private FlightRepository repo;

	    @Override
	    public Flight save(Flight flight) {
	        return repo.save(flight);
	    }

	    @Override
	    public Flight findByCode(int code) {
	        return repo.findById(code)
	                .orElseThrow(() -> new InvalidFlightException("Flight not found with code: " + code));
	    }

	    @Override
	    public List<Flight> findByCarrier(String carrier) {
	        return repo.findByCarrier(carrier);
	    }

	    @Override
	    public List<Flight> findByRoute(String source, String destination) {
	        return repo.findBySourceAndDestination(source, destination);
	    }

	    @Override
	    public List<Flight> findByPriceRange(int min, int max) {
	        return repo.findByCostBetween(min, max);
	    }

	    @Override
	    public List<Flight> list() {
	        return repo.findAll();
	    }

	    @Override
	    public void delete(int code) {
	        Flight flight = repo.findById(code)
	                .orElseThrow(() -> new InvalidFlightException("Flight not found with code: " + code));

	        repo.delete(flight);
	    }

}
