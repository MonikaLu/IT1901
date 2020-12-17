[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.idi.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2020/gr2020)

# Group 20 repository

I dette prosjektet skal vi lage en Mood Tracker. Prosjektet er konfigurert med Maven som byggeverktøy og er gitpodifisert.

# Starting av applikasjonen

Kjør $ mvn compile for å kompilere prosjektet. Dersom det skulle komme noen errorer kan du kjøre $ mvn clean install for å fikse disse.

Gå til springboot/restserver og kjør kommandoen:

$ mvn spring-boot:run

for å starte restserveren. 

Deretter går du til fxui og kjører

$ mvn javafx:run

Dette kan også kjørest ytters i filstrukturen ved

$ mvn javafx:run -f fxui/pom.xml


# Organisering av kode

*  **core/src/main/java/moodtracker/core** for domenelogikk
*  **core/src/test/java/core** for testkoden for core
*  **core/src/main/java/moodtracker/json** for persistens
*  **core/src/test/java/json** for testkoden for json
*  **core/src/main/resources/json** for resources til json
*  **fxui/src/main/resources/fxui/FxApp.fxml** for koden til fxml
*  **fxui/src/test/java/fxui** for testkoden for fxui
*  **springboot/restserver/src/main/java/moodtracker/restserver** for koden til springboot restserveren
*  **springboot/restserver/src/test/java/moodtracker/restserver** for testkoden til springboot restserveren


# Arkitektur

Her har vi et bilde som viser hele arkitekturen for packages våre.
![Package_arkitekturen](http://www.plantuml.com/plantuml/png/XP1T3eCW48IVkwVW11oXyIeKqMY19Vmq9iQxbqrgr2rD7vdvcDtPBcKLSt4MDBg0tlWiD4OZLWXAFzLeX4FiSwmF4oLPJMDEw663KyfSZIgAzIeFIvcgTck5aCT3NcfOY3ydE4lATaLJY9CV7uZvn-u7G1_vN27b_Rjgc-AzkK3KtX_QA_mVnra-5FPzFaSwtdGWw8plYxDl)

Her har vi et bilde som viser hele arkitekturen for klassene våre.
![Class_arkitektur](http://www.plantuml.com/plantuml/png/bLPVRzem47_dKrXzIYNw2TPLq5QLEgq5bQelqnwkyH6iEZQp7HX3_UxBPS4OpouK2KMv--NtkpyNNqRMWN5DBGVZvV96AsUqb6ZOnSKLkuMDJPXRou1SEynWvi09wqIPDn7JdP0uMQ9fFLe5zWUj-OFcA2CTRv-XMOaIiy2O9NOULsXJagBi0Xl4ibuYwndGhvHWBTsnxO2nxLoWvE_ikov1te93ni7Xby3p0ClBcJD2LKDshnnMQAwOTU1iXwhHBJH_9xvYzCrgDJLwYSPjYZDYEZkFg8SiGdLKBIolSnxehBJW33X_Db2-eId-FT7XGjXUiq97roBqzlJae_xdI98HzS_O0y8RQbL9NUaVsECUeKO2HodOFgp3uYHWlTf7rNaFccgxhWPz5TPTjlOhnidvbIbSix4niF6UBhYzBWkI8JTbGiGnRDU9rbUqV-BF81vnJQ1y2iDeHb8y1ckzmhnOQ-qZlTuaAflFqUEwJAagapdTfXEsskoguSiUdHVRA32gkq7WTqRN1DsDsrn8L51ZKWPM-AnJw657I0K8EnySOv5yMFLRtRpADiyIPDb8S3XUeO4A3rhJpSMkFtx6FiqvQIzJD4BpmWSSz9bugP8EGoCgJjTOBXzTsVMnVqVYmIN9Y8ftqJBRr25wFusbDlW2ii7umMkj9O9YvGBBj_jkGExLFZ_IEZqxtxQ3dHMzkRvg1zP-6eGIxgwVYCJua5k057ymESJDabw99wo4TVtK9d4ArxHAGc0IPnTwFKFdsiZIH0l1CGTu5auYduBgpkfnwSGAwIv9n56YGmegBGHI7gHrG-r2NkpQfoLVPJEW8PfhaptiIgbjgyr1-WsG9ofE7_MO0xbmknJ3KM7Rb22H5tEXGD8fK7LoLPAt1g4yaiRenqy6fhA1Ikf3WsJJN4TPiiFztnTA7HaE95E4SVNzYy7nT-oZZlerbjqRnMWARl42Hi2hHF_vqXFJtSVxRVI4lngqxbhp3SjjH3_39mh6DVK5-infGRk3laFILHbB-K5X9ef23QRvU26EvNuYOTymlpPpUv90_QwaUerGyQQMVm40http://www.plantuml.com/plantuml/png/bLPHR-Cs37xFhn3koGLol-1kHTDs1Jeib-9wk9TX3wp4EDfaAPFaT5dG_pvHZXD5aUkqGE5O_FIH_4ZAkd4Uh6yRDPcjrtT6UskKGii-Vxvczx1rXUMmCY5JZvayU_3IUSc7buZfGIfShD46Yz79-jmOCJS2LURdiFwCTYCvbe4ZB6GSzn1IKfB7m2QvsuABp3AXFwx0ENfZkmbZkwL49TxOxuQ3kWUFJ837Bubb3kklpzvANK_Pe_POexrcpeDt4TMWNndnHdpLpM_Ew2ThWdP-MtqYfazN6VMKPQX85M33VguIFqUo6ZqzgzHQuY3WCz6S83T62kPwosM-zvQUiEfsHDpLv2rHraLf4t6byr_3-ZKJPFo5QNnbCsjX6otNCRAZ7tP7bUTKvBYB719h3eivW131LeL_8YyXB3PcWyCWMkqyt6wZegS56uxjW8iHKYgB9RsM0tN6zbaSjCecRNSMc6CMGJnOqn2qLtmf5MfeiEW6DlZ3bD3JZf1Aa8IG702f-wpnxqtxeaAU71Hl5NYSRT12ZMddT6NkswVlduzpBiBZ2QqqeZe4F4scA1w4z2jTH2teMN9NZevtTUpwFsyuSfbHYHWjSssJee_3met5dw1Qp3VU6gCGDEChv7y_nWPvrCVyoDUbsILbEpdjit6RSOxEKMXs2JnGeWsI1hZkelvIQ1kffPUWv7zORDvsJQFm7UiWuhvRIpWQR3fyKs0H9r2Xnr4OA7NYyuuqH57rcXebDiKXM9dNMDa7OxEJem2gBbI_m852-b8M1V5-3L0SLuQ3vgW8N3HxKzSAvo3rt0XGxs1YQgCWz5xg-aoW7z8h54-WutTcnhtS8BrLWsSddL4HaMnTWyPXtvHZY2_dB7y8kKNm64pavr6lspDvphwC1MGUT7xK5yxV3kWMBtyX9wQJ4MIxGdODI5qjfGR5kZJ--9E1hLsn1wdV3c2eaLRgTD1BLvzXjvd3SvR1G-FipjHa2wSk2uIvpFlFZzG2_otRUd7VAT3D4_ZLJx0INXJsLwf-ExsLYK68R_WQ2RxZFoqwVsl4zhXlMzxMJUf5VgViGym25Lx8d9SjgK5MiyFdyMGr-Un_WmQ7X9yfzOxs2utvV8nhVHIX-pKcnGXxLfBy7ZtYZOQYRx331MhenaIW_Qs9vBz1BTf6_Gy0)

![Sequence_diagram](http://www.plantuml.com/plantuml/png/tPDDxjem4CNtESKismKNo091QRigQWKdiF2hSMDiD9w0k4dlqujrKgiAg149R2h-AoFhVOn_J1PP34jx2DK7RXim8itcL7uxHip8qOj0Qle2vqXy8DKmdC_2O1zTLGt8P_FbyRXAKJY5qCNM575UfsGtnMh4f_ZkVLNTw4hz1bao-0IkoLZxqLnKs5_IJ3N_nAiGbgovZ4HIJGwYcNaqLALMTOE1WuZQepM2wnTs9UDeDx3UWS6JsDBWf1eXOso7-zKioRc0JngnJXPg7Uxzwo-DuaCm0YOku_VptkqAS2hkt1Gl86LlFNZg5XN4tQHuT9MATSes5PelkqwVVu0Z-DaFrI90y23SOh9_u8vmAEBNnVlhPynwIrcCgDKt3PaJnOIevmrnH4o5dlVflDN0_3rnUUu8cUbWxd3Pza-eQMV2hWr6iDGWu_1LZszWdwnMN2c7sJDo6-H_q2_SZVOz2r-zDuEizjWri6_zF-YNJiCNYBOzXDy0)

# Kjøring av prosjektet
Prosjektet bruker maven til bygging og kjøring, og må bygges via <strong>mvn install</strong> fra rotmappen (moodtracker). <br>
Deretter kan prosjektet kjøres via <strong>mvn javafx:run -f fxui/pom.xml</strong>. Det er også mulig å gå inn i <strong>fxui</strong> med <strong>cd fxui</strong> og deretter <strong>mvn javafx:run</strong>

I utgangspunktet skal prosjektet kjøres uten probelmer. Om det oppstår situasjoner der det dukker opp feilmeldinger i terminalen, kan det være en fordel å restarte workspacen i gitpod og kjøre på nytt.
Vi har opplevd tidligere at vi fikk mange feilmeldinger i terminalen, men de forsvant etter workspacen restartet.