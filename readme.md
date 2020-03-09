This is an implementation of assessment task which is a web-service listening 8080 port and expecting HTTP GET request with provided ip address within 'X-Forwarded-For' header. Upon receiving valid request it makes two subsequent calls to 3rd party web-services:
* to https://api.ipfind.com/ to resolve provided ip address to geo coordinates (latitude and longitude)
* to http://api.weatherapi.com/ to inquire weather info for that particular region

Since the application is designed to be resilient and responsive due to connection problems with other web-services the artificial failure rate introduced. That rate is configured in the application.conf
`mintos.failure-rate.ip2geo = 0.5`and `mintos.failure-rate.weather = 0.5`. Which means that with the probability of 50% according web-services would fail in each invocation.

Some persisting business logic implemented to save the inquiry data into H2 database (file ./h2db/testdb).
This application deliberately implemented without using any known frameworks. 
Caching and DB schema versioning also could be used, but not implemented at this point.