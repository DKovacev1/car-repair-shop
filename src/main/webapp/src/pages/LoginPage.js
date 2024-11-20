import React from "react";
import { useNavigate } from "react-router-dom";
import { Form } from "antd";
import { AppContext } from "../AppContext";
import axios from "axios";
import { BASE_URL } from "../constants";
import { toast } from "react-toastify";
import { SessionStorageService } from "../service/SessionStorageService";
import { RegisterForm } from "../components";

export const LoginPage = ({ mode }) => {
    const navigate = useNavigate();
    const appContext = React.useContext(AppContext);
    const [form] = Form.useForm();

    const onLogin = (formValues) => {
        axios
            .post(BASE_URL + "/api/login", formValues, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then((response) => {
                toast.success("You are logged in!");
                SessionStorageService.addToken(response.data.jwt);
                navigate("/");
                appContext.dispatch({
                    type: "LOGIN_SUCCESS",
                    payload: response.data,
                });
            })
            .catch((error) => {
                toast.error("Something is wrong. Try again!");
                form.resetFields();
                appContext.dispatch({
                    type: "LOGIN_FAILURE",
                    payload: error.message,
                });
            });
    };

    const onRegister = (formValues) => {
        axios
            .post(BASE_URL + "/api/register", formValues, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then((response) => {
                toast.success("You applied successfully for an account!");
                SessionStorageService.addToken(response.data.jwt);
                navigate("/");
                appContext.dispatch({
                    type: "LOGIN_SUCCESS",
                    payload: response.data,
                });
            })
            .catch((error) => {
                toast.error("Something is wrong. Try again!");
                form.resetFields();
                appContext.dispatch({
                    type: "LOGIN_FAILURE",
                    payload: error.message,
                });
            });
    };

    return (
        <RegisterForm
            formRef={form}
            onFormFinish={(formValues) =>
                mode === "Register" ? onRegister(formValues) : onLogin(formValues)
            }
            mode={mode}
        />
    );
};

LoginPage.defaultProps = {
    mode: "Log In",
};
