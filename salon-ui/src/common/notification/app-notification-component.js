import { Alert } from "react-bootstrap";
import React, { Component, Fragment } from "react";
import { appNotification } from "./app-notification";

const TEN_SECONDS = 10 * 1000;

class AppNotificationComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      show: false,
      title: "",
      variant: "",
      message: "",
    };
  }

  componentDidMount() {
    this.subscription = appNotification.onChange().subscribe((res) => {
      this.onNotificationReceived(res);
    });
  }

  onNotificationReceived(res) {
    this.setState({
      show: true,
      title: res.title,
      variant: res.variant,
      message: res.message,
    });

    this.resetAfterTenSeconds();
  }

  componentWillUnmount() {
    this.subscription.unsubscribe();
  }

  reset() {
    this.setState({
      show: false,
      title: "",
      variant: "",
      message: "",
    });
  }

  resetAfterTenSeconds() {
    setTimeout(() => {
      this.reset();
    }, TEN_SECONDS);
  }

  render() {
    const { show, title, message, variant } = this.state;
    return (
      <Fragment>
        {show === true && (
          <div className="message-container ">
            <div className="container">
              <Alert variant={variant} onClose={() => this.reset()} dismissible>
                <Alert.Heading>{title}</Alert.Heading>
                <p>{message}</p>
              </Alert>
            </div>
          </div>
        )}
      </Fragment>
    );
  }
}

export default AppNotificationComponent;
