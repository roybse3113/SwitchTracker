# SwitchTracker
Tracks the stocks of mechanical switches from various vendors. With limited supplies with mechanical switches for mechanical keyboard enthusiasts, it may be useful to have a way to keep track of which switches are in stock and which are not. This is a full-stack application. The frontend is implemented with react javascript while the RESTful backend is implemented using java in Spring Boot.

**Front-end (React)**
The React front-end was designed to allow for ease of access to the switches that in stock or not in stock.

![image](https://user-images.githubusercontent.com/79131282/133378008-c4297f98-aff7-4276-b653-a4cf4f441b34.png)

The UI allowed for **filtering** switches by whether or not they were in stock.

![image](https://user-images.githubusercontent.com/79131282/133378142-b74e7882-2b85-4bd4-8e70-11a205253b77.png)

The search option allows the user to input some mechanical keyboard switch such that the list of switches filter accordingly.

![image](https://user-images.githubusercontent.com/79131282/133378304-fdef36c7-9660-4a0d-92f7-7d11a63a1d73.png)

**Back-end (Java)**
The RESTful backend implemented in java through Spring Boot served to access the fields needed for each switch. This includes the price, the name, and whether it is in stock or not. This information was accessed through JSoup and Java regex implemented in Java to web scrape prominent mechanical switch vendors such as Novelkeys, Ringerkeys, and KBDFans. This data is then accessed in the front end by using the useEffect() react hook and axios within React.

![image](https://user-images.githubusercontent.com/79131282/133377633-b080870a-89dc-4ba0-a469-c3d2f600e6ab.png)

