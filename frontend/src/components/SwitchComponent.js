import React, { useRef } from "react";
import axios from "axios";
import { FaSearch } from "react-icons/fa";
import "./style.css";
import "bootstrap/dist/css/bootstrap.css";
import img1 from "./images/ringerkeys.jpg";
import img2 from "./images/novelkeys.jpg";
import img3 from "./images/primekb.jpg";
import {
  Nav,
  Navbar,
  NavDropdown,
  Dropdown,
  DropdownButton,
  Carousel,
} from "react-bootstrap";
import logo from "./logo.svg";
import NavbarCollapse from "react-bootstrap/esm/NavbarCollapse";

const SwitchComponent = (props) => {
  const input = useRef("");

  const styles = {
    top: "2px",
    padding: "5px",
    background: "none",
  };

  const styles2 = {
    textAlign: "left",
    textSize: "small",
    width: "250px",
    fontSize: "15px",
    padding: "8px",
    float: "center",
    borderRadius: "40px",
    positio: "absolute",
    fontColor: "white",
    textColor: "white",
    color: "white",
    fontSize: "small",
    outline: "none",
  };

  const styles3 = {
    padding: "1px",
    position: "absolute",
    left: "225px",
    top: "17px",
  };

  const styles4 = {
    fontSize: "large",
    fontWeight: "bold",
    padding: "5px",
    borderRadius: "15px",
  };

  const styles5 = {
    fontWeight: "bold",
  };

  const styles6 = {
    backgroundColor: "rgb(40, 41, 38)",
    background: "rgb(40, 41, 38)",
    color: "white",
    textAlign: "center",
    padding: "10px",
    fontSize: "medium",
  };

  const styles7 = {
    backgroundColor: "rgb(40, 41, 38)",
    background: "rgb(40, 41, 38)",
    color: "white",
    textAlign: "center",
    padding: "8px",
    fontSize: "small",
  };

  const styles8 = {
    textDecoration: "none",
    color: "white",
    // display: "inline-block",
    // position: "relative",
    zIndex: "1",
    padding: "1em",
    margin: "-1em",
    background: "none",
  };

  const imageStyle = {
    width: "750px",
    height: "500px",
    float: "center",
    margin: "auto",
    opacity: 0.5,
  };

  const getSearchTerm = () => {
    props.searchKeyword(input.current.value);
  };

  return (
    <div style={styles6}>
      <div className="search">
        <Navbar bg="dark" variant="dark" fixed="top" expand="false">
          <Navbar.Brand>
            <img src={logo} width="40px" height="40px" />
            SwitchTracker
          </Navbar.Brand>

          <div className="searchBox">
            <input
              ref={input}
              type="text"
              placeholder={
                input.length < 1 ? "Search switches..." : "Search switches..."
              }
              className="prompt"
              value={props.term}
              onChange={getSearchTerm}
              autofocus
            />
            <a className="searchButton" href="#">
              <FaSearch />
            </a>
          </div>

          <Navbar.Collapse>
            <Nav>
              <NavDropdown title="Vendors">
                <NavDropdown.Item href="Vendors/RingerKeys">
                  RingerKeys
                </NavDropdown.Item>
                <NavDropdown.Item href="Vendors/KBDFans">
                  KBDFans
                </NavDropdown.Item>
              </NavDropdown>
              <Nav.Link href="Products">About</Nav.Link>
              <Nav.Link href="Products">Contact</Nav.Link>
            </Nav>
          </Navbar.Collapse>

          <DropdownButton
            className="filter"
            menuAlign="left"
            title="Filter"
            id="dropdown-menu-align-left"
            variant="secondary"
          >
            <Dropdown.Item eventKey="1" onClick={props.filterInStock}>
              In Stock
            </Dropdown.Item>
            <Dropdown.Item eventKey="2" onClick={props.filterOutOfStock}>
              Out of Stock
            </Dropdown.Item>
            <Dropdown.Item eventKey="3" onClick={props.noFilter}>
              No Filter
            </Dropdown.Item>
          </DropdownButton>

          <Navbar.Toggle />
        </Navbar>

        <Carousel fade>
          <Carousel.Item>
            <a href="https://ringerkeys.com/" target="_blank">
              <div className="img">
                <img
                  className="d-block w-26"
                  style={imageStyle}
                  src={img1}
                  alt="RingerKeys"
                />
              </div>
            </a>
            <Carousel.Caption>
              <a>
                <h3>RingerKeys</h3>
              </a>
            </Carousel.Caption>
          </Carousel.Item>
          <Carousel.Item>
            <a href="https://novelkeys.xyz" target="_blank">
              <div className="img">
                <img
                  className="d-block w-26"
                  style={imageStyle}
                  src={img2}
                  alt="NovelKeys"
                />
              </div>
            </a>

            <Carousel.Caption>
              <a>
                <h3>NovelKeys</h3>
              </a>
            </Carousel.Caption>
          </Carousel.Item>
          <Carousel.Item>
            <a href="https://www.primekb.com/" target="_blank">
              <div className="img">
                <img
                  className="d-block w-26"
                  style={imageStyle}
                  src={img3}
                  alt="PrimeKB"
                />
              </div>
            </a>

            <Carousel.Caption>
              <a>
                <h3>PrimeKB</h3>
              </a>
            </Carousel.Caption>
          </Carousel.Item>
        </Carousel>
      </div>
      <table className="table table-striped table-dark">
        <thead>
          <tr className="TableHeader">
            <td style={styles4}>Switch Name</td>
            <td style={styles4}>Switch Status</td>
          </tr>
        </thead>
        <tbody>
          {props.switches.map((s) => (
            <tr className="SwitchRow" key={s.id}>
              <td>
                <a style={styles8} href={s.url} target="_blank">
                  {s.name}
                </a>
              </td>
              <td>{s.inStock ? "In stock" : "Out of stock"}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <footer style={styles7}>&#169; 2021, Roy Bae</footer>
    </div>
  );
};

export default SwitchComponent;
