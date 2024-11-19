import { useEffect, useState } from "react";
import { CarsService, JobOrderService } from "../service";

export const useJobOrders = () => {
    const [jobOrders, setJobOrders] = useState([]);

    useEffect(() => {
        /*JobOrderService.getAllJobOrders().then((response) => {
            setJobOrders(response.data);
        });*/
    }, []);

    const resetJobOrders = () => {
        CarsService.getAllCars().then((response) => {
            setJobOrders(response.data);
        });
    };

    return [jobOrders, resetJobOrders];
};
