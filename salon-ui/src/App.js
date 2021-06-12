import React from "react";

import "./App.css";

import ChooseService from "./_services/ChooseService";

import LoadingIndicatorComponent from "./common/loader/loading-indicator-component";
import AppNotificationComponent from "./common/notification/app-notification-component";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import ChooseSlot from "./choose-slot/ChooseSlot";

function App() {
  return (
    <Router>
      <div>
        <LoadingIndicatorComponent />
        <nav className="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
          <a className="navbar-brand" href="/">
            Ar Salon & Day Spa
          </a>
        </nav>
        <main role="main" className="container">
          <div className="padding-container">
            <Switch>
              <Route exact path="/" component={ChooseService}></Route>
              <Route
                path="/chooseslot/:serviceId/:serviceName"
                component={ChooseSlot}
              ></Route>
              <Route>
                <ChooseService />
              </Route>
            </Switch>
          </div>
        </main>
        <AppNotificationComponent />
      </div>
    </Router>
  );
}

export default App;
