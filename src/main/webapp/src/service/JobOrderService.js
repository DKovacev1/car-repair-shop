import axios from "axios";
import { BASE_URL } from "../constants";
import { toast } from "react-toastify";

export const JobOrderService = {
    getSchedule: (repairTime) => {
        return axios.get(BASE_URL + "/api/schedule" /*?repairTime=" + repairTime*/, {
            headers: {
                "Access-Control-Allow-Origin": "*",
            },
        });
    },

    getAllParts: () => {
        return axios.get(BASE_URL + "/api/part", {
            headers: {
                "Access-Control-Allow-Origin": "*",
            },
        });
    },

    addPart: (partDetails) => {
        return axios
            .post(BASE_URL + "/api/part", partDetails, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then(() => {
                toast.success("New part is added!");
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
