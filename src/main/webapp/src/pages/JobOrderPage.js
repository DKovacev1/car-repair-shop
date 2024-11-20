import { JobOrderDetails } from "../components";
import { useJobOrders } from "../hooks";

export const JobOrderPage = () => {
    const getIdFromUrl = () => {
        const url = new URL(window.location.href);
        const params = new URLSearchParams(url.search);
        return params.get("id");
    };

    const id = getIdFromUrl();

    const [jobOrder] = useJobOrders(id);

    return <>{jobOrder.length != 0 && <JobOrderDetails jobOrder={jobOrder} />}</>;
};
