import React, { useEffect, useState } from "react";

import {
    getAllFlights,
    deleteFlight,
    saveFlight,
    getFlightByCode,
    getFlightByCarrier,
    getFlightsByPriceRange,
    getFlightsByRoute
} from "../services/FlightService";

import "../styles/flight.css";

const FlightList = () => {

    const [flights, setFlights] = useState([]);

    // ADD FLIGHT STATE
    const [flight, setFlight] = useState({
        code: "",
        carrier: "",
        source: "",
        destination: "",
        cost: ""
    });

    // SEARCH STATES
    const [searchCode, setSearchCode] = useState("");
    const [searchCarrier, setSearchCarrier] = useState("");
    const [minPrice, setMinPrice] = useState("");
    const [maxPrice, setMaxPrice] = useState("");

    // ROUTE SEARCH STATES
    const [source, setSource] = useState("");
    const [destination, setDestination] = useState("");

    useEffect(() => {
        loadFlights();
    }, []);

    // LOAD ALL FLIGHTS
    const loadFlights = () => {

        getAllFlights()
            .then(res => setFlights(res.data))
            .catch(err => console.log(err));
    };

    // HANDLE INPUT CHANGE
    const handleChange = (e) => {

        setFlight({
            ...flight,
            [e.target.name]: e.target.value
        });
    };

    // SAVE FLIGHT
    const handleSubmit = (e) => {

        e.preventDefault();

        saveFlight(flight)
            .then(() => {

                loadFlights();

                setFlight({
                    code: "",
                    carrier: "",
                    source: "",
                    destination: "",
                    cost: ""
                });

            })
            .catch(err => console.log(err));
    };

    // DELETE FLIGHT
    const handleDelete = (code) => {

        deleteFlight(code)
            .then(() => loadFlights())
            .catch(err => console.log(err));
    };

    // SEARCH BY CODE
    const handleSearchByCode = () => {

        getFlightByCode(searchCode)
            .then(res => setFlights([res.data]))
            .catch(err => console.log(err));
    };

    // SEARCH BY CARRIER
    const handleSearchByCarrier = () => {

        getFlightByCarrier(searchCarrier)
            .then(res => setFlights(res.data))
            .catch(err => console.log(err));
    };

    // SEARCH BY PRICE RANGE
    const handleSearchByPrice = () => {

        getFlightsByPriceRange(minPrice, maxPrice)
            .then(res => setFlights(res.data))
            .catch(err => console.log(err));
    };

    // SEARCH BY ROUTE
    const handleSearchByRoute = () => {

        getFlightsByRoute(source, destination)
            .then(res => setFlights(res.data))
            .catch(err => console.log(err));
    };

    return (

        <div className="flight-app">

            <h1>Flight Management System</h1>

            {/* ADD FLIGHT */}
            <h2>Add Flight</h2>

            <form onSubmit={handleSubmit}>

                <input
                    name="code"
                    placeholder="Code"
                    value={flight.code}
                    onChange={handleChange}
                />

                <input
                    name="carrier"
                    placeholder="Carrier"
                    value={flight.carrier}
                    onChange={handleChange}
                />

                <input
                    name="source"
                    placeholder="Source"
                    value={flight.source}
                    onChange={handleChange}
                />

                <input
                    name="destination"
                    placeholder="Destination"
                    value={flight.destination}
                    onChange={handleChange}
                />

                <input
                    name="cost"
                    placeholder="Cost"
                    value={flight.cost}
                    onChange={handleChange}
                />

                <button type="submit">
                    Add Flight
                </button>

            </form>

            <hr />

            {/* SEARCH SECTION */}
            <h2>Search Flights</h2>

            <div className="search-container">

                {/* SEARCH INPUTS */}
                <div className="search-inputs">

                    <input
                        placeholder="Search by Code"
                        value={searchCode}
                        onChange={(e) => setSearchCode(e.target.value)}
                    />

                    <input
                        placeholder="Search by Carrier"
                        value={searchCarrier}
                        onChange={(e) => setSearchCarrier(e.target.value)}
                    />

                    <input
                        placeholder="Min Price"
                        value={minPrice}
                        onChange={(e) => setMinPrice(e.target.value)}
                    />

                    <input
                        placeholder="Max Price"
                        value={maxPrice}
                        onChange={(e) => setMaxPrice(e.target.value)}
                    />

                    <input
                        placeholder="Source"
                        value={source}
                        onChange={(e) => setSource(e.target.value)}
                    />

                    <input
                        placeholder="Destination"
                        value={destination}
                        onChange={(e) => setDestination(e.target.value)}
                    />

                </div>

                {/* SEARCH BUTTONS */}
                <div className="search-actions">

                    <button onClick={handleSearchByCode}>
                        Search Code
                    </button>

                    <button onClick={handleSearchByCarrier}>
                        Search Carrier
                    </button>

                    <button onClick={handleSearchByPrice}>
                        Search Price
                    </button>

                    <button onClick={handleSearchByRoute}>
                        Search Route
                    </button>

                    <button
                        className="reset-btn"
                        onClick={loadFlights}
                    >
                        Reset Flights
                    </button>

                </div>

            </div>

            <hr />

            {/* FLIGHT TABLE */}
            <h2>Flight List</h2>

            <table>

                <thead>

                    <tr>
                        <th>Code</th>
                        <th>Carrier</th>
                        <th>Source</th>
                        <th>Destination</th>
                        <th>Cost</th>
                        <th>Action</th>
                    </tr>

                </thead>

                <tbody>

                    {flights.map((f) => (

                        <tr key={f.code}>

                            <td>{f.code}</td>
                            <td>{f.carrier}</td>
                            <td>{f.source}</td>
                            <td>{f.destination}</td>
                            <td>{f.cost}</td>

                            <td>

                                <button
                                    onClick={() => handleDelete(f.code)}
                                >
                                    Delete
                                </button>

                            </td>

                        </tr>

                    ))}

                </tbody>

            </table>

        </div>
    );
};

export default FlightList;