import { Subject } from "rxjs";

const subject = new Subject();

/**
 * This function will show notification with rxjs
 *
 * Show an error message
 * Show a success message
 *
 */
export const AppNotification = {
  errorMessage: (errMessage) => subject.next({ message: errMessage }),
  successMessage: (message) => subject.next({ message: message }),
};
