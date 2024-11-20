import axios from "axios";
import { BASE_URL } from "../constants";
import { toast } from "react-toastify";

export const RepairsService = {
    getAllRepairs: () => {
        return axios.get(BASE_URL + "/api/repair", {
            headers: {
                "Access-Control-Allow-Origin": "*",
            },
        });
    },

    addRepair: (repairDetails) => {
        return axios
            .post(BASE_URL + "/api/repair", repairDetails, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then(() => {
                toast.success("New repair is added!");
            });
    },

    deleteRepair: (repairId) => {
        return axios
            .delete(BASE_URL + "/api/repair/" + repairId, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then(() => {
                toast.success("Car repair is deleted!");
            });
    },

    editRepair: (repairId, repairDetails) => {
        return axios
            .put(BASE_URL + "/api/repair/" + repairId, repairDetails, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then(() => {
                toast.success("Car repair is updated!");
            });
    },

};
