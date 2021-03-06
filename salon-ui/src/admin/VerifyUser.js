import React, { Component, Fragment } from "react";
import QrReader from "react-qr-reader";
import { withRouter } from "react-router-dom";
import { API_URL } from "../common/configuration";
import { handleHttpErrors } from "../common/HttpHelper";
import { loadingIndicator } from "../common/loader/loading-indicator";
import { appNotification } from "../common/notification/app-notification";

class VerifyUser extends Component {
  constructor(props) {
    super(props);
    this.state = {
      showScanner: true,
      showUser: false,
      payment: null,
    };
  }

  handleScan = (data) => {
    if (data) {
      console.log("received: ", data);
      if (data.indexOf("/") > 0) {
        const parts = data.split("/");
        const id = parts[parts.length - 1];
        this.showTicketDetails(id);
      }
    }
  };

  showTicketDetails = (ticketId) => {
    loadingIndicator.show();
    this.hideScanner();

    const url = API_URL + "tickets/" + ticketId;

    fetch(url)
      .then((res) => handleHttpErrors(res))
      .then((res) => res.json())
      .then((result) => this.onReceiveData(result))
      .catch((err) => {
        this.onError(err);
      });
  };

  onReceiveData(ticket) {
    loadingIndicator.hide();
    const payment = ticket.payment;

    this.setState({
      payment: payment,
    });
  }

  onError(error) {
    loadingIndicator.hide();
    appNotification.showError("Unable to retrieve User - " + error);
    this.showScanner();
  }

  showScanner() {
    this.setState({
      showScanner: true,
      payment: null,
    });
  }

  hideScanner() {
    this.setState({
      showScanner: false,
    });
  }

  handleError = (err) => {
    console.log(err);
  };

  handleAnotherScan = () => {
    this.setState({
      isScan: true,
      result: "No Result",
      ticketUrl: null,
      ticketDetails: null,
    });
  };

  render() {
    const { showScanner, payment } = this.state;
    if (showScanner) {
      return (
        <div>
          <QrReader
            delay={300}
            onScan={this.handleScan}
            onError={this.onError}
            style={{ width: "100%" }}
          />
        </div>
      );
    } else if (payment !== null) {
      const { email, phoneNumber, name, amount } = this.state.payment;
      const { stylistName, slotFor } = this.state.payment.slot;
      const serviceName = this.state.payment.slot.selectedService.name;
      const slotDate = new Date(slotFor).toDateString();

      return (
        <Fragment>
          <h3>Details</h3>
          <div className="row">
            <div className="col-6">
              <strong> Service Details</strong>
              <div>
                {serviceName} @ {slotDate} By {stylistName}{" "}
              </div>
              <hr />
              <br />
              <strong> User Information</strong>
              <div>{name}</div>
              <div>{email}</div>

              <div>Zip {amount}</div>
              <div>Phone {phoneNumber}</div>
            </div>
            <div className="col-6">
              <button
                type="button"
                className="btn btn-primary"
                onClick={(evt) => this.showScanner()}
              >
                Scan another
              </button>
            </div>
          </div>
        </Fragment>
      );
    }

    return <Fragment></Fragment>;
  }
}

export default withRouter(VerifyUser);
