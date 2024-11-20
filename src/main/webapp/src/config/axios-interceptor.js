import axios from "axios";
import { BASE_URL } from "../constants";
import { toast } from "react-toastify";

const TIMEOUT = 1 * 60 * 1000;
axios.defaults.timeout = TIMEOUT;
axios.defaults.baseURL = BASE_URL;

export const setupAxiosInterceptors = (onUnauthenticated) => {
    const onRequestSuccess = (config) => {
        const token = sessionStorage.getItem("authenticationToken");

        if (token) {
            config.headers["Authorization"] = `Bearer ${token}`;
        }

        config.headers["Strict-Transport-Security"] =
            "max-age=31536000; includeSubDomains; preload";

        return config;
    };

    const onResponseSuccess = (response) => {
        return response;
    };

    const onResponseError = (err) => {
        const status = err.status || (err.response ? err.response.status : 0);

        if (status === 403 || status === 401) {
            onUnauthenticated();
        }

        return Promise.reject(err);
    };

    axios.interceptors.request.use(onRequestSuccess);
    axios.interceptors.response.use(onResponseSuccess, onResponseError);
};
