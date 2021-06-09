import { Subject } from "rxjs";

const apiBaseUrl = "http://localhost:8080/api/services";

export const retrieveAvailableSalonServices = () =>
  fetch(apiBaseUrl + "/retrieveAvailableSalonServices");
