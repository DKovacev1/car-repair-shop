import { Button, Form, Input, Select } from "antd";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useRoles } from "../hooks";

export const RegisterForm = ({ mode, data, formRef, onFormFinish }) => {
    const navigate = useNavigate();
    const [roles] = useRoles("Add User");

    const [formValues, setFormValues] = useState({});

    const onValueChange = (e) => {
        setFormValues({ ...formValues, [e.target.name]: e.target.value });
    };

    useEffect(() => {
        if (data != undefined) {
            formRef.setFieldsValue({
                ...data,
                idRole: data.role.idRole,
            });
        }
    }, [data]);

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
            {(mode === "Register" ||
                mode === "Add User" ||
                mode === "Update User") && (
                <>
                    <Form.Item
                        name="firstName"
                        label="First Name"
                        rules={[
                            {
                                required: true,
                                message: "Please input first name!",
                            },
                        ]}
                    >
                        <Input
                            placeholder="Enter first name"
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
                                message: "Please input last name!",
                            },
                        ]}
                    >
                        <Input
                            placeholder="Enter last name"
                            onChange={onValueChange}
                            name="lastName"
                        />
                    </Form.Item>
                </>
            )}
            <Form.Item
                name="email"
                label="E-mail"
                rules={[
                    {
                        type: "email",
                        required: true,
                        message: "Please input e-mail!",
                    },
                ]}
            >
                <Input
                    type="mail"
                    placeholder="Enter E-mail address"
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
                            message: "Please input Password!",
                        },
                    ]}
                >
                    <Input.Password
                        placeholder="Enter password"
                        onChange={onValueChange}
                        name="password"
                    />
                </Form.Item>
            )}
            {(mode === "Add User" || mode === "Update User") && (
                <Form.Item
                    name="idRole"
                    label="Role"
                    rules={[
                        { required: true, message: "Please select a role" },
                    ]}
                >
                    <Select
                        allowClear
                        style={{ width: "100%", textAlign: "left" }}
                        placeholder={"Select wanted role"}
                        onChange={(value) => {
                            const e = {
                                target: {
                                    name: "idRole",
                                    value: value ? value : "",
                                },
                            };
                            onValueChange(e);
                        }}
                        options={roles.map((role) => {
                            let roleName = role.name.toLowerCase();
                            roleName =
                                roleName.charAt(0).toUpperCase() +
                                roleName.slice(1);
                            return { value: role.idRole, label: roleName };
                        })}
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
