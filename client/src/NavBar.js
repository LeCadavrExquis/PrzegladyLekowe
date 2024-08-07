import React from 'react';
import './NavBar.css';

import { useNavigate } from 'react-router-dom';

const NavBar = ({ role }) => {

    const navigate = useNavigate();

    const goToStartingPage = () => { navigate('start') }
    const goToLogIn = () => { navigate('login') }
    const goToRegister = () => { navigate('register') }
    const goToCreateAssignment = () => { navigate('assignment') }
    const goToDashboard = () => { navigate('app') }

    const goToProfile = () => { navigate('profile') }
    const logOut = () => { /* TODO IMPLEMENT LOG OUT */ }


    return (
        <div className="navbar">

            {(role === "DOCTOR") && <>
                <button className='nav-button' onClick={goToCreateAssignment}>👩‍⚕️ Create Assignment</button>
            </>}

            {(role === "USER" || role === "DOCTOR") && <>
                <button className='nav-button' onClick={goToDashboard}>📝 Assignments</button>
                <button className="nav-button" onClick={goToProfile} >👤 Profile</button>
                <button className="nav-button" onClick={logOut} >😑 Log Out</button>
            </>}

            {(role === "DEFAULT") && <>
                <button className="nav-button" onClick={goToStartingPage}>🏠 Start</button>
                <button className="nav-button" onClick={goToLogIn}>🔑 Log In</button>
                <button className="nav-button" onClick={goToRegister}>🧾 Create Account</button>

            </>}
        </div>
    );
};

export { NavBar }