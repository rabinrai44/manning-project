import React, { Component } from "react";
import { withRouter } from "react-router-dom";
import GetBillingDetails from "./childs/GetBillingDetails";
import PayWithStripe from "./childs/PayWithStripe";
import ShowConfirmedTicket from "./childs/ShowConfirmedTicket";

class PaymentContainer extends Component {
  constructor(props) {
    super(props);

    this.state = {
      serviceId: this.props.match.params.serviceId,
      serviceName: this.props.match.params.serviceName,
      slotId: this.props.match.params.slotId,
      initiatePaymentResponse: null,
      paymentSuccessResponse: null,
    };
  }

  updatePaymentResponse(res) {
    console.log("updating payment response", res);
    this.setState({
      initiatePaymentResponse: res,
    });
  }

  onPaymentSuccess(paymentSuccessResponse) {
    //Now
    this.setState({
      paymentSuccessResponse: paymentSuccessResponse,
    });
  }

  render() {
    const {
      serviceId,
      serviceName,
      slotId,
      initiatePaymentResponse,
      paymentSuccessResponse,
    } = this.state;

    let container;

    if (null == initiatePaymentResponse)
      container = (
        <GetBillingDetails
          serviceId={serviceId}
          serviceName={serviceName}
          slotId={slotId}
          onUpdateBillingDetails={(evt) => this.updatePaymentResponse(evt)}
        />
      );
    else if (null == paymentSuccessResponse)
      container = (
        <PayWithStripe
          initiatePaymentResponse={initiatePaymentResponse}
          onPaymentSuccess={(evt) => this.onPaymentSuccess(evt)}
        />
      );
    else
      container = (
        <ShowConfirmedTicket
          paymentSuccessResponse={paymentSuccessResponse}
          payment={initiatePaymentResponse}
        />
      );

    return <div>{container}</div>;
  }
}

export default withRouter(PaymentContainer);
