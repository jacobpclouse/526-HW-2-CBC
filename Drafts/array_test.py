import datetime
from operator import xor # used to get the datetime for "defang_datetime" function
import random # IV generation use

from Crypto.Cipher import AES # AES Encryption Functions
from Crypto.Random import get_random_bytes # AES Encryption Functions
from Crypto.Util.Padding import pad, unpad
    

# setting plaintext
plaintext = "j"


# Initialize Encryption Function
print(f"Starting CBC Encryption at: {datetime}")

# Pad Plaintext bytes object
padded_plaintext = pad(plaintext.encode('utf-8'), AES.block_size)
arrayOfPlaintext = []
print(f"Padded plaintext: '{padded_plaintext.decode('utf-8')}'")

numberOfLinks = int(len(padded_plaintext.decode('utf-8'))) # should be a multiple of 8
intial_val = 0 
end_val = 15

# break into array of 16 letter chunks
for i in range(numberOfLinks):
    print(i)
    print(plaintext[intial_val:end_val])
    intial_val = end_val
    end_val += 16