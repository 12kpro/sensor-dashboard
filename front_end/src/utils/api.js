import axios from "axios";

const baseURL = process.env.REACT_APP_SRV_URL;
//const baseURL = "http://localhost:5080";

const api = axios.create({
  baseURL
});

export default api;
