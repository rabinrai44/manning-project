import React, { Component } from "react";
import { API_URL } from "../../common/configuration";
import { handleHttpErrors } from "../../common/HttpHelper";
import { loadingIndicator } from "../../common/loader/loading-indicator";
import { appNotification } from "../../common/notification/app-notification";

class GetBillingDetails extends Component {
  constructor(props) {
    super(props);

    this.state = {
      serviceId: this.props.serviceId,
      serviceName: this.props.serviceName,
      slotId: this.props.slotId,
      firstName: "",
      lastName: "",
      email: "",
      phoneNumber: "",
      wasValidated: "",
    };
  }

  onReceiveData(paymentResponse) {
    console.log(paymentResponse);

    loadingIndicator.hide();
    this.props.onUpdateBillingDetails(paymentResponse);
  }

  onError(error) {
    loadingIndicator.hide();
    appNotification.showError("Unable to initiate payment -" + error);
  }

  initatePayment(event) {
    const form = event.currentTarget;

    if (form.checkValidity() === false) return false;

    event.preventDefault();
    event.stopPropagation();

    loadingIndicator.show();

    const paymentRequest = {
      selectedServiceId: this.state.serviceId,
      slotId: this.state.slotId,
      firstName: this.state.firstName,
      lastName: this.state.lastName,
      email: this.state.email,
      phoneNumber: this.state.phoneNumber,
    };

    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(paymentRequest),
    };

    const url = API_URL + "payments/initiate";

    fetch(url, requestOptions)
      .then((res) => handleHttpErrors(res))
      .then((res) => res.json())
      .then((results) => this.onReceiveData(results))
      .catch((errorObject) => this.onError(errorObject));

    return false;
  }

  updateState(key, value) {
    this.setState({
      [key]: value,
      wasValidated: "was-validated",
    });

    console.log(key + " field was updated");
    console.log("Current State: " + JSON.stringify(this.state));
  }

  render() {
    const { firstName, lastName, email, phoneNumber, wasValidated } =
      this.state;

    return (
      <div className="row">
        <div className="col-12 pl-0">
          <h3>Enter Billing Details</h3>

          <form
            className={wasValidated}
            onSubmit={(event) => {
              return this.initatePayment(event);
            }}
          >
            <div
              className="form-group"
              required
              value={firstName}
              onChange={(event) =>
                this.updateState("firstName", event.target.value)
              }
            >
              <label>First Name</label>
              <input type="text" className="form-control" />
            </div>
            <div
              className="form-group"
              required
              value={lastName}
              onChange={(event) =>
                this.updateState("lastName", event.target.value)
              }
            >
              <label>Last Name</label>
              <input type="text" className="form-control" />
            </div>
            <div
              className="form-group"
              required
              value={email}
              onChange={(event) =>
                this.updateState("email", event.target.value)
              }
            >
              <label>Email Address</label>
              <input type="email" className="form-control" />
            </div>
            <div
              className="form-group"
              required
              value={phoneNumber}
              onChange={(event) =>
                this.updateState("phoneNumber", event.target.value)
              }
            >
              <label>Phone Number</label>
              <input type="tel" className="form-control" />
            </div>

            <button type="submit" className="btn btn-primary">
              Make Payment
            </button>
          </form>
        </div>
      </div>
    );
  }
}

export default GetBillingDetails;
