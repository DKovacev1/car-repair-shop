import "./App.css";
import { SessionStorageService } from "./service/SessionStorageService";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { ConfigProvider, theme } from "antd";
import { BASE_URL, ROLE_NAMES } from "./constants";
import { useCallback, useReducer } from "react";
import axios from "axios";
import { setupAxiosInterceptors } from "./config/axios-interceptor";
import { AppContext } from "./AppContext";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { LoginPage, MainPage, AdminPage, UserPage, NoPage, UsersPage } from "./pages";
import { CustomLayout, SecureRoute } from "./containers";

const initialUserDataState = {
    loading: true,
    isAuthenticated: false,
    firstName: "",
    lastName: "",
    email: "",
    role: "",
};

function App() {
    const [userData, dispatch] = useReducer(
        useCallback(authenticationReducer, []),
        initialUserDataState
    );

    const clearAuthToken = () => SessionStorageService.removeToken();

    function authenticationReducer(state, action) {
        console.log(action);
        switch (action.type) {
            case "LOGIN_START":
                axios
                    .post(BASE_URL + "/api/login", action.payload, {
                        headers: {
                            "Access-Control-Allow-Origin": "*",
                        },
                    })
                    .then((response) => {
                        toast.success("You are logged in!");
                        SessionStorageService.addToken(response.data.jwt);
                        dispatch({
                            type: "LOGIN_SUCCESS",
                            payload: response.data,
                        });
                    })
                    .catch((error) => {
                        toast.error("Something is wrong. Try again!");
                        dispatch({
                            type: "LOGIN_FAILURE",
                            payload: error.message,
                        });
                    });
                return { ...state, loading: true };
            case "REGISTER_START":
                axios
                    .post(BASE_URL + "/api/register", action.payload, {
                        headers: {
                            "Access-Control-Allow-Origin": "*",
                        },
                    })
                    .then((response) => {
                        toast.success("Your account is made!");
                        SessionStorageService.addToken(response.data.jwt);
                        dispatch({
                            type: "LOGIN_SUCCESS",
                            payload: response.data,
                        });
                    })
                    .catch((error) => {
                        toast.error("Something is wrong. Try again!");
                        dispatch({
                            type: "LOGIN_FAILURE",
                            payload: error.message,
                        });
                    });
                return { ...state, loading: true };
            case "LOGIN_SUCCESS":
                return {
                    ...state,
                    firstName: action.payload.firstName,
                    lastName: action.payload.lastName,
                    email: action.payload.email,
                    role: action.payload.role
                        ? action.payload.role
                        : { name: "" },
                    data: action.payload,
                    loading: false,
                    isAuthenticated: true,
                };
            case "LOGIN_FAILURE":
                return {
                    ...state,
                    loading: false,
                    isAuthenticated: false,
                };
            case "TOKEN_CLEAR":
                clearAuthToken();
                return {
                    ...state,
                    loading: false,
                    isAuthenticated: false,
                    role: "",
                    firstName: "",
                    lastName: "",
                    email: "",
                };
            default:
                return state;
        }
    }

    setupAxiosInterceptors(() => {
        clearAuthToken();
    });

    return (
        <ConfigProvider
            theme={{
                algorithm: theme.darkAlgorithm,
                /*token:{
                    colorText: "#E2E2B6"
                }*/
            }}
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
                                    element={<LoginPage mode="register" />}
                                />
                                <Route path="/login" element={<LoginPage />} />
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
                                        path="/users"
                                        element={<UsersPage />}
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
                                        path="/user"
                                        element={<UserPage />}
                                    />
                                </Route>

                                {/* Pages for all authenticated users, admin, etc. */}
                                <Route
                                    path="/"
                                    element={
                                        <SecureRoute
                                            roles={[
                                                ROLE_NAMES.Admin,
                                                ROLE_NAMES.User,
                                            ]}
                                        />
                                    }
                                ></Route>
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
