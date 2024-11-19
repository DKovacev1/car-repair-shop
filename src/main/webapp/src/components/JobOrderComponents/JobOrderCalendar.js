import React, { useState } from "react";
import { Table, Button } from "antd";
import dayjs from "dayjs";

const WEEK_DAYS = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday"];
const HOURS = Array.from({ length: 16 }, (_, i) => {
    const hour = 8 + Math.floor(i / 2);
    const minute = i % 2 === 0 ? "00" : "30";
    return `${hour.toString().padStart(2, "0")}:${minute}`;
});

export const JobOrderCalendar = ({ jobOrders }) => {
    const [currentWeek, setCurrentWeek] = useState(dayjs().startOf("week").add(1, "day"));

    const getWeekDates = () => {
        return WEEK_DAYS.map((_, index) => currentWeek.add(index, "day").format("YYYY-MM-DD"));
    };

    const weekDates = getWeekDates();

    const getOrdersForDayAndTime = (day, time) => {
        return jobOrders.filter((order) => {
            const orderDate = dayjs(order.orderDate).format("YYYY-MM-DD");
            const orderStart = dayjs(`${orderDate} ${order.timeFrom}`);
            const orderEnd = dayjs(`${orderDate} ${order.timeTo}`);
            const cellTime = dayjs(`${day} ${time}`);
            return cellTime.isSame(orderStart) || cellTime.isBetween(orderStart, orderEnd, "minute");
        });
    };

    const renderCellContent = (day, time) => {
        const matchingOrder = getOrdersForDayAndTime(day, time)[0]; 
        if (matchingOrder) {
            const orderStart = dayjs(`${matchingOrder.orderDate} ${matchingOrder.timeFrom}`);
            const orderEnd = dayjs(`${matchingOrder.orderDate} ${matchingOrder.timeTo}`);
            const rowSpan = orderEnd.diff(orderStart, "minutes") / 30;

            return {
                content: (
                    <div
                        style={{
                            width: "100%",
                            height: "100%",
                            background: "#e6f7ff",
                            border: "1px solid #91d5ff",
                            borderRadius: "4px",
                            padding: "8px",
                            boxSizing: "border-box",
                            cursor: "pointer",
                            textAlign: "center",
                        }}
                        onClick={() => console.log(matchingOrder)}
                    >
                        {matchingOrder.description}
                    </div>
                ),
                rowSpan,
            };
        }

        return { content: null, rowSpan: 1 };
    };

    const renderColumns = () =>
        weekDates.map((date, index) => ({
            title: `${WEEK_DAYS[index]} (${date})`,
            dataIndex: date,
            render: (_, record) => {
                const { time } = record;
                const { content, rowSpan } = renderCellContent(date, time);
                return rowSpan > 1 ? (
                    <td rowSpan={rowSpan}>{content}</td>
                ) : (
                    <td>{content}</td>
                );
            },
        }));

    const dataSource = HOURS.map((time) => ({
        key: time,
        time,
        ...Object.fromEntries(weekDates.map((date) => [date, null])),
    }));

    return (
        <div>
            <div style={{ marginBottom: "20px", textAlign: "center" }}>
                <Button onClick={() => setCurrentWeek(currentWeek.subtract(1, "week"))}>
                    Previous Week
                </Button>
                <span style={{ margin: "0 20px", fontSize: "16px", fontWeight: "bold" }}>
                    {currentWeek.format("YYYY-MM-DD")} -{" "}
                    {currentWeek.add(4, "day").format("YYYY-MM-DD")}
                </span>
                <Button onClick={() => setCurrentWeek(currentWeek.add(1, "week"))}>
                    Next Week
                </Button>
            </div>
            <Table
                bordered
                pagination={false}
                dataSource={dataSource}
                columns={[
                    {
                        title: "Time",
                        dataIndex: "time",
                        key: "time",
                        render: (text) => (
                            <div
                                style={{
                                    textAlign: "center",
                                    fontWeight: "bold",
                                }}
                            >
                                {text}
                            </div>
                        ),
                    },
                    ...renderColumns(),
                ]}
                rowClassName={() => "calendar-row"}
                style={{
                    border: "1px solid #f0f0f0",
                    margin: "20px auto",
                    maxWidth: "1200px",
                    backgroundColor: "#fff",
                    borderRadius: "8px",
                }}
            />
        </div>
    );
};
