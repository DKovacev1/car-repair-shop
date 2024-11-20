import { Button, Form, InputNumber, Modal, Select } from "antd";
import { usePayments } from "../../hooks";
import { useState } from "react";
import { JobOrderService } from "../../service";

export const JobOrderPaymentModal = ({ orderData, open, close }) => {
    const [payments] = usePayments((payment) => ({
        value: payment.idPayment,
        label:
            payment.name +
            (payment.discount == 0
                ? ""
                : " ( -" + payment.discount * 100 + "% )"),
    }));

    const handleOk = () => {
        JobOrderService.addReceipt({
            idJobOrder: orderData.idJobOrder,
            ...formValues,
        }).then(() => close());
    };

    const [formValues, setFormValues] = useState({
        additionalDiscount: 0.0,
        idPayment: 0,
    });

    return (
        <Modal
            title="Payment Info"
            open={open}
            centered
            onOk={handleOk}
            onCancel={close}
            footer={[
                <Button key="back" onClick={close}>
                    Return
                </Button>,
                <Button key="submit" type="primary" onClick={handleOk}>
                    Pay For Order
                </Button>,
            ]}
            destroyOnClose
        >
            <Form
                labelCol={{
                    span: 8,
                }}
                wrapperCol={{
                    span: 12,
                }}
            >
                <Form.Item
                    label="Payment Method"
                    name="payment"
                    rules={[
                        {
                            required: true,
                            message: "Please select payment method!",
                        },
                    ]}
                >
                    <Select
                        allowClear
                        placeholder="Select Payment Method"
                        options={payments}
                        onChange={(value) =>
                            setFormValues({ ...formValues, idPayment: value })
                        }
                    />
                </Form.Item>
                <Form.Item label="Discount" name="discount">
                    <InputNumber
                        onChange={(value) =>
                            setFormValues({
                                ...formValues,
                                additionalDiscount: value / 100,
                            })
                        }
                        min={0}
                        max={100}
                        addonAfter="%"
                    />
                </Form.Item>
            </Form>
        </Modal>
    );
};
