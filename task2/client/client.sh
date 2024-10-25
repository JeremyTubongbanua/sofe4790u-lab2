#!/bin/bash
javac *.java && java -Djava.security.policy=policy.txt FileClient $1 $2 $3
