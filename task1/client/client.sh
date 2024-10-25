#!/bin/bash
javac *.java && java -Djava.security.policy=policy.txt FileClient myTextFile1.txt localhost
