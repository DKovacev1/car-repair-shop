import axios from "axios";
import { BASE_URL } from "../constants";
import { toast } from "react-toastify";

export const JobOrderService = {
    getSchedule: (repairTime) => {
        return axios.get(BASE_URL + "/api/schedule?repairTime=" + repairTime, {
            headers: {
                "Access-Control-Allow-Origin": "*",
            },
        });
    },

    getJobOrder: (id) => {
        return axios.get(BASE_URL + "/api/job-order/" + id, {
            headers: {
                "Access-Control-Allow-Origin": "*",
            },
        });
    },

    getAllJobOrders: () => {
        return axios.get(BASE_URL + "/api/job-order", {
            headers: {
                "Access-Control-Allow-Origin": "*",
            },
        });
    },

    addJobOrder: (orderDetails) => {
        return axios
            .post(BASE_URL + "/api/job-order", orderDetails, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then((res) => {
                toast.success("New order is made!");
                return res.data;
            });
    },

    deletePart: (partId) => {
        return axios
            .delete(BASE_URL + "/api/part/" + partId, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then(() => {
                toast.success("Car part is deleted!");
            });
    },

    editPart: (partId, partDetails) => {
        return axios
            .put(BASE_URL + "/api/part/" + partId, partDetails, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then(() => {
                toast.success("Car part is updated!");
            });
    },
};
