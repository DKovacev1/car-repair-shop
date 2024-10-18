import { Button, Form, Input } from "antd";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export const RegisterForm = ({ mode, formRef, onFormFinish }) => {
    const navigate = useNavigate();

    const [formValues, setFormValues] = useState({});

    const onValueChange = (e) => {
        setFormValues({ ...formValues, [e.target.name]: e.target.value });
    };

    return (
        <Form
            name="register-form"
            form={formRef}
            className="register-form"
            labelCol={{
                span: 8,
            }}
            wrapperCol={{
                span: 8,
            }}
            onFinish={() => onFormFinish(formValues)}
            clearOnDestroy
        >
            {(mode === "Register" || mode === "Add User") && (
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
            {(mode === "Log In" || mode === "Register") && (
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
            )}
            <Form.Item wrapperCol={{ offset: 8, span: 8 }}>
                <Button
                    type="primary"
                    htmlType="submit"
                    className="register-form-button"
                    style={{ marginRight: "5px" }}
                >
                    {mode}
                </Button>
                {(mode === "Log In" || mode === "Register") && (
                    <>
                        Or{" "}
                        <a href="" onClick={() => navigate("/register")}>
                            {mode === "Register" ? "Log in!" : "Register!"}
                        </a>
                    </>
                )}
            </Form.Item>
        </Form>
    );
};
