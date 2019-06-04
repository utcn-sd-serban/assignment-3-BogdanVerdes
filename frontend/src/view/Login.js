import React from "react"

const Login = ({username,password,onChange,onLogin,onRegister}) => (
    <div>
        <h2>Login</h2>
        <label>Username:</label>
        <input value={username}
        onChange={ e=> onChange("username",e.target.value)}></input>
        <br />
        <label>Password:</label>
        <input value={password}
        onChange={ e=> onChange("password",e.target.value)}></input>
        <br />
        <button onClick={onLogin}>Login</button>
        <button onClick={onRegister}>Register</button>
    </div>
);

export default Login;