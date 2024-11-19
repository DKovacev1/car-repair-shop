import "./App.css";
import { SessionStorageService } from "./service/SessionStorageService";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { ConfigProvider, theme } from "antd";
import { initialUserDataState, ROLE_NAMES } from "./constants";
import { useCallback, useReducer } from "react";
import { setupAxiosInterceptors } from "./config/axios-interceptor";
import { AppContext } from "./AppContext";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import {
    LoginPage,
    MainPage,
    AdminPage,
    NoPage,
    UsersPage,
    MyCarsPage,
    SettingsPage,
    AddNewCarPage,
    ProfilePage,
    WorkplacePage,
    PartsPage,
    RepairsPage,
    NewJobOrderPage,
    JobOrderTableViewPage,
    JobOrderCalendarViewPage,
    JobOrderPage,
} from "./pages";
import { CustomLayout, SecureRoute } from "./containers";
import { authenticationReducer } from "./reducers";

function App() {
    const [userData, dispatch] = useReducer(
        useCallback(authenticationReducer, []),
        initialUserDataState
    );

    setupAxiosInterceptors(() => {
        SessionStorageService.removeToken();
    });

    return (
        <ConfigProvider
            theme={
                {
                    //algorithm: theme.darkAlgorithm,
                    /*token:{
                    colorText: "#E2E2B6"
                }*/
                }
            }
        >
            <div className="App">
                <AppContext.Provider value={{ ...userData, dispatch }}>
                    <BrowserRouter>
                        <CustomLayout userData={userData}>
                            <Routes>
                                {/* Pages for all customers */}
                                <Route path="/" element={<MainPage />} />
                                <Route
                                    path="/register"
                                    element={<LoginPage mode="Register" />}
                                />
                                <Route path="/login" element={<LoginPage />} />
                                <Route
                                    path="/settings"
                                    element={<SettingsPage />}
                                />

                                <Route path="*" element={<NoPage />} />

                                {/* Pages for admin */}
                                <Route
                                    path="/"
                                    element={
                                        <SecureRoute
                                            authorized_roles={[
                                                ROLE_NAMES.Admin,
                                            ]}
                                        />
                                    }
                                >
                                    <Route
                                        path="/admin"
                                        element={<AdminPage />}
                                    />
                                    <Route
                                        path="/workplaces"
                                        element={<WorkplacePage />}
                                    />
                                    <Route
                                        path="/parts"
                                        element={<PartsPage />}
                                    />
                                    <Route
                                        path="/repairs"
                                        element={<RepairsPage />}
                                    />
                                </Route>

                                {/* Pages for employees and admin */}
                                <Route
                                    path="/"
                                    element={
                                        <SecureRoute
                                            authorized_roles={[
                                                ROLE_NAMES.Employee,
                                                ROLE_NAMES.Admin,
                                            ]}
                                        />
                                    }
                                >
                                    <Route
                                        path="/users"
                                        element={<UsersPage />}
                                    />
                                    <Route
                                        path="/job-orders-calendar"
                                        element={<JobOrderCalendarViewPage />}
                                    />
                                </Route>

                                {/* Pages for employees*/}
                                <Route
                                    path="/"
                                    element={
                                        <SecureRoute
                                            authorized_roles={[
                                                ROLE_NAMES.Employee,
                                            ]}
                                        />
                                    }
                                >
                                    <Route
                                        path="/new-job-order"
                                        element={<NewJobOrderPage />}
                                    />
                                </Route>

                                {/* Pages for all users */}
                                <Route
                                    path="/"
                                    element={
                                        <SecureRoute
                                            authorized_roles={[ROLE_NAMES.User]}
                                        />
                                    }
                                >
                                    <Route
                                        path="/profile"
                                        element={<ProfilePage />}
                                    />
                                    <Route
                                        path="/my-cars"
                                        element={
                                            <MyCarsPage
                                                userId={userData.firstName}
                                            />
                                        }
                                    />
                                    <Route
                                        path="/my-cars/add-new-car"
                                        element={<AddNewCarPage />}
                                    />
                                </Route>

                                {/* Pages for all authenticated users, admin, etc. */}
                                <Route
                                    path="/"
                                    element={
                                        <SecureRoute
                                            authorized_roles={[
                                                ROLE_NAMES.Admin,
                                                ROLE_NAMES.Employee,
                                                ROLE_NAMES.User,
                                            ]}
                                        />
                                    }
                                >
                                    <Route
                                        path="/job-orders-table"
                                        element={<JobOrderTableViewPage />}
                                    />
                                    <Route 
                                        path="/job-order"
                                        element={<JobOrderPage />}
                                    />
                                </Route>
                            </Routes>
                            <ToastContainer
                                theme="colored"
                                className="toast-position"
                            />
                        </CustomLayout>
                    </BrowserRouter>
                </AppContext.Provider>
            </div>
        </ConfigProvider>
    );
}

export default App;
