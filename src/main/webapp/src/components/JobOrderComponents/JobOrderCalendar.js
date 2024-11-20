import React from "react";
import { Table } from "antd";
import dayjs from "dayjs";
import isSameOrBefore from "dayjs/plugin/isSameOrBefore";
import isSameOrAfter from "dayjs/plugin/isSameOrAfter";
import { useWorkplaces } from "../../hooks";
import { OrderCell } from "./OrderCell";

dayjs.extend(isSameOrBefore);
dayjs.extend(isSameOrAfter);

export const JobOrderCalendar = ({ jobOrders }) => {
    const [workplaces] = useWorkplaces();

    const columns = [
        {
            title: "Time",
            dataIndex: "time",
            key: "time",
            align: "center",
        },
        ...workplaces.map((workplace) => ({
            title: workplace.name,
            dataIndex: `workplace-${workplace.idWorkplace}`,
            key: `workplace-${workplace.idWorkplace}`,
            align: "center",
            render: (_, record) => {
                const cellData = record[`workplace-${workplace.idWorkplace}`];
                return {
                    children: cellData?.content || "",
                    props: {
                        rowSpan: cellData?.rowSpan || 0,
                    },
                };
            },
        })),
    ];

    const times = [];
    const startTime = dayjs("08:00", "HH:mm");
    const endTime = dayjs("16:00", "HH:mm");
    const interval = 30;

    for (let time = startTime; time.isBefore(endTime) || time.isSame(endTime); time = time.add(interval, "minute")) {
        times.push(time.format("HH:mm"));
    }

    const calculateRowSpans = (jobOrders, workplaces, times) => {
        const data = times.map((time) => {
            const row = { time };

            workplaces.forEach((workplace) => {
                row[`workplace-${workplace.idWorkplace}`] = null;
            });

            return row;
        });

        jobOrders.forEach((order) => {
            const start = dayjs(order.timeFrom, "HH:mm:ss");
            const end = dayjs(order.timeTo, "HH:mm:ss");
            const startIdx = times.findIndex((t) => dayjs(t, "HH:mm").isSame(start));
            const endIdx = times.findIndex((t) => dayjs(t, "HH:mm").isSame(end));
            const duration = endIdx - startIdx;

            const workplaceKey = `workplace-${order.workplace.idWorkplace}`;
            if (startIdx !== -1 && duration > 0) {
                data[startIdx][workplaceKey] = {
                    content: <OrderCell order={order} />,
                    rowSpan: duration,
                };

                for (let i = startIdx + 1; i < endIdx; i++) {
                    data[i][workplaceKey] = {
                        rowSpan: 0,
                    };
                }
            }
        });

        data.forEach((row) => {
            workplaces.forEach((workplace) => {
                const key = `workplace-${workplace.idWorkplace}`;
                if (!row[key]) {
                    row[key] = {
                        content: "",
                        rowSpan: 1,
                    };
                }
            });
        });

        return data;
    };

    const data = calculateRowSpans(jobOrders, workplaces, times);

    return (
        <Table
            dataSource={data}
            columns={columns}
            rowKey="time"
            bordered
            pagination={false}
        />
    );
};
