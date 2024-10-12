import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Button, Form, Input } from "antd";
import { AppContext } from "../AppContext";

export const LoginPage = ({ mode }) => {
    const navigate = useNavigate();
    const appContext = React.useContext(AppContext);

    const [formValues, setFormValues] = useState({
        email: "",
        password: "",
    });

    const onValueChange = (e) => {
        setFormValues({ ...formValues, [e.target.name]: e.target.value });
    };

    return (
        <div>
            <Form
                name="register-form"
                className="register-form"
                labelCol={{
                    span: 8,
                }}
                wrapperCol={{
                    span: 8,
                }}
                onFinish={() => {
                    appContext.dispatch({
                        type: "LOGIN_START",
                        payload: formValues,
                    });
                }}
            >
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
                        style={{marginRight: "5px"}}
                    >
                        Log in
                    </Button>
                    Or{" "}
                    <a href="" onClick={() => navigate("/register")}>
                        Register!
                    </a>
                </Form.Item>
            </Form>
        </div>
    );
};

LoginPage.defaultProps = {
    mode: "login",
};
