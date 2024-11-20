import { useEffect, useState } from "react";
import { JobOrderService } from "../service";

export const usePayments = (mapper = (item) => item) => {
    const [payments, setPayments] = useState([]);

    useEffect(() => {
        JobOrderService.getPayments().then((data) => {
            setPayments(data.map(mapper));
        });
    }, []);

    return [payments];
};
