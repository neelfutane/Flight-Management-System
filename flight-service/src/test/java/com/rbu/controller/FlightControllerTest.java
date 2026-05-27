package com.rbu.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.rbu.entity.Flight;
import com.rbu.service.FlightService;

@WebMvcTest(FlightController.class)
public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService service;


    @Test
    public void testSaveFlight103() throws Exception {

        Flight flight = new Flight(
                103,
                "SpiceJet",
                "Banglore",
                "Mumbai",
                5500
        );

        when(service.save(any(Flight.class)))
                .thenReturn(flight);

        mockMvc.perform(post("/flights/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                {
                    "code":103,
                    "carrier":"SpiceJet",
                    "source":"Banglore",
                    "destination":"Mumbai",
                    "cost":5500
                }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(103))
                .andExpect(jsonPath("$.carrier").value("SpiceJet"))
                .andExpect(jsonPath("$.source").value("Banglore"))
                .andExpect(jsonPath("$.destination").value("Mumbai"))
                .andExpect(jsonPath("$.cost").value(5500));
    }



    @Test
    public void testGetAllFlights() throws Exception {

        List<Flight> flights = List.of(
                new Flight(101, "Indigo", "Delhi", "Pune", 4500),
                new Flight(102, "AirIndia", "Mumbai", "Chennai", 7000)
        );

        when(service.list())
                .thenReturn(flights);

        mockMvc.perform(get("/flights/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].code").value(101))
                .andExpect(jsonPath("$[0].carrier").value("Indigo"))
                .andExpect(jsonPath("$[1].code").value(102))
                .andExpect(jsonPath("$[1].carrier").value("AirIndia"));
    }


    @Test
    public void testGetFlightByCode() throws Exception {

        Flight flight = new Flight(
                101,
                "Indigo",
                "Delhi",
                "Pune",
                4500
        );

        when(service.findByCode(101))
                .thenReturn(flight);

        mockMvc.perform(get("/flights/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(101))
                .andExpect(jsonPath("$.carrier").value("Indigo"))
                .andExpect(jsonPath("$.source").value("Delhi"))
                .andExpect(jsonPath("$.destination").value("Pune"))
                .andExpect(jsonPath("$.cost").value(4500));
    }


    @Test
    public void testSearchByCarrier() throws Exception {

        List<Flight> flights = List.of(
                new Flight(201, "SpiceJet", "Nagpur", "Goa", 6000),
                new Flight(202, "SpiceJet", "Delhi", "Mumbai", 5500)
        );

        when(service.findByCarrier("SpiceJet"))
                .thenReturn(flights);

        mockMvc.perform(get("/flights/carrier/SpiceJet"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].carrier").value("SpiceJet"))
                .andExpect(jsonPath("$[1].carrier").value("SpiceJet"));
    }


    @Test
    public void testSearchByPriceRange() throws Exception {

        List<Flight> flights = List.of(
                new Flight(301, "Vistara", "Delhi", "Goa", 5000),
                new Flight(302, "Indigo", "Mumbai", "Pune", 6500)
        );

        when(service.findByPriceRange(4000, 7000))
                .thenReturn(flights);

        mockMvc.perform(get("/flights/price")
                .param("min", "4000")
                .param("max", "7000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cost").value(5000))
                .andExpect(jsonPath("$[1].cost").value(6500));
    }


    @Test
    public void testSearchByRoute() throws Exception {

        List<Flight> flights = List.of(
                new Flight(401, "AirIndia", "Delhi", "Mumbai", 8000)
        );

        when(service.findByRoute("Delhi", "Mumbai"))
                .thenReturn(flights);

        mockMvc.perform(get("/flights/route")
                .param("source", "Delhi")
                .param("destination", "Mumbai"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].source").value("Delhi"))
                .andExpect(jsonPath("$[0].destination").value("Mumbai"));
    }


    @Test
    public void testDeleteFlight() throws Exception {

        mockMvc.perform(delete("/flights/101"))
                .andExpect(status().isOk());
    }
}