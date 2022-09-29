# README #

SPMediaRemoteControllerServer is a Java program that allows to receive and perform **user defined commands** on any operating system.    
Commands are received via a **TCP Socket** so, it can be also used for tasks where reliability matters.   

By defualt it runs on the **2905** port.   

A GUI has been provided for starting/stopping the server.   


### Add New Commands ###

It's only required to implement your own **Performer** (src/os/Performer) and configure the **SPCommandReceiver** (src/net/SPCommandReveiver) with it.


### Define A Client ###

Client can be anything: you can built your own or use tool (e.g. netcat) to interact with server.   
    
[SPMediaRemoteControllerSwift](https://github.com/t0re199/SPMDRMTCTRL_SWIFT) is an iOS application client.   