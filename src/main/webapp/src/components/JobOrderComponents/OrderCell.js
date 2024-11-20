import React from "react";
import { useNavigate } from "react-router-dom";

export const OrderCell = ({ order }) => {
    const navigate = useNavigate();

    return (
        <div
            style={{
                backgroundColor: "#add8e6", 
                padding: "10px",
                borderRadius: "5px",
                fontSize: "0.9rem",
                color: "#000",
                boxShadow: "0 1px 3px rgba(0, 0, 0, 0.2)",
                width: "100%",
                height: "100%", 
                display: "grid",
                gridTemplateColumns: "1fr 1fr", 
                gap: "5px", 
                alignItems: "center",
                boxSizing: "border-box",
            }}

            onClick={() => navigate("/job-order?id=" + order.idJobOrder)}
        >
            <div>
                <strong>Owner:</strong> {order.car.carOwner.firstName}{" "}
                {order.car.carOwner.lastName}
            </div>
            <div>
                <strong>Employee:</strong> {order.jobOrderAppUserEmployee.firstName}{" "}
                {order.jobOrderAppUserEmployee.lastName}
            </div>
            <div>
                <strong>Time:</strong> {order.timeFrom.substring(0,5)} - {order.timeTo.substring(0,5)}
            </div>
            <div>
                <strong>Car:</strong> {order.car.maker} {order.car.model} (
                {order.car.registrationPlate})
            </div>
            <div style={{ gridColumn: "1 / span 2" }}>
                <strong>Description:</strong> {order.description}
            </div>
        </div>
    );
};
