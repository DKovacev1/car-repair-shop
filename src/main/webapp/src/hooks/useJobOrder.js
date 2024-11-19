import { useEffect, useState } from "react";
import { JobOrderService } from "../service";

export const useJobOrder = (id = null) => {
    const [jobOrder, setJobOrder] = useState([]);

    useEffect(() => {
        if (id == null) {
            JobOrderService.getAllJobOrders().then((response) => {
                setJobOrder(response.data);
            });
        } else
            JobOrderService.getJobOrder(id).then((response) => {
                setJobOrder(response.data);
            });
    }, []);

    const resetJobOrders = () => {
        JobOrderService.getAllJobOrders().then((response) => {
            setJobOrder(response.data);
        });
    };

    return [jobOrder, resetJobOrders];
};
