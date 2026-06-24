# RSA-implementation
Implementation of RSA cypher with encryption, exchange, and decryption

Overview: There are three programs, AliceRSA - for generating keys, Bob - for making a message and using the 
keys to encrypt it, and AliceDecrypt - for decrypting the message from Bob. The message is already written, but the keys will be generated when you run the program.

Steps:
1) Compile all files: 

Commands:
javac AliceRSA.java
javac Bob.java
javac AliceDecrypt.java

2) Run AliceRSA to generate keys. This will generate a 1024 bit public key and a public exponent, labled "Public key n" and "Public exponent e". The key size can be adjusted in the java file, but it is set to 1024 for now. 

Commands: 
java AliceRSA

3) Run Bob to encrypt a message. You will need to paste the public key and public exponent in the command line 
in the specific order: n e with only one space in between. This will generate one large number which is c, 
labeled "Encrypted message c"

Commands:
java Bob (public key n) (public exponent e)

4) Run AliceDecrypt to decrypt the message. This will decrypt the numerical message and return it in English. To do this, paste the following in this order in the command line with only spaces in between them.

Commands
java AliceDecrypt (private key d) (message c) ( public key n)
