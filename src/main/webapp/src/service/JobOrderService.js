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

    getAllJobOrders: () => {
        return axios.get(BASE_URL + "/api/job-order", {
            headers: {
                "Access-Control-Allow-Origin": "*",
            },
        });
    },

    deleteJobOrder: (orderId) => {
        return axios
            .delete(BASE_URL + "/api/job-order/" + orderId, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then(() => {
                toast.success("Job order is deleted!");
            });
    },
};
