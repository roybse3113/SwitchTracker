import axios from "axios";

const SWITCHES_REST_API_URL = "http://localhost:8080/api/switches";

class SwitchService {
  getSwitches() {
    axios.get(SWITCHES_REST_API_URL);
  }
}

export default new SwitchService();
