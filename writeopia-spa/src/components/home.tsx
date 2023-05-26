import { useEffect, useState } from "react";
import { LinkContainer } from "react-router-bootstrap";
import { Card } from "react-bootstrap";
import axios from "../api/axios";
import Blog from "./blog";
import { Link } from "react-router-dom";
import "../css/animbackground.css";

const PUBLIC_BLOGS_API = "/blogs";

type Props = {};

/**
 * Home component
 */
export default function Home({}: Props) {
  const [blogsdata, setBlogsdata] = useState<any>([]);

  useEffect(() => {
    const getBlogs = async () => {
      const response = await axios.get(PUBLIC_BLOGS_API);
      setBlogsdata(response.data);
      console.log(response.data);
    };
    getBlogs();
  }, []);

  return (
    <div>
      <div className="bgAni"></div>
      <div className="bgAni bg2"></div>
      <div className="bgAni bg3"></div>
      <div className="p-5 mb-4 bg-light rounded-3">
        <div className="container-fluid">
          <h1 className="display-5 fw-bold mt-5 text-center">
            Welcome To Writeopia
          </h1>
          <p className="fs-4 mt-5 text-center">
            Discover, Create, and Inspire with our Blogging Platform - Unleash
            Your Words!
          </p>
        </div>
      </div>
      <div className="container d-flex flex-wrap justify-content-center">
        {blogsdata?.map((blog: any) => (
          <div>
            <Link to={`/blog/${blog?.id}`} style={{ textDecoration: "none" }}>
              <Card
                onClick={() => {
                  <Blog />;
                }}
                bg="light"
                key={blog?.id}
                text="dark"
                style={{
                  width: "25vw",
                  height: "40vh",
                  margin: "0.5rem",
                  padding: "2px",
                  boxShadow: "0px 0px 15px 0px rgba(2,67,233,0.08)",
                }}
                className="mb-2 border-0"
              >
                <Card.Img
                  variant="top"
                  src="https://source.unsplash.com/random/300x100/?blog,write,pen"
                  style={{ width: "25vw", height: "20vh" }}
                />
                <Card.Body style={{ overflowY: "hidden" }}>
                  <Card.Title>{blog?.title}</Card.Title>
                  <Card.Text>
                    {blog?.body}
                    <br></br>
                    {blog?.publishedDate}
                  </Card.Text>
                </Card.Body>
              </Card>
            </Link>
          </div>
        ))}
      </div>
    </div>
  );
}
