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
        console.log(carId);
        console.log(carDetails);
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
};
