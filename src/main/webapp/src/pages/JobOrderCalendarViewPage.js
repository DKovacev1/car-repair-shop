import { Table } from "antd";
import dayjs from "dayjs";
import { useJobOrders } from "../hooks";
import { JobOrderCalendar } from "../components";

export const JobOrderCalendarViewPage = () => {
    const [jobOrders] = useJobOrders();

    return <JobOrderCalendar jobOrders={jobOrders} />;
};
