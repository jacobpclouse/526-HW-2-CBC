from Crypto.Cipher import AES
from Crypto.Random import get_random_bytes

toEncrypt = b'secret data'

# from: https://onboardbase.com/blog/aes-encryption-decryption/

# --- Function for AES Encryption - from: https://onboardbase.com/blog/aes-encryption-decryption/
def encrypt_AES(data):
    key = get_random_bytes(16)
    cipher = AES.new(key, AES.MODE_EAX)
    ciphertext, tag = cipher.encrypt_and_digest(data)
    nonce = cipher.nonce
    return ciphertext, key, tag, nonce

#  --- Function for AES Decryption - from: https://onboardbase.com/blog/aes-encryption-decryption/
def decrypt_AES(ciphertext,key,tag,nonce):
    cipher = AES.new(key, AES.MODE_EAX, nonce)
    data = cipher.decrypt_and_verify(ciphertext, tag)
    return data

# -----
my_ciphertext, my_key, my_tag, my_nonce = encrypt_AES(toEncrypt)
print(my_ciphertext, my_key, my_tag, my_nonce)

returned_data = decrypt_AES(my_ciphertext, my_key, my_tag, my_nonce)
print(returned_data)
