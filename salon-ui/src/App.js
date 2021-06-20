import React from "react";

import "./App.css";

import ChooseService from "./_services/ChooseService";

import LoadingIndicatorComponent from "./common/loader/loading-indicator-component";
import AppNotificationComponent from "./common/notification/app-notification-component";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import PaymentContainer from "./payment/PaymentContainer";
import ChooseSlot from "./choose-slot/ChooseSlot";

import { Elements } from "@stripe/react-stripe-js";
import { loadStripe } from "@stripe/stripe-js";
import VerifyUser from "./admin/VerifyUser";

const stripePromise = loadStripe(
  "pk_test_51J1cX3GKCC6E2UZBj05S9wJ1HIeLwOdCG2e8RUXTBwNlT6lxwlcG0FqfPfae3ZPa94Quw7DGi1wA5x3Z3IdPRsyi00fRa03I3D"
);

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
              <Route path="/makepayment/:slotId/:serviceId/:serviceName">
                <Elements stripe={stripePromise}>
                  <PaymentContainer></PaymentContainer>
                </Elements>
              </Route>

              <Route path="/admin/verify-user" component={VerifyUser}></Route>
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
