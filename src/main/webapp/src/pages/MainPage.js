import { Button } from "antd";
import axios from "axios";
import { BASE_URL } from "../constants";
import { toast } from "react-toastify";

export const MainPage = () => {
    const onClick = () => {
        axios
            .post(BASE_URL + "/api/echo")
            .then((res) => {
                toast.success("Prošlo");
            })
            .catch(() => {
                toast.error("FAIL");
            });
    };

    return (
        <div>
            MainPage
            <Button type="primary" onClick={onClick}>
                Echo
            </Button>
        </div>
    );
};
