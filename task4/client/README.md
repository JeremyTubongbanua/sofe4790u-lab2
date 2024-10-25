# client

## Commands

Download myTextFile1.txt from the server

```bash
javac *.java && java -Djava.security.policy=policy.txt FileClient  localhost 1099 download myTextFile1.txt
```

Download myBinaryFile from the server

```bash
javac *.java && java -Djava.security.policy=policy.txt FileClient  localhost 1099 download myBinaryFile
```

Upload myTextFile2.txt to the server

```bash
javac *.java && java -Djava.security.policy=policy.txt FileClient  localhost 1099 upload myTextFile1.txt
```
