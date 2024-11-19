import { JobOrderDetails } from "../components";
import { useJobOrder } from "../hooks";

export const JobOrderPage = () => {
    const getIdFromUrl = () => {
        const url = new URL(window.location.href);
        const params = new URLSearchParams(url.search);
        return params.get("id");
    };

    const id = getIdFromUrl();

    const [jobOrder] = useJobOrder(id);

    console.log(jobOrder);

    return <>{jobOrder!=[] && <JobOrderDetails jobOrder={jobOrder} />}</>;
};
