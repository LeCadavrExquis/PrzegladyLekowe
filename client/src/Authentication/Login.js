import { useCallback, useState } from "react";
import { useNavigate } from 'react-router-dom';
import './Authentiaction.css';

const Login = () => {
    const navigate = useNavigate();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const login = useCallback(async () => {
        const response = await fetch("/signin", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                email,
                password,
            })
        });
        if (!response.redirected && response.ok) {
            navigate("/app")
        } else {
            console.error(response);
        }
    }, [email, password]);

    const signUp = () => {
        navigate("/register");
    };

    return (
        <div className="auth-container">
            <div className="form-container">
                <div className="form-row">
                    <label htmlFor="email" className="form-label">Email:</label>
                    <input
                        id="email"
                        type="email"
                        value={email}
                        className="form-input"
                        onChange={e => setEmail(e.target.value)}
                    />
                </div>
                <div className="form-row">
                    <label htmlFor="password" className="form-label">Password:</label>
                    <input
                        id="password"
                        type="password"
                        value={password}
                        className="form-input"
                        onChange={e => setPassword(e.target.value)}
                    />
                </div>
                <button className="auth-button" onClick={login}>Log In</button>
                <button className="auth-button" onClick={signUp}>Create Account</button>
            </div>
        </div>
    );
};

export { Login };
