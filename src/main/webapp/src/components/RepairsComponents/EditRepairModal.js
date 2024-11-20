import { Button, Form, Modal } from "antd";
import { RepairsService } from "../../service";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import { RepairForm } from "./RepairForm";

export const EditRepairModal = ({ repairDetails, open, close }) => {
    const [newRepairDetails, setNewRepairDetails] = useState();

    const [formRef] = Form.useForm();

    useEffect(() => {
        setNewRepairDetails(repairDetails);
    }, [repairDetails]);

    const handleOk = () => {
        RepairsService.editRepair(repairDetails.idRepair, newRepairDetails).then(() =>
            close()
        );
    };

    const handleValidate = async () => {
        try {
            const values = await formRef.validateFields();
            handleOk();
        } catch (errorInfo) {
            toast.error("All fields should be filled!");
        }
    };

    return (
        <Modal
            title="Edit Car Repair Information"
            open={open}
            centered
            onOk={handleValidate}
            onCancel={close}
            footer={[
                <Button key="back" onClick={close}>
                    Return
                </Button>,
                <Button key="submit" type="primary" onClick={handleValidate}>
                    Update Car Repair
                </Button>,
            ]}
            destroyOnClose
        >
            <RepairForm
                repairDetails={repairDetails}
                formRef={formRef}
                onChange={(item) =>
                    setNewRepairDetails({ ...newRepairDetails, ...item })
                }
            />
        </Modal>
    );
};
