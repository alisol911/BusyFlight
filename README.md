### About
Simple microservice using Spring Boot 2

### Build
```
$ mvn clean install
```

### Run

#### Start
```
$ ./busyflight.sh start
```

#### Stop
```
$ ./busyflight.sh stop
```

### Test

#### BusyFlight
```
$ curl 'localhost:8080/flight?origin=AED&destination=CEA&departureDate=2019-07-04-10:0&returnDate=2019-07-05-11:00'
```
```json
[{"airline":"Saha","supplier":"CrazyAir","fare":90.0,"departureAirportCode":"AED","destinationAirportCode":"CEA","departureDate":"2019-07-04T05:30:00.000+0000","arrivalDate":"2019-07-05T06:30:00.000+0000"},{"airline":"Iran Air","supplier":"ToughJet","fare":96.0,"departureAirportCode":"AED","destinationAirportCode":"CEA","departureDate":"2019-07-04T05:30:00.000+0000","arrivalDate":"2019-07-05T06:30:00.000+0000"},{"airline":"Iran Air","supplier":"CrazyAir","fare":100.0,"departureAirportCode":"AED","destinationAirportCode":"CEA","departureDate":"2019-07-04T05:30:00.000+0000","arrivalDate":"2019-07-05T06:30:00.000+0000"},{"airline":"Mahan","supplier":"ToughJet","fare":110.0,"departureAirportCode":"AED","destinationAirportCode":"CEA","departureDate":"2019-07-04T05:30:00.000+0000","arrivalDate":"2019-07-05T06:30:00.000+0000"}]
```

#### CrazyAir
```
$ curl 'localhost:8085/flight?origin=DDC'
```
```json
[{"airline":"Saha","price":130.0,"cabinclass":"","departureAirportCode":"DDC","destinationAirportCode":"CEA","departureDate":"2019-07-04T06:30:00.000+0000","arrivalDate":"2019-07-05T06:30:00.000+0000"}]
```
#### ToughJet

```
$ curl 'localhost:8095/flight?from=AED&to=CEA&outboundDate=2019-07-04-10:00&inboundDate=2019-07-05-11:00'
```
```json
[{"carrier":"Iran Air","basePrice":100.0,"tax":5.0,"discount":9.0,"departureAirportName":"AED","arrivalAirportName":"CEA","outboundDateTime":"2019-07-04T05:30:00.000+0000","inboundDateTime":"2019-07-05T06:30:00.000+0000"},{"carrier":"Mahan","basePrice":120.0,"tax":0.0,"discount":10.0,"departureAirportName":"AED","arrivalAirportName":"CEA","outboundDateTime":"2019-07-04T05:30:00.000+0000","inboundDateTime":"2019-07-05T06:30:00.000+0000"}]
```
