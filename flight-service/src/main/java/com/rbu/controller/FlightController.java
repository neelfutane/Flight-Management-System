package com.rbu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.rbu.entity.Flight;
import com.rbu.service.FlightService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/flights")
public class FlightController {
	
	  @Autowired
	    private FlightService service;

	    @PostMapping("/save")
	    public Flight save(@RequestBody Flight flight) {
	        return service.save(flight);
	    }

	    @GetMapping("/{code}")
	    public Flight findByCode(@PathVariable int code) {
	        return service.findByCode(code);
	    }

	    @GetMapping("/carrier/{carrier}")
	    public List<Flight> findByCarrier(@PathVariable String carrier) {
	        return service.findByCarrier(carrier);
	    }

	    @GetMapping("/route")
	    public List<Flight> findByRoute(@RequestParam String source,
	                                    @RequestParam String destination) {
	        return service.findByRoute(source, destination);
	    }

	    @GetMapping("/price")
	    public List<Flight> findByPriceRange(@RequestParam int min,
	                                         @RequestParam int max) {
	        return service.findByPriceRange(min, max);
	    }

	    @GetMapping("/list")
	    public List<Flight> list() {
	        return service.list();
	    }

	    @DeleteMapping("/{code}")
	    public String delete(@PathVariable int code) {
	        service.delete(code);
	        return "Flight deleted successfully";
	    }
}
