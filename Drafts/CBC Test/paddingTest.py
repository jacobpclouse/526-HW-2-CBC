from Crypto.Cipher import AES # AES Encryption Functions
from Crypto.Random import get_random_bytes # AES Encryption Functions
from Crypto.Util.Padding import pad, unpad


plaintext = (input("Feed me input: ")).encode('utf-8')

 # Pad Plaintext
padded_plaintext = pad(plaintext, AES.block_size)

print(f"Padded Input: {padded_plaintext}")

decoded_plaintext = padded_plaintext.decode('utf-8')
print(f"decoded: '{decoded_plaintext}', length: {len(decoded_plaintext)}")


# ---

# Step 2: Generate a random IV
iv = get_random_bytes(AES.block_size)
print(iv)
print(f"IV length: {len(iv)}")