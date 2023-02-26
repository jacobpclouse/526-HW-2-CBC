from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
import os

# Set the key
key = os.urandom(32) # 32 bytes = 256 bits for AES-256

# Create a Cipher object using AES in ECB mode
cipher = Cipher(algorithms.AES(key), modes.ECB())

# Define the plaintext to encrypt
plaintext = b"Hello, world!"

# Encrypt the plaintext in ECB mode
encryptor = cipher.encryptor()
ciphertext = encryptor.update(plaintext) + encryptor.finalize()

# Print the ciphertext
print("Ciphertext:", ciphertext.hex())
