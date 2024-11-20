import {
    Button,
    Checkbox,
    FloatButton,
    Form,
    Input,
    Select,
    Table,
    Tag,
} from "antd";
import {
    checkboxOffset,
    formCols,
    formOffset,
    initialFormUser,
    ROLE_NAMES,
} from "../constants";
import { LockFilled, UnlockFilled, UserAddOutlined } from "@ant-design/icons";
import React, { useState } from "react";
import { UsersService } from "../service";
import { ActivationModal, AddNewUserModal } from "../components";
import { useRoles } from "../hooks";
import { AppContext } from "../AppContext";
import { UpdateUserModal } from "../components/UsersPageComponents";

export const UsersPage = () => {
    const [roles] = useRoles("");
    const appContext = React.useContext(AppContext);

    const [userList, setUserList] = useState([]);

    const [selectedUser, setSelectedUser] = useState({});

    const [isActivationModalOpened, setIsActivationModalOpened] =
        useState(false);
    const [isAddNewUserModalOpened, setIsAddNewUserModalOpened] =
        useState(false);
    const [isUpdateModalOpened, setIsUpdateModalOpened] = useState(false);
    const [isDeleteModalOpened, setIsDeleteModalOpened] = useState(false);

    const [formValues, setFormValues] = useState(initialFormUser);

    const columns = [
        {
            title: "Status",
            dataIndex: "activated",
            key: "activated",
            render: (_, record) => (
                <>
                    {record.activated ? (
                        <UnlockFilled
                            style={{ color: "green", fontSize: "2em" }}
                        />
                    ) : (
                        <LockFilled
                            style={{ color: "red", fontSize: "2em" }}
                            onClick={() => {
                                setSelectedUser(record);
                                setIsActivationModalOpened(true);
                            }}
                        />
                    )}
                </>
            ),
        },
        {
            title: "First Name",
            dataIndex: "firstName",
            key: "firstName",
        },
        {
            title: "Last Name",
            dataIndex: "lastName",
            key: "lastName",
        },
        {
            title: "E mail",
            dataIndex: "email",
            key: "email",
        },
        {
            title: "Role",
            key: "role",
            dataIndex: "role",
            render: (_, { role }) => (
                <Tag
                    color={role.name === ROLE_NAMES.Admin ? "red" : "blue"}
                    key={role.idRole}
                >
                    {role.name.toUpperCase()}
                </Tag>
            ),
        },
        {
            title: "Actions",
            key: "actions",
            render: (_, user) => {
                if (
                    appContext.role.name === ROLE_NAMES.Employee &&
                    user.role.name === ROLE_NAMES.User
                ) {
                    return (
                        <div>
                            <Button onClick={() => openUpdateWindow(user)}>
                                Update
                            </Button>
                            <Button onClick={() => openDeleteWindow(user)}>
                                Delete
                            </Button>
                        </div>
                    );
                }
            },
        },
    ];

    const openUpdateWindow = (user) => {
        setIsUpdateModalOpened(true);
        setSelectedUser(user);
    };

    const openDeleteWindow = (user) => {
        setIsDeleteModalOpened(true);
        setSelectedUser(user);
    };

    const onValueChange = (attributeKey, attributeValue) =>
        setFormValues({ ...formValues, [attributeKey]: attributeValue });

    const onFormFinish = () =>
        UsersService.getUsersByFilter(formValues).then((users) =>
            setUserList(
                users.map((user) => {
                    user = { key: user.idAppUser, ...user };
                    return user;
                })
            )
        );

    return (
        <div>
            <Form
                {...formCols}
                labelWrap
                onFinish={onFormFinish}
                style={{ marginTop: "50px", marginBottom: "75px" }}
            >
                <Form.Item name="firstName" label="First Name">
                    <Input
                        placeholder="Enter wanted first name"
                        onChange={(e) =>
                            onValueChange(e.target.name, e.target.value)
                        }
                        name="firstName"
                    />
                </Form.Item>
                <Form.Item name="lastName" label="Last Name">
                    <Input
                        placeholder="Enter wanted last name"
                        onChange={(e) =>
                            onValueChange(e.target.name, e.target.value)
                        }
                        name="lastName"
                    />
                </Form.Item>
                <Form.Item name="email" label="e Mail">
                    <Input
                        type="email"
                        placeholder="Enter wanted email"
                        onChange={(e) =>
                            onValueChange(e.target.name, e.target.value)
                        }
                        name="email"
                    />
                </Form.Item>
                <Form.Item name="role" label="Role">
                    <Select
                        allowClear
                        style={{ width: "100%", textAlign: "left" }}
                        placeholder={"Select wanted role"}
                        onChange={(value) =>
                            onValueChange("idRole", value ? value : "")
                        }
                        options={roles.map((role) => {
                            let roleName = role.name.toLowerCase();
                            roleName =
                                roleName.charAt(0).toUpperCase() +
                                roleName.slice(1);
                            return { value: role.idRole, label: roleName };
                        })}
                    />
                </Form.Item>
                <Form.Item
                    name="isActivated"
                    label="Show only activated users"
                    {...checkboxOffset}
                >
                    <Checkbox
                        name="isActivated"
                        onChange={(e) => {
                            onValueChange(
                                "isActivated",
                                e.target.checked ? true : ""
                            );
                        }}
                    />
                </Form.Item>

                <Form.Item {...formOffset}>
                    <Button type="primary" htmlType="submit">
                        Get Users
                    </Button>
                </Form.Item>
            </Form>

            <Table
                columns={columns}
                dataSource={userList}
                locale={{ emptyText: "No Users Found" }}
            />

            <ActivationModal
                userData={selectedUser}
                open={isActivationModalOpened}
                close={() => {
                    setIsActivationModalOpened(false);
                    onFormFinish();
                }}
            />

            <AddNewUserModal
                open={isAddNewUserModalOpened}
                close={() => {
                    setIsAddNewUserModalOpened(false);
                    onFormFinish();
                }}
            />

            <UpdateUserModal
                userData={selectedUser}
                open={isUpdateModalOpened}
                close={() => {
                    setIsUpdateModalOpened(false);
                    onFormFinish();
                }}
            />

            <ActivationModal
                mode={"DELETE"}
                userData={selectedUser}
                open={isDeleteModalOpened}
                close={() => {
                    setIsDeleteModalOpened(false);
                    onFormFinish();
                }}
            />

            <FloatButton
                shape="square"
                type="primary"
                style={{
                    insetInlineEnd: 48,
                }}
                icon={<UserAddOutlined />}
                tooltip={<div>Add New User</div>}
                onClick={() => setIsAddNewUserModalOpened(true)}
            />
        </div>
    );
};
