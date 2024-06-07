import logo from './logo.svg';
import './App.css';
import {useCallback, useState} from "react";

function App() {

  const [login, setLogin] = useState("")
  const [password, setPassword] = useState("")
  const [sessionToken, setSessionToken] = useState(null)

    const buttonClicked = useCallback( async () => {
            const sessionToken = await fetch("http://localhost:8080/login", {
                method: "POST",
                body: {
                    login: login,
                    password: password,
                }
            })
            setSessionToken(sessionToken)
        }
        , [])

  return (
    <div className="App">
        { sessionToken != null && <p>SIEMA</p> }
        { sessionToken == null &&
        <div className={"column"}>
            <input
                value={login}
                style={{padding: 16, margin: 8}}
                onChange={ e =>
                    setLogin(e.target.value)
                }
            />
            <input
                value={password}
                type={"password"}
                style={{padding: 16, margin: 8}}
                onChange={ e =>
                    setPassword(e.target.value)
                }
            />
            <button
                style={{padding: 16}}
                onClick={buttonClicked}
            >LOG IN</button>
        </div> }
    </div>
  );
}

export default App;
