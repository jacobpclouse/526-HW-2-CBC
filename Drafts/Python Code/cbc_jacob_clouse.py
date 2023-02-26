# Jacob Clouse, 2023 - This Python Program was written on Windows 10 and Linux Mint using VScode, your milage may vary based on your OS and configuration.

'''
This program will impliment:
 ______    _______   ______      
/_____/\ /_______/\ /_____/\     
\:::__\/ \::: _  \ \\:::__\/     
 \:\ \  __\::(_)  \/_\:\ \  __   
  \:\ \/_/\\::  _  \ \\:\ \/_/\  
   \:\_\ \ \\::(_)  \ \\:\_\ \ \ 
    \_____\/ \_______\/ \_____\/ 
                                 
(Cipher Block Chaining)
'''

# GOALS for Development:
# 1st: figure out how to encrypt with AES (just one step) - ie: how to XOR and what you get as output
# 2nd: figure out how to take that output, store it, then use it again in the same encryption
# 3rd: make sure that you can still decrypt this
# 4th: figure out how to run it N times and still decrypt it
# 5th: 
# 6th: 

# =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
# Importing Libraries / Modules 
# =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

import datetime
from operator import xor # used to get the datetime for "defang_datetime" function
import random # IV generation use

from Crypto.Cipher import AES # AES Encryption Functions
from Crypto.Random import get_random_bytes # AES Encryption Functions
from Crypto.Util.Padding import pad, unpad


# =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
# Variables
# =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

# =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
# Functions
# =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

# --- Function to print out my Logo ---
def myLogo():
    print("Created and Tested by: ")
    print("   __                  _         ___ _                       ")
    print("   \ \  __ _  ___ ___ | |__     / __\ | ___  _   _ ___  ___  ")
    print("    \ \/ _` |/ __/ _ \| '_ \   / /  | |/ _ \| | | / __|/ _ \ ")
    print(" /\_/ / (_| | (_| (_) | |_) | / /___| | (_) | |_| \__ \  __/ ")
    print(" \___/ \__,_|\___\___/|_.__/  \____/|_|\___/ \__,_|___/\___| ")
    print("Dedicated to Peter Zlomek and Harley Alderson III")

# --- Function to Defang date time ---
def defang_datetime():
    current_datetime = f"_{datetime.datetime.now()}"

    current_datetime = current_datetime.replace(":","_")
    current_datetime = current_datetime.replace(".","-")
    current_datetime = current_datetime.replace(" ","_")
    
    return current_datetime

# --- Function to turn plaintext/ciphertext String into binary  ---    # below is a snipit from GeeksForGeeks:
def convert_string_to_binary(input_string):
    print("Converting String to Binary: \n")
    # Encode the string into bytes using utf-8 encoding
    bytes = input_string.encode('utf-8')
    # Convert each byte to its binary representation
    binary = [bin(byte)[2:].zfill(8) for byte in bytes]
    # Join the binary representations of each byte into a single string
    binary_string = ''.join(binary)
    return(binary_string)

# --- Function to turn binary into plaintext/ciphertext string ---    # below is a snipit from GeeksForGeeks:
def return_binary_to_string(input_binary_data):
    print("Returning Binary to String: \n")
    # Split the binary string into a list of 8-bit binary representations
    binary_list = [input_binary_data[i:i+8] for i in range(0, len(input_binary_data), 8)]
    # Convert each binary representation to its decimal equivalent
    decimal_list = [int(binary, 2) for binary in binary_list]
    # Convert the sequence of decimal values back to the original string
    byte_string = bytes(decimal_list)
    original_string = byte_string.decode('utf-8')
    return original_string


# --- Function for AES Encryption - from: https://onboardbase.com/blog/aes-encryption-decryption/
def encrypt_AES(data):
    key = get_random_bytes(16)
    cipher = AES.new(key, AES.MODE_EAX)
    # cipher = AES.new(key, AES.MODE_ECB)
    ciphertext, tag = cipher.encrypt_and_digest(data)
    nonce = cipher.nonce
    return ciphertext, key, tag, nonce

#  --- Function for AES Decryption - from: https://onboardbase.com/blog/aes-encryption-decryption/
def decrypt_AES(ciphertext,key,tag,nonce):
    cipher = AES.new(key, AES.MODE_EAX, nonce)
    # cipher = AES.new(key, AES.MODE_ECB, nonce)
    data = cipher.decrypt_and_verify(ciphertext, tag)
    return data

'''
# --- Function to Generate IV  ---
def generate_IV(plaintext):
    # will take in input from user outside of function and then pass it in
    print("Generating IV\n")
    # using the length of plaintext to generate random one digit numbers from 0 to 9, need to store and output
    IV_key= ''
    for letters in plaintext:
        current_key_value = str(random.randint(0,9))
        IV_key += current_key_value
        #print(f"Letter: {letters}, Key value: {current_key_value}")
    # Convert IV to binary numbers
    converted_IV = convert_string_to_binary(IV_key)
    return IV_key, converted_IV
'''

# --- Function to Encrypt CBC --- are we going to divide up the blocks beforehand or inside of this? number of rounds determined by the plaintext
def encrypt_cbc(datetime,plaintext):
    # Initialize Encryption Function
    print(f"Starting CBC Encryption at: {datetime}")

    # Pad Plaintext bytes object
    padded_plaintext = pad(plaintext.encode('utf-8'), AES.block_size)
    arrayOfPlaintext = []


    numberOfLinks = int(len(padded_plaintext.decode('utf-8'))) # should be a multiple of 8
    intial_val = 0 
    end_val = 16

    # break into array of 16 letter chunks
    for i in range(numberOfLinks):
        print(i)
    


    # get length of padded plaintext and then make a random IV of bytes
    # my_iv = get_random_bytes(AES.block_size)

    # SPLIT UP BLOCK OF DATA INTO BLOCKS OF 16 HERE, FEED EACH ONE THROUGH WITH IV ()

   # setting this up so we can keep IV and still update value 
    cipher_XOR_Value = my_iv

    lengthOfPlaintext = len(padded_plaintext)  # need to have converted this to the binary rep beforehand
    lengthOfXOR = len(cipher_XOR_Value)
    After_XOR = ''

    if(lengthOfPlaintext == lengthOfXOR):
        print(f"Blocks are both {lengthOfXOR}, can procceed")

        # XOR function
        for index, character in enumerate(padded_plaintext):
            corresponding_value = cipher_XOR_Value[index]
            # print(f"{character} at index {index}, XOR Value: {corresponding_value}")
            # print(f"Type character: {type(character)}, type XOR {type(corresponding_value)}, Int Char {type(int(character))}")
            After_XOR += str(int(character) ^ int(corresponding_value))

        After_XOR_to_bytes = After_XOR.encode('utf-8')
        print(f"After XOR: {After_XOR}, bytes: {After_XOR_to_bytes}")

        

        

        # AES encryption - not good!
        my_ciphertext, my_key, my_tag, my_nonce = encrypt_AES(After_XOR.encode('utf-8')) # need to encode before AES
        print(my_ciphertext)



        cipher_XOR_Value = After_XOR # Setting new IV to the value of the


    
    else:
        print("Error: Length of Plaintext Block and Length of XOR block do not match.")


    # Creating Array to Store Ciphertext
    #encryption_ciphertext = []


# 1 - break it down - just do each part of cbc seperatly, xoring, etc
# 2 - try it with one block, check output, write decryption for one block, check output
# 3 - try adding more blocks, one by one, see if it works, etc
# BE WARY OF DIVIDING UP THE MESSAGE - take note of when you do it and how you do it



# =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
# MAIN PROGRAM
# =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-


# Grabbing DateTime
grab_time = defang_datetime()


# setting plaintext
our_plaintext = "J"

encrypt_cbc(grab_time,our_plaintext)


# need to have the b before the string 
#testVar = b'break it down boi'
#break_it_down(testVar)

# converting plaintext to binary
#binary_plaintext = convert_string_to_binary(our_plaintext)
# generating IV of same length as our plaintext
# string_IV_key, binary_IV_Key = generate_IV(our_plaintext)

#print(f"binary_plaintext: {binary_plaintext}, Binary IV: {binary_IV_Key}")
#encrypt_cbc(grab_time, binary_plaintext, binary_IV_Key)



# input_String = 'l2'
# converted = convert_string_to_binary(input_String)
# print(converted)
# reverted = return_binary_to_string(converted)
# print(reverted)