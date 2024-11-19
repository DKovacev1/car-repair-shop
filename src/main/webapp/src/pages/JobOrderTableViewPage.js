import React, { useState } from "react";
import { JobOrderDeleteModal, JobOrderTable } from "../components";
import { useJobOrders } from "../hooks";

export const JobOrderTableViewPage = () => {
    const [jobOrders] = useJobOrders();
    const [selectedJobOrder, setSelectedJobOrder] = useState({});

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
                }}
            />
        </div>
    );
};
