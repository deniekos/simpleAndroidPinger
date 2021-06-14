# Android Simple Pinger

This is a simple android apps to ping a certain ip address.

## Deep Dive
Take a close look at `getLatencyIp(String Ip)` method. It is the backbone method of this apps. It will return `latency`, an integer value of latency of IP which is passed to this method. If you want a more accurate data, take a look at `strAvgRtt`, it is a double value which give you a bit more accurate data than a mere integer.

## Work to do
Currently i have no idea why `new URL()` is not work in my case, and that's the reason why my `getLatencyUrl()` method not work. To make pinging easier for everyone, i try to convert url into ip. Will update this repo once i able to convert url into ip. 
