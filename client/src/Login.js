import {useCallback, useState} from "react";

const Login = () => {
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")

    const login = useCallback( async () => {
            const response = await fetch("/signin", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    email: email,
                    password: password,
                })
            })
            if (!response.redirected && response.ok) {
                window.location.href = "/app"
            } else {
                console.error(response)
            }
        }, [email, password]
    )
    return <>
        <div className="App">
            <div className={"main-column"}>
                <div className={"row"}>
                    <p style={{paddingRight: '16px'}}>email: </p>
                    <input
                        value={email}
                        style={{padding: 16, margin: 8}}
                        onChange={e =>
                            setEmail(e.target.value)
                        }
                    />
                </div>
                <div className={"row"}>
                    <p style={{paddingRight: '16px'}}>hasło: </p>
                    <input
                        value={password}
                        type={"password"}
                        style={{padding: 16, margin: 8}}
                        onChange={e =>
                            setPassword(e.target.value)
                        }
                    />
                </div>
                <button
                    style={{
                        width: 200,
                        padding: 16,
                        margin: '24px'
                    }}
                    onClick={login}
                >Zaloguj
                </button>
                {/* TODO: Create account */}
                <button
                    disabled={true}
                    style={{
                        width: 200,
                        padding: 16,
                    }}
                >Utwórz konto
                </button>
            </div>
        </div>
    </>
}

export {Login}