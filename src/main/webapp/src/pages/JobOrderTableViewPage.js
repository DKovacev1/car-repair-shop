import React, { useState } from "react";
import { JobOrderDeleteModal, JobOrderTable } from "../components";
import { useJobOrders } from "../hooks";

export const JobOrderTableViewPage = () => {
    const [jobOrders, resetJobOrders] = useJobOrders();
    const [selectedJobOrder, setSelectedJobOrder] = useState({
        "idJobOrder": 1,
        "description": "",
        "orderDate": "",
        "timeFrom": "",
        "timeTo": "",
        "workplace": {},
        "jobOrderAppUserEmployee": {
            "firstName": "",
            "lastName": "",
        },
        "car": {
            "maker": "",
            "model": "",
            "carOwner": {
                "firstName": "",
                "lastName": "",
            }
        },
        "jobOrderStatus": {},
        "repairs": [],
        "parts": []
    });

    const [isDeleteModalOpened, setIsDeleteModalOpened] = useState(false);

    const openDeleteWindow = (jobOrder) => {
        setIsDeleteModalOpened(true);
        setSelectedJobOrder(jobOrder);
    };

    return (
        <div>
            <JobOrderTable 
                jobOrders={jobOrders}
                openDeleteWindow={openDeleteWindow}
            />

            <JobOrderDeleteModal
                orderData={selectedJobOrder}
                open={isDeleteModalOpened}
                close={() => {
                    setIsDeleteModalOpened(false);
                    resetJobOrders();
                }}
            />
        </div>
    );
};
