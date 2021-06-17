import React from "react";
import SwitchService from "../services/SwitchService";
import axios from "axios";

class SwitchComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      switches: [],
    };
  }

  componentDidMount() {
    axios.get("http://localhost:8080/api/switches").then((results) => {
      this.setState({ switches: results.data });
    });
  }

  render() {
    const styles = {
      backgroundColor: "grey",
      padding: "15px",
      outline: "2px solid black",
    };
    return (
      <div>
        <h1 style={styles} className="text-center">
          Switches List
        </h1>
        <table className="table table-striped">
          <thead>
            <tr>
              <td>Switch Name</td>
              <td>Switch Status</td>
            </tr>
          </thead>
          <tbody>
            {this.state.switches.map((s) => (
              <tr key={s.id}>
                <td>{s.name}</td>
                <td>{s.inStock ? "In stock" : "Not in stock"}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}

export default SwitchComponent;
