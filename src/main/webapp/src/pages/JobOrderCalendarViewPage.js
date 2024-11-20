import { Button, Table } from "antd";
import dayjs from "dayjs";
import { useJobOrders } from "../hooks";
import { JobOrderCalendar } from "../components";
import { useState } from "react";

export const JobOrderCalendarViewPage = () => {
    const [jobOrders] = useJobOrders();
    const [selectedDate, setSelectedDate] = useState(dayjs().startOf("day"));

    const filteredJobOrders = jobOrders.filter((order) =>
        dayjs(order.orderDate).isSame(selectedDate, "day")
    );

    const formattedDate = selectedDate.format("YYYY-MM-DD");
    const dayOfWeek = selectedDate.format("dddd");

    return (
        <div>
            <div style={{ marginBottom: "20px", textAlign: "center" }}>
                <Button
                    onClick={() =>
                        setSelectedDate(selectedDate.subtract(1, "day"))
                    }
                    style={{ marginRight: "10px" }}
                >
                    Previous
                </Button>
                <span
                    style={{
                        fontSize: "16px",
                        fontWeight: "bold",
                        display: "inline-block",
                        margin: "0 20px",
                    }}
                >
                    {formattedDate} ({dayOfWeek})
                </span>
                <Button
                    onClick={() => setSelectedDate(selectedDate.add(1, "day"))}
                    style={{ marginLeft: "10px" }}
                >
                    Next
                </Button>
            </div>
            <JobOrderCalendar
                jobOrders={filteredJobOrders}
            />
        </div>
    );
};
