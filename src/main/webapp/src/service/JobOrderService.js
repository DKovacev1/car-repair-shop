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
        return axios.post(BASE_URL + "/api/job-order", orderDetails, {
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

    incrementStatus: (orderId) => {
        return axios
            .post(
                BASE_URL + "/api/job-order/" + orderId + "/increment-status",
                {
                    headers: {
                        "Access-Control-Allow-Origin": "*",
                    },
                }
            )
            .then(() => {
                toast.success("Job order status is incremented!");
            });
    },

    getJobOrderStatuses: (orderId) => {
        return axios
            .get(BASE_URL + "/api/job-order-status", {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then((response) => {
                return response.data;
            });
    },

    getPayments: () => {
        return axios
            .get(BASE_URL + "/api/payment", {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then((response) => {
                return response.data;
            });
    },

    addReceipt: (value) => {
        return axios.post(BASE_URL + "/api/receipt", value, {
            headers: {
                "Access-Control-Allow-Origin": "*",
            },
        });
    },

    downloadReceipt: async (jobOrderId) => {
        const url = BASE_URL + "/api/job-order/" + jobOrderId + "/receipt";

        const pdfBlob = await axios
            .get(url, {
                responseType: "arraybuffer",
                headers: {
                    "Access-Control-Allow-Origin": "*",
                    Accept: "application/pdf",
                },
            })
            .then((res) => res.data)
            .then(
                (data) =>
                    new Blob([data], {
                        type: "application/pdf",
                    })
            );

        const link = document.createElement("a");
        link.href = window.URL.createObjectURL(pdfBlob);
        link.download = "receipt.pdf";
        document.body.appendChild(link);
        link.click();

        document.body.removeChild(link);
    },
};
