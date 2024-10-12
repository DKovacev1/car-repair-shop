import { Button } from "antd";
import axios from "axios";
import { BASE_URL } from "../constants";
import { toast } from "react-toastify";

export const MainPage = () => {
    const onClick = () => {
        axios
            .get(BASE_URL + "/api/echo")
            .then(() => {
                toast.success("Prošlo");
            })
            .catch(() => {
                toast.error("FAIL");
            });
    };

    const onClickAuth = () => {
        axios
            .get(BASE_URL + "/api/echo-authenticated")
            .then(() => {
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
            <Button type="primary" onClick={onClickAuth}>
                Echo Authenticated
            </Button>
        </div>
    );
};
