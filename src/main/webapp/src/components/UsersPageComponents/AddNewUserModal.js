import { Form, Modal } from "antd";
import { RegisterForm } from "../RegisterForm";
import { UsersService } from "../../service";

export const AddNewUserModal = ({ open, close }) => {
    const [form] = Form.useForm();

    const handleOk = (userData) => {
        UsersService.addNewUser(userData)
            .then(() => {close();
        });
    };

    return (
        <Modal
            title="Add New User"
            open={open}
            centered
            onOk={handleOk}
            onCancel={close}
            footer={null}
            destroyOnClose
        >
            <RegisterForm formRef={form} onFormFinish={handleOk} mode={"Add User"} />
        </Modal>
    );
};
