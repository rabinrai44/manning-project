import { BrowserRouter as Router } from "react-router-dom";
import "./App.css";

function App() {
  return (
    <div classNameName="App">
      <Navbar />
      {/* Routing setup */}
      <Router>
        <div className="container">
          {/* Import module here */}
          <Home />
        </div>
      </Router>
    </div>
  );
}

function Home() {
  return <div className="col">"This will be home page content..."</div>;
}

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
