import { Divider } from "antd";
import { useCars } from "../hooks";
import { CarCard } from "../components/Cards/CarCard";
import { AddNewCarCard } from "../components/Cards/AddNewCarCard";
import { useEffect, useState } from "react";
import { DeleteCarModal } from "../components/MyCarsPageComponents/DeleteCarModal";

export const MyCarsPage = () => {
    const [carsList, resetCars] = useCars();

    const [selectedCar, setSelectedCar] = useState({});

    const [isAddNewCarModalOpened, setIsAddNewCarModalOpened] = useState(false);
    const [isDeleteModalOpened, setIsDeleteModalOpened] = useState(false);
    const [isEditModalOpened, setIsEditModalOpened] = useState(false);

    return (
        <div>
            <div
                style={{
                    display: "flex",
                    justifyContent: "space-between",
                    alignItems: "end",
                }}
            >
                <h1
                    style={{
                        margin: "15px",
                        fontSize: "1.7em",
                        lineHeight: "0",
                        display: "inline-block",
                    }}
                >
                    My Cars
                </h1>
                <h3
                    style={{
                        margin: "15px",
                        fontSize: "1.3em",
                        fontWeight: "normal",
                        lineHeight: "0",
                        display: "inline-block",
                    }}
                >
                    Cars: {carsList.length}
                </h3>
            </div>
            <Divider
                style={{ margin: "7px 0", borderWidth: "3px" }}
                variant="solid"
            />
            <div
                style={{
                    display: "flex",
                    flexWrap: "wrap",
                    justifyContent: "flex-start",
                    alignContent: "space-between",
                }}
            >
                {carsList.map((item) => (
                    <CarCard
                        carDetails={item}
                        editFunction={null}
                        deleteFunction={() => {
                            setSelectedCar(item);
                            setIsDeleteModalOpened(true);
                        }}
                    />
                ))}

                <DeleteCarModal
                    carDetails={selectedCar}
                    open={isDeleteModalOpened}
                    close={() => {
                        setIsDeleteModalOpened(false);
                        resetCars();
                    }}
                />

                <AddNewCarCard />
            </div>
        </div>
    );
};
