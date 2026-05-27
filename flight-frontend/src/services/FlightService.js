import axios from "axios";

const BASE_URL = "http://localhost:8089/flights";

// GET ALL FLIGHTS
export const getAllFlights = () =>
    axios.get(`${BASE_URL}/list`);


// SAVE FLIGHT
export const saveFlight = (flight) =>
    axios.post(`${BASE_URL}/save`, flight);


// DELETE FLIGHT
export const deleteFlight = (code) =>
    axios.delete(`${BASE_URL}/${code}`);


// SEARCH BY CODE
export const getFlightByCode = (code) =>
    axios.get(`${BASE_URL}/${code}`);


// SEARCH BY CARRIER
export const getFlightByCarrier = (carrier) =>
    axios.get(`${BASE_URL}/carrier/${carrier}`);


// SEARCH BY PRICE RANGE
export const getFlightsByPriceRange = (min, max) =>
    axios.get(`${BASE_URL}/price?min=${min}&max=${max}`);

export const getFlightsByRoute = (source, destination) =>
    axios.get(
        `${BASE_URL}/route?source=${source}&destination=${destination}`
    );