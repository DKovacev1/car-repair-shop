import axios from "axios";
import { BASE_URL } from "../constants";
import { toast } from "react-toastify";

export const CarsService = {
    getAllCars: () => {
        return axios.get(BASE_URL + "/api/car", {
            headers: {
                "Access-Control-Allow-Origin": "*",
            },
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
        });
    },

    getCarModels: (carMakerId) => {
        return axios.get(BASE_URL + "/api/car-model?idCarMaker=" + carMakerId, {
            headers: {
                "Access-Control-Allow-Origin": "*",
            },
        });
    },
};
