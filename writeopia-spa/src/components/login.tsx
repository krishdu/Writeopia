import { useState } from "react";
import axios from "../api/axios";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { Card } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import useAuth from "../hooks/useAuth";

const LOGIN_API = "/authenticate";
type Props = {};

/**
 * Login component
 */
export default function Login({}: Props) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [success, setSuccess] = useState(false);

  const userAuth = useAuth();

  const navigate = useNavigate();

  const handleNavigate = () => {
    navigate("/profile");
  };

  const handleSubmit = async (e: any) => {
    e.preventDefault();
    const response = await axios.post(LOGIN_API, {
      userName: username,
      password: password,
    });
    console.log(response.data.jwt);
    localStorage.setItem("token", response.data.jwt);
    userAuth.setIsAuthenticated(true);
    await new Promise((r) => setTimeout(r, 500));
    setSuccess(true);
  };

  return (
    <div>
      <div className="bgAni"></div>
      <div className="bgAni bg2"></div>
      <div className="bgAni bg3"></div>
      {success ? (
        <>{handleNavigate()}</>
      ) : (
        <div onSubmit={handleSubmit} className="container">
          <h1 className="text-center mx-auto mt-5">Login</h1>
          <Card className="m-5 p-2 w-50 mx-auto">
            <Form className="m-5">
              <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label>Username</Form.Label>
                <Form.Control
                  type="text"
                  id="username"
                  placeholder="Enter a registered username"
                  onChange={(e) => setUsername(e.target.value)}
                  required
                />
              </Form.Group>

              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Password</Form.Label>
                <Form.Control
                  type="password"
                  placeholder="Your Password"
                  id="password"
                  onChange={(e) => setPassword(e.target.value)}
                  required
                />
              </Form.Group>
              <div className="text-center">
                <Button variant="primary" type="submit">
                  Login
                </Button>
                <Form.Text className="text-muted">
                  <br />
                  Don't have a account yet! go to{" "}
                  <Link to="/register">Register</Link>
                </Form.Text>
              </div>
            </Form>
          </Card>
        </div>
      )}
    </div>
  );
}
