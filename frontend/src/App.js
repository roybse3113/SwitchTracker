import "./App.css";
import SwitchComponent from "./components/SwitchComponent";
import { useState, useEffect } from "react";
import axios from "axios";

function App() {
  const [switches, setswitches] = useState([]);

  const [searchTerm, setSearchTerm] = useState("");

  const [searchResults, setSearchResults] = useState([]);

  const [filteredResults, setFilteredResults] = useState([]);

  const [filterSelected, setFilterSelected] = useState(false);

  useEffect(() => {
    axios.get("http://localhost:8080/api/switches").then((results) => {
      setswitches(results.data);
    });
  });

  const searchKeyword = (searchTerm) => {
    setSearchTerm(searchTerm);
    if (filterSelected) {
      if (searchTerm !== "") {
        const filteredSwitches = filteredResults.filter((s) => {
          return Object.values(s)
            .join(" ")
            .toLowerCase()
            .includes(searchTerm.toLowerCase());
        });
        setSearchResults(filteredSwitches);
      } else {
        setSearchResults(filteredResults);
      }
    } else {
      if (searchTerm !== "") {
        const filteredSwitches = switches.filter((s) => {
          return Object.values(s)
            .join(" ")
            .toLowerCase()
            .includes(searchTerm.toLowerCase());
        });
        setSearchResults(filteredSwitches);
      } else {
        setSearchResults(switches);
      }
    }
  };

  const filterInStock = () => {
    setFilterSelected(true);
    console.log(filterSelected);
    const filteredSwitches = switches.filter((s) => {
      return s.inStock;
    });
    setFilteredResults(filteredSwitches);
  };

  const filterOutOfStock = () => {
    setFilterSelected(true);
    console.log(filterSelected);
    const filteredSwitches = switches.filter((s) => {
      return !s.inStock;
    });
    setFilteredResults(filteredSwitches);
  };

  const noFilter = () => {
    setFilterSelected(false);
  };

  const display = () => {
    if (filterSelected) {
      if (searchTerm.length < 1) {
        return filteredResults;
      } else {
        return searchResults;
      }
    } else {
      if (searchTerm.length < 1) {
        return switches;
      } else {
        return searchResults;
      }
    }
  };

  return (
    <div className="App">
      <div>
        <SwitchComponent
          filterState={filterSelected}
          setFilterState={setFilterSelected}
          // switches={searchTerm.length < 1 ? switches : searchResults}
          switches={display()}
          term={searchTerm}
          searchKeyword={searchKeyword}
          filterInStock={filterInStock}
          filterOutOfStock={filterOutOfStock}
          noFilter={noFilter}
        />
      </div>
    </div>
  );
}

export default App;
