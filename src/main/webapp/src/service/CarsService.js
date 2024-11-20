import axios from "axios";
import { BASE_URL } from "../constants";
import { toast } from "react-toastify";
import {Modal} from "antd";

export const CarsService = {
    getAllCars: () => {
        return axios.get(BASE_URL + "/api/car", {
            headers: {
                "Access-Control-Allow-Origin": "*",
            },
        }).catch(err => {
                Modal.error({
                    title: "Error",
                    content: err.response?.data?.message || "An unexpected error occurred. Please try again later.",
                });
            });
    },

    addCar: (carDetails) => {
        return axios
            .post(BASE_URL + "/api/car", carDetails, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then(() => {
                toast.success("New car is added!");
            })
            .catch(err => {
                Modal.error({
                    title: "Error",
                    content: err.response?.data?.message || "An unexpected error occurred. Please try again later.",
                });
            });
    },

    deleteCar: (carId) => {
        return axios
            .delete(BASE_URL + "/api/car/" + carId, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then(() => {
                toast.success("Car is deleted!");
            })
            .catch(err => {
                Modal.error({
                    title: "Error",
                    content: err.response?.data?.message || "An unexpected error occurred. Please try again later.",
                });
            });
    },

    editCar: (carId, carDetails) => {
        return axios
            .put(BASE_URL + "/api/car/" + carId, carDetails, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then(() => {
                toast.success("Car is updated!");
            });
    },

    getCarMakers: () => {
        return axios.get(BASE_URL + "/api/car-maker", {
            headers: {
                "Access-Control-Allow-Origin": "*",
            },
        }).catch(err => {
            Modal.error({
                title: "Error",
                content: err.response?.data?.message || "An unexpected error occurred. Please try again later.",
            });
        });
    },

    getCarModels: (carMakerId) => {
        return axios.get(BASE_URL + "/api/car-model?idCarMaker=" + carMakerId, {
            headers: {
                "Access-Control-Allow-Origin": "*",
            },
        }).catch(err => {
            Modal.error({
                title: "Error",
                content: err.response?.data?.message || "An unexpected error occurred. Please try again later.",
            });
        });
    },
};
