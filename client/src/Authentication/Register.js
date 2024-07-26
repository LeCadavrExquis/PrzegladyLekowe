import { useCallback, useState } from "react";
import { useNavigate } from "react-router-dom";
import './Authentication.css';

const Register = () => {
    
    const navigate = useNavigate();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");

    const signUp = useCallback(async () => {
        const response = await fetch("/signup", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                email,
                password,
                firstName,
                lastName,
            })
        });
        if (response.ok) {
            navigate("/app");
        } else {
            console.error(response);
        }
    }, [email, password, firstName, lastName]);

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
                <div className="form-row">
                    <label htmlFor="firstName" className="form-label">First Name:</label>
                    <input
                        id="firstName"
                        type="text"
                        value={firstName}
                        className="form-input"
                        onChange={e => setFirstName(e.target.value)}
                    />
                </div>
                <div className="form-row">
                    <label htmlFor="lastName" className="form-label">Last Name:</label>
                    <input
                        id="lastName"
                        type="text"
                        value={lastName}
                        className="form-input"
                        onChange={e => setLastName(e.target.value)}
                    />
                </div>
                <button className="auth-button" onClick={signUp}>Create Account</button>
            </div>
        </div>
    );
};

export { Register };
