## O-URL
A URL shortening program written in Java intended for learning purposes.
I'll assume you're using a Linux machine and have Java and Eclipse EE installed.

## Quick Start
Setup and start Redis database on your local machine.
See here https://github.com/antirez/redis

The best way to use this project is in Eclipse EE.
Use File>Import>Projects from Git.

Once the project is imported, run StoreTest.java to test if your Redis database is setup correctly.
Then run Server.java.

Lastly, open your browser and go to localhost:8080
You should be greeted by a page asking you for a long url to shorten.

## Citation
My URL shortening method is to use the Murmur32 hash.
Credit to nurkiewicz for this method.
http://www.nurkiewicz.com/2014/08/url-shortener-service-in-42-lines-of.html
