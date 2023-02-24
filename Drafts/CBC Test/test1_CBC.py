from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.primitives import padding
import os

# Set the key and initialization vector (IV)
key = os.urandom(32) # 32 bytes = 256 bits for AES-256
iv = os.urandom(16) # 16 bytes = 128 bits for AES

# Create a Cipher object using AES in CBC mode
cipher = Cipher(algorithms.AES(key), modes.CBC(iv))

# Define a padding object (PKCS#7 padding)
padder = padding.PKCS7(128).padder()

# Define the plaintext to encrypt
plaintext = b"Hello, world!"

# Pad the plaintext
padded_plaintext = padder.update(plaintext) + padder.finalize()

# Get the block size of the cipher
block_size = cipher.algorithm.block_size // 8

# Encrypt the padded plaintext in CBC mode
encryptor = cipher.encryptor()
ciphertext = iv
for i in range(0, len(padded_plaintext), block_size):
    block = padded_plaintext[i:i+block_size]
    ciphertext += encryptor.update(bytes([a^b for a,b in zip(block, ciphertext[-block_size:])]))
ciphertext += encryptor.finalize()

# Print the ciphertext
print("Ciphertext:", ciphertext.hex())
