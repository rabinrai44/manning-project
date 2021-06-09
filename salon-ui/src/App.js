import { useState, useEffect } from "react";
import { BrowserRouter as Router } from "react-router-dom";
import "./App.css";
import { LoadingIndicator } from "./LoadingIndicator";

import { retrieveAvailableSalonServices } from "./_services/ChooseService";

import { Card } from "./components/Card";

import { AppNotification } from "./components/AppNotification";

function App() {
  const [salonServices, setSalonServices] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchSalonServices = () => {
    retrieveAvailableSalonServices()
      .then((res) => {
        if (!res.ok) {
          throw Error("could not fetch the data for that resource");
        }
        return res.json();
      })
      .then((res) => {
        console.log(res);
        setSalonServices(res);
      })
      .catch((err) => {
        console.log(err.message);
        setError(err.message);
      })
      .finally(() => setLoading(false));
  };

  useEffect(() => {
    fetchSalonServices();
  }, []);

  const renderAvailableServices = () => {
    if (!!error) {
      const errorMessage = error;
      setError(null);
      return (
        <div className="row">
          <p className="text-danger">{errorMessage}</p>
        </div>
      );
    }
    if (loading) return <LoadingIndicator />;

    return (
      <div className="row text-center">
        {salonServices.map((service, index) => (
          <div className="col-md-4" key={index}>
            <Card
              name={service.name}
              price={service.price}
              description={service.description}
            />
          </div>
        ))}
      </div>
    );
  };

  return (
    <div className="App">
      <Navbar />
      {/* Routing setup */}
      <Router>
        <div className="container mt-4">
          {/* Import module here */}
          {renderAvailableServices()}
          <Home />
        </div>
      </Router>
    </div>
  );
}

const Home = () => "Home Page Content";

function Navbar() {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <a className="navbar-brand" href="#">
        Ar Salon & Day Spa
      </a>
      <button
        className="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarScroll"
        aria-controls="navbarScroll"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span className="navbar-toggler-icon"></span>
      </button>
    </nav>
  );
}

export default App;
