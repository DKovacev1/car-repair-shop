import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Button, Form, Input } from "antd";
import { AppContext } from "../AppContext";
import axios from "axios";
import { BASE_URL } from "../constants";
import { toast } from "react-toastify";
import { SessionStorageService } from "../service/SessionStorageService";

export const LoginPage = ({ mode }) => {
    const navigate = useNavigate();
    const appContext = React.useContext(AppContext);
    const [form] = Form.useForm();

    const [formValues, setFormValues] = useState({
        email: "",
        password: "",
    });

    const onValueChange = (e) => {
        setFormValues({ ...formValues, [e.target.name]: e.target.value });
    };

    const onLogin = () => {
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
                setFormValues({});
                appContext.dispatch({
                    type: "LOGIN_FAILURE",
                    payload: error.message,
                });
            });
    };

    const onRegister = () => {
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
                setFormValues({});
                appContext.dispatch({
                    type: "LOGIN_FAILURE",
                    payload: error.message,
                });
            });
    };

    return (
        <div>
            <Form
                name="register-form"
                form={form}
                className="register-form"
                labelCol={{
                    span: 8,
                }}
                wrapperCol={{
                    span: 8,
                }}
                onFinish={() => {
                    mode === "register"
                        ? onRegister(formValues)
                        : onLogin(formValues);
                }}
                clearOnDestroy
            >
                {mode === "register" && (
                    <>
                        <Form.Item
                            name="firstName"
                            label="First Name"
                            rules={[
                                {
                                    required: true,
                                    message: "Please input your first name!",
                                },
                            ]}
                        >
                            <Input
                                placeholder="Enter your first name"
                                onChange={onValueChange}
                                name="firstName"
                            />
                        </Form.Item>
                        <Form.Item
                            name="lastName"
                            label="Last Name"
                            rules={[
                                {
                                    required: true,
                                    message: "Please input your last name!",
                                },
                            ]}
                        >
                            <Input
                                placeholder="Enter your last name"
                                onChange={onValueChange}
                                name="lastName"
                            />
                        </Form.Item>
                    </>
                )}
                <Form.Item
                    name="mail"
                    label="E-mail"
                    rules={[
                        {
                            type: "email",
                            required: true,
                            message: "Please input your e-mail!",
                        },
                    ]}
                >
                    <Input
                        type="mail"
                        placeholder="Enter your E-mail address"
                        onChange={onValueChange}
                        name="email"
                    />
                </Form.Item>
                <Form.Item
                    name="password"
                    label="Password"
                    rules={[
                        {
                            required: true,
                            message: "Please input your Password!",
                        },
                    ]}
                >
                    <Input.Password
                        placeholder="Enter your password"
                        onChange={onValueChange}
                        name="password"
                    />
                </Form.Item>
                <Form.Item wrapperCol={{ offset: 8, span: 8 }}>
                    <Button
                        type="primary"
                        htmlType="submit"
                        className="register-form-button"
                        style={{ marginRight: "5px" }}
                    >
                        {mode === "register" ? "Register" : "Log in"}
                    </Button>
                    Or{" "}
                    <a href="" onClick={() => navigate("/register")}>
                        {mode === "register" ? "Log in!" : "Register!"}
                    </a>
                </Form.Item>
            </Form>
        </div>
    );
};

LoginPage.defaultProps = {
    mode: "login",
};
